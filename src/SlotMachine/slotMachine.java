package SlotMachine;

public class slotMachine {
    private int coinNum;

    public void takeCoin(int coinNum) {
        if (coinNum <= this.coinNum) {
            this.coinNum -= coinNum;
        }
    }

    public void giveBackCoin(int coinNum) {
        this.coinNum += coinNum;
    }

    public int getCoinNum() {
        return coinNum;
    }
}
