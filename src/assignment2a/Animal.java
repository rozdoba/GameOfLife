/**
 * 
 */
package assignment2a;

/**
 * @author Robert Ozdoba
 *
 */
public interface Animal {
    
    /**
     * Moves the location of this Animal into the cell passed into the parameter, while
     * at the same time eating the Organism that originally occupied the cell.
     * @param cell
     */
    void eat(World.Cell cell);

}
