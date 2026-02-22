package Platform;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

public class Platform {
    private JPanel panel;
    private int x;
    private int y;
    private int width;
    private int height;
    private int moveSpeed = 20; // Speed at which the platform moves
    private Color backgroundcColour;
    private Rectangle2D.Double platformRect;
    
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
    g2d.fillRect(x, y, width, height);
}

    public void erase() {
        Graphics g = panel.getGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(panel.getBackground());
        g2d.fill(new Rectangle2D.Double(x, y, width, height));
        g.dispose();
    }

    public void move(int direction) {
        int pW = panel.getWidth();

      if (!panel.isVisible ()) {return;} else this.erase();

      if ((direction == 1) && (x>=10)) {	// move left
          x = x - moveSpeed;
      }
      else
      if ((direction == 2) && (x<=pW)) {  	// move right
          x = x + moveSpeed;
      }

    }

    public void setMoveSpeed(int speed) {
        this.moveSpeed = speed;
    }
    public Rectangle2D.Double getBounds() {
        return platformRect;
    }
    
}
