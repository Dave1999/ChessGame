/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame.controllers;

import chessgame.exceptions.ExposesOwnTeamToCheckException;
import chessgame.exceptions.InvalidMoveException;
import chessgame.exceptions.WrongTurnException;

import chessgame.models.BoardLocation;
import chessgame.models.Game;
import chessgame.models.ImageManager;
import chessgame.models.Move;
import chessgame.models.Piece;
import chessgame.views.IView;
import java.util.ArrayList;
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
    
    private final String WHITE_SQUARE_COLOR = "#4248f4";
    private final String BLACK_SQUARE_COLOR = "#c42d0f"; 
    private final String HIGHLIGHT_BORDER_COLOR = "#129b24";
    private final String SELECTED_BORDER_COLOR = "#4f8ff7";
    
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
        
        // Initialize the game state.
        this.game = new Game();
        game.InitializeGameState();
    }

    /** 
     * Event handler for the 
     * @param event 
     */
    public void PieceButton_Click(MouseEvent event)
    {
        try
        {
            Button clickedButton = (Button)event.getSource();
            BoardLocation targetLocation = getButtonPosition(clickedButton);
          
            // If a piece is already selected, 
            if (IsPieceSelected)
            {
                /* If the piece that was clicked on is the same color as the piece that was 
                   previously selected, then change the selected location to the location that was just 
                   clicked on. */
                if (targetLocation.equals(selectedLocation))
                {
                    return;
                }
                
                Piece target = game.getPieceAt(targetLocation);
                if ((target != null) && (target.getColor() == game.getTurnIndicator()))
                {
                    Display();
                    setSelectedLocation(targetLocation);
                    return;
                }

                // Otherwise, attempt to move the piece, by constructing a new Move and having the game 
                // perform it.
                Move move = new Move(
                        game.getPieceAt(selectedLocation), 
                        game.getPieceAt(targetLocation), 
                        selectedLocation, 
                        targetLocation);
                
                boolean result = game.PerformMove(move);
                
                // If the move worked, then re-display the board. Otherwise, show an error message. 
                if (result)
                {
                    this.Display();
                }
                else 
                {
                    throw new InvalidMoveException("Invalid move.");
                }

                // After successfully performing the move, there should not be a piece selected.
                IsPieceSelected = false;
            }
            else 
            {
                setSelectedLocation(targetLocation);
            }
        }
        /*
        catch (WrongTurnException wtx)
        {
            // Log the exception to the console, and show a message box informing the user that it's the wrong turn.
            System.out.println(wtx.getMessage());
            JOptionPane.showMessageDialog(null, "It's not your turn!", "Invalid move", JOptionPane.INFORMATION_MESSAGE);
        }
        */
        catch (ExposesOwnTeamToCheckException ex)
        {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Invalid move! " + ex.getMessage(), "Invalid move", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (InvalidMoveException imx)
        {
            System.out.println(imx.getMessage());
            JOptionPane.showMessageDialog(null, "Invalid move! Please try a different option.", "Invalid move", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void setSelectedLocation(BoardLocation targetLocation)
    {
        try
        {
            selectedLocation = targetLocation;
        
            ArrayList<BoardLocation> availableMoves = game.getAvailableMoves(targetLocation);

            for (BoardLocation location : availableMoves)
            {
                int row = location.getRow();
                int col = location.getColumn();

                SetButtonStyle(buttons[row][col], row, col, HIGHLIGHT_BORDER_COLOR);
            }

            // Set the selected button's border color to cyan.
            int row = targetLocation.getRow();
            int col = targetLocation.getColumn();
            SetButtonStyle(buttons[row][col], row, col, SELECTED_BORDER_COLOR);

            IsPieceSelected = true;
        }
        catch (NullPointerException npx)
        {
            System.out.println(npx);
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
        
        SetButtonStyle(b, row, column, "");

        //String colorStyle
       
        //b.setStyle(".button:hover {-fx-border-color: yellow; -fx-border-width: 1; } .button:pressed {-fx-background-color: brown; }");
        
        return b;
    }
    
    private void SetButtonStyle(Button b, int row, int column, String borderColor)
    {
        String style = "-fx-background-color: ";
        String backgroundColor;
        
        if (getSquareColor(row, column))
        {
            // If the square is a white square, set the color to the designated white-square color.
            backgroundColor = WHITE_SQUARE_COLOR;
        }
        else 
        {
            // If the square is a black square, set the color to the designated black-square color.
            backgroundColor = BLACK_SQUARE_COLOR;
        }
        
        style += backgroundColor + ";";       
       
        if (!borderColor.equals(new String()))
        {
            style +=  "-fx-border-color: " + borderColor + "; -fx-border-width: 4 4 4 4;";
        }
        
        b.setStyle(style);
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
        view.SetGame(this.game);
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
                // Re-set the button borders.
                SetButtonStyle(buttons[i][k], i, k, "");

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
        
        this.view.Display();
        // Update the IView object belonging to this class.
        //this.view.display(game);
    }
}