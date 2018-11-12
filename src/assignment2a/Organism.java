/**
 * 
 */
package assignment2a;

import javafx.scene.paint.Color;

/**
 * Organisms is a superclass object of Herbivore and Plant.
 * @author Robert Ozdoba
 */
abstract class Organism {
    
    /**
     * Cell occupied by the organism
     */
    protected World.Cell cell;
    
    /**
     * Color of the Organism's cell
     */
    private Color color;
    
    /**
     * Truth value for if the Organism has been processed within the turn.
     */
    private boolean processed;
    
    /**
     * Constructor for the Organism.
     * @param cell to be occupied by the Organism
     */
    public Organism(World.Cell cell) {
        
        this.cell = cell;
        processed = false;
    }

    /**
     * Getter method for color of the Organism.
     * @return
     */
    protected Color getColor() {
        return this.color;
    }
    
    /**
     * Setter method for the color of the Organism.
     * @param color
     */
    protected void setColor(Color color) {
       this.color = color;
    }
    
    /**
     * Getter method for the process value of the Organism
     * @return true if organism has been processed
     */
    protected boolean isProcessed() {
        return this.processed;
    }

    /**
     * Setter Method for the process value of the Organism.
     * @param set true if organism has been processed this turn
     */
    protected void setProcessed(boolean processed) {
        this.processed = processed;
    }
    
    /**
     * Removes the reference to the Cell this Organism occupies.
     */
    public void die() {
        this.cell.setOrganism(null);
        this.cell = null;
        
    }
    
    /**
     * Processes the Organism. Organisms are processed in different ways.
     */
    protected abstract void process();
    
    /**
     * Creates another Organism in the passed in cell.
     * @param cell
     */
    protected abstract void reproduce(World.Cell cell);
    
}
