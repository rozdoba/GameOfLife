/**
 * 
 */
package assignment2a;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Abstract class that includes methods to move, eat, and choosePosition to move into for all Animals. 
 * Contains setters to set the minimum Mates, EmptyNeighbors, and FoodNeigbors in order to give birth.
 * as well as a getter and setter for the hungerLevel of the Animal.
 * @author Robert Ozdoba
 * @version 2.0
 */
abstract class Animal extends Organism {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * The maximum number of turns an Organism can go without eating food before it dies.
     */
    public static final int HUNGERCAP = 5;
    
    /**
     * The amount of turns the Organism has gone without eating food.
     */
    private int hungerLevel;
    
    /**
     * The minimum number of neighboring Cells containing mate-able Organisms for this Organism to giveBirth()
     */
    private int minMatesToBirth;
    
    /**
     * The minimum number of neighboring empty Cells for this Organism to giveBirth()
     */
    private int minEmptyNeighborsToBirth;
    
    /**
     * The minimum number of neighboring Cells containing edible Organisms for this Organism to giveBirth()
     */
    private int minFoodNeighborsToBirth;
    
    /**
     * Constructor method for Animal.
     * @param cell containing this Animal
     */
    public Animal(World.Cell cell) {
        super(cell);
        setHungerLevel(0);
    }

    /**
     * Getter method that returns the current hungerLevel of the Herbivore
     * @return the hungerLevel of the Herbivore
     */
    protected int getHungerLevel() {
        return hungerLevel;
    }

    /**
     * Setter method for the hungerLevel of the Herbivore
     * @param hungerLevel of the Herbivore to set
     */
    protected void setHungerLevel(int hungerLevel) {
        this.hungerLevel = hungerLevel;
    }
    
    /**
     * Resets the hungerLevel back to 0. To be called whenever a Herbivore
     * eats a Plant.
     */
    protected void resetHunger() {
        setHungerLevel(0);
    }
    
    /**
     * Increases the current hungerLevel by 1.
     */
    protected void increaseHungerLevel() {
        this.hungerLevel++;
    }
    
    /**
     * Setter method for the mateNumber
     * @param mateNumber the mateNumber to set
     */
    protected void setMinMatesToBirth(int minMatesToBirth) {
        this.minMatesToBirth = minMatesToBirth;
    }
    
    /**
     * Setter method for the emptyNeighborNumber
     * @param emptyNeighborNumber the emptyNeighborNumber to set
     */
    protected void setMinEmptyNeighborsToBirth(int minEmptyNeighborsToBirth) {
        this.minEmptyNeighborsToBirth = minEmptyNeighborsToBirth;
    }

    /**
     * Setter method for the foodNeighborNumber
     * @param foodNeighborNumber the foodNeighborNumber to set
     */
    protected void setMinFoodNeighborsToBirth(int minFoodNeighborsToBirth) {
        this.minFoodNeighborsToBirth = minFoodNeighborsToBirth;
    }

    /**
     * Checks if this Organism has already made a turn (been processed). If not, increases hungerLevel by 1 and checks
     * to see if the Organism has reached its hunger cap in which case it dies. The Organism gives birth and chooses a move
     * position.
     */
    protected void process() {
        
        if(!this.isProcessed()) {
            this.setProcessed(true);
            increaseHungerLevel();
            
            if(getHungerLevel() >= HUNGERCAP) {
                this.die();
                return;
                
            }
            
            giveBirth(minMatesToBirth, minEmptyNeighborsToBirth, minFoodNeighborsToBirth);
            chooseMovePosition();
            
        }
    }
    
    /**
     * Filters the ArrayList of Cells for organisms that are edible by the organism calling this function.
     * @param neighborList containing neighboring Cells
     * @return the shortened neighborlist containing only edible organisms
     */
    protected ArrayList<World.Cell> foodFilter(ArrayList<World.Cell> neighborList) {
        
        Iterator<World.Cell> edibleListIterator = neighborList.iterator();
        
        while(edibleListIterator.hasNext()) {
            
               World.Cell cell = edibleListIterator.next();
               if(isEmpty(cell.getOrganism()) || !isEdible(cell.getOrganism())) {
                   edibleListIterator.remove();
            }
        }
        
        return neighborList;
        
    }
    
    /**
     * Filters the ArrayList of Cells for Cells that this organism can move into. Moveable cells are cells that
     * are empty or Cells that contain an edible Organism
     * @param neighborList containing neighboring Cells
     * @return the shortened neighborlist containing only moveable Cells
     */
    protected ArrayList<World.Cell> moveableCellFilter(ArrayList<World.Cell> neighborList) {
        
        Iterator<World.Cell> movableListIterator = neighborList.iterator();
        
        while(movableListIterator.hasNext()) {
            
               World.Cell cell = movableListIterator.next();
               
               if(isEmpty(cell.getOrganism())) {
                   continue;
               }
               
               if(!isEdible(cell.getOrganism())) {
                   movableListIterator.remove();
            }
        }
        
        return neighborList;
        
    }
    
    /**
     * Checks to see if this Organism has neighboring Cells containing edible Organisms, and eats a random neighboring edible Organism
     * @return true if the neighboring Cells contain at-least 1 Cell with an edible Organism, false otherwise
     */
    protected boolean prioritizeFood() {
        
        ArrayList<World.Cell> edibleNeighborList = cell.getNeighbors();
        edibleNeighborList = foodFilter(edibleNeighborList);
        
        if(edibleNeighborList.size() == 0) {
            return false;
        }
        
        int rand = RandomGenerator.nextNumber(edibleNeighborList.size());
        World.Cell randomCell = edibleNeighborList.get(rand);
        this.eat(randomCell);
        return true;
    }
    
    
    /**
     * Checks to see if this Organism has neighboring Cells containing empty or edible Organisms. Eats a random neighboring edible Organism
     * or moves into a random neighboring empty cell.
     */
    protected void chooseMovePosition() {
        
        ArrayList<World.Cell> moveableNeighborList = cell.getNeighbors();
        moveableNeighborList = moveableCellFilter(moveableNeighborList);
        
        if(moveableNeighborList.size() == 0) {
            return;
        }
             
        int rand = RandomGenerator.nextNumber(moveableNeighborList.size());
        World.Cell randomCell = moveableNeighborList.get(rand);
        
        if(isEdible(randomCell.getOrganism())) {
            this.eat(randomCell);
        }
        
        if(isEmpty(randomCell.getOrganism())) {
            this.move(randomCell);
        }
    }
    
    /**
     * Moves the Herbivore's location into the passed in Cell
     * @param cell to eat
     */
    protected void eat(World.Cell cell) {
        cell.organism.die();
        this.resetHunger();
        this.move(cell);
    }
    
    /**
     * Moves the Herbivore's location into the passed in Cell
     * @param cell
     */
    protected void move(World.Cell cell) {
        cell.setOrganism(this);
        this.cell.setOrganism(null);
        this.cell = cell;
    }
}
