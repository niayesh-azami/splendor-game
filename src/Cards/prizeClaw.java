package Cards;

import java.util.Random;

public class prizeClaw {
    Random rand = new Random();

    private final int coinNum;
    private final int score;
    private final int[] coins = new int[10];
    private int position;

    public prizeClaw() {
        score = setScore();
        coinNum = setCoinNum();
        setCoins();
        position = -1;
    }

    private int setScore() {
        return rand.nextInt(2) + 3;
    }

    private int setCoinNum() {
        return rand.nextInt(5) + 8;
    }

    private void setCoins() {
        for (int i = 0; i < coinNum; i++) {
            int coin = rand.nextInt(5);
            if (coins[coin] < 6)
                coins[coin]++;
            else i--;
        }
    }

    public int getScore() {
        return score;
    }
}
