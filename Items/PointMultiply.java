package Items;
import java.awt.Graphics;
public class PointMultiply extends AbstractItem {
    private int multiplier;

    public PointMultiply(int x, int y, int width, int height, javax.swing.JPanel panel, int multiplier) {
        //super(x, y, width, height, panel);
        this.multiplier = multiplier;
    }

    @Override
    public void draw(Graphics g) {
        // Implement drawing logic for the PointMultiply item
    }

    @Override
    public void useItem() {
        // Implement logic to multiply points when the item is used
    }
    
}
