/**
 * 
 */
package assignment2a;

import java.util.ArrayList;

import javafx.scene.paint.Color;

/**
 * @author Robert Ozdoba
 * @version 1.0
 */
public class Herbivore extends Organism implements Animal {
    
    static final int HUNGERCAP = 5; 
    
    public Herbivore(World.Cell cell, int posX, int posY) {
        
        setCoordinates(posX, posY);
        setColor(Color.YELLOW);
        
        this.cell = cell;
    }
    
    public void move() {
        
        ArrayList<World.Cell> neighborList = cell.getNeighbors();
        int rand = RandomGenerator.nextNumber(neighborList.size());
        World.Cell randomCell = neighborList.get(rand);
        
        //while random neighbor cell contains a Herbivore
        while(randomCell.organism instanceof Animal) {
            //generate new random neighbor
            rand = RandomGenerator.nextNumber(neighborList.size());
            randomCell = neighborList.get(rand);
            
        }
        
        //if random neighbor cell contains a Plant
        if(randomCell.organism instanceof HerbivoreEdible) {
            
            //eat it
            eat(randomCell);
            randomCell.organism = this;
            setCoordinates(randomCell.getPosX(), randomCell.getPosY());
        //else, cell is unoccupied, move freely  
        } else {
            randomCell.organism = this;
            setCoordinates(randomCell.getPosX(), randomCell.getPosY());
        }
    }
    
    public void eat(World.Cell cell) {
        
    }
    
    public void resetHunger() {
        hungerLevel = 0;
    }
    
    public void die() {
        
    }
    
    public void procreate() {
        
    }
    
}
