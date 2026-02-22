package GameLogic;

import javax.swing.SwingUtilities;

public class GameApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainWindow();
        });
    }
}
