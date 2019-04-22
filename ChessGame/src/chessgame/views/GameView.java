/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame.views;

import chessgame.models.Game;
import javafx.scene.layout.StackPane;
import chessgame.models.Move;
import chessgame.models.Piece.TeamColor;
import static chessgame.models.Piece.TeamColorToString;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

/**
 *
 * @author David
 */
public class GameView extends StackPane implements IView
{
    private Label TurnDisplay;
    private Label CheckStatusDisplay;
    private ListView<String> MoveListView;
    
    private VBox DisplayBox;
    private Game game;
    private boolean HasInitializedDisplay;
    
    // This class extends StackPane, and displays a List of move. Display adds a Move object to the list,
    // and displays it in the list-view.
    public GameView()
    {
        super();
        MoveListView = new ListView<>();
        TurnDisplay = new Label();
        CheckStatusDisplay = new Label();

        DisplayBox = new VBox();
        HasInitializedDisplay = false;
        
        int width = 500;
        int height = 680;
        // Set the height and width.
        
        MoveListView.setMinWidth(width);
        MoveListView.setMaxWidth(width);
        MoveListView.setMinHeight(height - (TurnDisplay.getHeight() * 2));
        MoveListView.setMaxHeight(height - (TurnDisplay.getHeight() * 2));
        
        this.setWidth(width);
        this.setMaxWidth(width);
        this.setHeight(height);
        this.setMaxHeight(height);
    }
    
    @Override
    public void Display()
    {    
        this.displayLastMove();
        String teamName = TeamColorToString(game.getTurnIndicator());
        this.TurnDisplay.setText(teamName + "'s turn");
        
        /* Set the check status display to either "White is in check", "Black is in check", or "Neither
         * team is in check". */
        if (game.hasGameStarted())
        {
            if (game.isCheck(game.getTurnIndicator()))
            {
                this.CheckStatusDisplay.setText(teamName + " is in check");
            }
            else 
            {
                this.CheckStatusDisplay.setText("Neither team is in check");
            }
        }
        else 
        {
            this.CheckStatusDisplay.setText("Neither team is in check");
        }
        
        if (!HasInitializedDisplay)
        {
            DisplayBox.getChildren().addAll(TurnDisplay, CheckStatusDisplay, MoveListView);
            this.getChildren().add(DisplayBox);
            HasInitializedDisplay = true;
        }
    }
    
    private void displayLastMove()
    {
        Move lastMove = game.getLastMove();
        
        if (lastMove == null)
        {
           this.MoveListView.getItems().clear();
        }
        else 
        {
            String turnCount = this.getTurnCountString();
            String moveString = lastMove.toString();
            
            String finalString = turnCount + moveString;
            if (!MoveListView.getItems().contains(finalString))
            {
                MoveListView.getItems().add(finalString);
            }
        }
    }
    
    private String getTurnCountString()
    {
        String letter = "";
        
        if (game.getTurnIndicator() == TeamColor.Black)
        {
            letter = "a";
        }
        else
        {
            letter = "b";
        }    
        
        String result = Integer.toString(game.getTurnCount()) + letter + ": ";
        return result;
    }
    
    @Override 
    public void SetGame(Game chessGame)
    {
        this.game = chessGame;
    }
}
