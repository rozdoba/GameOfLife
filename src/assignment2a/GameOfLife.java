/**
 * 
 */
package assignment2a;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
/**
 * GameOfLife is a type of Board Game. The GameOfLife starts off with Plants and Herbivores (plant eaters) on a grid. 
 * The grid displays the plants (green) and Herbivores (yellow) by filling in the squares where they are found. 
 * Blank squares represent empty areas. Herbivores “graze” by moving around the grid eating plants they find. 
 * Herbivores must find a plant to eat before 5 “turns” have passed or they die. A “turn” is a step in time which occurs 
 * when a user clicks anywhere on the window displaying the world. Herbivores move by checking neighboring cells
 * and randomly picking one. They cannot move to a neighboring cell that contains a Herbivore. They move 1 cell per turn. 
 * Plants do not move. Plant however will seed. Each plant will send seeds to a random neighboring empty cell assuming that 
 * there are at least 3 empty cells to send seeds to and there are exactly 4 other plants to help cross pollenate.
 * @author Robert Ozdoba
 * @version 1.0
 */
public class GameOfLife extends BoardGame {
    
    /**
     * Mouse event handler that performs the next turn of the GameOfLife whenever
     * the mouse is clicked.
     */
    protected EventHandler<MouseEvent> processMouseClicked = new EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {
            nextTurn();
        }
    };
    
    /**
     * Constructor for the GameOfLife. Sets the Board dimensions and adds an event handler
     * to the GridPane.
     * @param width of the Board.
     * @param height of the Board.
     */
    public GameOfLife(int width, int height) {
        
        setWidth(width);
        setHeight(height);
        setWorld(new World(width, height));
        
        gridPane.addEventHandler(MouseEvent.MOUSE_CLICKED, processMouseClicked);
        drawGridPane();
        
    }
    
    /**
     * Generates the GridPane with coloring in the Cells if the pane with different colors depending on
     * what is occupied by the current cell.
     */
    public void drawGridPane() {
        
        for(int row = 0; row < this.getHeight(); row++) {
            for(int col = 0; col < this.getWidth(); col++) {
                
                Rectangle rectangle = new Rectangle(10, 10);
                rectangle.setStrokeType(StrokeType.INSIDE);
                rectangle.setStroke(Color.BLACK);
                
                if(world.getCellAt(col, row).getOrganism() != null) {
                    
                    world.getCellAt(col, row).getOrganism().setProcessed(false);
                    rectangle.setFill(world.getCellAt(col, row).getOrganism().getColor());
                   
                } else {
                    rectangle.setFill(Color.TRANSPARENT);
                }
                getGridPane().add(rectangle, col, row);
            }
        }
    }
    
    /**
     * Takes the next turn of the GameOfLife.
     */
    public void nextTurn() {
        
        //System.out.println(++turns);
        gridPane.getChildren().clear();
        world.takeTurn();
        drawGridPane();
    }
    
}
