package Bricks;

import java.awt.Color;
import javax.swing.JPanel;
import Items.ItemInterface;
public class RegularBrick extends AbstractBrick {
    public RegularBrick(int x, int y, int width, int height, JPanel panel) {
        super(x, y, width, height, panel);
        setColor(java.awt.Color.GREEN); // Set the color of the regular brick
        super.pointValue = 100; // Set the point value for destroying a regular brick
    }
    public RegularBrick(int x, int y, int width, int height, JPanel panel, ItemInterface item) {
        super(x, y, width, height, panel, item);
        setColor(Color.GREEN); // Set the color of the regular brick
        super.pointValue = 100; // Set the point value for destroying a regular brick
        super.hitsRequired = 2;
    }
    
    @Override
    public void destroy() {
        hitsRequired--;
        if (hitsRequired <= 0) {
            destroyed = true;
        } else if (hitsRequired == 1) {
            setColor(Color.YELLOW); // Change color after the first hit
        } else if (hitsRequired == 0) {
            setColor(Color.ORANGE); // Change color after the second hit
        } else{
            super.erase();
        }
        super.onDestroy(); // Call the onDestroy method to handle item dropping and point awarding
    }
    
}
