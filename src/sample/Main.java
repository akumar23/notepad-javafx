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
        
        //variables to hold the initial values of the text
        final StringBuilder color = new StringBuilder("-fx-text-inner-color: black;");
        final StringBuilder txtSize = new StringBuilder("-fx-font-size:12;");
        final StringBuilder style = new StringBuilder("-fx-font-style:normal;");
        final StringBuilder font = new StringBuilder("-fx-font-style:normal;");
        final StringBuilder highlight = new StringBuilder("-fx-highlight-fill: lightgray; -fx-highlight-text-fill: firebrick;");
        final StringBuilder themeColor = new StringBuilder("-fx-control-inner-background:white;");
        
        //creates a text area and sets its height and width 
        TextArea text = new TextArea();
        text.setPrefHeight(1000);
        text.setPrefWidth(500);
        
        
        
        
        //creates menus
        Menu fileMenu = new Menu("File");
        Menu fontType = new Menu("Font Type");
        Menu fontSizes = new Menu("Font Sizes");
        Menu colorMenu = new Menu("Font Color");
        Menu fontStyle = new Menu("Font Style");
        Menu highlightMenu = new Menu("Highlight Text");
        Menu themeMenu = new Menu("Themes");

        //creates menu items
        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem saveAsItem = new MenuItem("Save As");
        
        MenuItem serif = new MenuItem("Serif");
        MenuItem monspace = new MenuItem("Monospace");
        MenuItem impact = new MenuItem("Impact");
        MenuItem sans_serif = new MenuItem("Sans-Serif");
        
        MenuItem fontSize1 = new MenuItem("Size 12");
        MenuItem fontSize2 = new MenuItem("Size 14");
        MenuItem fontSize3 = new MenuItem("Size 20");
        MenuItem fontSize4 = new MenuItem("Size 30");
        
        MenuItem blackTxt = new MenuItem("black");
        MenuItem redTxt = new MenuItem("red");
        MenuItem blueTxt = new MenuItem("blue");
        
        MenuItem defaultStyle = new MenuItem("Default");
        MenuItem italicItem = new MenuItem("Italics");
        MenuItem boldItem = new MenuItem("Bold");
        
        MenuItem yellowHlt = new MenuItem("yellow");
        MenuItem redHlt = new MenuItem("red");
        MenuItem greenHlt = new MenuItem("green");
        MenuItem blueHlt = new MenuItem("blue");
        MenuItem blackHlt = new MenuItem("black");
        
        MenuItem whiteTheme = new MenuItem("white");
        MenuItem blackTheme = new MenuItem("black");
        
        //adds menu items to the appropriate menus
        fileMenu.getItems().add(newItem);
        fileMenu.getItems().add(openItem);
        fileMenu.getItems().add(saveItem);
        fileMenu.getItems().add(saveAsItem);
        
        fontType.getItems().add(serif);
        fontType.getItems().add(sans_serif);
        fontType.getItems().add(monspace);
        fontType.getItems().add(impact);
        
        fontSizes.getItems().add(fontSize1);
        fontSizes.getItems().add(fontSize2);
        fontSizes.getItems().add(fontSize3);
        fontSizes.getItems().add(fontSize4);
        
        colorMenu.getItems().add(blackTxt);
        colorMenu.getItems().add(redTxt);
        colorMenu.getItems().add(blueTxt);
        
        fontStyle.getItems().add(defaultStyle);
        fontStyle.getItems().add(italicItem);
        fontStyle.getItems().add(boldItem);
        
        highlightMenu.getItems().add(yellowHlt);
        highlightMenu.getItems().add(redHlt);
        highlightMenu.getItems().add(greenHlt);
        highlightMenu.getItems().add(blueHlt);
        highlightMenu.getItems().add(blackHlt);
        
        themeMenu.getItems().add(whiteTheme); 
        themeMenu.getItems().add(blackTheme);
               
        //creates a bar and adds menus to that bar
        MenuBar bar = new MenuBar();
        bar.getMenus().add(fileMenu);
        bar.getMenus().add(fontType);
        bar.getMenus().add(fontSizes);
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
        			color.delete(0,color.length());
        			color.append("-fx-text-inner-color: black;");
            		text.setStyle("-fx-text-inner-color: black;" + font.toString() + themeColor.toString() + style.toString() + txtSize.toString());
            	}
        });
        
        redTxt.setOnAction(
        	new EventHandler<ActionEvent>() {
        		@Override
        		public void handle(ActionEvent actionevent){
        			color.delete(0,color.length());
        			color.append("-fx-text-inner-color: red;");
        			text.setStyle("-fx-text-inner-color: red;" + font.toString() + themeColor.toString() + style.toString() + txtSize.toString());
        		}
        });
        
        blueTxt.setOnAction(
            new EventHandler<ActionEvent>() {
            	@Override
            	public void handle(ActionEvent actionevent){
        			color.delete(0,color.length());
        			color.append("-fx-text-inner-color: blue;");
            		text.setStyle("-fx-text-inner-color: blue;" + font.toString() + themeColor.toString() + style.toString() + txtSize.toString());
            	}
            });
        
        blackTheme.setOnAction(
        	new EventHandler<ActionEvent>() {
        		@Override
        		public void handle(ActionEvent actionevent) {
        			themeColor.delete(0, color.length());
        			themeColor.append("-fx-control-inner-background:black;");
        			color.delete(0,color.length());
        			color.append("-fx-text-inner-color: white;");
        			text.setStyle("-fx-control-inner-background:black;" + font.toString() + style.toString() + txtSize.toString() + color.toString());
        		}
        });
        
        whiteTheme.setOnAction(
            	new EventHandler<ActionEvent>() {
            		@Override
            		public void handle(ActionEvent actionevent) {
            			themeColor.delete(0, color.length());
            			themeColor.append("-fx-control-inner-background:white;");
            			color.delete(0,color.length());
            			color.append("-fx-text-inner-color: black;");
            			text.setStyle("text-area-background: red;" + font.toString() + style.toString() + txtSize.toString() + color.toString());
            		}
            });
        
        fontSize1.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				txtSize.delete(0,txtSize.length());
        				txtSize.append("-fx-font-size:12;");
        				text.setStyle(color.toString() + style.toString() + font.toString() + themeColor.toString() + "-fx-font-size:12;");
        			}
        			
        		}
        );
        
        fontSize2.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				txtSize.delete(0,txtSize.length());
        				txtSize.append("-fx-font-size:14;");
        				text.setStyle(color.toString() + style.toString() + font.toString() + themeColor.toString() + "-fx-font-size:14;");
        			}
        			
        		}
        );
        
        fontSize3.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				txtSize.delete(0,txtSize.length());
        				txtSize.append("-fx-font-size:20;");
        				text.setStyle(color.toString() + style.toString() + font.toString() + themeColor.toString() + "-fx-font-size:20;");
        			}
        			
        		}
        );
        
        fontSize4.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				txtSize.delete(0,txtSize.length());
        				txtSize.append("-fx-font-size:30;");
        				text.setStyle(color.toString() + style.toString() + font.toString() + themeColor.toString() + "-fx-font-size:30;");
        			}
        			
        		}
        );
        
        defaultStyle.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				style.delete(0,txtSize.length());
        				style.append("-fx-font-style:normal;");
        				text.setStyle(color.toString() + txtSize.toString() + font.toString() + themeColor.toString() + "-fx-font-style:normal;");
        			}
        			
        		}
        );
        
        
        serif.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				font.delete(0,txtSize.length());
        				font.append("-fx-font-style:serif;");
        				text.setStyle(color.toString() + txtSize.toString() + style.toString() + themeColor.toString() + "-fx-font-family:serif;");
        			}
        			
        		}
        );
        
        monspace.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				font.delete(0,txtSize.length());
        				font.append("-fx-font-style:monospace;");
        				text.setStyle(color.toString() + txtSize.toString() + style.toString() + themeColor.toString() + "-fx-font-family:monospace;");
        			}
        			
        		}
        );
        
        impact.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				font.delete(0,txtSize.length());
        				font.append("-fx-font-style:impact;");
        				text.setStyle(color.toString() + txtSize.toString() + style.toString() + themeColor.toString() + "-fx-font-family:impact;");
        			}
        			
        		}
        );
        
        sans_serif.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				font.delete(0,txtSize.length());
        				font.append("-fx-font-style:sans-serif;");
        				text.setStyle(color.toString() + txtSize.toString() + style.toString() + themeColor.toString() + "-fx-font-family:sans-serif;");
        			}
        			
        		}
        );
        
        italicItem.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				style.delete(0,txtSize.length());
        				style.append("-fx-font-style:italic;");
        				text.setStyle(color.toString() + txtSize.toString() + "-fx-font-style:italic;" + themeColor.toString() + font.toString());
        			}
        			
        		}
        );
        
        boldItem.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				style.delete(0,txtSize.length());
        				style.append("-fx-font-style:bold;");
        				text.setStyle(color.toString() + txtSize.toString() + "-fx-font-weight:bold;" + themeColor.toString() + font.toString());
        			}
        			
        		}
        );
        
        yellowHlt.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				highlight.delete(0,  highlight.length());
        				highlight.append("-fx-highlight-fill: yellow; -fx-highlight-text-fill: black;");
            			text.setStyle(color.toString() + font.toString() + themeColor.toString() + style.toString() + txtSize.toString() + highlight.toString());
                		text.selectRange(0, text.getLength());
        			}
        		}
        );
        
        redHlt.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				highlight.delete(0,  highlight.length());
        				highlight.append("-fx-highlight-fill: red; -fx-highlight-text-fill: black;");
            			text.setStyle(color.toString() + font.toString() + themeColor.toString() + style.toString() + txtSize.toString() + highlight.toString());
                		text.selectRange(0, text.getLength());
        			}
        		}
        );
        
        greenHlt.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				highlight.delete(0,  highlight.length());
        				highlight.append("-fx-highlight-fill: green; -fx-highlight-text-fill: white;");
            			text.setStyle(color.toString() + font.toString() + themeColor.toString() + style.toString() + txtSize.toString() + highlight.toString());
                		text.selectRange(0, text.getLength());
        			}
        		}
        );
        
        blueHlt.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				highlight.delete(0,  highlight.length());
        				highlight.append("-fx-highlight-fill: blue; -fx-highlight-text-fill: white;");
            			text.setStyle(color.toString() + font.toString() + themeColor.toString() + style.toString() + txtSize.toString() + highlight.toString());
                		text.selectRange(0, text.getLength());
        			}
        		}
        );
        
        blackHlt.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				highlight.delete(0,  highlight.length());
        				highlight.append("-fx-highlight-fill: black; -fx-highlight-text-fill: white;");
            			text.setStyle(color.toString() + font.toString() + themeColor.toString() + style.toString() + txtSize.toString() + highlight.toString());
                		text.selectRange(0, text.getLength());
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

