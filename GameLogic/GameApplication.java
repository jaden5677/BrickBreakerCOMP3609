package GameLogic;

import javax.swing.SwingUtilities;
/*I dont know if this will be seen but I am Jaden Sooklal, I had intentions to implement items into the game, 
however, I could not figure out how in the timespan I had. AI was used solely for debugging because
I had some insane issues that I never would have guessed the problem for*/
public class GameApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainWindow();
        });
    }
}
