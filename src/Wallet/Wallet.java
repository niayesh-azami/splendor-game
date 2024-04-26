package Wallet;

import static sun.swing.MenuItemLayoutHelper.max;

public class Wallet {
    private int[] coins = new int[10];
    private int[] specialCoins = new int[10];
    private int allCoinsNum = 0;

    public void addCoin(int coin, int amount) {
        coins[coin] += amount;
        allCoinsNum += amount;
    }

    public void addSpecialCoin(int coin) {
        specialCoins[coin]++;
    }

    public void minusCoins(int coin, int amount) {
            if (amount <= coins[coin]) {
                coins[coin] -= amount;
                allCoinsNum -= amount;
            } else {
                allCoinsNum -= coins[coin];
                coins[coin] = 0;
            }
    }

    public int getCoinNum(int coin) {
        return coins[coin];
    }

    public int getSpecialCoinNum(int coin) {
        return specialCoins[coin];
    }

    public int getAllCoinsNum() {
        return allCoinsNum;
    }
}
