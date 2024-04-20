import GameState.GameState;

public class GameLogic {
    private GameState gameState = new GameState();

    public GameLogic(String player1name, String player2name) {
        gameState.startTheGame(player1name, player2name);
    }

    public GameState getGameState() {
        return gameState;
    }
}
