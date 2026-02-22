package GameLogic;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame implements KeyListener {
    private MainGamePanel gamePanel;
    private StartMenu startMenu;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private Timer timer;

    public MainWindow() {
        setTitle("Brick Breaker Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        startMenu = new StartMenu();
        gamePanel = new MainGamePanel(mainPanel);

        mainPanel.add(startMenu, "StartMenu");
        mainPanel.add(gamePanel, "GamePanel");
        add(mainPanel);

        // Show start menu first
        cardLayout.show(mainPanel, "StartMenu");

        // Start button transitions to game
        startMenu.getStartButton().addActionListener(e -> {
            gamePanel.generateEntities();
            cardLayout.show(mainPanel, "GamePanel");
            gamePanel.requestFocusInWindow();
            gamePanel.startGame();
            timer.start();
        });

        startMenu.getExitButton().addActionListener(e -> System.exit(0));

        // Game loop timer
        timer = new Timer(16, e -> { // ~60fps
            if (!gamePanel.isGameOver() && !gamePanel.isGameWon()) {
                gamePanel.checkCollisions();
                gamePanel.repaint();
            } else {
                timer.stop();
                showEndScreen();
            }
        });

        addKeyListener(this);
        setFocusable(true);
        setVisible(true);
    }

    private void showEndScreen() {
        String message = gamePanel.isGameWon() ? "You Win! Score: " : "Game Over! Score: ";
        int result = JOptionPane.showConfirmDialog(
            this,
            message + gamePanel.getScore() + "\nPlay again?",
            gamePanel.isGameWon() ? "You Win!" : "Game Over",
            JOptionPane.YES_NO_OPTION
        );
        if (result == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            System.exit(0);
        }
    }

    private void restartGame() {
        gamePanel.resetGame();
        cardLayout.show(mainPanel, "StartMenu");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            gamePanel.movePlatform(-20);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            gamePanel.movePlatform(20);
        } else if (e.getKeyCode() == KeyEvent.VK_P) {
            gamePanel.togglePause();
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}