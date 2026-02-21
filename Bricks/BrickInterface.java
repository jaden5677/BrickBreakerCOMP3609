package Bricks;
import java.awt.Graphics;
public interface BrickInterface {
    void draw(Graphics g);
    void move();
    boolean isDestroyed();
    void destroy();
    void setColor(java.awt.Color color);
    int onDestroy();
    int getPointValue();
}
