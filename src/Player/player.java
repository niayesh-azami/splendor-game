package Player;

import Wallet.Wallet;

public class player {
    private int score = 0;
    private final String playerName;
    private Wallet wallet = new Wallet();

    public player(String playerName) {
        this.playerName = playerName;
        ///////////
        for (int i = 0; i < 6; i++) {
            wallet.addCoin(i);
            wallet.addSpecialCoin(i);
        }
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
}
