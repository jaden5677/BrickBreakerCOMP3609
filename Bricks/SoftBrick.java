package Bricks;

import java.awt.Color;
import javax.swing.JPanel;
import Items.ItemInterface;
public class SoftBrick extends AbstractBrick {

    SoftBrick(int x, int y, int width, int height, JPanel panel) {
        super(x, y, width, height, panel);
        setColor(Color.BLUE); // Set the color of the soft brick
        super.pointValue = 100; // Set the point value for destroying a soft brick
    }
    SoftBrick(int x, int y, int width, int height, JPanel panel, ItemInterface item) {
        super(x, y, width, height, panel, item);
        setColor(Color.BLUE); // Set the color of the soft brick
        super.pointValue = 100; // Set the point value for destroying a soft brick
    }

    @Override
    public void destroy() {
        destroyed = true; // Mark the brick as destroyed
        super.onDestroy(); // Call the onDestroy method to handle item dropping and point awarding
    }
    
}
