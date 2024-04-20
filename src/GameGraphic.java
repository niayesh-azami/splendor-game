import Cards.normalCard;
import GameState.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class GameGraphic extends JFrame {
    private GameLogic game;
    private int width, height;

    public GameGraphic(GameLogic game) {
        this.game = game;

        setSize(1100, 600);
        setLocationRelativeTo(null);
        setTitle("Splendor Game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        setVisible(true);

        /*addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                System.out.println(e.getX());
                System.out.println(e.getY());
            }
        });*/
    }

    private Color getColor(int color) {
        if (color == 0) return new Color(176, 208, 159);
        if (color == 1) return new Color(237, 237, 237);
        if (color == 2) return new Color(198, 168, 218);
        if (color == 3) return new Color(157, 206, 218);
        return new Color(246, 188, 188);
    }

    @Override
    public void paint(Graphics g) {
        //((Graphics2D) g).setStroke(new BasicStroke(3));
        //getContentPane().setBackground(new java.awt.Color(204, 166, 166));
        //getContentPane().setBackground(new Color(237, 237, 237));

        Rectangle recCard = new Rectangle(80, 120);

        // showing level 3 cards
        for (int i = game.getGameState().getCardsNo(3) - 1, j = 0; i >= 0 && j < 4 ; i--, j++) {
            normalCard card = game.getGameState().getCard(3, i);
            g.setColor(getColor(card.getSpecialCoin()));
            g.fillRect(620 + j * (30 + recCard.width), 130, recCard.width, recCard.height);
            if
        }

        // showing level 2 cards
        for (int i = game.getGameState().getCardsNo(2) - 1, j = 0; i >= 0 && j < 4 ; i--, j++) {
            normalCard card = game.getGameState().getCard(2, i);
            g.setColor(getColor(card.getSpecialCoin()));
            g.fillRect(620 + j * (30 + recCard.width), 130 + 120 + 35, recCard.width, recCard.height);
        }

        // showing level 1 cards
        for (int i = game.getGameState().getCardsNo(1) - 1, j = 0; i >= 0 && j < 4 ; i--, j++) {
            normalCard card = game.getGameState().getCard(1, i);
            g.setColor(getColor(card.getSpecialCoin()));
            g.fillRect(620 + j * (30 + recCard.width), 130 + 240 + 70, recCard.width, recCard.height);
        }

        g.fillOval(100, 100, 60, 60);
    }

    public static void main(String[] args) {

        GameLogic gameLogic = new GameLogic("player1", "player2");
        GameGraphic gameGraphic = new GameGraphic(gameLogic);

    }
}
