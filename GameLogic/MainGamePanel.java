package GameLogic;
import Bricks.*;
import Items.*;
import Ball.Ball;
import Platform.Platform;
import java.util.Vector;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
public class MainGamePanel extends JPanel {
    private JPanel panel;
    private Vector<Ball> balls;
    private Platform platform;
    private BrickInterface[][] bricks;
    private int score;
    private int lives;
    private boolean isGameOver;
    private boolean isGameWon;
    private boolean isGameStarted;
    private boolean isPaused;
    
    private JPanel gamePanel;
    private int panelWidth;
    private int panelHeight;


    public MainGamePanel(JPanel panel) {
        super();
        this.panel = panel;
        this.panelWidth = panel.getWidth();
        this.panelHeight = panel.getHeight();
        this.balls = new Vector<>();
        this.score = 0;
        this.lives = 3;
        this.isGameOver = false;
        this.isGameWon = false;
        this.isGameStarted = false;
        this.isPaused = false;
        //initializeBricks();

    }
    private void initializeBricks(JPanel panel) {
        //Bricks come down 2 rows at a time, with 5 columns, reach row has a mix of soft and normal and hard bricks, with a chance of an item being dropped
        int brickWidth = panelWidth / 5;
        int brickHeight = 30;
        bricks = new BrickInterface[10][5];
        bricks = BrickFactory.createBrickGrid(10, 5, brickWidth, brickHeight, panel);
        
    }
    public void generateEntities(){
        platform = new Platform(panelWidth / 2 - 50, panelHeight - 30, 100, 20, panel);
        initializeBricks(gamePanel);
        Ball ball = new Ball(panelWidth / 2 - 10, panelHeight - 50, 20, 2, -2, panel);
        balls.add(ball);
        ball.start();
    }
    public void startGame() {
        if (!isGameStarted) {
            drawGameEntities();
            isGameStarted = true;
        }

    }
    public void drawGameEntities(){
        for(int i=0;i<10;i++){
            for(BrickInterface brick:bricks[i]){
            brick.draw(gamePanel);
        }
        }
        for(Ball ball:balls){
            ball.draw();
        }
        platform.draw();
        
    }
    public void updateGame(int direction) {
        
    }
    

    public void checkCollisions() {
    for (Ball ball : balls) {
        Ellipse2D ballBounds = ball.getBounds();

        // --- Platform collision ---
        if (ballBounds.intersects(platform.getBounds())) {
            ball.setVelocity(ball.getXVelocity(), -Math.abs(ball.getYVelocity())); 
            // Math.abs ensures the ball always bounces upward off the platform,
            // preventing it from getting stuck inside
        }

        // --- Brick collisions ---
        for (int row = 0; row < bricks.length; row++) {
            for (int col = 0; col < bricks[row].length; col++) {
                BrickInterface brick = bricks[row][col];

                if (brick == null || brick.isDestroyed()) continue;

                if (ballBounds.intersects(brick.getBounds())) {
                    handleBrickCollision(ball, brick, row, col);
                }
            }
        }

        // --- Ball out of bounds (missed platform) ---
        if (ball.getY() > panelHeight) {
            balls.remove(ball);
            lives--;
            if (lives <= 0) {
                isGameOver = true;
            }
            break; // avoid ConcurrentModificationException after removal
        }
    }
}

private void handleBrickCollision(Ball ball, BrickInterface brick, int row, int col) {
    Ellipse2D ballBounds = ball.getBounds();
    Rectangle2D brickBounds = brick.getBounds();

    // Determine which side of the brick was hit to reflect correctly
    double ballCenterX = ballBounds.getCenterX();
    double ballCenterY = ballBounds.getCenterY();

    double brickLeft   = brickBounds.getMinX();
    double brickRight  = brickBounds.getMaxX();
    double brickTop    = brickBounds.getMinY();
    double brickBottom = brickBounds.getMaxY();

    double overlapLeft   = ballCenterX - brickLeft;
    double overlapRight  = brickRight  - ballCenterX;
    double overlapTop    = ballCenterY - brickTop;
    double overlapBottom = brickBottom - ballCenterY;

    double minOverlap = Math.min(Math.min(overlapLeft, overlapRight),
                                 Math.min(overlapTop,  overlapBottom));

    if (minOverlap == overlapLeft || minOverlap == overlapRight) {
        ball.setVelocity(-ball.getXVelocity(), ball.getYVelocity()); // side hit
    } else {
        ball.setVelocity(ball.getXVelocity(), -ball.getYVelocity()); // top/bottom hit
    }

    brick.destroy(); // decrement hitsRequired in your brick classes
    if (brick.isDestroyed()) {
        brick.erase();
        score += brick.getPointValue();

        if (brick.hasItem()) {
            // TODO: spawn the dropped item into the game
        }

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

    public boolean isGameOver() { return isGameOver; }
    public boolean isGameWon()  { return isGameWon; }
    public int getScore()       { return score; }

    public void movePlatform(int dx) {
    platform.move(dx);
    repaint();
    }

    public void togglePause() {
    isPaused = !isPaused;
    }

    public void resetGame() {
    balls.clear();
    score = 0;
    lives = 3;
    isGameOver = false;
    isGameWon = false;
    isGameStarted = false;
    generateEntities();
    }

    @Override
    protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (isGameStarted && !isGameOver && !isGameWon) {
        drawGameEntities(g); // pass Graphics here instead of using panel.getGraphics()
    }
    }
    

public void drawGameEntities(Graphics g) {
    for (BrickInterface[] row : bricks) {
        for (BrickInterface brick : row) {
            if (brick != null && !brick.isDestroyed()) {
                brick.draw(); // bricks should accept Graphics parameter
            }
        }
    }
    for (Ball ball : balls) {
        ball.draw();
    }
    platform.draw();

    // Draw HUD
    g.setColor(Color.WHITE);
    g.setFont(new Font("Arial", Font.BOLD, 16));
    g.drawString("Score: " + score, 10, 20);
    g.drawString("Lives: " + lives, getWidth() - 80, 20);
}
}
