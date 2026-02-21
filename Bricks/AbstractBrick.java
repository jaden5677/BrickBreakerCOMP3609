package Bricks;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import Items.ItemInterface;

public abstract class AbstractBrick extends JPanel implements BrickInterface {
    private JPanel panel;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean destroyed;
    private Color color;
    private ItemInterface item; // Optional item that can be dropped when the brick is destroyed
    private boolean hasItem; // Flag to indicate if the brick has an item
    private int pointValue;
    public AbstractBrick(int x, int y, int width, int height, JPanel panel) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.panel = panel;
    }
    public AbstractBrick(int x, int y, int width, int height, JPanel panel, ItemInterface item) {
        this(x, y, width, height, panel);
        this.item = item;
        this.hasItem = true;
    }
    
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GREEN);
        g2d.fill(new Rectangle2D.Double(x, y, width, height));
    }
    public void setColor(Color color) {
        this.color = color;
    }
    
    public ItemInterface getItem() {
        return item;
    }
    public boolean hasItem() {
        return hasItem;
    }
    public void onDestroy() {
        // This method can be overridden by subclasses to perform specific actions when the brick is destroyed
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
        erase();
    }
    public boolean isDestroyed() {
        return destroyed;
    }
    public void move() {
        // Bricks move downwards by one brick height  each time move is called
        if(!destroyed) {
            erase();
        }
        int panelHeight = panel.getHeight();
        if (y + height < panelHeight) {
            y += height;
        } else {
            // If the brick reaches the bottom of the panel, it is considered destroyed
            destroy();
        }
        
        y += height; // Move the brick down by one brick height
    }
    
}
