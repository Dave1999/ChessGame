package chessgame.models;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author David
 */
public class Game 
{  
    public enum TeamColor { White, Black };

    private final int BOARD_HEIGHT = 8;
    private final int BOARD_WIDTH = 8;
    
    private Piece[][] m_Pieces;
    private int TurnCount;
    private TeamColor TurnIndicator;
    private Board GameBoard;
    private ArrayList<Move> Moves; 
    
    public Game()
    {
        this.m_Pieces = new Piece[BOARD_HEIGHT][BOARD_WIDTH];
        
        InitializeGameState();
    }
    
    /** 
     * Initializes the game board to the default game state, by creating all the Piece objects and 
     * placing them where they belong.
     */
    // Initializes the game board to the d
    public void InitializeGameState()
    {
        
    }
    
    public void UpdateTurn()
    {
        if (this.TurnIndicator == TeamColor.Black)
        {
            this.TurnCount++;
            this.TurnIndicator = TeamColor.White;
        }
        else 
        {
            this.TurnIndicator = TeamColor.Black; 
        }
    }
    
    public Piece[][] getPieces() 
    {
        return this.m_Pieces;
    }
}
