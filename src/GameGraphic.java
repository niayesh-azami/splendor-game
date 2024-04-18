import javax.swing.*;
import java.awt.*;

public class GameGraphic extends JFrame {
    GameLogic game;

    public GameGraphic(GameLogic game) {
        this.game = game;

        setSize(1000, 600);
        setLocationRelativeTo(null);
        setTitle("Splendor Game");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        setVisible(true);
    }
}
