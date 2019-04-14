/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame.controllers;

import chessgame.models.Game;
import chessgame.models.ImageManager;
import chessgame.models.Piece;
import chessgame.views.IView;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author David and Grady.
 */

public class GameController extends javafx.scene.layout.StackPane
{
    private static final int BUTTONSIZE = 60;

    // Models and views.
    private IView view;
    private Game game;
    private boolean IsPieceSelected;
    private final ImageManager images;
    
    // User interface elements.
    private Group group;
    private final Button[][] buttons;
    //private VBox vbox[][];
    
    // Button click event handler.
    //final EventHandler<MouseEvent> handler;

    public GameController() 
    {
        super();
        
        images = new ImageManager();
        game = new Game();
        
        group = new Group();
        buttons = new Button[Game.BOARD_WIDTH][Game.BOARD_HEIGHT];
        
        // Route the button-click event handler to the PieceButton_Click method.
    

        for (int i = 0; i < 8; i++) 
        {
            for (int k = 0; k < 8; k++) 
            {
                buttons[i][k] = new Button();
                buttons[i][k].setLayoutX(BUTTONSIZE*k);
                buttons[i][k].setLayoutY(BUTTONSIZE*i);
                //buttons[i][k].setOnMouseClicked(handler);
                
                buttons[i][k].setOnMouseClicked((MouseEvent arg0) -> 
                {
                    System.out.println("Button click");            
                });
                
                buttons[i][k].setOnMouseReleased((MouseEvent arg0) -> 
                {
                    System.out.println("Button released");            
                });
            }
        }
        
        int size = 8 * BUTTONSIZE;
        this.setWidth(size);
        this.setHeight(size);

        // Initialize the game state, and display the empty game.
        this.game = new Game();
        game.InitializeGameState();
        this.Display();
    }
     
    public void PieceButton_Click(MouseEvent event)
    {
        // If a piece is already selected, 
        if (IsPieceSelected)
        {
            
        }
        else 
        {
           // BoardLocation location = new BoardLocation();
            //ArrayList<BoardLocation> availableMoves = game.getAvailableMoves(location); //Display();
        }
    }
    
    public void setView(IView view)
    {
        this.view = view;
    }
    
    // Cycle through every space on the board. If empty, ignores.
    // Checks non-empty spaces first for its PieceType and then its TeamColor.
    // Draws the appropriate image from Images
    public final void Display()
    { 
        group.getChildren().clear();
        Piece[][] pieces = this.game.getPieces();
        
        for(int i = 0; i < 8; i++) 
        {
            for (int k = 0; k < 8; k++) 
            {
                if(pieces[i][k] == null)
                {
                    continue;
                }
                
                // Create a new ImageView from the correct image based on the Piece's type and color.
                ImageView imageView = new ImageView(images.getImage(pieces[i][k].getColor(), pieces[i][k].getType()));
                
                // Set the image's height and ensure that its fit ratio is preserved.
                imageView.setPreserveRatio(true);
                imageView.setFitHeight(BUTTONSIZE);
                imageView.setFitWidth(BUTTONSIZE);

                // Add the image to the button.
                buttons[i][k].setGraphic(imageView);
//                buttons[i][k].setOnMouseClicked(handler);
                group.getChildren().add(buttons[i][k]);
            }
        }

        this.getChildren().clear();
        this.getChildren().add(group);
        //stage.setScene(scene);
        //stage.show();
    }
}