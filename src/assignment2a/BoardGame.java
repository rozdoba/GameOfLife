/**
 * 
 */
package assignment2a;

import java.io.Serializable;

import javafx.scene.layout.GridPane;

/**
 * BoardGame which contains a reference to World and dimensions of the Board.
 * Has a GridPane object to display the Board. 
 * @author Robert Ozdoba
 * @version 2.0
 */
public abstract class BoardGame implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * GridPane that BoardGame is displayed on
     */
    protected GridPane gridPane;
    
    /**
     * Width of the board
     */
    private int width;
    
    /**
     * Height of the board
     */
    private int height;
    
    /**
     * Number of turns taken
     */
    protected int turns;
    
    /**
     * Constructor for the BoardGame.
     */
    public BoardGame() {
        gridPane = new GridPane();
    }
    
    /**
     * Getter method for the GridPane
     * @return the pane
     */
    protected GridPane getGridPane() {
        return gridPane;
    }

    /**
     * Setter method for the GridPane
     * @param pane the pane to set
     */
    protected void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
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
     */
    public abstract void nextTurn();
    
}
