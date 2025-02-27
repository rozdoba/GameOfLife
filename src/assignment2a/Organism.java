/**
 * 
 */
package assignment2a;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * Organism is a superclass object Animal and Plant.
 * @author Robert Ozdoba
 * @version 2.0
 */
abstract class Organism implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Cell occupied by the organism
     */
    protected World.Cell cell;
    
    /**
     * 
     * Color of the Organism's cell in String format
     *
     */
    protected String colorString;
    
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
     * @return the colorString
     */
    protected String getColorString() {
        return colorString;
    }

    /**
     * @param colorString the colorString to set
     */
    protected void setColorString(String colorString) {
        this.colorString = colorString;
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
    protected void die() {
        this.cell.setOrganism(null);
        this.cell = null;
        
    }

    /**
     * 
     * @param neighborList
     * @return
     */
    protected int countMates(ArrayList<World.Cell> neighborList) {
        int mateCount = 0;
        
        for(World.Cell cell : neighborList) {
            if(isMateable(cell.getOrganism())) {
                mateCount++;
            }
        }
        
        return mateCount;
    }
    
    /**
     * 
     * @param neighborList
     * @return
     */
    protected int countEmpty(ArrayList<World.Cell> neighborList) {
        int emptyCount = 0;
        
        for(World.Cell cell : neighborList) {
            if(isEmpty(cell.getOrganism())) {
                emptyCount++;
            }
        }
        
        return emptyCount;
    }
    
    /**
     * 
     * @param neighborList
     * @return
     */
    protected int countFood(ArrayList<World.Cell> neighborList) {
        int foodCount = 0;
        
        for(World.Cell cell : neighborList) {
            if(isEdible(cell.getOrganism())) {
                foodCount++;
            }
        }
   
        return foodCount;
    }
    
    protected void giveBirth(int numMates, int numEmptyCells, int numFoodAvailable) {
        
        ArrayList<World.Cell> neighborList = cell.getNeighbors();
        
        if(     countMates(neighborList) >= numMates && 
                countEmpty(neighborList) >= numEmptyCells && 
                countFood(neighborList) >= numFoodAvailable) {
            
            neighborList = emptyCellFilter(neighborList);
            int rand = RandomGenerator.nextNumber(neighborList.size());
            
            World.Cell randomEmptyCell = neighborList.get(rand);
            reproduce(randomEmptyCell);
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
            if(!isEmpty(cell.getOrganism())) {
                neighborListIterator.remove();
            }
        }
        return neighborList;
    }
    
    /**
     * Processes the Organism for the turn. Organisms are processed in different ways.
     */
    protected abstract void process();
    
    protected abstract boolean isEdible(Organism organism);
    
    protected abstract boolean isMateable(Organism organism);
    
    protected boolean isEmpty(Organism organism) {
        return organism == null;
    }
    
    /**
     * Creates another Organism in the passed in cell.
     * @param cell
     */
    protected abstract void reproduce(World.Cell cell);
    
}
