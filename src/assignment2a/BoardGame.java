/**
 * 
 */
package assignment2a;

import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;

/**
 * @author Robert Ozdoba
 *
 */
public abstract class BoardGame {
    
    protected World world;
    
    /**
     * GridPane that BoardGame is displayed on
     */
    GridPane pane;
    
    /**
     * Width of the board
     */
    private int width;
    
    /**
     * Height of the board
     */
    private int height;
    
    /**
     * Getter method for the world
     * @return world associated with the BoardGame
     */
    public World getWorld() {
        return this.world;
    }
    
    /**
     * Getter method for height
     * @return height of the board
     */
    public int getHeight() {
        return this.height;
    }
    
    /**
     * Getter method for width
     * @return width of the board
     */
    public int getWidth() {
        return this.width;
    }
    
    /**
     * Setter method for the height of the board
     * @param height of the board
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
    /**
     * Setter method for the width of the board
     * @param width of the width
     */
    public void setWidth(int width) {
        this.width = width;
    }
    
    /**
     * Performs the next turn of the BoardGame
     * @param event that triggers the next turn
     */
    public abstract void nextTurn(ActionEvent event);
    
}
