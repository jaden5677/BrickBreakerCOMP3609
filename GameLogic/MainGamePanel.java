package GameLogic;
import Bricks.*;
import Ball.Ball;
import Platform.Platform;
import java.util.Vector;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

public class MainGamePanel extends JPanel {
    private Vector<Ball> balls;
    private Platform platform;
    private BrickInterface[][] bricks;
    private int score;
    private int lives;
    private boolean isGameOver;
    private boolean isGameWon;
    private boolean isGameStarted;
    private boolean isPaused;

    public MainGamePanel() {
        super();
        this.balls = new Vector<>();
        this.score = 0;
        this.lives = 3;
        this.isGameOver = false;
        this.isGameWon = false;
        this.isGameStarted = false;
        this.isPaused = false;
    }

    private void initializeBricks() {
        int brickWidth = getWidth() / 5;
        int brickHeight = 30;
        bricks = BrickFactory.createBrickGrid(10, 5, brickWidth, brickHeight, this);
    }

    public void generateEntities() {
        int w = getWidth();
        int h = getHeight();
        platform = new Platform(w / 2 - 50, h - 50, 100, 20, this);
        initializeBricks();
        balls.clear();
        Ball ball = new Ball(w / 2 - 10, h - 80, 20, 2, -2, this);
        balls.add(ball);
    }

    public void startGame() {
        if (!isGameStarted) {
            isGameStarted = true;
            repaint();
        }
    }

    public void update() {
    if (isPaused || isGameOver || isGameWon) return;
    for (Ball ball : balls) {
        ball.move();
    }
    
    checkCollisions();
    repaint();
}

    public void checkCollisions() {
        Vector<Ball> toRemove = new Vector<>();
        for (Ball ball : balls) {
            Ellipse2D ballBounds = ball.getBounds();
            if (ballBounds.intersects(platform.getBounds())) {
                ball.setVelocity(ball.getXVelocity(), -Math.abs(ball.getYVelocity()));
            }

            // Brick collisions
            for (int row = 0; row < bricks.length; row++) {
                for (int col = 0; col < bricks[row].length; col++) {
                    BrickInterface brick = bricks[row][col];
                    if (brick == null || brick.isDestroyed()) continue;
                    if (ballBounds.intersects(brick.getBrickBounds())) {
                        handleBrickCollision(ball, brick, row, col);
                    }
                }
            }
            if (ball.getY() > platform.getY()) {
                toRemove.add(ball);
                lives--;
                if (lives <= 0) {
                isGameOver = true;
             }
            }
        }
        balls.removeAll(toRemove);

        if (balls.isEmpty() && !isGameOver) {
            Ball newBall = new Ball(getWidth() / 2 - 10, getHeight() - 80, 20, 2, -2, this);
            balls.add(newBall);
            repaint();
            }
    }

    private void handleBrickCollision(Ball ball, BrickInterface brick, int row, int col) {
        Ellipse2D ballBounds = ball.getBounds();
        Rectangle2D brickBounds = brick.getBrickBounds();

        double ballCenterX = ballBounds.getCenterX();
        double ballCenterY = ballBounds.getCenterY();

        double overlapLeft   = ballCenterX - brickBounds.getMinX();
        double overlapRight  = brickBounds.getMaxX() - ballCenterX;
        double overlapTop    = ballCenterY - brickBounds.getMinY();
        double overlapBottom = brickBounds.getMaxY() - ballCenterY;

        double minOverlap = Math.min(Math.min(overlapLeft, overlapRight),
                                     Math.min(overlapTop, overlapBottom));

        if (minOverlap == overlapLeft || minOverlap == overlapRight) {
            ball.setVelocity(-ball.getXVelocity(), ball.getYVelocity());
        } else {
            ball.setVelocity(ball.getXVelocity(), -ball.getYVelocity());
        }

        brick.destroy();
        if (brick.isDestroyed()) {
            score += brick.getPointValue();
            bricks[row][col] = null;
            checkWinCondition();
        }
    }

    private void checkWinCondition() {
        for (BrickInterface[] row : bricks) {
            for (BrickInterface brick : row) {
                if (brick != null && !brick.isDestroyed()) return;
            }
        }
        isGameWon = true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isGameStarted && !isGameOver && !isGameWon) {
        drawGameEntities(g);
        }
        if (isPaused) {
            drawPauseOverlay(g);
        }
    }
    private void drawPauseOverlay(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(new Color(0, 0, 0, 150));
    g2d.fillRect(0, 0, getWidth(), getHeight());

    // Pause text
    g2d.setColor(Color.WHITE);
    g2d.setFont(new Font("Arial", Font.BOLD, 48));
    FontMetrics fm = g2d.getFontMetrics();
    String pauseText = "PAUSED";
    int x = (getWidth() - fm.stringWidth(pauseText)) / 2;
    int y = getHeight() / 2;
    g2d.drawString(pauseText, x, y);

    // Sub text
    g2d.setFont(new Font("Arial", Font.PLAIN, 20));
    fm = g2d.getFontMetrics();
    String subText = "Press P to resume";
    x = (getWidth() - fm.stringWidth(subText)) / 2;
    g2d.drawString(subText, x, y + 40);
    }



    public void drawGameEntities(Graphics g) {
        if (bricks != null) {
            for (BrickInterface[] row : bricks) {
                for (BrickInterface brick : row) {
                    if (brick != null && !brick.isDestroyed()) {
                        brick.draw(g);
                    }
                }
            }
        }
        if (platform != null) platform.draw(g);
        for (Ball ball : balls) {
            ball.draw(g);
        }

        // HUD
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Score: " + score, 10, 20);
        g.drawString("Lives: " + lives, getWidth() - 80, 20);
    }

    public boolean isGameOver()  { return isGameOver; }
    public boolean isGameWon()   { return isGameWon; }
    public int getScore()        { return score; }

    public void movePlatform(int dx) {
        if (platform != null) platform.move(dx);
        repaint();
    }

    public void togglePause() { isPaused = !isPaused; }

    public void resetGame() {
        balls.clear();
        score = 0;
        lives = 3;
        isGameOver = false;
        isGameWon = false;
        isGameStarted = false;
        generateEntities();
    }
}