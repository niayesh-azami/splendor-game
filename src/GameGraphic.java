import Cards.normalCard;
import Cards.prizeClaw;
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
        if (color == -1) return new Color(255, 156, 106);
        if (color == 0) return new Color(176, 208, 159);
        if (color == 1) return new Color(206, 206, 206);
        if (color == 2) return new Color(198, 168, 218);
        if (color == 3) return new Color(157, 206, 218);
        if (color == 4) return new Color(246, 188, 188);
        return new Color(255, 234, 130);
    }



    @Override
    public void paint(Graphics g) {
        //((Graphics2D) g).setStroke(new BasicStroke(3));
        //getContentPane().setBackground(new java.awt.Color(204, 166, 166));
        //getContentPane().setBackground(new Color(237, 237, 237));

        Rectangle recCard = new Rectangle(80, 120);

        // drawing coins
        for (int i = 0; i < 6; i++) {
            if (game.getGameState().getSlotMachine(i).getCoinNum() > 0) {
                g.setColor(getColor(i));
                g.fillOval(590 + i * 80, 50, 50, 50);
                g.setColor(Color.white);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
                g.drawString("" + game.getGameState().getSlotMachine(i).getCoinNum(), 590 + i * 80 + 17, 50 + 33);
            }
        }

        // drawing initial cards
        for (int i = 0; i < 3; i++) {
            if (game.getGameState().getCardsNo(i + 1) > 0) {
                g.setColor(getColor(5));
                g.fillRect(500, 130 + i * (30 + recCard.height), 60, 90);
                g.setColor(Color.white);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
                g.drawString("" + game.getGameState().getCardsNo(i + 1), 500 + 15, 130 + i * (30 + recCard.height) + 50);

            }
        }

        // drawing level 3 cards
        for (int i = game.getGameState().getCardsNo(3) - 1, j = 0; i >= 0 && j < 4 ; i--, j++) {
            normalCard card = game.getGameState().getCard(3, i);
            g.setColor(getColor(card.getSpecialCoin()));
            g.fillRect(620 + j * (30 + recCard.width), 130, recCard.width, recCard.height);
            if (card.getScore() > 0) {
                g.setColor(Color.white);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
                g.drawString("" + card.getScore(), 620 + j * (30 + recCard.width) + 13, 130 + 30);
            }
        }

        // drawing level 2 cards
        for (int i = game.getGameState().getCardsNo(2) - 1, j = 0; i >= 0 && j < 4 ; i--, j++) {
            normalCard card = game.getGameState().getCard(2, i);
            g.setColor(getColor(card.getSpecialCoin()));
            g.fillRect(620 + j * (30 + recCard.width), 130 + 120 + 35, recCard.width, recCard.height);
            if (card.getScore() > 0) {
                g.setColor(Color.white);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
                g.drawString("" + card.getScore(), 620 + j * (30 + recCard.width) + 13, 130 + 120 + 35 + 30);
            }
        }

        // drawing level 1 cards
        for (int i = game.getGameState().getCardsNo(1) - 1, j = 0; i >= 0 && j < 4 ; i--, j++) {
            normalCard card = game.getGameState().getCard(1, i);
            g.setColor(getColor(card.getSpecialCoin()));
            g.fillRect(620 + j * (30 + recCard.width), 130 + 240 + 70, recCard.width, recCard.height);
            if (card.getScore() > 0) {
                g.setColor(Color.white);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
                g.drawString("" + card.getScore(), 620 + j * (30 + recCard.width) + 13, 130 + 240 + 70 + 30);
            }
        }
        // drawing prize claws
        for (int i = 0; i < game.getGameState().getPrizeClawNo(); i++) {
            prizeClaw card = game.getGameState().getPrizeClaw(i);
            g.setColor(getColor(-1));
            g.fillRect(370, 130 + i * 150, 80,90);
            if (card.getScore() > 0) {
                g.setColor(Color.white);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
                g.drawString("" + card.getScore(), 370 + 13, 130 + i * 150 + 30);
            }
        }
    }

    public static void main(String[] args) {

        GameLogic gameLogic = new GameLogic("player1", "player2");
        GameGraphic gameGraphic = new GameGraphic(gameLogic);

    }
}
