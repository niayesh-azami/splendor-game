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
    private int curX, curY;

    public GameGraphic(GameLogic game) {
        this.game = game;

        setSize(1200, 600);
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
                curX = e.getX();
                curY = e.getY();
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
                g.fillOval(690 + i * 80, 50, 50, 50);
                g.setColor(Color.white);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
                g.drawString("" + game.getGameState().getSlotMachine(i).getCoinNum(), 690 + i * 80 + 17, 50 + 33);
            }
        }

        // drawing initial cards
        for (int i = 0; i < 3; i++) {
            if (game.getGameState().getCardsNo(i + 1) > 0) {
                g.setColor(getColor(5));
                g.fillRect(600, 130 + i * (30 + recCard.height), 60, 90);
                g.setColor(Color.white);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
                g.drawString("" + game.getGameState().getCardsNo(i + 1), 600 + 15, 130 + i * (30 + recCard.height) + 50);

            }
        }

        // drawing cards
        for (int level = 1; level <= 3; level++)
            for (int i = game.getGameState().getCardsNo(level) - 1, j = 0; i >= 0 && j < 4 ; i--, j++) {
                normalCard card = game.getGameState().getCard(level, i);
                System.out.println(level + " , " + j + " , " + card.getCoinNum());
                g.setColor(getColor(card.getSpecialCoin()));
                g.fillRect(720 + j * (30 + recCard.width), 130 + (3 - level) * (recCard.height + 35), recCard.width, recCard.height);
                if (card.getScore() > 0) {
                    g.setColor(Color.white);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
                    g.drawString("" + card.getScore(), 720 + j * (30 + recCard.width) + 13, 130 + (3 - level) * (recCard.height + 35) + 30);
                }


                if (card.getCoins(0) > 0) {
                    g.setColor(new Color(130, 180, 100));
                    g.fillOval(720 + j * (30 + recCard.width) + 5, 130 + (3 - level) * (recCard.height + 35) + 120 - 25, 20, 20);
                    g.setColor(Color.white);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
                    g.drawString("" + card.getCoins(0), 720 + j * (30 + recCard.width) + 5 + 6, 130 + (3 - level) * (recCard.height + 35) + 120 - 25 + 15);
                }
                if (card.getCoins(1) > 0) {
                    g.setColor(new Color(242, 242, 242));
                    g.fillOval(720 + j * (30 + recCard.width) + 30, 130 + (3 - level) * (recCard.height + 35) + 120 - 25, 20, 20);
                    g.setColor(Color.GRAY);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
                    g.drawString("" + card.getCoins(1), 720 + j * (30 + recCard.width) + 30 + 6, 130 + (3 - level) * (recCard.height + 35) + 120 - 25 + 15);
                }
                if (card.getCoins(2) > 0) {
                    g.setColor(new Color(149, 94, 186));
                    g.fillOval(720 + j * (30 + recCard.width) + 55, 130 + (3 - level) * (recCard.height + 35) + 120 - 25, 20, 20);
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
                    g.drawString("" + card.getCoins(2), 720 + j * (30 + recCard.width) + 55 + 6, 130 + (3 - level) * (recCard.height + 35) + 120 - 25 + 15);
                }
                if (card.getCoins(3) > 0) {
                    g.setColor(new Color(89, 171, 192));
                    g.fillOval(720 + j * (30 + recCard.width) + 5, 130 + (3 - level) * (recCard.height + 35) + 120 - 50, 20, 20);
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
                    g.drawString("" + card.getCoins(3), 720 + j * (30 + recCard.width) + 5 + 6, 130 + (3 - level) * (recCard.height + 35) + 120 - 50 + 15);
                }
                if (card.getCoins(4) > 0) {
                    g.setColor(new Color(237, 120, 120));
                    g.fillOval(720 + j * (30 + recCard.width) + 30, 130 + (3 - level) * (recCard.height + 35) + 120 - 50, 20, 20);
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
                    g.drawString("" + card.getCoins(4), 720 + j * (30 + recCard.width) + 30 + 6, 130 + (3 - level) * (recCard.height + 35) + 120 - 50 + 15);
                }
            }

        // drawing prize claws
        for (int i = 0; i < game.getGameState().getPrizeClawNo(); i++) {
            prizeClaw card = game.getGameState().getPrizeClaw(i);
            g.setColor(getColor(-1));
            g.fillRect(470, 130 + i * 150, 80,90);
            if (card.getScore() > 0) {
                g.setColor(Color.white);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
                g.drawString("" + card.getScore(), 470 + 13, 130 + i * 150 + 30);
            }
        }

        // drawing players' names
        g.setColor(Color.lightGray);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 35));
        if (game.getGameState().getTurnSW() == 0) {
            g.drawString(game.getGameState().getPlayers(1).getPlayerName() + "'s turn", 40, 80);
            g.drawString(game.getGameState().getPlayers(2).getPlayerName(), 40, 335);
        }
        else {
            g.drawString(game.getGameState().getPlayers(1).getPlayerName(), 40, 80);
            g.drawString(game.getGameState().getPlayers(2).getPlayerName() + "'s turn", 40, 335);
        }

        // drawing coins
        for (int player = 0; player < 2; player++)
            for (int i = 0; i < 6; i++)
                if (game.getGameState().getPlayers(2).getWallet().getCoinNum(i) > 0) {
                    g.setColor(getColor(i));
                    g.fillOval(40 + i * 50, 110 + player * 255, 30, 30);
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 22));
                    g.drawString("" + game.getGameState().getPlayers(1).getWallet().getCoinNum(i), 40 + 9 + i * 50, 110 + player * 255 + 23);
                }

        // drawing special coins
        for (int player = 0; player < 2; player++)
            for (int i = 0; i < 6; i++)
                if (game.getGameState().getPlayers(1).getWallet().getSpecialCoinNum(i) > 0) {
                    g.setColor(getColor(i));
                    g.fillRect(40 + i * 50, 160 + player * 255, 30, 45);
                    if (i != 5) {
                        g.setColor(Color.WHITE);
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 22));
                        g.drawString("" + game.getGameState().getPlayers(1).getWallet().getCoinNum(i), 40 + 9 + i * 50, 160 + player * 255 + 30);
                    }
                }


    }

    public static void main(String[] args) {

        GameLogic gameLogic = new GameLogic("player1", "player2");
        GameGraphic gameGraphic = new GameGraphic(gameLogic);

    }
}
