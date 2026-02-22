package Items;
import java.awt.Graphics;
public class AbstractItem implements ItemInterface {

    public AbstractItem() {
     // Default constructor for AbstractItem
    }
    
    @Override
    public void draw(Graphics g) {
        // Default implementation for drawing the item
    }

    @Override
    public void useItem() {
        // Default implementation for using the item
    }
}
