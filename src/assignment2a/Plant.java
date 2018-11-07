/**
 * 
 */
package assignment2a;

import javafx.scene.paint.Color;

/**
 * @author Robert Ozdoba
 * @version 1.0
 */
public class Plant extends Organism implements HerbivoreEdible {
    
    public Plant(World.Cell cell, int posX, int posY) {
        
        setCoordinates(posX, posY);
        setColor(Color.GREEN);
        
        this.cell = cell;
    }
}
