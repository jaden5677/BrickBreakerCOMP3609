package Items;

public class ItemFactory {
    public static ItemInterface createItem(String itemType) {
        switch (itemType) {
            case "SpeedUpBalls":
                return new SpeedUpBalls();
            case "MultiplyBall":
                return new MultiplyBall();
            case "SlowDownBalls":
                return new SlowDownBalls();
            case "PointMultiply":
                return new PointMultiply(0, 0, 0, 0, null, 1); // Default values for PointMultiply
            default:
                throw new IllegalArgumentException("Unknown item type: " + itemType);
        }
    }
    public static ItemInterface createRandomItem() {
        String[] itemTypes = {"SpeedUpBalls", "MultiplyBall", "SlowDownBalls", "PointMultiply"};
        int randomIndex = (int) (Math.random() * itemTypes.length);
        return createItem(itemTypes[randomIndex]);
    }
    
}
