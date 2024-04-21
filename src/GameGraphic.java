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

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                curX = e.getX();
                curY = e.getY();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(curX + " " + curY);
                outerLoop :
                for (int level = 1; level <= 3; level++)
                    for (int i = game.getGameState().getCardsNo(level) - 1, j = 0; i >= 0 && j < 4 ; i--, j++) {
                        Rectangle recCard = new Rectangle(80, 120);
                        if (720 + j * (30 + recCard.width) <= curX &&
                                curX <= 720 + j * (30 + recCard.width) + recCard.width &&
                                130 + (3 - level) * (recCard.height + 35) <= curY &&
                                curY <= 130 + (3 - level) * (recCard.height + 35) + recCard.height) {
                            int ans = play(1);
                            if (ans != -1) {
                                if (ans == 0) game.buyCard(level, i);
                                repaint();
                            }
                            break outerLoop;
                        }
                    }
            }
        });
    }

    private int play(int i) {
        if (i == 0) { // getting coins

        }

        else if (i == 1) {
            if (game.getGameState().getPlayers(game.getGameState().getTurnSW()).getReservedCardsNum() < 3) {
                String[] options = new String[]{"buy", "hold"};
                return JOptionPane.showOptionDialog(this, "what do you want to do with this card?",
                        "?", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            }
            else {
                String[] options = new String[]{"buy"};
                return JOptionPane.showOptionDialog(this, "what do you want to do with this card?",
                        "?", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            }

        }

        else {

        }
        return -1;

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

        // drawing coins
        for (int i = 0; i < 6; i++) {
            if (game.getGameState().getSlotMachine(i).getCoinNum() > 0) {
                g.setColor(getColor(i));
                g.fillOval(690 + i * 80, 50, 50, 50);
                g.setColor(Color.white);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
                g.drawString("" + game.getGameState().getSlotMachine(i).getCoinNum(), 690 + i * 80 + 17, 50 + 33);
            }
            else {
                g.setColor(Color.white);
                g.fillOval(690 + i * 80, 50, 50, 50);
            }
        }

        // drawing initial cards
        for (int level = 1; level <= 3; level++) {
            Rectangle recCard = new Rectangle(60, 90);
            if (game.getGameState().getCardsNo(level) > 0) {
                g.setColor(getColor(5));
                g.fillRect(600, 130 + (3 - level) * (30 + recCard.height), 60, 90);
                g.setColor(Color.white);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
                g.drawString("" + game.getGameState().getCardsNo(level), 600 + 15, 130 + (3 - level) * (30 + recCard.height) + 50);
            }
            else {
                g.setColor(Color.white);
                g.fillRect(600, 130 + (3 - level) * (30 + recCard.height), 60, 90);
            }
        }

        // drawing cards
        for (int level = 1; level <= 3; level++)
            for (int i = game.getGameState().getCardsNo(level) - 1, j = 0; j < 4 ; i--, j++) {
                Rectangle recCard = new Rectangle(80, 120);
                if (i >= 0) {
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
                else {
                        g.setColor(Color.white);
                        g.fillRect(720 + j * (30 + recCard.width), 130 + (3 - level) * (recCard.height + 35), recCard.width, recCard.height);
                }
            }

        // drawing prize claws
        for (int i =  game.getGameState().getPrizeClawNo() - 1, j = 0; j < 3; i--, j++) {
            if (i >= 0) {
                prizeClaw card = game.getGameState().getPrizeClaw(i);
                g.setColor(getColor(-1));
                Rectangle recCard = new Rectangle(80, 90);
                g.fillRect(470, 130 + i * 150, 80, 90);
                if (card.getScore() > 0) {
                    g.setColor(Color.white);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
                    g.drawString("" + card.getScore(), 470 + 13, 130 + i * 150 + 30);
                }

                if (card.getCoinNum() > 0) {
                    if (card.getCoins(0) > 0) {
                        g.setColor(new Color(130, 180, 100));
                        g.fillOval(470 + 5, 130 + i * 150 + recCard.height - 25, 20, 20);
                        g.setColor(Color.white);
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
                        g.drawString("" + card.getCoins(0), 470 + 5 + 6, 130 + i * 150 + recCard.height - 25 + 15);
                    }
                    if (card.getCoins(1) > 0) {
                        g.setColor(new Color(242, 242, 242));
                        g.fillOval(470 + 30, 130 + i * 150 + recCard.height - 25, 20, 20);
                        g.setColor(Color.GRAY);
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
                        g.drawString("" + card.getCoins(1), 470 + 30 + 6, 130 + i * 150 + recCard.height - 25 + 15);
                    }
                    if (card.getCoins(2) > 0) {
                        g.setColor(new Color(149, 94, 186));
                        g.fillOval(470 + 55, 130 + i * 150 + recCard.height - 25, 20, 20);
                        g.setColor(Color.WHITE);
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
                        g.drawString("" + card.getCoins(2), 470 + 55 + 6, 130 + i * 150 + recCard.height - 25 + 15);
                    }
                    if (card.getCoins(3) > 0) {
                        g.setColor(new Color(89, 171, 192));
                        g.fillOval(470 + 5, 130 + i * 150 + recCard.height - 50, 20, 20);
                        g.setColor(Color.WHITE);
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
                        g.drawString("" + card.getCoins(3), 470 + 5 + 6, 130 + i * 150 + recCard.height - 50 + 15);
                    }
                    if (card.getCoins(4) > 0) {
                        g.setColor(new Color(237, 120, 120));
                        g.fillOval(470 + 30, 130 + i * 150 + recCard.height - 50, 20, 20);
                        g.setColor(Color.WHITE);
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
                        g.drawString("" + card.getCoins(4), 470 + 30 + 6, 130 + i * 150 + recCard.height - 50 + 15);
                    }
                }
            }
            else {
                g.setColor(Color.white);
                g.fillRect(470, 130 + i * 150, 80, 90);
            }
        }

        // drawing players' names
        g.setFont(new Font("TimesRoman", Font.PLAIN, 35));
        if (game.getGameState().getTurnSW() == 1) {
            g.setColor(Color.white);
            g.drawString(game.getGameState().getPlayers(2).getPlayerName() + "'s turn", 40, 335);
            g.setColor(Color.lightGray);
            g.drawString(game.getGameState().getPlayers(1).getPlayerName() + "'s turn", 40, 80);
            g.drawString(game.getGameState().getPlayers(2).getPlayerName(), 40, 335);
        }
        else {
            g.setColor(Color.white);
            g.drawString(game.getGameState().getPlayers(1).getPlayerName() + "'s turn", 40, 80);
            g.setColor(Color.lightGray);
            g.drawString(game.getGameState().getPlayers(1).getPlayerName(), 40, 80);
            g.drawString(game.getGameState().getPlayers(2).getPlayerName() + "'s turn", 40, 335);
        }

        // drawing players' scores
        g.setColor(Color.white);
        g.fillRect(300, 30, 50, 50);
        g.fillRect(300, 285, 50, 50);
        g.setColor(Color.lightGray);
        g.drawString("" + game.getGameState().getPlayers(1).getScore(), 300, 80);
        g.drawString("" + game.getGameState().getPlayers(2).getScore(), 300, 335);


        // drawing coins
        for (int player = 1; player <= 2; player++)
            for (int i = 0; i < 6; i++) {
                if (game.getGameState().getPlayers(player).getWallet().getCoinNum(i) > 0) {
                    g.setColor(getColor(i));
                    g.fillOval(40 + i * 50, 110 + player * 255, 30, 30);
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 22));
                    g.drawString("" + game.getGameState().getPlayers(1).getWallet().getCoinNum(i), 40 + 9 + i * 50, 110 + player * 255 + 23);
                }
                else {
                    g.setColor(Color.white);
                    g.fillOval(40 + i * 50, 110 + player * 255, 30, 30);
                }
            }


        // drawing special coins
        for (int player = 1; player <= 2; player++)
            for (int i = 0; i < 6; i++) {
                if (game.getGameState().getPlayers(player).getWallet().getSpecialCoinNum(i) > 0) {
                    g.setColor(getColor(i));
                    g.fillRect(40 + i * 50, 160 + (player - 1) * 255, 30, 45);
                    if (i != 5) {
                        g.setColor(Color.WHITE);
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 22));
                        g.drawString("" + game.getGameState().getPlayers(player).getWallet().getSpecialCoinNum(i), 40 + 9 + i * 50, 160 + (player - 1) * 255 + 30);
                    }
                }
                else {
                    g.setColor(Color.white);
                    g.fillRect(40 + i * 50, 160 + (player - 1) * 255, 30, 45);
                }
            }


    }

    public static void main(String[] args) {

        GameLogic gameLogic = new GameLogic("player1", "player2");
        GameGraphic gameGraphic = new GameGraphic(gameLogic);

    }
}
