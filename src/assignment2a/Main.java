/**
 * 
 */
package assignment2a;

import java.awt.Desktop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * Driver class for the BoardGame class.
 * @author Robert Ozdoba
 * @version 1.0
 */
public class Main extends Application {
    
    /**
     * The BoardGame that will be created using JavaFX
     */
    BoardGame game;
    
    /**
     * 
     */
    FileChooser fileChooser;
    
    /**
     * Method that opens a serialized BoardGame file
     * @param file
     * @throws IOException
     */
    private void openFile(File file) throws IOException {
        try {
            
            FileInputStream fileIn = new FileInputStream(file.getPath());
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            ((GameOfLife) game).setWorld((World) objIn.readObject());
            
            objIn.close();
           
        } catch (IOException e) {
            e.printStackTrace();
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Method that saves a BoardGame at the particular step of turns.
     * @param file
     * @throws IOException
     */
    private void saveFile(File file) throws IOException {
        try {
            
            FileOutputStream fileOut = new FileOutputStream(file.getPath());
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(((GameOfLife) game).getWorld());
            objOut.flush();
            objOut.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     */
    public void start(Stage stage) {
        
        game = new GameOfLife(50, 50);
      
        Menu fileMenu = new Menu("File");
        
        MenuItem open = new MenuItem("Open");
        open.setOnAction(new EventHandler<ActionEvent>() {
            
            public void handle(ActionEvent event) {
                
                fileChooser = new FileChooser();
                fileChooser.setTitle("Open Game File");
                fileChooser.getExtensionFilters().add(new ExtensionFilter("BoardGame Files", "*.ser"));
                File gameFile = fileChooser.showOpenDialog(stage);
                
                if(gameFile != null) {
                    try {
                        openFile(gameFile);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    
                }
            }
        });
        
        MenuItem save = new MenuItem("Save");
        save.setOnAction(new EventHandler<ActionEvent>() {
            
            public void handle(ActionEvent event) {
                fileChooser = new FileChooser();
                fileChooser.setTitle("Save Game File");
                fileChooser.getExtensionFilters().add(new ExtensionFilter("BoardGame Files", "*.ser"));
                File gameFile = fileChooser.showSaveDialog(stage);
                
                if (gameFile != null) {
                    try {
                        saveFile(gameFile);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });
        
        fileMenu.getItems().addAll(open, save);
        
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(fileMenu);
        
        VBox vBox = new VBox(menuBar, game.getGridPane());
        
        Scene scene = new Scene(vBox, 500, 525);
        
        stage.setTitle("Game Of Life");
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * 
     * @param arg
     */
    public static void main(String arg[]) {
        
        Application.launch();
    }
    
}
