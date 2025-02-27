/**
 * 
 */
package assignment2a;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * World of the Game Of Life that contains a grid of Column * Row Cells
 * each of which may possibly be empty or contain an Organism. The World will generate
 * a Herbivore 20% of the time, will generate a Plant 40% will generate a Carnivore 50% of the time 
 * and will generate an Omnivore 55% of the time. If no Organism is generated within a cell, 
 * the Cell will remain empty until a Plant seeds the cell, or until a Herbivore moves into a Cell.
 * @author Robert Ozdoba
 * @version 2.0
 */
public class World implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Value used to seen the RandomGenerator.
     */
    public static final int RANDOM_SEED_VALUE = 99;
    
    /**
     * Holds a 2D array of Cell objects
     */
    public Cell grid[][];
    
    /**
     * Holds the height of the grid that GameOfLife is played on
     */
    private final int height;
    
    /**
     * Holds the width of the grid that GameOfLife is played on
     */
    private final int width;
    
    /**
     * @return the height
     */
    protected int getHeight() {
        return height;
    }

    /**
     * @return the width
     */
    protected int getWidth() {
        return width;
    }

    /**
     * Inner class of World. A cell has x and y coordinates as well as holds a 
     * reference to an Organism object that occupies the Cell.
     * @author Robert Ozdoba
     * @version 1.0
     */
    class Cell implements Serializable {
        
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        /**
         * X coordinate of the Cell
         */
        private final int posX;
        /**
         * Y coordinate of the Cell
         */
        private final int posY;
        /**
         * Organism which occupies the cell
         */
        protected Organism organism;
        
        /**
         * Constructor for cell that takes position coordinates and 
         * sets organism reference to null.
         * @param posX of the Cell
         * @param posY of the Cell
         */
        public Cell(int posX, int posY) {
          
            this.posX = posX;
            this.posY = posY;
            this.organism = null;
            
        }
        
        /**
         * Getter method that returns the x-coordinate of the cell.
         */
        protected int getPosX() {
            return this.posX;
        }
        
        /**
         * Getter method that returns the y-coordinate of the cell.
         */
        protected int getPosY() {
            return this.posY;
        }
        
        /**
         * Getter method that returns the Organism referenced by the Cell.
         * @return the Organism referenced by the cell.
         */
        protected Organism getOrganism() {
            return organism;
        }

        /**
         * Setter method that sets the Organism referenced to by the Cell.
         * @param the Organism referenced by the cell.
         */
        protected void setOrganism(Organism organism) {
            this.organism = organism;
        }

        /**
         * This method finds all the neighboring cells and puts them into an array list
         * @return ArrayList<Cell> containing all neighboring cells
         */
        protected ArrayList<Cell> getNeighbors() {
            ArrayList<Cell> neighborList = new ArrayList<Cell>();
          
            for(int row = -1; row <= 1; row++) {
                for(int col = -1; col <= 1; col++) {
                    if(row == 0 && col == 0) {
                        continue; //this.cell is not a neighbor 
                    } 
                    
                    if( (posX + col >= 0)
                    &&  (posX + col < width)
                    &&  (posY + row >= 0)
                    &&  (posY + row < height)) {
                        neighborList.add(getCellAt((posX + col), (posY + row)));
                    }
                }
            }
            return neighborList;
            
        }

    }
    
    /**
     * Constructor for the World
     * @param width of the World 
     * @param height of the World
     */
    public World(int width, int height) {
        
        this.width = width;
        this.height = height;
        this.grid = new Cell[width][height];
        populateWorld();
        
    }
    
    /**
     * Iterates through the coordinates of the 2D Board and populates the World with Cells 
     * at each coordinate. Cells are also assigned to reference organisms.
     */
    private void populateWorld() {
        RandomGenerator.reset();
        for(int row = 0; row < height; row++) {
            for(int col = 0; col < width; col++) {
                
                this.grid[col][row] = new Cell(col, row);
                generateOrganism(getCellAt(col, row), col, row);
            }
        }
        
    }

    /**
     * Instantiates an organism depending on the random number generated by the RandomGenerator
     * class. Herbivores have a 15% chance to be instantiated, Plants have a 20% chance to be
     * instantiated. 
     * @param cell
     * @param posX
     * @param posY
     */
    private void generateOrganism(Cell cell, int posX, int posY) {
        int rand = RandomGenerator.nextNumber(RANDOM_SEED_VALUE);
        
        if(rand >= 80) {
            cell.setOrganism(new Herbivore(cell));
            
        } else if(rand >= 60) {
            cell.setOrganism(new Plant(cell));
            
        } else if(rand >= 50) {
            cell.setOrganism(new Carnivore(cell));
            
        } else if(rand >= 45) {
            cell.setOrganism(new Omnivore(cell));
            
        } else {
            cell.setOrganism(null);
        }
        
    }
    
    /**
     * Returns the Cell at the coordinates (posX, posY)
     * @param posX x-axis coordinate
     * @param posY y-axis coordinate
     * @return Cell at the given coordinates
     */
    public Cell getCellAt(int posX, int posY) {
        return this.grid[posX][posY];
    }
    
    /**
     * Processes each Cell of the World.
     */
    protected void takeTurn() {
        for(int row = 0; row < height; row++) {
            for(int col = 0; col < width; col++) {
                
                if(getCellAt(col, row).getOrganism() != null)
                    getCellAt(col, row).getOrganism().process();
              
            }
        }  
    }
}

