/**
 * 
 */
package assignment2a;

/**
 * Herbivores graze by moving around the grid eating plants they find.
 * Herbivores must find a plant to eat before 5 turns have passed or they die. Herbivores move by 
 * checking neighboring cells and randomly picking one. They cannot move to a 
 * neighboring cell that contains a Herbivore. They move 1 cell per turn. 
 * @author Robert Ozdoba
 * @version 2.0
 */
public class Herbivore extends Animal implements CarnivoreEdible, OmnivoreEdible {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Number of neighboring Herbivores needed to give birth.
     */
    public static final int MIN_MATES_TO_BIRTH = 1;
    
    /**
     * Number of empty neighbor cell needed to give birth.
     */
    public static final int MIN_EMPTY_NEIGHBORS_TO_BIRTH = 2;
    
    /**
     * Number of neighboring cells containing food (Plants), needed to give birth.
     */
    public static final int MIN_FOOD_NEIGHBORS_TO_BIRTH = 2;
    
    /**
     * Constructor for the Herbivore.
     * @param cell to be occupied by the Herbivore
     */
    public Herbivore(World.Cell cell) {
        
        super(cell);
        setColorString("#FFFF00");
        setMinMatesToBirth(MIN_MATES_TO_BIRTH);
        setMinEmptyNeighborsToBirth(MIN_EMPTY_NEIGHBORS_TO_BIRTH);
        setMinFoodNeighborsToBirth(MIN_FOOD_NEIGHBORS_TO_BIRTH);
    }
    
    /**
     * Checks if this Herbivore has already made a turn (been processed). If not, increases hungerLevel by 1 and checks
     * to see if the Herbivore has reached its hunger cap in which case it dies. The Herbivore gives birth and chooses a move
     * position prioritizing edible Cells first
     */
    protected void process() {
        
        if(!this.isProcessed()) {
            this.setProcessed(true);
            increaseHungerLevel();
            
            if(getHungerLevel() >= HUNGERCAP) {
                this.die();
                return;
                
            }
            
            giveBirth(MIN_MATES_TO_BIRTH, MIN_EMPTY_NEIGHBORS_TO_BIRTH, MIN_FOOD_NEIGHBORS_TO_BIRTH);
            if(!prioritizeFood()) {
                chooseMovePosition();
            }
        }
    }
    
    /**
     * Returns true if passed in Organism is HerbivoreEdible, false otherwise.
     */
    protected boolean isEdible(Organism organism) {
        return organism instanceof HerbivoreEdible;
    }
    
    /**
     * Returns true if passed in Organism is a Herbivore, false otherwise
     */
    public boolean isMateable(Organism organism) {
        return organism instanceof Herbivore;
    }
    
    /**
     * Placeholder function for when Herbivores can reproduce.
     */
    protected void reproduce(World.Cell cell) {
        
        cell.setOrganism(new Herbivore(cell));
        cell.getOrganism().setProcessed(true);
    }
    
}
