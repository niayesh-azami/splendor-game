package Wallet;

public class Wallet {
    private int[] coins = new int[10];
    private int[] specialCoins = new int[10];

    public void addCoin(int coin) {
        coins[coin]++;
    }

    public void addSpecialCoin(int coin) {
        specialCoins[coin]++;
    }

    public void minus(int coin) {
        if (coins[coin] > 0)
            coins[coin]--;
    }

    public int getCoinNum(int coin) {
        return coins[coin];
    }

    public int getSpecialCoinNum(int coin) {
        return specialCoins[coin];
    }
}
