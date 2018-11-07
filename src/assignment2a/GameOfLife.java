/**
 * 
 */
package assignment2a;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

/**
 * @author Robert Ozdoba
 *
 */
public class GameOfLife extends BoardGame {
    
    public GameOfLife(int width, int height) {
        
        super.setWidth(width);
        super.setHeight(height);
        super.world = new World(width, height);
        
        this.pane = new GridPane();
        pane.setGridLinesVisible(true);
        drawGridPane();
    }
    
    /**
     * Generates the GridPane 
     */
    public void drawGridPane() {
        
        for(int row = 0; row < this.getHeight(); row++) {
            for(int col = 0; col < this.getWidth(); col++) {
                
                if(world.getCellAt(col, row).organism != null) {
                    Circle circle = new Circle();
                    circle.setRadius(5.0);
                    circle.setFill(world.getCellAt(col, row).organism.getColor());
                    this.pane.add(circle, col, row);
                }
            }
        }
    }
    
    public void nextTurn(ActionEvent event) {
       pane.getChildren().removeAll();
       drawGridPane();
    }

}
