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
 * You are going to create a simulation of a simple world. Often referred to as The game of Life (not related to the Parker Brothers board game). 
 * The world starts off with Plants and Herbivores, Carnivores and Omnivores on a grid. 
 * The grid displays the plants (green) and Herbivores (yellow) etc by filling in the squares where they are found. Blank squares represent empty areas. 
 * Herbivores graze by moving around the grid eating plants they find. Herbivores must find a plant to eat before 5 turns 
 * have passed or they die. A turn is a step in time which occurs when a user clicks anywhere on the window displaying the world. 
 * @author Robert Ozdoba
 * @version 2.0
 */
public class GameOfLife extends BoardGame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * World that contains Cells which are populated with Organisms
     */
    protected World world;
    
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
     * Getter method for the World
     * @return World associated with the BoardGame
     */
    public World getWorld() {
        return this.world;
    }
    
    /**
     * Setter method for the World
     * @param World associated with the BoardGame
     */
    protected void setWorld(World world) {
        this.world = world;
    }

    
    /**
     * Generates the GridPane with coloring in the Cells if the pane with different colors depending on
     * what is occupied by the current cell.
     */
    protected void drawGridPane() {
        
        for(int row = 0; row < this.getHeight(); row++) {
            for(int col = 0; col < this.getWidth(); col++) {
                
                Rectangle rectangle = new Rectangle(10, 10);
                rectangle.setStrokeType(StrokeType.INSIDE);
                rectangle.setStroke(Color.BLACK);
                
                if(world.getCellAt(col, row).getOrganism() != null) {
                    
                    world.getCellAt(col, row).getOrganism().setProcessed(false);
                    rectangle.setFill(Color.web(this.world.getCellAt(col, row).getOrganism().getColorString()));
                   
                } else {
                    rectangle.setFill(Color.TRANSPARENT);
                }
                getGridPane().add(rectangle, col, row);
            }
        }
    }
    
    /**
     * Loads the GridPane after opening a saved GameOfLife de-serialized file.
     */
    protected void loadGridPane() {
        gridPane.getChildren().clear();
        drawGridPane();
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
