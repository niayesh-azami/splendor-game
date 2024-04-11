package Wallet;

public class wallet {
    private int[] coins = new int[10];

    public void add(int coin) {
        coins[coin]++;
    }

    public void minus(int coin) {
        if (coins[coin] > 0)
            coins[coin]--;
    }

    public int coinNum(int coin) {
        return coins[coin];
    }

}
