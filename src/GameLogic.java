import GameState.GameState;

public class GameLogic {
    private GameState gameState = new GameState();

    public GameLogic(String player1name, String player2name) {
        gameState.startTheGame(player1name, player2name);
    }

    public GameState getGameState() {
        return gameState;
    }

    public void buyCard(int level, int i) {
        gameState.getPlayers(gameState.getTurnSW()).addCard(gameState.getCard(level, i));
        gameState.deleteCard(level, i);
        gameState.changeTurnSW();
    }
}
