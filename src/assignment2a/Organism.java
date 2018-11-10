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
    
    protected Color color;
    
    private boolean processed;
    
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
    
    /**
     * @return true if organism has been processed
     */
    protected boolean isProcessed() {
        return processed;
    }

    /**
     * @param set true if organism has been processed this turn
     */
    protected void setProcessed(boolean processed) {
        this.processed = processed;
    }
    
    public void die() {
        //this.cell.organism.setColor(Color.WHITE);
        this.cell.organism = null;
    }
    
    protected abstract void process();
    
}
