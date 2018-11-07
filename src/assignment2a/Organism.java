/**
 * 
 */
package assignment2a;

import java.util.ArrayList;

import javafx.scene.paint.Color;

/**
 * @author Robert Ozdoba
 */
abstract class Organism {
    
    protected World.Cell cell;
    
    private int posX;
    
    private int posY;
    
    public Color color;
    
    protected int hungerLevel = 0;
    
    /**
     * Getter method that gets the x-coordinate of the Organism
     * @return posX of the Organism
     */
    protected int getPosX() {
        return posX;
    }

    /**
     * Getter method that gets the y-coordinate of the Organism
     * @return posY of the Organism
     */
    protected int getPosY() {
        return posY;
    }
    
    /**
     * Sets the (x, y) coordinates of the Organism
     * @param posX of the Organism
     * @param posY of the Organism
     */
    protected void setCoordinates(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    protected Color getColor() {
        return color;
    }
    
    protected void setColor(Color color) {
       this.color = color;
    }
    
}
