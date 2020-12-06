package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;
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
        Menu themeMenu = new Menu("Themes");

        //creates menu items
        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        
        MenuItem serif = new MenuItem("Serif");
        MenuItem monspace = new MenuItem("Monospace");
        MenuItem impact = new MenuItem("Impact");
        MenuItem sans_serif = new MenuItem("Sans-Serif");
        MenuItem chiller = new MenuItem("Chiller");
        MenuItem tahoma = new MenuItem("Tahoma");
        
        MenuItem fontSize0 = new MenuItem("Size 5");
        MenuItem fontSize1 = new MenuItem("Size 12");
        MenuItem fontSize2 = new MenuItem("Size 14");
        MenuItem fontSize3 = new MenuItem("Size 20");
        MenuItem fontSize4 = new MenuItem("Size 30");
        MenuItem fontSize5 = new MenuItem("Size 50");
        
        MenuItem blackTxt = new MenuItem("Black");
        MenuItem redTxt = new MenuItem("Red");
        MenuItem blueTxt = new MenuItem("Blue");
        MenuItem greenTxt = new MenuItem("Green");
        MenuItem pinkTxt = new MenuItem("Pink");
        MenuItem purpleTxt = new MenuItem("Purple");        
        
        MenuItem defaultStyle = new MenuItem("Default");
        MenuItem italicItem = new MenuItem("Italics");
        MenuItem boldItem = new MenuItem("Bold");

        MenuItem whiteTheme = new MenuItem("white");
        MenuItem blackTheme = new MenuItem("black");
        
        //adds menu items to the appropriate menus
        fileMenu.getItems().add(newItem);
        fileMenu.getItems().add(openItem);
        fileMenu.getItems().add(saveItem);
        
        fontType.getItems().add(serif);
        fontType.getItems().add(sans_serif);
        fontType.getItems().add(monspace);
        fontType.getItems().add(impact);
        fontType.getItems().add(chiller);
        fontType.getItems().add(tahoma);
        
        fontSizes.getItems().add(fontSize0);
        fontSizes.getItems().add(fontSize1);
        fontSizes.getItems().add(fontSize2);
        fontSizes.getItems().add(fontSize3);
        fontSizes.getItems().add(fontSize4);
        fontSizes.getItems().add(fontSize5);
        
        colorMenu.getItems().add(blackTxt);
        colorMenu.getItems().add(redTxt);
        colorMenu.getItems().add(blueTxt);
        colorMenu.getItems().add(greenTxt);
        colorMenu.getItems().add(pinkTxt);
        colorMenu.getItems().add(purpleTxt);
        
        fontStyle.getItems().add(defaultStyle);
        fontStyle.getItems().add(italicItem);
        fontStyle.getItems().add(boldItem); 
        
        themeMenu.getItems().add(whiteTheme); 
        themeMenu.getItems().add(blackTheme);
               
        //creates a bar and adds menus to that bar
        MenuBar bar = new MenuBar();
        bar.getMenus().add(fileMenu);
        bar.getMenus().add(fontType);
        bar.getMenus().add(fontSizes);
        bar.getMenus().add(colorMenu);
        bar.getMenus().add(fontStyle);
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
        			
        			//deletes the current string in themeColor
        			themeColor.delete(0, color.length());
        			
        			//appends the themeColor string with the css for the background color of white
        			themeColor.append("-fx-control-inner-background:white;");
        			
        			txtSize.delete(0, txtSize.length());
        			
        			txtSize.append("-fx-font-size:12;");
        			
        			//checks if the current text color is white
        			if(color.toString().equals("-fx-text-inner-color: white;")){
        				
        				//if it is then it deletes the current string in color
        				color.delete(0,color.length());
        				
        				//and then it appends the css for black colored text
        				color.append("-fx-text-inner-color: black;");
        			}
        			
        			//enables black text 
        			blackTxt.setDisable(false);
        			
        			//sets title back to Untitled 
        			primaryStage.setTitle("Untitled");
        			
        			//sets a white background color with all other css variables
        			text.setStyle("-fx-control-inner-background:white;" + font.toString() + style.toString() + txtSize.toString() + color.toString());     			
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
        
        //declares another file chooser for the save option to save the file using the file menu
        FileChooser saver = new FileChooser();
        
        //makes a set on action method for saveItem
        saveItem.setOnAction((ActionEvent a) -> {
        	
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
        
        //makes a set on action method for black text
        blackTxt.setOnAction(
            new EventHandler<ActionEvent>() {
            	@Override
            	public void handle(ActionEvent actionevent){
            		
            		//deletes the current string in color
        			color.delete(0,color.length());
        			
        			//appends the color string with the css for the black colored text
        			color.append("-fx-text-inner-color: black;");
        			
        			//sets the text style to have the text color black along with all other css variables
            		text.setStyle("-fx-text-inner-color: black;" + font.toString() + themeColor.toString() + style.toString() + txtSize.toString());
            	}
        });
        
        //makes a set on action method for red text
        redTxt.setOnAction(
        	new EventHandler<ActionEvent>() {
        		@Override
        		public void handle(ActionEvent actionevent){
        			
        			//deletes the current string in color
        			color.delete(0,color.length());
        			
        			//appends the color string with the css for the red colored text
        			color.append("-fx-text-inner-color: red;");
        			
        			//sets the text style to have the text color red along with all other css variables
        			text.setStyle("-fx-text-inner-color: red;" + font.toString() + themeColor.toString() + style.toString() + txtSize.toString());
        		}
        });
        
        //makes a set on action method for blue text
        blueTxt.setOnAction(
            new EventHandler<ActionEvent>() {
            	@Override
            	public void handle(ActionEvent actionevent){
            		
            		//deletes the current string in color
        			color.delete(0,color.length());
        			
        			//appends the color string with the css for blue colored text
        			color.append("-fx-text-inner-color: blue;");
        			
        			//sets the text style to have the text color blue along with all other css variables
            		text.setStyle("-fx-text-inner-color: blue;" + font.toString() + themeColor.toString() + style.toString() + txtSize.toString());
            	}
            });
        
        //makes a set on action method for green text
        greenTxt.setOnAction(
                new EventHandler<ActionEvent>() {
                	@Override
                	public void handle(ActionEvent actionevent){
                		
                		//deletes the current string in color
                		color.delete(0,color.length());
                		
                		//appends the color string with the css for green colored text
            			color.append("-fx-text-inner-color: green;");
            			
            			//sets the text style to have the text color green along with all other css variables
                		text.setStyle(themeColor.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
                	}
                });
        
        //makes a set on action method for pink text
        pinkTxt.setOnAction(
                new EventHandler<ActionEvent>() {
                	@Override
                	public void handle(ActionEvent actionevent){
                		
                		//deletes the current string in color
                		color.delete(0,color.length());
                		
                		//appends the color string with the css for pink colored text
            			color.append("-fx-text-inner-color: pink;");
            			
            			//sets the text style to have the text color pink along with all other css variables
                		text.setStyle(themeColor.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
                	}
                });
        
        //makes a set on action method for purple text
        purpleTxt.setOnAction(
                new EventHandler<ActionEvent>() {
                	@Override
                	public void handle(ActionEvent actionevent){
                		
                		//deletes the current string in color
                		color.delete(0,color.length());
                		
                		//appends the color string with the css for purple colored text
            			color.append("-fx-text-inner-color: purple;");
            			
            			//sets the text style to have the text color purple along with all other css variables
                		text.setStyle(themeColor.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
                	}
                });
        
        //makes a set on action method for black theme
        blackTheme.setOnAction(
        	new EventHandler<ActionEvent>() {
        		@Override
        		public void handle(ActionEvent actionevent) {
        			
        			//deletes the current string in themeColor 
        			themeColor.delete(0, color.length());
        			
        			//appends the themeColor string with the css for the background color of black
        			themeColor.append("-fx-control-inner-background:black;");
        			
        			//checks if the current text color is black
        			if(color.toString().equals("-fx-text-inner-color: black;")){
        				
        				//if it is then it deletes the current string in color
        				color.delete(0,color.length());
        				
        				//and then it appends the css for white colored text
        				color.append("-fx-text-inner-color: white;");
        			}
        			
        			//disables black text
        			blackTxt.setDisable(true);
        			
        			//sets a black background color with all other css variables 
        			text.setStyle("-fx-control-inner-background:black;" + font.toString() + style.toString() + txtSize.toString() + color.toString());
        		}
        });
        
        //makes a set on action method for white theme
        whiteTheme.setOnAction(
            	new EventHandler<ActionEvent>() {
            		@Override
            		public void handle(ActionEvent actionevent) {
            			
            			//deletes the current string in themeColor
            			themeColor.delete(0, color.length());
            			
            			//appends the themeColor string with the css for the background color of white
            			themeColor.append("-fx-control-inner-background:white;");
            			
            			//checks if the current text color is white
            			if(color.toString().equals("-fx-text-inner-color: white;")){
            				
            				//if it is then it deletes the current string in color
            				color.delete(0,color.length());
            				
            				//and then it appends the css for black colored text
            				color.append("-fx-text-inner-color: black;");
            			}
            			
            			//enables black text 
            			blackTxt.setDisable(false);
            			
            			//sets a white background color with all other css variables
            			text.setStyle("-fx-control-inner-background:white;" + font.toString() + style.toString() + txtSize.toString() + color.toString());
            		}
            });
        
        //makes a set on action method for fontSize0
        fontSize0.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				
        				//deletes the current string in txtSize
        				txtSize.delete(0,txtSize.length());
        				
        				//appends the txtSize string with the css for the font size of 5
        				txtSize.append("-fx-font-size:5;");
        				
        				//sets the text style to have size 12 font along with all other css variables
        				text.setStyle(color.toString() + style.toString() + font.toString() + themeColor.toString() + "-fx-font-size:5");
        			}
        			
        		}
        );
        
        //makes a set on action method for fontSize1
        fontSize1.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				
        				//deletes the current string in txtSize
        				txtSize.delete(0,txtSize.length());
        				
        				//appends the txtSize string with the css for the font size of 12
        				txtSize.append("-fx-font-size:12;");
        				
        				//sets the text style to have size 12 font along with all other css variables
        				text.setStyle(color.toString() + style.toString() + font.toString() + themeColor.toString() + "-fx-font-size:12");
        			}
        			
        		}
        );
        
        //makes a set on action method for fontSize2
        fontSize2.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				
        				//deletes the current string in txtSize
        				txtSize.delete(0,txtSize.length());
        				
        				//appends the txtSize string with the css for the font size of 14
        				txtSize.append("-fx-font-size:14;");
        				
        				//sets the text style to have size 14 font along with all other css variables
        				text.setStyle(color.toString() + style.toString() + font.toString() + themeColor.toString() + "-fx-font-size:14");
        			}
        			
        		}
        );
        
        //makes a set on action method for fontSize3
        fontSize3.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				
        				//deletes the current string in txtSize
        				txtSize.delete(0,txtSize.length());
        				
        				//appends the txtSize string with the css for the font size of 20
        				txtSize.append("-fx-font-size:20;");
        				
        				//sets the text style to have size 20 font along with all other css variables
        				text.setStyle(color.toString() + style.toString() + font.toString() + themeColor.toString() + "-fx-font-size:20");
        			}
        			
        		}
        );
        
        //makes a set on action method for fontSize4
        fontSize4.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				
        				//deletes the current string in txtSize
        				txtSize.delete(0,txtSize.length());
        				
        				//appends the txtSize string with the css for the font size of 30
        				txtSize.append("-fx-font-size:30;");
        				
        				//sets the text style to have size 30 font along with all other css variables
        				text.setStyle(color.toString() + style.toString() + font.toString() + themeColor.toString() + "-fx-font-size:30");
        			}
        			
        		}
        );
        
        //makes a set on action method for fontSize5
        fontSize5.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				
        				//deletes the current string in txtSize
        				txtSize.delete(0,txtSize.length());
        				
        				//appends the txtSize string with the css for the font size of 50
        				txtSize.append("-fx-font-size:50;");
        				
        				//sets the text style to have size 12 font along with all other css variables
        				text.setStyle(color.toString() + style.toString() + font.toString() + themeColor.toString() + "-fx-font-size:50");
        			}
        			
        		}
        );
             
        //makes a set on action method for serif
        serif.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				
        				//deletes the current string in font
        				font.delete(0,font.length());
        				
        				//appends the font string with the css for the serif font
        				font.append("-fx-font-style:serif;");
        				
        				//sets text style to have the serif font along with all other css related variables
        				text.setStyle(color.toString() + txtSize.toString() + style.toString() + themeColor.toString() + "-fx-font-family:serif");
        			}
        			
        		}
        );
        
        //makes a set on action method for monospace
        monspace.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				
        				//deletes the current string in font
        				font.delete(0,font.length());
        				
        				//appends the font string with the css for the monospace font
        				font.append("-fx-font-style:monospace;");
        				
        				//sets text style to have the monospace font along with all other css related variables
        				text.setStyle(color.toString() + txtSize.toString() + style.toString() + themeColor.toString() + "-fx-font-family:monospace");
        			}
        			
        		}
        );
        
        //makes a set on action method for impact
        impact.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				
        				//deletes the current string in font
        				font.delete(0,font.length());
        				
        				//appends the font string with the css for the impact font
        				font.append("-fx-font-style:impact;");
        				
        				//sets text style to have the impact font along with all other css related variables
        				text.setStyle(color.toString() + txtSize.toString() + style.toString() + themeColor.toString() + "-fx-font-family:impact");
        			}
        			
        		}
        );
        
        //makes a set on action method for sans_serif
        sans_serif.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				
        				//deletes the current string in font
        				font.delete(0,font.length());
        				
        				//appends the font string with the css for the sans-serif font
        				font.append("-fx-font-style:sans-serif;");
        				
        				//sets text style to have the sans-serif font along with all other css related variables
        				text.setStyle(color.toString() + txtSize.toString() + style.toString() + themeColor.toString() + "-fx-font-family:sans-serif");
        			}
        			
        		}
        );
        
        //makes a set on action method for chiller
        chiller.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				
        				//deletes the current string in font
        				font.delete(0,font.length());
        				
        				//appends the font string with the css for the chiller font
        				font.append("-fx-font-family:chiller;");
        				
        				//sets text style to have the chiller font along with all other css related variables
                		text.setStyle(themeColor.toString() + style.toString() + color.toString() + txtSize.toString() + "-fx-font-family:chiller;");
        			}
        			
        		}
        );
        
        //makes a set on action method for tahoma
        tahoma.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				
        				//deletes the current string in font
        				font.delete(0,font.length());
        				
        				//appends the font string with the css for the tahoma font
        				font.append("-fx-font-family:tahoma;");
        				
        				//sets text style to have the tahoma font along with all other css related variables
                		text.setStyle(themeColor.toString() + style.toString() + color.toString() + txtSize.toString() + "-fx-font-family:tahoma;");
        			}
        			
        		}
        );
        
        //makes a set on action method for defaultStyle
        defaultStyle.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				
        				//deletes the current string in style
        				style.delete(0,txtSize.length());
        				
        				//appends the style string with the css for the default style
        				style.append("-fx-font-style:normal;");
        				
        				//sets text style to have the deafult style along with all other css related variables
        				text.setStyle(color.toString() + txtSize.toString() + font.toString() + themeColor.toString() + "-fx-font-style:normal");
        			}
        			
        		}
        );
        
        //makes a set on action method for italicItem
        italicItem.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				
        				//deletes the current string of the style string
        				style.delete(0,txtSize.length());
        				
        				//appends the style string with the css for italics text
        				style.append("-fx-font-style:italic;");
        				
        				//sets text style to have the italics font along with all other css related variables
        				text.setStyle(color.toString() + txtSize.toString() + "-fx-font-style:italic;" + themeColor.toString() + font.toString());
        			}
        			
        		}
        );
        
        
        //makes a set on action method for boldItem
        boldItem.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				
        				//deletes the current string for the style string
        				style.delete(0,txtSize.length());
        				
        				//appends the style string with the css for bold text
        				style.append("-fx-font-style:bold;");
        				
        				//sets text style to have the bold font and all the other css related variables
        				text.setStyle(color.toString() + txtSize.toString() + "-fx-font-weight:bold;" + themeColor.toString() + font.toString());
        			}
        			
        		}
        );
        
        //sets the primary stage with the box, which holds the textArea
        primaryStage.setScene(new Scene(box, 500, 500));
        
        //shows primary stage on the javafx canvas
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

