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
        gamePanel = new MainGamePanel();
        gamePanel.addKeyListener(this);
        gamePanel.setFocusable(true);

        mainPanel.add(startMenu, "StartMenu");
        mainPanel.add(gamePanel, "GamePanel");
        add(mainPanel);

        cardLayout.show(mainPanel, "StartMenu");

    
        startMenu.getStartButton().addActionListener(e -> {
            gamePanel.generateEntities();
            cardLayout.show(mainPanel, "GamePanel");
            gamePanel.requestFocusInWindow();
            gamePanel.startGame();
            timer.start();
        });

        startMenu.getExitButton().addActionListener(e -> System.exit(0));

        timer = new Timer(16, e -> {
        if (!gamePanel.isGameOver() && !gamePanel.isGameWon()) {
            gamePanel.update();
        } else {
            timer.stop();
            showEndScreen();
        }
    });

        
        setVisible(true);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
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
        System.out.println("Key pressed: " + e.getKeyCode()); // add this
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                gamePanel.movePlatform(-20);
                break;
            case KeyEvent.VK_RIGHT:
                gamePanel.movePlatform(20);
                break;
            case KeyEvent.VK_P:
                gamePanel.togglePause();
                break;
            case KeyEvent.VK_ESCAPE:
                showEndScreen();
                break;
        }   
}

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}