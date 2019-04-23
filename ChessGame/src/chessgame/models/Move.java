/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame.models;

import static chessgame.models.Piece.PieceTypeToString;
import static chessgame.models.Piece.TeamColorToString;

/**
 *
 * @author David
 */
public class Move 
{
    private final BoardLocation StartLocation;
    private final BoardLocation EndLocation;
    private final Piece MovedPiece;
    private final Piece CapturedPiece;
    
    /**
     * Constructor for the Move class.
     * @param movedPiece
     * @param capturedPiece
     * @param start
     * @param end
     */
    public Move(Piece movedPiece, Piece capturedPiece, BoardLocation start, BoardLocation end)
    {
        this.MovedPiece = movedPiece;
        this.CapturedPiece = capturedPiece;
        this.StartLocation = start;
        this.EndLocation = end;
    }
    
    /**
     * Returns the initial location of the moved piece.
     * @return a BoardLocation holding the start location.
     */
    public BoardLocation getStartLocation()
    {
        return this.StartLocation;
    }
    
    /**
     * Returns the ending location of the moved piece.
     * @return a BoardLocation holding the end location.
     */
    public BoardLocation getEndLocation()
    {
        return this.EndLocation;
    }
    
    /**
     * Returns the Piece to be moved during the Move.
     * @return a Piece object holding the Piece that was moved.
     */
    public Piece getMovedPiece()
    {
        return this.MovedPiece;
    }
    
    /**
     * Returns the Piece to be captured during the Move. This value is stored
     * in case the move needs to be undone.
     * @return a Piece object holding the Piece that was captured. 
     */
    public Piece getCapturedPiece()
    {
        return this.CapturedPiece;
    }
    
    /**
     * Returns 'true' if a piece was captured as part of the move.
     * @return
     */
    public boolean wasPieceCaptured()
    {
        return this.CapturedPiece != null;
    }
    
    @Override
    /**
     * Returns a string version of the Move.
     */
    public String toString()
    {
        String moveStr = "Moved " + TeamColorToString(this.MovedPiece.getColor()) + " " + 
                PieceTypeToString(this.MovedPiece.getType()) + " from location " +
                this.StartLocation.toString() + " to location " + 
                this.EndLocation.toString();
        
        if (this.wasPieceCaptured())
        {
            moveStr += ", capturing a " + TeamColorToString(this.CapturedPiece.getColor()) + " " + PieceTypeToString(this.CapturedPiece.getType());
        }
        
        moveStr += ".";
        return moveStr;       
    }
}
