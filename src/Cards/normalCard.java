package Cards;

import java.util.Random;

public class normalCard {
    Random rand = new Random();

    private final int level;
    private final int coinNum;
    private final int score;
    private final int specialCoin;
    private final int[] coins = new int[10];
    private int position;

    public normalCard(int level) {
        this.level = level;
        score = setScore();
        coinNum = setCoinNum();
        specialCoin = setSpecialCoin();
        setCoins();
        position = 0;
    }

    private int setScore() {
        if (level == 1)
            return rand.nextInt(2);
        if (level == 2)
            return rand.nextInt(3) + 2;
        return rand.nextInt(3) + 3;
    }

    private int setCoinNum() {
        if (level == 1)
            return rand.nextInt(3) + 4;
        if (level == 2)
            return rand.nextInt(3) + 6;
        return rand.nextInt(4) + 7;
    }

    private int setSpecialCoin() {
        return rand.nextInt(5);
    }

    private void setCoins() {
        for (int i = 0; i < coinNum; i++) {
            int coin = rand.nextInt(5);
            if (coins[coin] < 6)
                coins[coin]++;
            else i--;
        }
    }

    public int getLevel() {
        return level;
    }

    public int getCoinNum() {
        return coinNum;
    }

    public int getScore() {
        return score;
    }

    public int getSpecialCoin() {
        return specialCoin;
    }

    public int[] getCoins() {
        return coins;
    }

    public int getInGame() {
        return position;
    }
}
