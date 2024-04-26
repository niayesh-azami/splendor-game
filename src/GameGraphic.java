import Cards.normalCard;
import Cards.prizeClaw;
import GameState.GameState;
import Player.player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
                //System.out.println(curX + " " + curY);
                if (game.getGameState().getPlayers(game.getGameState().getTurnSW()).getWallet().getAllCoinsNum() > 10)
                    play(-1);
                else {
                    outerLoop:
                    for (int level = 1; level <= 3; level++)
                        for (int i = game.getGameState().getCardsNo(level) - 1, j = 0; i >= 0 && j < 4; i--, j++) {
                            Rectangle recCard = new Rectangle(80, 120);
                            if (720 + j * (30 + recCard.width) <= curX &&
                                    curX <= 720 + j * (30 + recCard.width) + recCard.width &&
                                    130 + (3 - level) * (recCard.height + 35) <= curY &&
                                    curY <= 130 + (3 - level) * (recCard.height + 35) + recCard.height) {
                                int ans = play(1);
                                if (ans != -1) {
                                    if (ans == 0)
                                        if (!game.buyCard(level, i)) {
                                            invalidMove();
                                            repaint();
                                        }
                                    if (ans == 1) game.holdCard(level, i);
                                    repaint();
                                }
                                break outerLoop;
                            }
                        }

                    if (690 <= curX && curX <= 690 + 5 * 80 && 50 <= curY && curY <= 100) {
                        play(0);
                        repaint();
                    }

                    if (game.getGameState().getTurnSW() == 1) {
                        for (int i = 0; i < game.getGameState().getPlayers(1).getReservedCardsNum(); i++) {
                            Rectangle recCard = new Rectangle(80, 120);
                            if (-690 + 720 + i * (10 + recCard.width) <= curX && curX <= -690 + 720 + i * (10 + recCard.width) + recCard.width
                                    && 160 + (1 - 1) * 255 + 20 + 10 <= curY && curY <= 160 + (1 - 1) * 255 + recCard.height + 10) {
                                curX = i;
                                play(2);
                                repaint();
                            }
                        }
                    } else {
                        for (int i = 0; i < game.getGameState().getPlayers(2).getReservedCardsNum(); i++) {
                            Rectangle recCard = new Rectangle(80, 120);
                            if (-690 + 720 + i * (10 + recCard.width) <= curX && curX <= -690 + 720 + i * (10 + recCard.width) + recCard.width
                                    && 160 + (2 - 1) * 255 + 20 + 10 <= curY && curY <= 160 + (2 - 1) * 255 + recCard.height + 10) {
                                curX = i;
                                play(2);
                            }
                        }
                    }
                }

                if ((game.getGameState().getPlayers(1).getScore() >= 15 || game.getGameState().getPlayers(2).getScore() >= 15)
                        && game.getGameState().getTurnSW() == 2) {
                    setVisible(false);
                    endingScreen Screen = new endingScreen(game.getGameState());
                }
            }
        });
    }

    private void invalidMove() {
        String[] options = new String[]{"okay"};
        JOptionPane.showOptionDialog(this, "invalid move!",
                "!", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }

    private int play(int i) {

        if (i == -1) {
            ///// check if the coins are more than 10
            player p = game.getGameState().getPlayers(game.getGameState().getTurnSW());

            if (p.getWallet().getAllCoinsNum() > 10) {
                System.out.println("here 1");

                JFrame f;
                JPanel p1 = new JPanel();

                f = new JFrame("choosing coins");
                f.setLocationRelativeTo(null);
                f.setSize(400, 120);
                f.setLayout(new BorderLayout());

                // create buttons
                JToggleButton[] coinsButtons = new JToggleButton[5];
                coinsButtons[0] = new JToggleButton("Green");
                coinsButtons[1] = new JToggleButton("Gray");
                coinsButtons[2] = new JToggleButton("Purple");
                coinsButtons[3] = new JToggleButton("Blue");
                coinsButtons[4] = new JToggleButton("Red");

                coinsButtons[0].setActionCommand("green");
                coinsButtons[1].setActionCommand("gray");
                coinsButtons[2].setActionCommand("purple");
                coinsButtons[3].setActionCommand("blue");
                coinsButtons[4].setActionCommand("red");

                boolean[] finalSelectedCoins = new boolean[5];

                // add checkbox to panel
                for (int j = 0; j < 5; j++)
                    if (p.getWallet().getCoinNum(j) > 0)
                        p1.add(coinsButtons[j]);

                ActionListener selectedCoinsToRemove = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getActionCommand().equals("green")) {
                            if (coinsButtons[0].isSelected())
                                finalSelectedCoins[0] = true;
                            else finalSelectedCoins[0] = false;
                        } else if (e.getActionCommand().equals("gray")) {
                            if (coinsButtons[1].isSelected())
                                finalSelectedCoins[1] = true;
                            else finalSelectedCoins[1] = false;
                        } else if (e.getActionCommand().equals("purple")) {
                            if (coinsButtons[2].isSelected())
                                finalSelectedCoins[2] = true;
                            else finalSelectedCoins[2] = false;
                        } else if (e.getActionCommand().equals("blue")) {
                            if (coinsButtons[3].isSelected())
                                finalSelectedCoins[3] = true;
                            else finalSelectedCoins[3] = false;
                        } else {
                            if (coinsButtons[4].isSelected())
                                finalSelectedCoins[4] = true;
                            else finalSelectedCoins[4] = false;
                        }
                    }
                };

                for (int j = 0; j < 5; j++)
                    coinsButtons[j].addActionListener(selectedCoinsToRemove);

                f.add(p1, BorderLayout.CENTER);

                ////////////// endPage
                JToggleButton okayButton = new JToggleButton("okay");
                okayButton.setActionCommand("okay");
                okayButton.setSelected(false);

                ActionListener finishedSelectingToRemove = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getActionCommand().equals("okay")) {
                            if (okayButton.isSelected()) {

                                int selectedCoinsNum = 0;
                                boolean check = true;

                                for (int j = 0; j < 5; j++)
                                    if (finalSelectedCoins[j] == true)
                                        selectedCoinsNum++;

                                if (p.getWallet().getAllCoinsNum() - selectedCoinsNum <= 10) {
                                    f.setVisible(false);
                                    for (int j = 0; j < 5; j++)
                                        if (finalSelectedCoins[j])
                                            game.giveCoinToSlotMachine(j, 1);
                                    game.getGameState().changeTurnSW();
                                    repaint();
                                }
                            }
                        }
                    }
                };
                okayButton.addActionListener(finishedSelectingToRemove);
                f.add(okayButton, BorderLayout.PAGE_END);

                f.setVisible(true);
            }
        }


        if (i == 0) { // getting coins
            String[] options = new String[]{"2 coins", "3 coins"};
            int ans = JOptionPane.showOptionDialog(this, "2 coins from 1 slot machine or 3 from 3 different slot machines?",
                    "?", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (ans == 0) {
                boolean check = false;
                for (int j = 0; j < 5; j++)
                    if (game.getGameState().getSlotMachine(j).getCoinNum() == 4)
                        check = true;
                if (!check) invalidMove();
                else {
                    String[] optionsForCoins = new String[]{"green", "gray", "purple", "blue", "red"};
                    int coin = JOptionPane.showOptionDialog(this, "which coins?",
                            "?", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsForCoins, optionsForCoins[0]);
                    if (coin != -1) {
                        if (game.getGameState().getSlotMachine(coin).getCoinNum() != 4) invalidMove();
                        else if (!game.getCoinFromSlotMachine(coin, 2)) invalidMove();
                        else game.getGameState().changeTurnSW();
                    }
                }
            } else if (ans == 1) {
                JFrame f;
                JPanel p1 = new JPanel();

                f = new JFrame("choosing coins");
                f.setLocationRelativeTo(null);
                f.setSize(400, 120);
                f.setLayout(new BorderLayout());

                // create buttons
                JToggleButton[] coinsButtons = new JToggleButton[5];
                coinsButtons[0] = new JToggleButton("Green");
                coinsButtons[1] = new JToggleButton("Gray");
                coinsButtons[2] = new JToggleButton("Purple");
                coinsButtons[3] = new JToggleButton("Blue");
                coinsButtons[4] = new JToggleButton("Red");

                coinsButtons[0].setActionCommand("green");
                coinsButtons[1].setActionCommand("gray");
                coinsButtons[2].setActionCommand("purple");
                coinsButtons[3].setActionCommand("blue");
                coinsButtons[4].setActionCommand("red");

                // add checkbox to panel
                for (int j = 0; j < 5; j++)
                    if (game.getGameState().getSlotMachine(j).getCoinNum() > 0)
                        p1.add(coinsButtons[j]);

                boolean[] finalSelectedCoins = new boolean[5];

                ActionListener selectedCoins = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getActionCommand().equals("green")) {
                            if (coinsButtons[0].isSelected())
                                finalSelectedCoins[0] = true;
                            else finalSelectedCoins[0] = false;
                        } else if (e.getActionCommand().equals("gray")) {
                            if (coinsButtons[1].isSelected())
                                finalSelectedCoins[1] = true;
                            else finalSelectedCoins[1] = false;
                        } else if (e.getActionCommand().equals("purple")) {
                            if (coinsButtons[2].isSelected())
                                finalSelectedCoins[2] = true;
                            else finalSelectedCoins[2] = false;
                        } else if (e.getActionCommand().equals("blue")) {
                            if (coinsButtons[3].isSelected())
                                finalSelectedCoins[3] = true;
                            else finalSelectedCoins[3] = false;
                        } else {
                            if (coinsButtons[4].isSelected())
                                finalSelectedCoins[4] = true;
                            else finalSelectedCoins[4] = false;
                        }
                    }
                };

                for (int j = 0; j < 5; j++)
                    coinsButtons[j].addActionListener(selectedCoins);

                f.add(p1, BorderLayout.CENTER);

                ////////////// endPage
                JToggleButton okayButton = new JToggleButton("okay");
                okayButton.setActionCommand("okay");

                ActionListener finishedSelecting = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getActionCommand().equals("okay")) {
                            if (okayButton.isSelected()) {
                                f.setVisible(false);

                                int selectedCoinsNum = 0;
                                boolean check = true;

                                for (int j = 0; j < 5; j++)
                                    if (finalSelectedCoins[j] == true)
                                        selectedCoinsNum++;

                                for (int j = 0; j < 5; j++)
                                    if (finalSelectedCoins[j] && game.getGameState().getSlotMachine(j).getCoinNum() == 0)
                                        check = false;

                                if (selectedCoinsNum != 3 || check == false) invalidMove();
                                else {
                                    for (int j = 0; j < 5; j++)
                                        if (finalSelectedCoins[j])
                                            game.getCoinFromSlotMachine(j, 1);
                                    game.getGameState().changeTurnSW();
                                    repaint();
                                }
                            }
                        }
                    }
                };
                okayButton.addActionListener(finishedSelecting);
                f.add(okayButton, BorderLayout.PAGE_END);

                f.setVisible(true);
            }
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

        } else if (i == 2) {
            System.out.println("here");
            String[] options = new String[]{"buy"};
            int ans1 = JOptionPane.showOptionDialog(this, "what do you want to do with this card?!",
                    "?", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (ans1 == 0) {
                int whoIsPlaying = game.getGameState().getTurnSW();
                boolean ans = game.buyReservedCard(whoIsPlaying, curX);
                if (!ans) invalidMove();
            }
            repaint();
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
                g.fillRect(600, 130 + (3 - level) * (120 + 35), 60, 90);
                g.setColor(Color.white);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
                g.drawString("" + game.getGameState().getCardsNo(level), 600 + 15, 130 + (3 - level) * (120 + 35) + 50);
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
        for (int i = 0, j = 0; j < 3; i++, j++) {
            if (i < game.getGameState().getPrizeClawNo()) {
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


        // drawing players' coins
        for (int player = 1; player <= 2; player++)
            for (int i = 0; i < 6; i++) {
                if (game.getGameState().getPlayers(player).getWallet().getCoinNum(i) > 0) {
                    g.setColor(getColor(i));
                    g.fillOval(40 + i * 50, 110 + (player - 1) * 255, 30, 30);
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 22));
                    g.drawString("" + game.getGameState().getPlayers(player).getWallet().getCoinNum(i), 40 + 9 + i * 50, 110 + (player - 1) * 255 + 23);
                }
                else {
                    g.setColor(Color.white);
                    g.fillOval(40 + i * 50, 110 + (player - 1) * 255, 30, 30);
                }
            }


        // drawing special coins
        for (int player = 1; player <= 2; player++)
            for (int i = 0; i < 6; i++) {
                if (game.getGameState().getPlayers(player).getWallet().getSpecialCoinNum(i) > 0) {
                    g.setColor(getColor(i));
                    g.fillRect(40 + i * 50, 160 + (player - 1) * 255 - 15, 30, 30);
                    if (i != 5) {
                        g.setColor(Color.WHITE);
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 22));
                        g.drawString("" + game.getGameState().getPlayers(player).getWallet().getSpecialCoinNum(i), 40 + 9 + i * 50, 160 + (player - 1) * 255 + 30 - 7 - 15);
                    }
                }
                else {
                    g.setColor(Color.white);
                    g.fillRect(40 + i * 50, 160 + (player - 1) * 255, 30, 30);
                }
            }

        // drawing reserved cards
        for (int player = 1; player <= 2; player++)
            for (int i = 0, j = 0; j < 3; i++, j++) {
                Rectangle recCard = new Rectangle(80, 120);

                if (i < game.getGameState().getPlayers(player).getReservedCardsNum()) {
                    normalCard card = game.getGameState().getPlayers(player).getReservedCards()[i];
                    g.setColor(getColor(card.getSpecialCoin()));
                    //g.fillRect(720 + j * (30 + recCard.width), 130 + (3 - level) * (recCard.height + 35), recCard.width, recCard.height);
                    g.fillRect(-690 + 720 + i * (10 + recCard.width), 160 + (player - 1) * 255 + 20 + 10, recCard.width, recCard.height - 20);

                    if (card.getScore() > 0) {
                        g.setColor(Color.white);
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
                        g.drawString("" + card.getScore(), -690 + 720 + i * (10 + recCard.width) + 13, 160 + (player - 1) * 255 + 30 + 20 + 10);
                    }

                    if (card.getCoins(0) > 0) {
                        g.setColor(new Color(130, 180, 100));
                        g.fillOval(-690 + 720 + i * (10 + recCard.width) + 5, 160 + (player - 1) * 255 + 120 - 25 + 10, 20, 20);
                        g.setColor(Color.white);
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
                        g.drawString("" + card.getCoins(0), -690 + 720 + i * (10 + recCard.width) + 5 + 6, 160 + (player - 1) * 255 + 120 - 25 + 15 + 10);
                    }

                    if (card.getCoins(1) > 0) {
                        g.setColor(new Color(242, 242, 242));
                        g.fillOval(-690 + 720 + i * (10 + recCard.width) + 30, 10 + 160 + (player - 1) * 255 + 120 - 25, 20, 20);
                        g.setColor(Color.GRAY);
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
                        g.drawString("" + card.getCoins(1), -690 + 720 + i * (10 + recCard.width) + 30 + 6, 10 + 160 + (player - 1) * 255 + 120 - 25 + 15);
                    }
                    if (card.getCoins(2) > 0) {
                        g.setColor(new Color(149, 94, 186));
                        g.fillOval(-690 + 720 + i * (10 + recCard.width) + 55, 10 + 160 + (player - 1) * 255 + 120 - 25, 20, 20);
                        g.setColor(Color.WHITE);
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
                        g.drawString("" + card.getCoins(2), -690 + 720 + i * (10 + recCard.width) + 55 + 6, 10 + 160 + (player - 1) * (255) + 120 - 25 + 15);
                    }
                    if (card.getCoins(3) > 0) {
                        g.setColor(new Color(89, 171, 192));
                        g.fillOval(-690 + 720 + i * (10 + recCard.width) + 5, 10 + 160 + (player - 1) * 255 + 120 - 50, 20, 20);
                        g.setColor(Color.WHITE);
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
                        g.drawString("" + card.getCoins(3), -690 + 720 + i * (10 + recCard.width) + 5 + 6, 10 + 160 + (player - 1) * 255 + 120 - 50 + 15);
                    }
                    if (card.getCoins(4) > 0) {
                        g.setColor(new Color(237, 120, 120));
                        g.fillOval(-690 + 720 + i * (10 + recCard.width) + 30, 10 + 160 + (player - 1) * 255 + 120 - 50, 20, 20);
                        g.setColor(Color.WHITE);
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
                        g.drawString("" + card.getCoins(4), -690 + 720 + i * (10 + recCard.width) + 30 + 6, 10 + 160 + (player - 1) * 255 + 120 - 50 + 15);
                    }
                } else {
                    g.setColor(Color.white);
                    g.fillRect(-690 + 720 + i * (10 + recCard.width), 160 + (player - 1) * 255 + 20 + 10, recCard.width, recCard.height - 20);
                }
            }

        // drawing players' prize claw
        for (int player = 1; player <= 2; player++) {
            Player.player p = game.getGameState().getPlayers(player);
            for (int i = 0; i < p.getPrizeClawsNum(); i++) {
                Rectangle recCard = new Rectangle(30, 40);
                g.setColor(getColor(-1));
                g.fillRect(350, 110 + (player - 1) * 255 + i * 60, recCard.width, recCard.height);
            }

        }

    }

    public static void main(String[] args) {
        GameLogic gameLogic = new GameLogic("player1", "player2");
        GameGraphic gameGraphic = new GameGraphic(gameLogic);
    }
}
