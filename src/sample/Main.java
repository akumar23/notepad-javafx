package sample;

package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.control.TextArea;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Main class for the functions of the notepad
 */
public class Main extends Application {

    private Desktop d = Desktop.getDesktop();

    /**
     * Function to create the start page for the notepad
     * @param primaryStage - the primary stage for the canvas for the notepad
     * @throws Exception - an exception in the event that open file or save file throws an error
     */
    @Override
    public void start(Stage primaryStage) throws Exception{

        //sets title for notepad
        primaryStage.setTitle("notepad");

        //creates a text area
        TextArea text = new TextArea();
        //TextField text = new TextField();
        
        
        //text.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        //Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
        
        //uncomment this if you return to text area
        text.setPrefHeight(1000);
        text.setPrefWidth(500);
        
        //creates menus
        Menu fileMenu = new Menu("File");
        Menu fontMenu = new Menu("Font Size");
        Menu highlightMenu = new Menu("Highlight Text");

        //creates menu items
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem fontItem1 = new MenuItem("Size 12");
        MenuItem fontItem2 = new MenuItem("Size 14");
        MenuItem fontItem3 = new MenuItem("Size 20");
        MenuItem fontItem4 = new MenuItem("Size 30");

        //adds menu items to the appropriate menus
        fileMenu.getItems().add(openItem);
        fileMenu.getItems().add(saveItem);
        fontMenu.getItems().add(fontItem1);
        fontMenu.getItems().add(fontItem2);
        fontMenu.getItems().add(fontItem3);
        fontMenu.getItems().add(fontItem4);

        //creates a bar and adds menus to that bar
        MenuBar bar = new MenuBar();
        bar.getMenus().add(fileMenu);
        bar.getMenus().add(fontMenu);
        bar.getMenus().add(highlightMenu);

        //creates a vbox and adds the elements of the bar and text to it
        VBox box = new VBox(bar, text);

        //declares a file chooser for the choose file option
        final FileChooser chooser = new FileChooser();
        
        //makes a set on action method for openItem
        openItem.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        File file = chooser.showOpenDialog(primaryStage);
                        if(file != null){
                            //openFile(file);
                            ArrayList<String> txt = read(file);
                            text.clear();
                            for(String line:txt) {
                            	text.appendText(line + "\n");
                            }
                        }
                    }
                }
        );

        //declares another file chooser for the save option
        FileChooser saver = new FileChooser();
        
        //opens the file menu allowing the user to save the text file under a custom name
        saveItem.setOnAction((ActionEvent a) -> {
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            saver.getExtensionFilters().add(extFilter);
            
            File f = saver.showSaveDialog(primaryStage);
            if(f != null){
            	saveFile(text.getText(), f);
            }
        });
        


        primaryStage.setScene(new Scene(box, 500, 500));
        primaryStage.show();
    }

    public ArrayList<String> read(File file){
    	ArrayList<String> txtLine = new ArrayList<String>();
    	String currentLine;
    	try {
    		
    		BufferedReader br = new BufferedReader(new FileReader(file));
    		while((currentLine = br.readLine()) != null) {
    			txtLine.add(currentLine);
    		}
    		br.close();
    		
    	} catch (IOException e) {
    		
    		System.out.println(e.getMessage());
    		
    	}
    	
    	return txtLine;
    }
    
    private void openFile(File file){
        try {
            d.open(file);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    //main function to launch the notepad
    public static void main(String[] args) {
        launch(args);
    }
}


