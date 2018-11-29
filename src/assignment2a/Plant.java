/**
 * 
 */
package assignment2a;

import javafx.scene.paint.Color;

/**
 * Plants do not move. Plant however will seed. Each plant will send seeds to 
 * a random neighboring empty cell assuming that there are at least 3 empty cells 
 * to send seeds to and there are exactly 4 other plants to help cross pollinate.
 * @author Robert Ozdoba
 * @version 2.0
 */
public class Plant extends Organism implements HerbivoreEdible {
    
    /**
     * Number of neighboring Plants needed to give birth.
     */
    public static final int MIN_MATES_TO_BIRTH = 2;
    
    /**
     * Number of neighboring empty cells required to send seed.
     */
    public static final int MIN_EMPTY_NEIGHBORS_TO_BIRTH = 3;
    
    /**
     * Number of neighboring cells containing food, needed to give birth.
     */
    public static final int MIN_FOOD_NEIGHBORS_TO_BIRTH = 0;
    
    /**
     * Plant constructor takes in cell
     * @param cell
     * @param posX
     * @param posY
     */
    public Plant(World.Cell cell) {
        
        super(cell);
        setColor(Color.GREEN);
    }
    
    /**
     * Processes the Plant for the current turn.
     */
    protected void process() {
        
        if(!(this.isProcessed())) {
            this.setProcessed(true);
            this.giveBirth(MIN_MATES_TO_BIRTH, MIN_EMPTY_NEIGHBORS_TO_BIRTH, MIN_FOOD_NEIGHBORS_TO_BIRTH);
        }
    }
    
    protected boolean isEdible(Organism organism) {
        return false;
    }
    
    /**
     * 
     * @return
     */
    protected boolean isMateable(Organism organism) {
        return organism instanceof Plant;
    }
    
    /**
     * Creates a new Plant Organism in the passed in Cell, and sets its
     * process value to true so that it cannot seed in the turn it was created.
     */
    protected void reproduce(World.Cell cell) {
        
        cell.setOrganism(new Plant(cell));
        cell.getOrganism().setProcessed(true);
    }
}
