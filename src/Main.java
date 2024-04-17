// in the name of god

import GameState.gameState;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Main extends JFrame {
    gameState curGameState = new gameState();
    String player1name, player2name;

    public Main() {
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setTitle("Splendor Game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initTopPnl();
        initCntrPnl();
        initBotPnl();
        setVisible(true);
    }

    private void initTopPnl() {
        JPanel topPnl = new JPanel(new FlowLayout());
        JToggleButton PvPBtn = new JToggleButton("PvP");
        JToggleButton PvEBtn = new JToggleButton("PvE");
        topPnl.add(PvPBtn);
        topPnl.add(PvEBtn);
        PvPBtn.setActionCommand("PvP");
        PvEBtn.setActionCommand("PvE");
        PvPBtn.setSelected(true);

        ActionListener gameTypListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("PvP"))
                    PvEBtn.setSelected(!PvEBtn.isSelected());
                else
                    PvPBtn.setSelected(!PvPBtn.isSelected());
            }
        };

        PvPBtn.addActionListener(gameTypListener);
        PvEBtn.addActionListener(gameTypListener);

        add(topPnl, BorderLayout.PAGE_START);
    }

    private void initCntrPnl() {
        JPanel cntrPnl = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel player1Label = new JLabel("Player 1");
        JLabel player2Label = new JLabel("Player 2");
        JLabel textExp = new JLabel("*your names should contain at least 4 characters*");
        player1Label.setHorizontalAlignment(JLabel.CENTER);
        player2Label.setHorizontalAlignment(JLabel.CENTER);

        final JTextField[] player1name = {new JTextField("")};
        JTextField player2name = new JTextField("");
        player1name[0].setActionCommand("p1");
        player2name.setActionCommand("p2");
        player1name[0].setHorizontalAlignment(JTextField.CENTER);
        player2name.setHorizontalAlignment(JTextField.CENTER);

        cntrPnl.add(player1Label);
        cntrPnl.add(player2Label);
        cntrPnl.add(player1name[0]);
        cntrPnl.add(player2name);
        cntrPnl.add(textExp);

        DocumentListener typNAmeListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    if (isValid(e.getDocument().getText(0, e.getDocument().getLength())))
                        if (e.getDocument().getProperty("owner").equals("player1"))

                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }

            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }

            public boolean isValid(String name) {
                if (name.length() < 4) return false;
                return true;
            }
        };
        player1name[0].getDocument().addDocumentListener(typNAmeListener);
        player2name.getDocument().addDocumentListener(typNAmeListener);
        player1name[0].getDocument().putProperty("name", "player1");
        player2name.getDocument().putProperty("name", "player2");

        cntrPnl.setBorder(new EmptyBorder(180, 100, 230, 100));
        add(cntrPnl, BorderLayout.CENTER);
    }

    private void initBotPnl() {
        JButton startBtn = new JButton("Start");
        startBtn.setActionCommand("Start");

        ActionListener gameStartListener = new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              if (e.getActionCommand().equals("Start"));
          }
        };

        startBtn.addActionListener(gameStartListener);
        add(startBtn, BorderLayout.PAGE_END);
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