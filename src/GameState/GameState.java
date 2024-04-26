package GameState;

import Cards.normalCard;
import Cards.prizeClaw;
import Player.player;
import SlotMachine.slotMachine;

public class GameState {
    private final player[] players = new player[5];
    private final slotMachine[] slotsMachines = new slotMachine[6];
    private final normalCard[] level1cards = new normalCard[20];
    private final normalCard[] level2cards = new normalCard[20];
    private final normalCard[] level3cards = new normalCard[20];
    private final prizeClaw[] prizeClaws = new prizeClaw[5];
    private static int level1no, level2no, level3no, prizeClawNo;
    private int turnSW;

    public void startTheGame(String player0, String player1) {
        players[0] = new player(player0);
        players[1] = new player(player1);
        setSlotMachines();
        setNormalCards();
        setPrizeClaws();
        turnSW = 1;
    }

    private void setSlotMachines() {
        for (int i = 0; i < 5; i++) {
            slotsMachines[i] = new slotMachine();
            slotsMachines[i].giveBackCoin(4);
        }
        slotsMachines[5] = new slotMachine();
        slotsMachines[5].giveBackCoin(5);
    }

    private void setNormalCards() {
        // level 1
        for (int i = 0; i < 15; i++)
            level1cards[i] = new normalCard(1);
        level1no = 15;
        // level 2
        for (int i = 0; i < 15; i++)
            level2cards[i] = new normalCard(2);
        level2no = 15;
        // level 3
        for(int i = 0; i < 15; i++)
            level3cards[i] = new normalCard(3);
        level3no = 15;
    }

    private void setPrizeClaws() {
        for (int i = 0; i < 3; i++)
            prizeClaws[i] = new prizeClaw();
        prizeClawNo = 3;
    }

    public player getPlayers(int i) {
        return (i == 1? players[0] : players[1]);
    }

    public int getTurnSW() {
        return turnSW;
    }

    public void changeTurnSW() {
        player p = players[turnSW - 1];
        for (int i = 0; i < prizeClawNo; i++) {
            prizeClaw card = prizeClaws[i];

            boolean check = true;

            for (int j = 0; j < 5; j++)
                if (p.getWallet().getSpecialCoinNum(j) < card.getCoins(j))
                    check = false;

            if (check) {
                p.addPrizeClaw(card);
                prizeClaws[i] = prizeClaws[prizeClawNo - 1];
                prizeClawNo--;
            }
        }

        if (getPlayers(turnSW).getWallet().getAllCoinsNum() <= 10)
            turnSW = 3 - turnSW;
    }

    public int getCardsNo(int i) {
        if (i == 1) return level1no;
        if (i == 2) return level2no;
        if (i == 3) return level3no;
        return 0;
    }

    public int getPrizeClawNo() {
        return prizeClawNo;
    }

    public normalCard getCard(int level, int i) {
        if (level == 1) return level1cards[i];
        if (level == 2) return level2cards[i];
        return level3cards[i];
    }

    public void deleteCard(int level, int i) {
        if (level == 1) {
            for (int j = i + 1; j < level1no; j++)
                level1cards[j - 1] = level1cards[j];
            level1no--;
        }
        else if (level == 2) {
            for (int j = i + 1; j < level2no; j++)
                level2cards[j - 1] = level2cards[j];
            level2no--;
        }
        else {
            for (int j = i + 1; j < level3no; j++)
                level3cards[j - 1] = level3cards[j];
            level3no--;
        }
    }

    public slotMachine getSlotMachine(int i) {
        return slotsMachines[i];
    }

    public prizeClaw getPrizeClaw(int i) {
        return prizeClaws[i];
    }
}
