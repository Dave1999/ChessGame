package chessgame.models;

import java.util.ArrayList;
import chessgame.models.Piece.TeamColor;

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
    public static final int BOARD_HEIGHT = 8;
    public static final int BOARD_WIDTH = 8;
    
    private Piece[][] m_Pieces;
    private int TurnCount;
    private TeamColor TurnIndicator;
    private ArrayList<Move> Moves; 
    
    public Game()
    {
        this.Moves = new ArrayList<Move>();
        this.m_Pieces = new Piece[BOARD_HEIGHT][BOARD_WIDTH];
    }
    
    /** 
     * Initializes the game board to the default game state, by creating all the Piece objects and 
     * placing them where they belong.
     */
    // Initializes the game board to the d
    public final void InitializeGameState()
    {
        this.TurnIndicator = TeamColor.White;
        
        this.CreatePieces();
    }
    
    private void CreatePieces()
    {
         // Construct the game board, starting at (0, 0) (the top left corner) and progressing downward.
        // Black back row.
        this.m_Pieces[0][0] = new Rook(TeamColor.Black);
        this.m_Pieces[0][1] = new Knight(TeamColor.Black);
        this.m_Pieces[0][2] = new Bishop(TeamColor.Black);
        this.m_Pieces[0][3] = new Queen(TeamColor.Black);
        this.m_Pieces[0][4] = new King(TeamColor.Black);
        this.m_Pieces[0][5] = new Bishop(TeamColor.Black);
        this.m_Pieces[0][6] = new Knight(TeamColor.Black);
        this.m_Pieces[0][7] = new Rook(TeamColor.Black);
        
        // Black front row - pawns.
        this.m_Pieces[1][0] = new Pawn(TeamColor.Black);
        this.m_Pieces[1][1] = new Pawn(TeamColor.Black);
        this.m_Pieces[1][2] = new Pawn(TeamColor.Black);
        this.m_Pieces[1][3] = new Pawn(TeamColor.Black);
        this.m_Pieces[1][4] = new Pawn(TeamColor.Black);
        this.m_Pieces[1][5] = new Pawn(TeamColor.Black);
        this.m_Pieces[1][6] = new Pawn(TeamColor.Black);
        this.m_Pieces[1][7] = new Pawn(TeamColor.Black);
        
        // White front row - pawns.
        this.m_Pieces[6][0] = new Pawn(TeamColor.White);
        this.m_Pieces[6][1] = new Pawn(TeamColor.White);
        this.m_Pieces[6][2] = new Pawn(TeamColor.White);
        this.m_Pieces[6][3] = new Pawn(TeamColor.White);
        this.m_Pieces[6][4] = new Pawn(TeamColor.White);
        this.m_Pieces[6][5] = new Pawn(TeamColor.White);
        this.m_Pieces[6][6] = new Pawn(TeamColor.White);
        this.m_Pieces[6][7] = new Pawn(TeamColor.White);
        
        // White back row.
        this.m_Pieces[7][0] = new Rook(TeamColor.White);
        this.m_Pieces[7][1] = new Knight(TeamColor.White);
        this.m_Pieces[7][2] = new Bishop(TeamColor.White);
        this.m_Pieces[7][3] = new Queen(TeamColor.White);
        this.m_Pieces[7][4] = new King(TeamColor.White);
        this.m_Pieces[7][5] = new Bishop(TeamColor.White);
        this.m_Pieces[7][6] = new Knight(TeamColor.White);
        this.m_Pieces[7][7] = new Rook(TeamColor.White);
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
                    BoardLocation targetLocation = new BoardLocation(i, j);
                       
                    if (isMoveValid(selectedLocation, selectedPiece, targetLocation))
                    {
                        availableMoves.add(targetLocation);
                    }
                }
            }
            
            return availableMoves;
        }
        else 
        {
            return null;
        }                
    }
    
    public boolean isMoveValid(BoardLocation selectedLocation, Piece selectedPiece, BoardLocation targetLocation)
    {
        // First, check if the space is occupied by a piece of the same color.
        Piece pieceAtTarget = this.getPieceAt(targetLocation);
        
        if (pieceAtTarget != null)
        {
            if (pieceAtTarget.getColor() == TurnIndicator)
            {
                return false;
            }
        }
        
        if (selectedPiece.getColor() != TurnIndicator)
        {
            return false;
        }

        boolean isPatternValid = selectedPiece.isMovePatternValid(
                selectedLocation.getRow(), 
                selectedLocation.getColumn(), 
                targetLocation.getRow(), 
                targetLocation.getColumn()); 

        // Temporary hack for testing!
        return isPatternValid;
        /*
        if (!isPatternValid)
        {
            return false;
        }

        boolean areThereObstacles = true;
        
        if (areThereObstacles)
        {
            return false;
        }

        // If the function makes it to this point, the move is valid.
        return true;
        */
    }
    
    public boolean PerformMove(BoardLocation start, BoardLocation end)
    {
        Piece selectedPiece = this.getPieceAt(start);
        
        if (isMoveValid(start, selectedPiece, end))
        {
            this.setPieceAt(start, null);
            this.setPieceAt(end, selectedPiece);
            
            Move move = new Move(selectedPiece.getColor(), selectedPiece.getType(), start, end);
            this.Moves.add(move);

            //this.getPieceAt(start)
            this.UpdateTurn();
            return true;
        }
        else 
        {
            return false;
        }
    }
}
