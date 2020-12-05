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
        final StringBuilder font = new StringBuilder("-fx-font-style:normal;");
        final StringBuilder txtSize = new StringBuilder("-fx-font-size:12;");
        final StringBuilder color = new StringBuilder("-fx-text-inner-color: black;");
        final StringBuilder style = new StringBuilder("-fx-font-style:normal;");
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
        MenuItem chalkboard = new MenuItem("Chalkboard");
        MenuItem trattatello = new MenuItem("Trattatello");
        MenuItem georgia = new MenuItem("Georgia");
        
        MenuItem fontSize1 = new MenuItem("Size 5");
        MenuItem fontSize2 = new MenuItem("Size 7");
        MenuItem fontSize3 = new MenuItem("Size 8");
        MenuItem fontSize4 = new MenuItem("Size 10");
        MenuItem fontSize5 = new MenuItem("Size 12");
        MenuItem fontSize6 = new MenuItem("Size 14");
        MenuItem fontSize7 = new MenuItem("Size 18");
        MenuItem fontSize8 = new MenuItem("Size 20");
        MenuItem fontSize9 = new MenuItem("Size 24");
        MenuItem fontSize10 = new MenuItem("Size 26");
        MenuItem fontSize11 = new MenuItem("Size 28");
        MenuItem fontSize12 = new MenuItem("Size 36");
        MenuItem fontSize13 = new MenuItem("Size 40");
        MenuItem fontSize14 = new MenuItem("Size 48");
        MenuItem fontSize15 = new MenuItem("Size 55");


        
        MenuItem blackTxt = new MenuItem("Black");
        MenuItem redTxt = new MenuItem("Red");
        MenuItem blueTxt = new MenuItem("Blue");
        MenuItem greenTxt = new MenuItem("Green");
        MenuItem pinkTxt = new MenuItem("Pink");
        MenuItem purpleTxt = new MenuItem("Purple");
        MenuItem orangeTxt = new MenuItem("Orange");

        
        MenuItem defaultStyle = new MenuItem("Default");
        MenuItem italicItem = new MenuItem("Italics");
        MenuItem boldItem = new MenuItem("Bold");
        MenuItem underlineItem = new MenuItem("Underline");
        MenuItem strikethroughItem = new MenuItem("Strikethrough");
        
        MenuItem yellowHlt = new MenuItem("Yellow");
        MenuItem redHlt = new MenuItem("Red");
        MenuItem greenHlt = new MenuItem("Green");
        MenuItem blueHlt = new MenuItem("Blue");
        MenuItem blackHlt = new MenuItem("Black");
        
        MenuItem whiteTheme = new MenuItem("White");
        MenuItem blackTheme = new MenuItem("Black");
        
        //adds menu items to the appropriate menus
        fileMenu.getItems().add(newItem);
        fileMenu.getItems().add(openItem);
        fileMenu.getItems().add(saveItem);
        fileMenu.getItems().add(saveAsItem);
        
        fontType.getItems().add(serif);
        fontType.getItems().add(sans_serif);
        fontType.getItems().add(monspace);
        fontType.getItems().add(impact);
        fontType.getItems().add(chalkboard);
        fontType.getItems().add(trattatello);
        fontType.getItems().add(georgia);

        
        fontSizes.getItems().add(fontSize1);
        fontSizes.getItems().add(fontSize2);
        fontSizes.getItems().add(fontSize3);
        fontSizes.getItems().add(fontSize4);
        fontSizes.getItems().add(fontSize5);
        fontSizes.getItems().add(fontSize6);
        fontSizes.getItems().add(fontSize7);
        fontSizes.getItems().add(fontSize8);
        fontSizes.getItems().add(fontSize9);
        fontSizes.getItems().add(fontSize10);
        fontSizes.getItems().add(fontSize11);
        fontSizes.getItems().add(fontSize12);
        fontSizes.getItems().add(fontSize13);
        fontSizes.getItems().add(fontSize14);
        fontSizes.getItems().add(fontSize15);
        
        colorMenu.getItems().add(blackTxt);
        colorMenu.getItems().add(redTxt);
        colorMenu.getItems().add(blueTxt);
        colorMenu.getItems().add(greenTxt);
        colorMenu.getItems().add(pinkTxt);
        colorMenu.getItems().add(purpleTxt);
        colorMenu.getItems().add(orangeTxt);
        
        fontStyle.getItems().add(defaultStyle);
        fontStyle.getItems().add(italicItem);
        fontStyle.getItems().add(boldItem);
        fontStyle.getItems().add(underlineItem);
        fontStyle.getItems().add(strikethroughItem);
        
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
        
        Scene scene = new Scene(box, 750, 500);
        String css = Main.class.getResource("style.css").toExternalForm();
        scene.getStylesheets().add(css);
        
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
        
        serif.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				font.setLength(0);
        				font.append("-fx-font-family:serif;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
        			}
        			
        		}
        );
        
        monspace.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				font.setLength(0);
        				font.append("-fx-font-family:monospace;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
        			}
        			
        		}
        );
        
        impact.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				font.setLength(0);
        				font.append("-fx-font-family:impact;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
        			}
        			
        		}
        );
        
        sans_serif.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				font.setLength(0);
        				font.append("-fx-font-family:sans-serif;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
        			}
        			
        		}
        );        
        
        chalkboard.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				font.setLength(0);
        				font.append("-fx-font-family:chalkboard;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
        			}
        			
        		}
        );
        
        trattatello.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				font.setLength(0);
        				font.append("-fx-font-family:trattatello;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
        			}
        			
        		}
        );
        
        georgia.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				font.setLength(0);
        				font.append("-fx-font-family:georgia;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
        			}
        			
        		}
        );
        
        fontSize1.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				txtSize.setLength(0);
        				txtSize.append("-fx-font-size:5;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
        			}
        			
        		}
        );
        
        fontSize2.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				txtSize.setLength(0);
        				txtSize.append("-fx-font-size:7;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
        			}
        			
        		}
        );
        
        fontSize3.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				txtSize.setLength(0);
        				txtSize.append("-fx-font-size:8;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
        			}
        			
        		}
        );
        
        fontSize4.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				txtSize.setLength(0);
        				txtSize.append("-fx-font-size:10;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
        			}
        			
        		}
        );
        
        fontSize5.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				txtSize.setLength(0);
        				txtSize.append("-fx-font-size:12;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
        			}
        			
        		}
        );
        
        fontSize6.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				txtSize.setLength(0);
        				txtSize.append("-fx-font-size:14;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
        			}
        			
        		}
        );
        
        fontSize7.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				txtSize.setLength(0);
        				txtSize.append("-fx-font-size:18;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
        			}
        			
        		}
        );
        
        fontSize8.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				txtSize.setLength(0);
        				txtSize.append("-fx-font-size:20;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
        			}
        			
        		}
        );
        
        fontSize9.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				txtSize.setLength(0);
        				txtSize.append("-fx-font-size:24;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
        			}
        			
        		}
        );
        
        fontSize10.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				txtSize.setLength(0);
        				txtSize.append("-fx-font-size:26;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
        			}
        			
        		}
        );
        
        fontSize11.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				txtSize.setLength(0);
        				txtSize.append("-fx-font-size:28;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
        			}
        			
        		}
        );
        
        fontSize12.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				txtSize.setLength(0);
        				txtSize.append("-fx-font-size:36;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
        			}
        			
        		}
        );
        
        fontSize13.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				txtSize.setLength(0);
        				txtSize.append("-fx-font-size:40;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
        			}
        			
        		}
        );
        
        fontSize14.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				txtSize.setLength(0);
        				txtSize.append("-fx-font-size:48;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
        			}
        			
        		}
        );
        
        fontSize15.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				txtSize.setLength(0);
        				txtSize.append("-fx-font-size:55;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
        			}
        			
        		}
        );

        blackTxt.setOnAction(
            new EventHandler<ActionEvent>() {
            	@Override
            	public void handle(ActionEvent actionevent){
        			color.setLength(0);
        			color.append("-fx-text-inner-color: black;");
            		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
            	}
        });
        
        redTxt.setOnAction(
        	new EventHandler<ActionEvent>() {
        		@Override
        		public void handle(ActionEvent actionevent){
        			color.setLength(0);
        			color.append("-fx-text-inner-color: red;");
            		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
        		}
        });
        
        blueTxt.setOnAction(
            new EventHandler<ActionEvent>() {
            	@Override
            	public void handle(ActionEvent actionevent){
        			color.setLength(0);
        			color.append("-fx-text-inner-color: blue;");
            		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
            	}
            });
        
        greenTxt.setOnAction(
                new EventHandler<ActionEvent>() {
                	@Override
                	public void handle(ActionEvent actionevent){
            			color.setLength(0);
            			color.append("-fx-text-inner-color: green;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
                	}
                });
        
        pinkTxt.setOnAction(
                new EventHandler<ActionEvent>() {
                	@Override
                	public void handle(ActionEvent actionevent){
            			color.setLength(0);
            			color.append("-fx-text-inner-color: pink;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
                	}
                });
        
        purpleTxt.setOnAction(
                new EventHandler<ActionEvent>() {
                	@Override
                	public void handle(ActionEvent actionevent){
            			color.setLength(0);
            			color.append("-fx-text-inner-color: purple;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
                	}
                });
        
        orangeTxt.setOnAction(
                new EventHandler<ActionEvent>() {
                	@Override
                	public void handle(ActionEvent actionevent){
            			color.setLength(0);
            			color.append("-fx-text-inner-color: orange;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
                	}
                });
        
        defaultStyle.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				style.setLength(0);
        				style.append("-fx-font-style:normal;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
        			}
        			
        		}
        );
        
        italicItem.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				style.setLength(0);
        				style.append("-fx-font-style:italic;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
        			}
        			
        		}
        );
        
        boldItem.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				style.setLength(0);
        				style.append("-fx-font-weight:bold;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
        			}
        			
        		}
        );
        
        underlineItem.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				if (text.getStyleClass().contains("underline")){
        					text.getStyleClass().remove("underline");
        				} else {
        					text.getStyleClass().add("underline");
        				}
        			}
        			
        		}
        );
        
        strikethroughItem.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				if (text.getStyleClass().contains("strikethrough")){
        					text.getStyleClass().remove("strikethrough");
        				} else {
        					text.getStyleClass().add("strikethrough");
        				}
        			}
        			
        		}
        );
        
        yellowHlt.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				highlight.setLength(0);
        				highlight.append("-fx-highlight-fill: yellow; -fx-highlight-text-fill: black;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
                		text.selectRange(0, text.getLength());
        			}
        		}
        );
        
        redHlt.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				highlight.setLength(0);
        				highlight.append("-fx-highlight-fill: red; -fx-highlight-text-fill: black;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
                		text.selectRange(0, text.getLength());
        			}
        		}
        );
        
        greenHlt.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				highlight.setLength(0);
        				highlight.append("-fx-highlight-fill: green; -fx-highlight-text-fill: white;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
                		text.selectRange(0, text.getLength());
        			}
        		}
        );
        
        blueHlt.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				highlight.setLength(0);
        				highlight.append("-fx-highlight-fill: blue; -fx-highlight-text-fill: white;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
                		text.selectRange(0, text.getLength());
        			}
        		}
        );
        
        blackHlt.setOnAction(
        		new EventHandler<ActionEvent>() {
        			public void handle(ActionEvent actionevent) {
        				highlight.setLength(0);
        				highlight.append("-fx-highlight-fill: black; -fx-highlight-text-fill: white;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
                		text.selectRange(0, text.getLength());
        			}
        		}
        );
        
        whiteTheme.setOnAction(
            	new EventHandler<ActionEvent>() {
            		@Override
            		public void handle(ActionEvent actionevent) {
            			themeColor.setLength(0);
            			themeColor.append("-fx-control-inner-background:white;");
            			color.setLength(0);
            			color.append("-fx-text-inner-color: black;");
                		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
            		}
            });
        
        blackTheme.setOnAction(
        	new EventHandler<ActionEvent>() {
        		@Override
        		public void handle(ActionEvent actionevent) {
        			themeColor.setLength(0);
        			themeColor.append("-fx-control-inner-background:black;");
        			color.setLength(0);
        			color.append("-fx-text-inner-color: white;");
            		text.setStyle(themeColor.toString() + highlight.toString() + style.toString() + color.toString() + txtSize.toString() + font.toString());
        		}
        });

        primaryStage.setScene(scene);
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