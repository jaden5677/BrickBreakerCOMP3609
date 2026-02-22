package Bricks;
import java.util.Random;
import Items.*;
public class BrickFactory {
    ItemFactory itemFactory = new ItemFactory();
    public static BrickInterface createBrick(String type, int x, int y, int width, int height, javax.swing.JPanel panel) {
        switch (type) {
            case "Soft":
                return new SoftBrick(x, y, width, height, panel);
            case "Regular":
                return new RegularBrick(x, y, width, height, panel);
            case "Hard":
                return new HardBrick(x, y, width, height, panel);
            default:
                throw new IllegalArgumentException("Unknown brick type: " + type);
        }
    }
    
    public static BrickInterface createRandomBrick(int x, int y, int width, int height, javax.swing.JPanel panel) {
        String[] types = {"Soft", "Regular", "Hard"};
        Random rand = new Random();
        int value = rand.nextInt(6);
        Random itemChance = new Random();
        BrickInterface brick;
        int itemValue = itemChance.nextInt(4); // 1 in 10 chance of an item dropping
        if((value >0) && (value<3)){
            brick = createBrick(types[0], x, y, width, height, panel);
        } else if((value >=3) && (value<5)){
            brick = createBrick(types[1], x, y, width, height, panel);
        } else {
            brick = createBrick(types[2], x, y, width, height, panel);
        }
        if(itemValue == 0){
            ItemInterface item = ItemFactory.createRandomItem();
            brick.setItem(item);
        }
        return brick;
    }
    public static BrickInterface[][] createBrickGrid(int rows, int cols, int brickWidth, int brickHeight, javax.swing.JPanel panel) {
        BrickInterface[][] grid = new BrickInterface[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int x = j * brickWidth;
                int y = i * brickHeight;
                grid[i][j] = createRandomBrick(x, y, brickWidth, brickHeight, panel);
            }
        }
        return grid;
    }

    
}
