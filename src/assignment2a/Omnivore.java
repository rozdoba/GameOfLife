/**
 * 
 */
package assignment2a;

import javafx.scene.paint.Color;

/**
 * Omnivores eat Herbivores, Carnivores, and Plants and can give birth if there are at least 1 other Omnivore neighbors,
 * at least 3 free neighboring cells, and 1 neighboring cells with food (Herbivores, Carnivores, Plants)
 * @author Robert Ozdoba
 * @version 1.0
 */
public class Omnivore extends Animal implements CarnivoreEdible {
    
    /**
     * Number of neighboring Carnivore needed to give birth.
     */
    public static final int MIN_MATES_TO_BIRTH = 1;
    
    /**
     * Number of empty neighbor cell needed to give birth.
     */
    public static final int MIN_EMPTY_NEIGHBORS_TO_BIRTH = 3;
    
    /**
     * Number of neighboring cells containing food (Carnivores and Herbivores, Plants), needed to give birth.
     */
    public static final int MIN_FOOD_NEIGHBORS_TO_BIRTH = 1;
    
    /**
     * Constructor for the Omnivore
     * @param cell to be occupied by the Omnivore
     */
    public Omnivore(World.Cell cell) {
        super(cell);
        setColor(Color.BLUE);
        setMinMatesToBirth(MIN_MATES_TO_BIRTH);
        setMinEmptyNeighborsToBirth(MIN_EMPTY_NEIGHBORS_TO_BIRTH);
        setMinFoodNeighborsToBirth(MIN_FOOD_NEIGHBORS_TO_BIRTH);
    }
    
    /**
     * Returns true if passed in Organism is OmnivorEdible, false otherwise.
     */
    protected boolean isEdible(Organism organism) {
        return organism instanceof OmnivoreEdible;
    }
    
    /**
     * Returns true if passed in Organism is a Omnivore, false otherwise
     */
    public boolean isMateable(Organism organism) {
        return organism instanceof Omnivore;
    }
    
    protected void reproduce(World.Cell cell) {
        
        cell.setOrganism(new Omnivore(cell));
        cell.getOrganism().setProcessed(true);
    }

}
