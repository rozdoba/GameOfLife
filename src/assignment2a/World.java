/**
 * 
 */
package assignment2a;

import java.util.ArrayList;

/**
 * World of the Game Of Life that contains a grid of Column * Row Cells
 * each of which may possibly be empty or contain an Organism. The World will generate
 * a Herbivore 15% of the time and will generate a Plant 20% of the time. If neither 
 * Herbivores or Plants are generated within a cell, the Cell will remain empty until
 * a Plant seeds the cell, or until a Herbivore moves into a Cell.
 * @author Robert Ozdoba
 * @version 1.0
 */
public class World {
    
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
    class Cell {
        
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

        protected ArrayList<Cell> getNeighbors() {
            ArrayList<Cell> neighborList = new ArrayList<Cell>();
            
            //this.cell in top left corner of world
            if((posX == 0) && (posY == 0)) {
                neighborList.add(getCellAt(posX + 1, posY)); //E neighbor
                neighborList.add(getCellAt(posX + 1, posY + 1)); //SE neighbor
                neighborList.add(getCellAt(posX, posY + 1)); //S neighbor
     
            //this.cell on top edge of world
            } else if((posX > 0) && (posX < width - 1) && (posY == 0)) {
                neighborList.add(getCellAt(posX - 1, posY)); //W neighbor
                neighborList.add(getCellAt(posX + 1, posY)); //E neighbor
                neighborList.add(getCellAt(posX + 1, posY + 1)); //SE neighbor
                neighborList.add(getCellAt(posX, posY + 1)); //S neighbor
                neighborList.add(getCellAt(posX - 1, posY + 1)); //SW neighbor
                
            //this.cell in top right corner of world
            } else if((posX == width - 1) && (posY == 0)) {
                neighborList.add(getCellAt(posX - 1, posY)); //W neighbor
                neighborList.add(getCellAt(posX, posY + 1)); //S neighbor
                neighborList.add(getCellAt(posX - 1, posY + 1)); //SW neighbor
                
            //this.cell on right edge of world
            } else if((posX == width - 1) && (posY > 0) && (posY < height - 1)) {
                neighborList.add(getCellAt(posX - 1, posY)); //W neighbor
                neighborList.add(getCellAt(posX - 1, posY - 1)); //NW neighbor
                neighborList.add(getCellAt(posX, posY - 1)); //N neighbor
                neighborList.add(getCellAt(posX, posY + 1)); //S neighbor
                neighborList.add(getCellAt(posX - 1, posY + 1)); //SW neighbor
                
              //this.cell in bottom right corner of world
            } else if((posX == width - 1) && (posY == height - 1)) {
                neighborList.add(getCellAt(posX - 1, posY)); //W neighbor
                neighborList.add(getCellAt(posX - 1, posY - 1)); //NW neighbor
                neighborList.add(getCellAt(posX, posY - 1)); //N neighbor
                
              //this.cell on bottom edge world
            } else if((posX > 0) && (posX < width) && (posY == height - 1)) {
                neighborList.add(getCellAt(posX - 1, posY)); //W neighbor
                neighborList.add(getCellAt(posX - 1, posY - 1)); //NW neighbor
                neighborList.add(getCellAt(posX, posY - 1)); //N neighbor
                neighborList.add(getCellAt(posX + 1, posY - 1)); //NE neighbor
                neighborList.add(getCellAt(posX + 1, posY)); //E neighbor
              
              //this.cell in bottom left corner of world
            } else if((posX == 0) && (posY == height - 1)) {
                neighborList.add(getCellAt(posX, posY - 1)); //N neighbor
                neighborList.add(getCellAt(posX + 1, posY - 1)); //NE neighbor
                neighborList.add(getCellAt(posX + 1, posY)); //E neighbor
               
            //this.cell on left edge of world
            } else if((posX == 0) && (posY > 0) && (posY < height - 1)) {
                neighborList.add(getCellAt(posX, posY - 1)); //N neighbor
                neighborList.add(getCellAt(posX + 1, posY - 1)); //NE neighbor
                neighborList.add(getCellAt(posX + 1, posY)); //E neighbor
                neighborList.add(getCellAt(posX + 1, posY + 1)); //SE neighbor
                neighborList.add(getCellAt(posX, posY + 1)); //S neighbor
                
              //this.cell in middle of world
            } else {
                neighborList.add(getCellAt(posX - 1, posY - 1)); //NW neighbor
                neighborList.add(getCellAt(posX, posY - 1)); //N neighbor
                neighborList.add(getCellAt(posX + 1, posY - 1)); //NE neighbor
                neighborList.add(getCellAt(posX - 1, posY)); //W neighbor
                neighborList.add(getCellAt(posX + 1, posY)); //E neighbor
                neighborList.add(getCellAt(posX - 1, posY + 1)); //SW neighbor
                neighborList.add(getCellAt(posX, posY + 1)); //S neighbor
                neighborList.add(getCellAt(posX + 1, posY + 1)); //SE neighbor
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
        
        if(rand >= 85) {
            cell.setOrganism(new Herbivore(cell));
            
        } else if(rand >= 65) {
            cell.setOrganism(new Plant(cell));
            
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

