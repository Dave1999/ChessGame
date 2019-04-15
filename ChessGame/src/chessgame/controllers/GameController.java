/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame.controllers;

import chessgame.models.BoardLocation;
import chessgame.models.Game;
import chessgame.models.ImageManager;
import chessgame.models.Piece;
import chessgame.views.IView;
import java.awt.Point;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javax.swing.JOptionPane;

/**
 *
 * @author David and Grady.
 */

public class GameController extends javafx.scene.layout.StackPane
{
    private static final int BUTTONSIZE = 70;
    private static final int BUTTON_PADDING = 1;
    
    // Models and views.
    private IView view;
    private Game game;
    private boolean IsPieceSelected;
    private final ImageManager images;
    
    // User interface elements.
    // Gridpane to display the buttons.
    private GridPane grid;
    private final Button[][] buttons;
    
    // Holds the currently selected BoardLocation (if the user has selected a piece).
    private BoardLocation selectedLocation;
    
    public GameController() 
    {
        super();
        
        images = new ImageManager();
        grid = new GridPane();
        buttons = new Button[Game.BOARD_WIDTH][Game.BOARD_HEIGHT];
        
        // Create the mouse event handler object.
        EventHandler<MouseEvent> eventHandler = (MouseEvent e) -> 
        {
            PieceButton_Click(e);
        };  

        // Iterate through and create all the buttons.
        for (int i = 0; i < 8; i++) 
        {
            for (int k = 0; k < 8; k++) 
            {
                buttons[i][k] = createNewButton(i, k);
                
                // Register the button's click-event handler.
                buttons[i][k].addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);   
            }
        }
        
        grid = new GridPane();
        grid.setPadding(new Insets(BUTTON_PADDING));
        grid.setHgap(BUTTON_PADDING);
        grid.setVgap(BUTTON_PADDING);

        grid.setMouseTransparent(false);
        
        // Initialize the game state, and display the empty game.
        this.game = new Game();
        game.InitializeGameState();
        this.Display();
    }

    public void PieceButton_Click(MouseEvent event)
    {
        try
        {
                /*
          Logic: 
              Attempt to get the piece at the selected location.

              If no piece is ALREADY selected (from the previous move), then:
                  if the piece is null, do nothing
                  if the piece is NOT the same color as the team whose turn it is, alert "not your turn"
                  if the piece IS the correct color, select it and set IsPieceSelected to 'true'.

              If a piece is already selected (IsPieceSelected == true), then attempt to perform the move.
          */

          Button clickedButton = (Button)event.getSource();
          BoardLocation targetLocation = getButtonPosition(clickedButton);
          
          // If a piece is already selected, 
          if (IsPieceSelected)
          {
                // Attempt to move.
                //boolean result = game.PerformMove(, selectedLocation);
                boolean result = game.PerformMove(selectedLocation, targetLocation);

                if (!result)
                {
                      JOptionPane.showMessageDialog(null, "Invalid move! Please try a different option.", "InfoBox: " + "Invalid move", JOptionPane.INFORMATION_MESSAGE);
                }
                else 
                {
                    this.Display();
                }
              
                IsPieceSelected = false;
          }
          else 
          {
              // BoardLocation location = new BoardLocation();
              //ArrayList<BoardLocation> availableMoves = game.getAvailableMoves(location); //Display();
              selectedLocation = targetLocation;
              IsPieceSelected = true;
          }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    // Given a button object, this returns the row and column represented as a Point.
    private BoardLocation getButtonPosition(Button b)
    {
        BoardLocation location = null;
         
        for (int i = 0; i < 8; i++)
        {
            for (int k = 0; k < 8; k++)
            {
                if (b.equals(buttons[i][k]))
                {
                    return new BoardLocation(i, k);
                }
            }
        }
        
        return location;
    }
    
    private Button createNewButton(int row, int column)
    {
        Button b = new Button();
                
        // Set the max and min heights equal so the buttons display at exactly the size we want them.
        b.setMaxWidth(BUTTONSIZE);
        b.setMaxHeight(BUTTONSIZE);
        b.setMinWidth(BUTTONSIZE);
        b.setMinHeight(BUTTONSIZE);
        b.setLayoutX(BUTTONSIZE * column);
        b.setLayoutY(BUTTONSIZE * row);
        
        if (getSquareColor(row, column))
        {
            // If the square is a white square, set the color to the designated white-square color.
            b.setStyle("-fx-background-color: #4248f4; "); 
        }
        else 
        {
            // If the square is a black square, set the color to the designated black-square color.
            b.setStyle("-fx-background-color: #c42d0f; "); 
        }

        //String colorStyle
       
        //b.setStyle(".button:hover {-fx-border-color: yellow; -fx-border-width: 1; } .button:pressed {-fx-background-color: brown; }");
        
        return b;
    }
    
    private void loadImageView(Button button, Image img)
    {
        // Create a new ImageView from the correct image based on the Piece's type and color.
        ImageView imageView = new ImageView(img);
                
        // Set the image's height and ensure that its fit ratio is preserved.
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(BUTTONSIZE);
        imageView.setFitWidth(BUTTONSIZE);
        button.setGraphic(imageView);
    }
    
    // Returns 'true' if the specified square is white, and 'false' if the square is black.
    private boolean getSquareColor(int row, int column)
    {
        boolean isWhiteSquare;
        
        // Squares (0,0) and (7,7) are white.
        // 0 % 2 = . 0 % 1 = 1.
        if ((row % 2) == 0)
        {
            isWhiteSquare = (column % 2) == 0;
        }
        else 
        {
            isWhiteSquare = ((column + 1) % 2) == 0;
        }
        
        return isWhiteSquare;
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
        grid.getChildren().clear();
        Piece[][] pieces = this.game.getPieces();
        
        for(int i = 0; i < 8; i++) 
        {
            for (int k = 0; k < 8; k++) 
            {
                if(pieces[i][k] == null)
                {
                    // Set the Button's graphic to 'null', and add it to the grid.
                    buttons[i][k].setGraphic(null);
                    grid.add(buttons[i][k], k, i);
                }
                else 
                {
                    // Load the image with the piece in it.
                    loadImageView(buttons[i][k], images.getImage(pieces[i][k].getColor(), pieces[i][k].getType()));
                    grid.add(buttons[i][k], k, i);
                }
            }
        }

        this.getChildren().clear();
        this.getChildren().add(grid);
    }
}