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
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author David
 */
public class MainWindow extends Application 
{
    private GameController controller;
    private GameView view;
    
    @Override
    public void start(Stage primaryStage) 
    {
        controller = new GameController();
        view = new GameView();
        controller.setView(view);
        controller.Display();

        StackPane root = new StackPane();
        root.getChildren().add(controller);
        root.getChildren().add(view);
        
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
        
        //controller.setTranslateX(controller.getWidth() / 2);
        view.setTranslateX(controller.getWidth() / 2);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
}
