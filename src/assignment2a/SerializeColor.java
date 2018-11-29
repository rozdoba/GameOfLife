/**
 * 
 */
package assignment2a;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.scene.paint.Color;

/**
 * @author Robert Ozdoba
 *
 */
@SuppressWarnings("serial")
public class SerializeColor implements Serializable {

    private transient Color color;
    
    public SerializeColor(Color color) {
        this.color = color;
    }
    
    /**
     * 
     * @param objOut
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream objOut) throws IOException {
        objOut.writeDouble(color.getRed());
        objOut.writeDouble(color.getGreen());
        objOut.writeDouble(color.getBlue());
        objOut.writeDouble(color.getOpacity());
    }
    
    /**
     * @param objIn
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream objIn) throws IOException, ClassNotFoundException {
        double red = objIn.readDouble();
        double green = objIn.readDouble();
        double blue = objIn.readDouble();
        double opacity = objIn.readDouble();
        
        color = new Color(red, green, blue, opacity);
    }
    
    
    public static void writeColor(Color color, ObjectOutputStream objOut) throws IOException {
        objOut.writeDouble(color.getRed());
        objOut.writeDouble(color.getGreen());
        objOut.writeDouble(color.getBlue());
        objOut.writeDouble(color.getOpacity());
    }

    public static Color readColor(ObjectInputStream objIn) throws IOException {
        double red = objIn.readDouble();
        double green = objIn.readDouble();
        double blue = objIn.readDouble();
        double opacity = objIn.readDouble();
        
        return (new Color(red, green, blue, opacity));
    }
}
