/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame;

import chessgame.controllers.GameController;
import chessgame.views.GameView;
import javafx.scene.layout.StackPane;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author David
 */
public class MainWindow extends Application 
{
    private GameController gameController;
    private GameView view;
    
    @Override
    public void start(Stage primaryStage) 
    {
        gameController = new GameController();
        view = new GameView();
        
        gameController.setView(view);
        gameController.Display();

        //StackPane root = new StackPane();
        BorderPane root = new BorderPane();
        
        root.setLeft(gameController);
        root.setCenter(view);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Play Chess");
        primaryStage.setScene(scene);
        
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        //set Stage boundaries to visible bounds of the main screen
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
}
