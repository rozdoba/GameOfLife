/**
 * 
 */
package assignment2a;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.paint.Color;

/**
 * @author Robert Ozdoba
 * @version 1.0
 */
public class Herbivore extends Organism implements Animal {
    
    static final int HUNGERCAP = 5;
    
    private int hungerLevel;
    
    public Herbivore(World.Cell cell, int posX, int posY) {
        
        setCoordinates(posX, posY);
        setColor(Color.YELLOW);
        setProcessed(false);
        setHungerLevel(0);
        
        this.cell = cell;
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
        
        if(!isProcessed()) {
            if(this.hungerLevel >= HUNGERCAP) {
                die();
                return;
            } else {
                move();
                setHungerLevel(hungerLevel++);
            }
            setProcessed(true);
        }
        
    }

    /**
     * Removes all Cells from the ArrayList that contain Herbivore organisms.
     * @param neighborList containing neighbor cells
     * @return shortened neighborList devoid of Herbivore Organisms.
     */
    ArrayList<World.Cell> shortenNeighborList(ArrayList<World.Cell> neighborList) {
        
        Iterator<World.Cell> neighborIterator = neighborList.iterator();
        
        while(neighborIterator.hasNext()) {
            if(neighborIterator.next() instanceof Animal) {
                neighborIterator.remove();
            }
        }
        return neighborList;
    }
    
    /**
     * 
     */
    public void move() {
        //get neighbor cells of current cell
        ArrayList<World.Cell> neighborList = cell.getNeighbors();
        
        // shorten list of neighboring cells by removing all cells containing Herbivore
        neighborList = shortenNeighborList(neighborList);
        
        //generate random number to choose cell from list of neighbors
        int rand = RandomGenerator.nextNumber(neighborList.size());
        World.Cell randomCell = neighborList.get(rand);
     
        //if random neighbor cell contains a Plant
        if(randomCell.organism instanceof HerbivoreEdible) {
            
            //eat it
            eat(randomCell);
            resetHunger();
            setCoordinates(randomCell.getPosX(), randomCell.getPosY());
            randomCell.organism = this;
  
        //else, cell is unoccupied, move freely  
        } else {
            setCoordinates(randomCell.getPosX(), randomCell.getPosY());
            randomCell.organism = this;
            
        }
        
        die();
    }
    
    public void eat(World.Cell cell) {
        cell.organism.die();
    }
    
    public void procreate() {
        
    }
    
}
