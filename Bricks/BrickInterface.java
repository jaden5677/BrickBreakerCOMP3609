package Bricks;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics;
public interface BrickInterface {
    void draw(Graphics g);
    void erase();
    void move();
    boolean isDestroyed();
    void destroy();
    void setColor(java.awt.Color color);
    int onDestroy();
    int getPointValue();
    void setItem(Items.ItemInterface item);
    Rectangle2D getBrickBounds();
    boolean hasItem();
}
