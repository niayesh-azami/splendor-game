// in the name of god

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private String player1name, player2name;
    private JTextField player1nameTxt, player2nameTxt;
    private Main me;

    public Main() {
        setSize(500, 400);
        setLocationRelativeTo(null);
        setTitle("Splendor Game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initTopPnl();
        initCntrPnl();
        initBotPnl();
        setVisible(true);
        me = this;
        player1name = "Player 1";
        player2name = "Player 2";
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

        player1nameTxt = new JTextField("");
        player2nameTxt = new JTextField("");
        player1nameTxt.setActionCommand("p1");
        player2nameTxt.setActionCommand("p2");
        player1nameTxt.setHorizontalAlignment(JTextField.CENTER);
        player2nameTxt.setHorizontalAlignment(JTextField.CENTER);

        cntrPnl.add(player1Label);
        cntrPnl.add(player2Label);
        cntrPnl.add(player1nameTxt);
        cntrPnl.add(player2nameTxt);
        cntrPnl.add(textExp);

        DocumentListener typNAmeListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    if (isValid(e.getDocument().getText(0, e.getDocument().getLength())))
                        if (e.getDocument().getProperty("owner").equals("player1"));

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
        player1nameTxt.getDocument().addDocumentListener(typNAmeListener);
        player2nameTxt.getDocument().addDocumentListener(typNAmeListener);
        player1nameTxt.getDocument().putProperty("name", "player1");
        player2nameTxt.getDocument().putProperty("name", "player2");

        cntrPnl.setBorder(new EmptyBorder(75, 50, 125, 50));
        add(cntrPnl, BorderLayout.CENTER);
    }

    private void initBotPnl() {
        JToggleButton startBtn = new JToggleButton("Start");
        startBtn.setActionCommand("start");

        ActionListener gameStartListener = new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              if (e.getActionCommand().equals("start")) {
                  if (startBtn.isSelected() && checkNames()) {
                      setVisible(false);
                      GameLogic gameBoard = new GameLogic();
                      GameGraphic gameGraphic = new GameGraphic(gameBoard);
                  }
              }
          }
        };
        startBtn.addActionListener(gameStartListener);
        add(startBtn, BorderLayout.PAGE_END);

    }

    private boolean checkNames() {
        if (player1name.length() < 4)
            player1nameTxt.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        if (player2name.length() < 4)
            player2nameTxt.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        return (player1name.length() >= 4 && player2name.length() >= 4);
    }
    public static void main(String[] args) {

        new Main();

    }
}