package Bricks;
import java.awt.Color;
import javax.swing.JPanel;
import Items.ItemInterface;
public class HardBrick extends AbstractBrick {
    public HardBrick(int x, int y, int width, int height,JPanel panel) {
        super(x, y, width, height, panel);
        setColor(Color.RED); 
        super.hitsRequired = 3; 
        super.pointValue = 300; 
    }
    public HardBrick(int x, int y, int width, int height,JPanel panel, ItemInterface item) {
        super(x, y, width, height, panel, item);
        setColor(Color.RED);
        super.hitsRequired = 3;
        super.pointValue = 300; 
    }
    
    @Override
    public void destroy() {
        hitsRequired--;
        if (hitsRequired <= 0) {
            destroyed = true; 
            onDestroy(); 
        } else if (hitsRequired == 2) {
            setColor(Color.ORANGE);
        } else if (hitsRequired == 1) {
            setColor(Color.YELLOW); 
        }
    }
}
