import Cards.normalCard;
import GameState.GameState;
import Player.player;

import static java.lang.Math.*;

public class GameLogic {
    private GameState gameState = new GameState();

    public GameLogic(String player1name, String player2name) {
        gameState.startTheGame(player1name, player2name);
    }

    public GameState getGameState() {
        return gameState;
    }

    public boolean getCoinFromSlotMachine(int coin, int amount) {
        if (gameState.getSlotMachine(coin).getCoinNum() == 0) return false;

        gameState.getSlotMachine(coin).takeCoin(amount);

        player p = gameState.getPlayers(gameState.getTurnSW());
        p.getWallet().addCoin(coin, amount);

        //System.out.println("*** : " + p.getPlayerName() + " " + p.getWallet().getCoinNum(coin));
        return true;
    }

    public void giveCoinToSlotMachine(int coin, int amount) {
        gameState.getSlotMachine(coin).giveBackCoin(amount);

        player p = gameState.getPlayers(gameState.getTurnSW());
        p.getWallet().minusCoins(coin, amount);
    }

    public boolean buyCard(int level, int i) {

        int neededGoldCoins = 0;
        player p = gameState.getPlayers(gameState.getTurnSW());
        normalCard card = gameState.getCard(level, i);

        for (int j = 0; j < 5; j++)
            if (card.getCoins(j) > p.getWallet().getSpecialCoinNum(j) + p.getWallet().getCoinNum(j))
                neededGoldCoins += card.getCoins(j) - p.getWallet().getCoinNum(j) - p.getWallet().getSpecialCoinNum(j);

        if (neededGoldCoins <= p.getWallet().getCoinNum(5)) {
            p.addCard(card);
            gameState.deleteCard(level, i);

            for (int j = 0; j < 5; j++)
                p.getWallet().minusCoins(j, card.getCoins(j));

            p.getWallet().minusCoins(5, neededGoldCoins);

            gameState.changeTurnSW();
            return true;
        } else return false;
    }

    public void holdCard(int level, int i) {
        ;
        player p = gameState.getPlayers(gameState.getTurnSW());
        normalCard card = gameState.getCard(level, i);
        System.out.println(p.getPlayerName());
        p.getWallet().addCoin(5, 1);
        p.holdCard(card);
        gameState.deleteCard(level, i);

        gameState.changeTurnSW();
    }
}
