/**
 * 
 */
package assignment2a;

/**
 * @author Robert Ozdoba
 *
 */
public interface Animal {
    
    public void eat(World.Cell cell);
    
    void die();
    
    void procreate();
    
    void resetHunger();

}
