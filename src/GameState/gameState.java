package GameState;

import Cards.normalCard;
import Player.player;
import SlotMachine.slotMachine;
import Wallet.wallet;

public class gameState {
    private final player[] players = new player[5];
    private final slotMachine[] slotsMachines = new slotMachine[6];
    private final normalCard[] level1cards = new normalCard[20];
    private final normalCard[] level2cards = new normalCard[20];
    private final normalCard[] level3cards = new normalCard[20];
    private static int level1no, level2no, level3no;
    private int turnSW;

    public void startTheGame(String player1, String player2) {
        players[1] = new player(player1);
        players[2] = new player(player2);
        setSlotMachines();
        setNormalCards();
        turnSW = 0;
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

    public player getPlayers(int i) {
        return (i == 0? players[0] : players[1]);
    }

    public int getTurnSW() {
        return turnSW;
    }

    public void changeTurnSW() {
        turnSW = 1 - turnSW;
    }
}
