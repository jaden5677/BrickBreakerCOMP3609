import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

public class Ball {
    int x;
    int y;
    int diameter;
    int xVelocity;
    int yVelocity;
    JPanel panel;
    private Color backgroundColour;;

    public Ball(int x, int y, int diameter, int xVelocity, int yVelocity, JPanel panel) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.panel = panel;
        this.backgroundColour = panel.getBackground();
    }
       public void erase () {
      Graphics g = panel.getGraphics ();
      Graphics2D g2 = (Graphics2D) g;

      // erase bat by drawing a ball on top of it

      g2.setColor (backgroundColour);
      g2.fill (new Ellipse2D.Double (x, y, diameter, diameter));

      g.dispose();
   }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.fill(new Ellipse2D.Double(x, y, diameter, diameter));
    }
    public void setVelocity(int xVelocity, int yVelocity) {
    this.xVelocity = xVelocity;
    this.yVelocity = yVelocity;
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
}
