package Player;

import Cards.normalCard;
import Cards.prizeClaw;
import Wallet.Wallet;

public class player {
    private int score = 0;
    private final String playerName;
    private Wallet wallet = new Wallet();
    private final normalCard[] normalCards = new normalCard[20];
    private final normalCard[] reservedCards = new normalCard[5];
    private final prizeClaw[] prizeClaws = new prizeClaw[5];
    private int normalCardsNum, reservedCardsNum, prizeClawsNum;

    public player(String playerName) {
        this.playerName = playerName;
        normalCardsNum = 0;
        reservedCardsNum = 0;
        prizeClawsNum = 0;
        score = 0;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public int getScore() {
        return score;
    }

    public void addCard(normalCard card) {
        normalCards[normalCardsNum] = card;
        normalCardsNum++;
        wallet.addSpecialCoin(card.getSpecialCoin());
        score += card.getScore();
    }

    public void holdCard(normalCard card) {
        reservedCards[reservedCardsNum] = card;
        reservedCardsNum++;
    }

    public void addNormalCardsNum(int normalCardsNum) {
        normalCardsNum++;
    }

    public int getNormalCardsNum() {
        return normalCardsNum;
    }

    public void addReservedCardsNum() {
        reservedCardsNum++;
    }
    public int getReservedCardsNum() {
        return reservedCardsNum;
    }

    public void addPrizeClawsNum() {
        prizeClawsNum++;
    }

    public int getPrizeClawsNum() {
        return prizeClawsNum;
    }
}
