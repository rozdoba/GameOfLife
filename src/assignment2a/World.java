/**
 * 
 */
package assignment2a;

import java.util.ArrayList;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @author Robert Ozdoba
 * @version 1.0
 */
public class World {
    
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
        public Organism organism;
        
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
     * World of the 
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
        
        for(int row = 0; row < this.height; row++) {
            for(int col = 0; col < this.width; col++) {
                
                this.grid[col][row] = new Cell(col, row);
                setOrganism(getCellAt(col, row), col, row);
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
    private void setOrganism(Cell cell, int posX, int posY) {
         
        int rand = RandomGenerator.nextNumber(99);
        
        if(rand >= 85) {
            cell.organism = new Herbivore(cell, posX, posY);
            
        } else if(rand >= 65) {
            cell.organism = new Plant(cell, posX, posY);
            
        } else {
            cell.organism = null;
        }
        
    }
    
    public Cell getCellAt(int posX, int posY) {
        return this.grid[posX][posY];
    }
    
    protected void takeTurn() {
        for(int row = 0; row < this.height; row++) {
            for(int col = 0; col < this.width; col++) {
                if(getCellAt(col,row).organism != null)
                    getCellAt(col, row).organism.process();
              
            }
        }  
    }
}

