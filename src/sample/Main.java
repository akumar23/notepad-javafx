package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;

import javax.swing.*;
//import javafx.embed.swing;

public class Main extends Application {

    private Desktop d = Desktop.getDesktop();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("notepad");

        TextArea text = new TextArea();

        Menu fileMenu = new Menu("File");
        Menu fontMenu = new Menu("Font Size");
        Menu highlightMenu = new Menu("Highlight Text");

        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem fontItem1 = new MenuItem("Size 12");
        MenuItem fontItem2 = new MenuItem("Size 14");
        MenuItem fontItem3 = new MenuItem("Size 20");

        fileMenu.getItems().add(openItem);
        fileMenu.getItems().add(saveItem);
        fontMenu.getItems().add(fontItem1);
        fontMenu.getItems().add(fontItem2);
        fontMenu.getItems().add(fontItem3);

        MenuBar bar = new MenuBar();
        bar.getMenus().add(fileMenu);
        bar.getMenus().add(fontMenu);
        bar.getMenus().add(highlightMenu);

        VBox box = new VBox(bar, text);

        final FileChooser choose = new FileChooser();
        //final Button openFile = new Button("File");

        openItem.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        File file = choose.showOpenDialog(primaryStage);
                        if(file != null){
                            openFile(file);
                        }
                    }
                }
        );

        FileChooser save = new FileChooser();
        save.setTitle("Save");
        saveItem.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        save.showSaveDialog(primaryStage);
                    }
                }
        );

        primaryStage.setScene(new Scene(box, 500, 500));
        //primaryStage.setScene(new Scene(boxText, 300,275));
        primaryStage.show();
    }

    private void openFile(File file){
        try {
            d.open(file);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
