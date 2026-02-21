package Bricks;
import java.awt.Color;
import javax.swing.JPanel;
import Items.ItemInterface;
public class HardBrick extends AbstractBrick {
    public HardBrick(int x, int y, int width, int height,JPanel panel) {
        super(x, y, width, height, panel);
        setColor(Color.RED); // Set the color of the hard brick
        super.hitsRequired = 3; // Set the number of hits required to destroy the hard brick
        super.pointValue = 300; // Set the point value for destroying a hard brick
    }
    public HardBrick(int x, int y, int width, int height,JPanel panel, ItemInterface item) {
        super(x, y, width, height, panel, item);
        setColor(Color.RED); // Set the color of the hard brick
        super.hitsRequired = 3; // Set the number of hits required to destroy the hard brick
        super.pointValue = 300; // Set the point value for destroying a hard brick
    }
    
    @Override
    public void destroy() {
        hitsRequired--;
        if (hitsRequired <= 0) {
            destroyed = true; // Rememeber to check for this in main game loop to remove the brick from the game
            onDestroy(); // Call the onDestroy method to handle item dropping and point awarding
        } else if (hitsRequired == 2) {
            setColor(Color.ORANGE); // Change color after the first hit
        } else if (hitsRequired == 1) {
            setColor(Color.YELLOW); // Change color after the second hit
        }
    }
}
