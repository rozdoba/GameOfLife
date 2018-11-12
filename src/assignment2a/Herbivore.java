/**
 * 
 */
package assignment2a;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.paint.Color;

/**
 * Herbivores “graze” by moving around the grid eating plants they find.
 * Herbivores must find a plant to eat before 5 “turns” have passed or they die. Herbivores move by 
 * checking neighboring cells and randomly picking one. They cannot move to a 
 * neighboring cell that contains a Herbivore. They move 1 cell per turn. 
 * @author Robert Ozdoba
 * @version 1.0
 */
public class Herbivore extends Organism implements Animal {
    
    protected static final int HUNGERCAP = 5;
    
    private int hungerLevel;
    
    public Herbivore(World.Cell cell) {
        
        super(cell);
        setColor(Color.YELLOW);
        setHungerLevel(0);
    }
    
    /**
     * Getter method that returns the current hungerLevel of the Herbivore
     * @return the hungerLevel of the Herbivore
     */
    public int getHungerLevel() {
        return hungerLevel;
    }

    /**
     * Setter method for the hungerLevel of the Herbivore
     * @param hungerLevel of the Herbivore to set
     */
    public void setHungerLevel(int hungerLevel) {
        this.hungerLevel = hungerLevel;
    }
    
    /**
     * Resets the hungerLevel back to 0. To be called whenever a Herbivore
     * eats a Plant.
     */
    public void resetHunger() {
        setHungerLevel(0);
    }
    
    /**
     * Moves the Herbivore to an adjacent cell, and increases its hungerLevel
     */
    protected void process() {
        
        if(!this.isProcessed()) {
            this.setProcessed(true);
            this.hungerLevel++;
            
            if(this.hungerLevel >= HUNGERCAP) {
                this.die();
                return;
                
            } else {
                this.scoutNeighborCells();
            }
        }
        
    }
    
    /**
     * Removes all Cells from the ArrayList that contain Herbivore organisms.
     * @param neighborList containing neighboring cells
     * @return the shortened neighborList
     */
    ArrayList<World.Cell> filterAnimals(ArrayList<World.Cell> neighborList) {
        
        Iterator<World.Cell> neighborIterator = neighborList.iterator();
        
        while(neighborIterator.hasNext()) {
            World.Cell cell = neighborIterator.next();
            if(cell.getOrganism() instanceof Animal) {
                neighborIterator.remove();
            }
        }
        return neighborList;
    }
    
    /**
     * Checks neighboring Cells, removing instances of other Herbivores. Then moves the 
     * Herbivore
     */
    public void scoutNeighborCells() {
        //get neighbor cells of current cell
        ArrayList<World.Cell> neighborList = this.cell.getNeighbors();
        
        // shorten list of neighboring cells by removing all cells containing Herbivore
        neighborList = filterAnimals(neighborList);
        if(neighborList.size() == 0) {
            return;
        }
        
        //generate random number to choose cell from list of neighbors
        int rand = RandomGenerator.nextNumber(neighborList.size());
        World.Cell randomCell = neighborList.get(rand);
     
        //if random neighbor cell contains a Plant
        if(randomCell.getOrganism() instanceof HerbivoreEdible) {
            
            //eat it
            this.eat(randomCell);
       
        } else {
            
            //cell is unoccupied, move freely  
            this.move(randomCell);
            
        }
        
    }
    
    /**
     * Moves the Herbivore's location into the passed in Cell
     * @param cell to eat
     */
    public void eat(World.Cell cell) {
        cell.organism.die();
        this.resetHunger();
        this.move(cell);
    }
    
    /**
     * Moves the Herbivore's location into the passed in Cell
     * @param cell
     */
    public void move(World.Cell cell) {
        cell.setOrganism(this);
        this.cell.setOrganism(null);
        this.cell = cell;
        
    }
    
    /**
     * Placeholder function for when Herbivores can reproduce.
     */
    protected void reproduce(World.Cell cell) {
        
    }
    
}
