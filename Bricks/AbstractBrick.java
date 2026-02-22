package Bricks;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import Items.ItemInterface;

public abstract class AbstractBrick implements BrickInterface {
    protected JPanel panel;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean destroyed;
    public Color color;
    protected ItemInterface item;
    protected boolean hasItem; 
    protected int pointValue;
    protected int hitsRequired; 
    public AbstractBrick(int x, int y, int width, int height, JPanel panel) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.panel = panel;
        this.destroyed = false;
        this.hasItem = false;
    }
    public AbstractBrick(int x, int y, int width, int height, JPanel panel, ItemInterface item) {
        this(x, y, width, height, panel);
        this.item = item;
        this.hasItem = true;
        this.destroyed = false;
        this.hasItem = true;
    }
    
    public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(color);
    g2d.fill(new Rectangle2D.Double(x, y, width, height));
}
    public void setColor(Color color) {
        this.color = color;
    }
    public void setItem(ItemInterface item) {
        this.item = item;
        this.hasItem = true;
    }
    public ItemInterface getItem() {
        return item;
    }
    public boolean hasItem() {
        return hasItem;
    }
    public int onDestroy() {
        return pointValue;
    }
    public int getPointValue() {
        return pointValue;
    }
    public void erase() {
        Graphics g = panel.getGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(panel.getBackground());
        g2d.fill(new Rectangle2D.Double(x, y, width, height));
        g.dispose();
    }
    public void destroy() {

        destroyed = true;

    }
    public boolean isDestroyed() {
        return destroyed;
    }
    public void move() {
        if(!destroyed) {
            erase();
        }
        int panelHeight = panel.getHeight();
        if (y + height < panelHeight) {
            y += height;
        } else {
            destroy();
        }
        
        y += height;
    }
    public Rectangle2D getBrickBounds() {
    return new Rectangle2D.Double(x, y, width, height);
    }

    
}
