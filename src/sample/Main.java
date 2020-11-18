package sample;

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

import javax.swing.JTextArea;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Main class for the functions of the notepad
 */
public class Main extends Application {

    /**
     * Function to create the start page for the notepad
     * @param primaryStage - the primary stage for the canvas for the notepad
     * @throws Exception - an exception in the event that open file or save file throws an error
     */
    @Override
    public void start(Stage primaryStage) throws Exception{

        //sets title for notepad
        primaryStage.setTitle("Untitled");
        
        //final String color = "-fx-text-inner-color: black;";
        
        //creates a text area and sets its height and width 
        TextArea text = new TextArea();
        text.setPrefHeight(1000);
        text.setPrefWidth(500);
       
        text.setStyle("-fx-font: fantasy");
        
        //creates menus
        Menu fileMenu = new Menu("File");
        Menu fontMenu = new Menu("Font Size");
        Menu colorMenu = new Menu("Font Color");
        Menu fontStyle = new Menu("Font Style");
        Menu highlightMenu = new Menu("Highlight Text");
        Menu themeMenu = new Menu("Themes");

        //creates menu items
        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem saveAsItem = new MenuItem("Save As");
        
        MenuItem fontItem1 = new MenuItem("Size 12");
        MenuItem fontItem2 = new MenuItem("Size 14");
        MenuItem fontItem3 = new MenuItem("Size 20");
        MenuItem fontItem4 = new MenuItem("Size 30");
        
        MenuItem blackTxt = new MenuItem("black");
        MenuItem redTxt = new MenuItem("red");
        MenuItem blueTxt = new MenuItem("blue");
        
        MenuItem italicItem = new MenuItem("Italics");
        MenuItem boldItem = new MenuItem("Bold");
        
        MenuItem blackTheme = new MenuItem("black");
        MenuItem redTheme = new MenuItem("red");

        //adds menu items to the appropriate menus
        fileMenu.getItems().add(newItem);
        fileMenu.getItems().add(openItem);
        fileMenu.getItems().add(saveItem);
        fileMenu.getItems().add(saveAsItem);
        
        fontMenu.getItems().add(fontItem1);
        fontMenu.getItems().add(fontItem2);
        fontMenu.getItems().add(fontItem3);
        fontMenu.getItems().add(fontItem4);
        
        colorMenu.getItems().add(blackTxt);
        colorMenu.getItems().add(redTxt);
        colorMenu.getItems().add(blueTxt);
        
        fontStyle.getItems().add(italicItem);
        fontStyle.getItems().add(boldItem);
        
        themeMenu.getItems().add(blackTheme);
        themeMenu.getItems().add(redTheme);        

        //creates a bar and adds menus to that bar
        MenuBar bar = new MenuBar();
        bar.getMenus().add(fileMenu);
        bar.getMenus().add(fontMenu);
        bar.getMenus().add(colorMenu);
        bar.getMenus().add(fontStyle);
        bar.getMenus().add(highlightMenu);
        bar.getMenus().add(themeMenu);

        //creates a vbox and adds the elements of the bar and text to it
        VBox box = new VBox(bar, text);
        
        //makes a set on action method for newItem to create a new blank canvas to type on
        newItem.setOnAction(
        	new EventHandler<ActionEvent>() {
        		@Override
        		public void handle(ActionEvent actionEvent){
        			
        			//clears the canvas
        			text.clear();
        			
        			//sets title back to Untitled 
        			primaryStage.setTitle("Untitled");
        		}
        });

        //declares a file chooser for the choose file option to choose a file from the file menu
        final FileChooser chooser = new FileChooser();
        
        //makes a set on action method for openItem
        openItem.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                    	
                    	//make a file equal to what is chosen from the open dialog of the file menu
                        File f = chooser.showOpenDialog(primaryStage);
                        
                        //checks that the file isn't null
                        if(f != null){
                        	
                        	//sets the name of the canvas to the file's name
                            primaryStage.setTitle(f.getName());
                            
                            //gets all the lines in the text file that is being opened
                            ArrayList<String> txt = read(f);
                            
                            //clears the canvas
                            text.clear();
                            
                            //prints the lines from the text file onto the canvas
                            for(String line:txt) {
                            	text.appendText(line + "\n");
                            }
                            
                        }
                    }
                }
        );
        
        /*
         * return to this
         */
        if(primaryStage.getTitle() != "Untitled") {
        	saveItem.setOnAction(
        		new EventHandler<ActionEvent>() {
        			@Override
        			public void handle(ActionEvent actionEvent){
        				System.out.print("lol");
        			}
        	});
        }
        
        //declares another file chooser for the save option to save the file using the file menu
        FileChooser saver = new FileChooser();
        
        //makes a set on action method for saveItem
        saveAsItem.setOnAction((ActionEvent a) -> {
        	
        	//creates a filter for the FileChooser so that only the txt extension can be used
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            saver.getExtensionFilters().add(extFilter);
            
            //makes a file equal to what is choose from the save dialog of the file menu
            File f = saver.showSaveDialog(primaryStage);
            
            //checks that the file isn't null
            if(f != null){
            	
            	//sets the title of the canvas to the file's name
            	primaryStage.setTitle(f.getName());
            	
            	//calls a function to save the current text on the canvas to the file
            	saveFile(text.getText(), f);
            }
        });

        blackTxt.setOnAction(
            new EventHandler<ActionEvent>() {
            	@Override
            	public void handle(ActionEvent actionevent){
            		text.setStyle("-fx-text-inner-color: black;");
            	}
        });
        
        redTxt.setOnAction(
        	new EventHandler<ActionEvent>() {
        		@Override
        		public void handle(ActionEvent actionevent){
        			text.setStyle("-fx-text-inner-color: red;");
        		}
        });
        
        blueTxt.setOnAction(
            new EventHandler<ActionEvent>() {
            	@Override
            	public void handle(ActionEvent actionevent){
            		text.setStyle("-fx-text-inner-color: blue;");
            	}
            });
        
        blackTheme.setOnAction(
        	new EventHandler<ActionEvent>() {
        		@Override
        		public void handle(ActionEvent actionevent) {
        			text.setStyle("text-area-background: black;");
        		}
        });
        
        redTheme.setOnAction(
            	new EventHandler<ActionEvent>() {
            		@Override
            		public void handle(ActionEvent actionevent) {
            			text.setStyle("text-area-background: red;");
            		}
            });
        
        fontItem1.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				text.setStyle("-fx-font-size:12");
        			}
        			
        		}
        );
        
        fontItem2.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				text.setStyle("-fx-font-size:14");
        			}
        			
        		}
        );
        
        fontItem3.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				text.setStyle("-fx-font-size:20");
        			}
        			
        		}
        );
        
        fontItem4.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				text.setStyle("-fx-font-size:30");
        			}
        			
        		}
        );
        
        italicItem.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				text.setStyle("-fx-font-style:italic");
        			}
        			
        		}
        );
        
        boldItem.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				text.setStyle("-fx-font-weight:bold");
        			}
        			
        		}
        );

        primaryStage.setScene(new Scene(box, 500, 500));
        primaryStage.show();
    }
    
    /**
     * function that reads a file and saves its text to an ArrayList and returns that ArrayList
     * @param file - the file that this function will read from
     * @return - an ArrayList made up of lines from the file that is passed in
     */
    public ArrayList<String> read(File file){
    	
    	//ArrayList to hold lines from the file
    	ArrayList<String> txtLine = new ArrayList<String>();
    	
    	//string to keep position as the function loops through the strings in the file 
    	String currentLine;
    	try {
    		
    		//a BufferedReader to read through a file
    		BufferedReader br = new BufferedReader(new FileReader(file));
    		
    		//loops through the file until currentLine hits null
    		while((currentLine = br.readLine()) != null) {
    			
    			//adds the currentLine in the file to the ArrayList 
    			txtLine.add(currentLine);
    		}
    		
    		//closes the BufferedReader
    		br.close();
    	} catch (IOException e) {
    		System.out.println(e.getMessage());
    	}
    	
    	//returns the ArrayList
    	return txtLine;
    }
    
    /**
     * function that saves the current text on the canvas to a file
     * @param txt - the current text on the canvas
     * @param file - the file that the text is going to be saved to
     */
    public void saveFile(String txt, File file){
    	try {
    		FileWriter f = new FileWriter(file);
    		f.write(txt);
    		f.close();
    	} catch (IOException e) {
    		System.out.println(e.getMessage());
    	}
    }

    //main function to launch the notepad
    public static void main(String[] args) {
        launch(args);
    }
}

