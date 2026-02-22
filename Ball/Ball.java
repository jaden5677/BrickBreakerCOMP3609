package Ball;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
public class Ball extends Thread{
    int x;
    int y;
    int diameter;
    int xVelocity;
    int yVelocity;
    JPanel panel;
    private Ellipse2D ball;
    private Image footballImage;
    private Color backgroundColour;;

    public Ball(int x, int y, int diameter, int xVelocity, int yVelocity, JPanel panel) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.panel = panel;
        this.backgroundColour = panel.getBackground();
        loadImage();
    }
    public void erase() {
        Graphics g = panel.getGraphics();
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(backgroundColour);
        g2.fill(new Ellipse2D.Double(x, y, diameter, diameter));
        g.dispose();
    }
    private void loadImage() {
    try {
        InputStream stream = getClass().getResourceAsStream("FootBall.png");
        if (stream == null) {
            System.out.println("Image not found, using fallback.");
            footballImage = null;
            return;
        }
        footballImage = ImageIO.read(stream);
        footballImage = footballImage.getScaledInstance(diameter, diameter, Image.SCALE_SMOOTH);
    } catch (IOException e) {
        System.out.println("Could not load football image, falling back to red circle.");
        footballImage = null;
    }
}

    public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    ball = new Ellipse2D.Double(x, y, diameter, diameter);
    if (footballImage != null) {
        g2d.drawImage(footballImage, x, y, diameter, diameter, null);
    } else {
        g2d.setColor(Color.RED);
        g2d.fill(ball);
    }
    }

    public void setVelocity(int xVelocity, int yVelocity) {
    this.xVelocity = xVelocity;
    this.yVelocity = yVelocity;
    }
    public int getXVelocity(){
        return xVelocity;
    }
    public int getYVelocity(){
        return yVelocity;
    }

    public void move() {
    int panelWidth = panel.getWidth();
    int panelHeight = panel.getHeight();

    x += xVelocity;
    y += yVelocity;

    if (x < 0) {
        x = 0;
        xVelocity = -xVelocity;
    } else if (x + diameter > panelWidth) {
        x = panelWidth - diameter;
        xVelocity = -xVelocity;
    }

    if (y < 0) {
        y = 0;
        yVelocity = -yVelocity;
    } else if (y + diameter > panelHeight) {
        y = panelHeight - diameter;
        yVelocity = -yVelocity;
    }
    }
    public Ellipse2D getBounds() {
    return new Ellipse2D.Double(x, y, diameter, diameter);
}
    public void run() {
        while (true) {
            move();
            try {
                Thread.sleep(30); // Adjust the sleep time for smoother or faster movement
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public int getY(){return this.y;}
}

