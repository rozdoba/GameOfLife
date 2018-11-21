/**
 * 
 */
package assignment2a;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Robert Ozdoba
 * @version 1.0
 */
abstract class Animal extends Organism {

    public static final int HUNGERCAP = 5;
    
    /**
     * The amount of turns the Organism has gone without eating food.
     */
    private int hungerLevel;
    
    private int minMatesToBirth;
    
    private int minEmptyNeighborsToBirth;
    
    private int minFoodNeighborsToBirth;
    
    /**
     * @param cell
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

    protected void process() {
        
        if(!this.isProcessed()) {
            this.setProcessed(true);
            increaseHungerLevel();
            
            if(getHungerLevel() >= HUNGERCAP) {
                this.die();
                return;
                
            }
            
            giveBirth(minMatesToBirth, minEmptyNeighborsToBirth, minFoodNeighborsToBirth);
            choosePosition();
        }
    }
    
    /**
     * Removes all Cells from the ArrayList that contain Herbivore organisms.
     * @param neighborList containing neighboring cells
     * @return the shortened neighborList
     */
    ArrayList<World.Cell> moveableCellFilter(ArrayList<World.Cell> neighborList) {
        
        Iterator<World.Cell> neighborIterator = neighborList.iterator();
        
        while(neighborIterator.hasNext()) {
            World.Cell cell = neighborIterator.next();
            
            if(isEmpty(cell.getOrganism())) {
                continue;
            }
            
            if(!isEdible(cell.getOrganism())) { 
                    neighborIterator.remove();
            }
        }
        
        return neighborList;
    }
    
    protected void choosePosition() {
        ArrayList<World.Cell> neighborList = cell.getNeighbors();
        neighborList = moveableCellFilter(neighborList);
        
        if(neighborList.size() == 0) {
            return;
        }
        
        int rand = RandomGenerator.nextNumber(neighborList.size());
        World.Cell randomCell = neighborList.get(rand);
        
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
