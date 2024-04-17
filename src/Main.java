// in the name of god

import GameState.gameState;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main extends JFrame {

    public Main() {
        setSize(800, 800);
        setLocationRelativeTo(null);
        setTitle("Splendor Game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initTopPnl();
        setVisible(true);
    }

    private void initTopPnl() {
        JPanel topPnl = new JPanel(new FlowLayout());
        JToggleButton gameTyp = new JToggleButton("Game Type");
        topPnl.add(gameTyp);
        add(topPnl, BorderLayout.PAGE_START);
    }

    public static void main(String[] args) {

        new Main();

        // if new game starts
        gameState parkGameState = new gameState();

        String player1name, player2name;
        Scanner input = new Scanner(System.in);
        player1name = input.nextLine();
        player2name = input.nextLine();

        parkGameState.startTheGame(player1name, player2name);

        while(!((parkGameState.getPlayers(0).getScore() >= 15 ||
                parkGameState.getPlayers(1).getScore() >= 15) && parkGameState.getTurnSW() == 0)) {
            int choice = input.nextInt();
        }
    }
}