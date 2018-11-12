/**
 * 
 */
package assignment2a;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.paint.Color;

/**
 * Plants do not move. Plant however will seed. Each plant will send seeds to 
 * a random neighboring empty cell assuming that there are at least 3 empty cells 
 * to send seeds to and there are exactly 4 other plants to help cross pollinate.
 * @author Robert Ozdoba
 * @version 1.0
 */
public class Plant extends Organism implements HerbivoreEdible {
    
    /**
     * Number of neighboring plants to help cross pollinate.
     */
    public static final int CROSS_POLLENATION_NUMBER = 4;
    
    /**
     * Number of neighboring empty cells required to send seed.
     */
    public static final int SEED_NUMBER = 3;
    
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
        
        if(!this.isProcessed()) {
            this.setProcessed(true);
            this.seed();
        }
        
    }
    
    
    /**
     * Removes all Cells from the ArrayList that are not Empty Cells.
     * @param neighborList containing neighboring Cells.
     * @return the shortened neighborList containing Empty Cells only.
     */
    ArrayList<World.Cell> emptyCellFilter(ArrayList<World.Cell> neighborList) {
        
        Iterator<World.Cell> neighborListIterator = neighborList.iterator();
        
        while(neighborListIterator.hasNext()) {
            
            World.Cell cell = neighborListIterator.next();
            if(cell.getOrganism() != null) {
                neighborListIterator.remove();
            }
        }
        return neighborList;
    }
    
    /**
     * Counts the number of neighboring Plants next to a viable empty cell
     * @param empty cell
     * @return number of Plant neighbors
     */
    protected int pollinatorCounter(World.Cell cell) {
        
        int pollinatorCount = 0;
        
        ArrayList<World.Cell> neighborList = cell.getNeighbors();
        Iterator<World.Cell> neighborListIterator = neighborList.iterator();
        
        while(neighborListIterator.hasNext()) {
            
            World.Cell emptyCell = neighborListIterator.next();
            
            if(emptyCell.getOrganism() instanceof Plant) {
                pollinatorCount++;
                
            }
        }
        
        return pollinatorCount;
    }
    
    
    /**
     * Removes all Cells from the ArrayList that are not Plant Organisms.
     * @param neighborList containing neighboring Cells.
     * @return shortened neighborList containing Plant Cells
     */
    ArrayList<World.Cell> fertileFilter(ArrayList<World.Cell> neighborList) {
        
        Iterator<World.Cell> neighborListIterator = neighborList.iterator();
        
        while(neighborListIterator.hasNext()) {
            
           World.Cell potentialCell = neighborListIterator.next();
           
           if(pollinatorCounter(potentialCell) != CROSS_POLLENATION_NUMBER) {
               neighborListIterator.remove();
           }
        }
        
        return neighborList;
    }
    
    /**
     * Sends seeds to a random neighboring cell only if there are at least 3 empty cells
     * to send seeds to, and there are exactly 4 other plants to help cross pollinate.
     */
    protected void seed() {
        
        ArrayList<World.Cell> neighborList = this.cell.getNeighbors();
        neighborList = emptyCellFilter(neighborList);
        
        if((neighborList.size() < SEED_NUMBER)) {
            return;
        }
        
        neighborList = fertileFilter(neighborList);
        if(neighborList.size() == 0) {
            return;
        }
        
        int rand = RandomGenerator.nextNumber(neighborList.size());
        World.Cell randomFertileCell = neighborList.get(rand);
        reproduce(randomFertileCell);
             
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
