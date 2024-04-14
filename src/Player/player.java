package Player;

import Wallet.wallet;

public class player {
    private int score = 0;
    private final String playerName;
    private wallet playerWallet = new wallet();

    public player(String playerName) {
        this.playerName = playerName;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public wallet getPlayerWallet() {
        return playerWallet;
    }

    public int getScore() {
        return score;
    }
}
