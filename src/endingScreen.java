import GameState.GameState;

import javax.swing.*;
import java.awt.*;

public class endingScreen extends JFrame {
    private GameState gameState;

    public endingScreen(GameState gameState) {
        this.gameState = gameState;

        setSize(500, 400);
        setLocationRelativeTo(null);
        setTitle("Congratulations!");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);
    }

    public void paint(Graphics g) {
        if (gameState.getPlayers(1).getScore() > gameState.getPlayers(2).getScore()) {
            g.setColor(Color.lightGray);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 35));
            g.drawString(gameState.getPlayers(1).getPlayerName() + " won the game!!!", 20, 200);
        } else if (gameState.getPlayers(1).getScore() < gameState.getPlayers(2).getScore()) {
            g.setColor(Color.lightGray);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 35));
            g.drawString(gameState.getPlayers(2).getPlayerName() + " won the game!!!", 20, 200);
        } else {
            int player1cards = 0, player2cards = 0;
            for (int i = 0; i < 5; i++) {
                player1cards += gameState.getPlayers(1).getWallet().getSpecialCoinNum(i);
                player2cards += gameState.getPlayers(2).getWallet().getSpecialCoinNum(i);
            }

            if (player1cards < player2cards) {
                g.setColor(Color.lightGray);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 35));
                g.drawString(gameState.getPlayers(1).getPlayerName() + " won the game!!!", 20, 200);
            } else if (player2cards < player1cards) {
                g.setColor(Color.lightGray);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 35));
                g.drawString(gameState.getPlayers(2).getPlayerName() + " won the game!!!", 20, 200);
            } else {
                g.setColor(Color.lightGray);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 35));
                g.drawString("no one won the game!!! :(", 20, 200);
            }
        }
    }
}
