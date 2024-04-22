package Wallet;

import static sun.swing.MenuItemLayoutHelper.max;

public class Wallet {
    private int[] coins = new int[10];
    private int[] specialCoins = new int[10];

    public void addCoin(int coin, int amount) {
        coins[coin] += amount;
    }

    public void addSpecialCoin(int coin) {
        specialCoins[coin]++;
    }

    public void minus(int coin, int amount) {
        coins[coin] = max(0, coins[coin] - amount);
    }

    public int getCoinNum(int coin) {
        return coins[coin];
    }

    public int getSpecialCoinNum(int coin) {
        return specialCoins[coin];
    }
}
