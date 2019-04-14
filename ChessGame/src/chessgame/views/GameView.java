/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame.views;

import javafx.scene.layout.StackPane;
import chessgame.models.Move;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 *
 * @author David
 */
public class GameView extends StackPane implements IView
{
    private ListView<String> MoveListView;
    
    // This class extends StackPane, and displays a List of move. Display adds a Move object to the list,
    // and displays it in the list-view.
    
    public GameView()
    {
        super();
        MoveListView = new ListView<>();
    }
    
    @Override
    public void Display(Move move)
    {
        MoveListView.getItems().add(move.toString());
    }
}
