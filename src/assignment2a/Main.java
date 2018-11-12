/**
 * 
 */
package assignment2a;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Driver class for the BoardGame class.
 * @author Robert Ozdoba
 * @version 1.0
 */
public class Main extends Application {

    public void start(Stage stage) {
        
        BoardGame game = new GameOfLife(50, 50);
        
        Scene scene = new Scene(game.getGridPane(), 500, 500);
        
        stage.setTitle("Game Of Life");
        stage.setScene(scene);
        stage.show();
    }
    
   
    
    public static void main(String arg[]) {
        
        Application.launch();
    }
    
}
