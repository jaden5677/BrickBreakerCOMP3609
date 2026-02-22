package GameLogic;
import javax.swing.*;
import java.awt.*;

public class StartMenu extends JPanel {
    private JButton startButton;
    private JButton exitButton;

    public StartMenu() {
        setLayout(new GridBagLayout()); // Centers content nicely
        setBackground(Color.BLACK);

        JLabel title = new JLabel("BRICK BREAKER");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 48));

        startButton = new JButton("Start Game");
        exitButton = new JButton("Exit Game");

        styleButton(startButton);
        styleButton(exitButton);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 0, 10, 0);

        gbc.gridy = 0;
        add(title, gbc);
        gbc.gridy = 1;
        add(startButton, gbc);
        gbc.gridy = 2;
        add(exitButton, gbc);
    }

    private void styleButton(JButton button) {
        button.setPreferredSize(new Dimension(150, 40));
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
    }

    public JButton getStartButton() { return startButton; }
    public JButton getExitButton()  { return exitButton; }
}