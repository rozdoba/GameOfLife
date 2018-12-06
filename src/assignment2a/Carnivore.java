/**
 * 
 */
package assignment2a;

/**
 * Carnivores are able to eat Herbivores and Omnivores. Carnivores can give birth
 * if there are at least 1 other Carnivore neighbor, at least 3 empty neighboring 
 * cells and at least 2 neighboring cells with food in them (Herbivores and Omnivores)
 * @author Robert Ozdoba
 * @version 2.0
 */
public class Carnivore extends Animal implements OmnivoreEdible {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Number of neighboring Carnivore needed to give birth.
     */
    public static final int MIN_MATES_TO_BIRTH = 1;
    
    /**
     * Number of empty neighbor cell needed to give birth.
     */
    public static final int MIN_EMPTY_NEIGHBORS_TO_BIRTH = 3;
    
    /**
     * Number of neighboring cells containing food (Carnivores and Omnivores and Herbivores), needed to give birth.
     */
    public static final int MIN_FOOD_NEIGHBORS_TO_BIRTH = 2;
    
    /**
     * Constructor for the Carnivore
     * @param cell to be occupied by the Carnivore
     */
    public Carnivore(World.Cell cell) {
        super(cell);
        setColorString("#FF0000");
        setMinMatesToBirth(MIN_MATES_TO_BIRTH);
        setMinEmptyNeighborsToBirth(MIN_EMPTY_NEIGHBORS_TO_BIRTH);
        setMinFoodNeighborsToBirth(MIN_FOOD_NEIGHBORS_TO_BIRTH);
    }
    
    /**
     * Returns true if passed in Organism is CarnivoreEdible, false otherwise.
     */
    protected boolean isEdible(Organism organism) {
        return organism instanceof CarnivoreEdible;
    }
    
    /**
     * Returns true if passed in Organism is a Carnivore, false otherwise.
     */
    public boolean isMateable(Organism organism) {
        return organism instanceof Carnivore;
    }
    
    /**
     * Creates another Organism in the passed in cell.
     * @param cell
     */
    protected void reproduce(World.Cell cell) {
        
        cell.setOrganism(new Carnivore(cell));
        cell.getOrganism().setProcessed(true);
    }
    
}
