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

    public boolean buyCard(int level, int i) {

        int neededGoldCoins = 0;
        player p = gameState.getPlayers(gameState.getTurnSW());
        normalCard card = gameState.getCard(level, i);

        for (int j = 0; j < 5; j++)
            neededGoldCoins += max(0, card.getCoins(j) - p.getWallet().getCoinNum(j));

        if (neededGoldCoins <= p.getWallet().getCoinNum(5)) {
            p.addCard(gameState.getCard(level, i));
            gameState.deleteCard(level, i);

            for (int j = 0; j < 5; j++)
                p.getWallet().minus(j, card.getCoins(j));

            p.getWallet().minus(5, neededGoldCoins);

            gameState.changeTurnSW();

            return true;
        } else return false;
    }
}
