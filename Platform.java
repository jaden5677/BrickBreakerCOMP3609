import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

public class Platform {
    private JPanel panel;
    private int x;
    private int y;
    private int width;
    private int height;
    
    public Platform(int x, int y, int width, int height, JPanel panel) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.panel = panel;
    }
    
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        g2d.fill(new Rectangle2D.Double(x, y, width, height));
    }
    
}
