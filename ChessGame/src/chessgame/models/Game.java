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
    
    public Piece getPieceAt(BoardLocation location)
    {
        return m_Pieces[location.getRow()][location.getColumn()];
    }
    
    public void setPieceAt(BoardLocation location, Piece piece)
    {
        m_Pieces[location.getRow()][location.getColumn()] = piece;
    }
    
    public ArrayList<BoardLocation> getAvailableMoves(BoardLocation selectedLocation)
    {
        ArrayList<BoardLocation> availableMoves = new ArrayList<BoardLocation>();
        
        Piece selectedPiece = this.getPieceAt(selectedLocation);
        
        // Only permit the move if the piece is of the correct color.
        if (selectedPiece.getColor() == TurnIndicator)
        {
            BoardLocation[] locations = null;
            
            for (int i = 0; i < BOARD_HEIGHT; i++)
            {
                for (int j = 0; j < BOARD_WIDTH; j++)
                {
                    // First, check if the space is occupied by a piece of the same color.

                    BoardLocation targetLocation = new BoardLocation(i, j);
                       
                    boolean isOccupiedByTeam = this.getPieceAt(targetLocation).getColor() == TurnIndicator;
                    if (isOccupiedByTeam)
                    {
                        continue;
                    }
                    
                    boolean isPatternValid = selectedPiece.IsMovePatternValid(selectedLocation, targetLocation); 
                    if (!isPatternValid)
                    {
                        continue;// Check if there are any obstacles.
                    }
                    
                    boolean areThereObstacles = true;
                    if (areThereObstacles)
                    {
                        continue;
                    }
                    
                    // If the function makes it to this point, the move is valid, and can be added
                    // to the list of potential moves.
                    
                    
                }
            }
            
            return availableMoves;
        }
        else 
        {
            return null;
        }                
    }
    
    public void PerformMove(BoardLocation start, BoardLocation end)
    {
        Piece piece = this.getPieceAt(start);
        
        Move move = new Move(piece.getColor(), piece.getType(), start, end);
        this.Moves.add(move);

        //this.getPieceAt(start)
    }
}
