/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame.models;

import chessgame.exceptions.UnknownColorException;
import chessgame.exceptions.UnknownTypeException;
import chessgame.models.Piece.TeamColor;
import chessgame.models.Piece.PieceType;
import static chessgame.models.Piece.PieceTypeToString;
import static chessgame.models.Piece.TeamColorToString;

/**
 *
 * @author David
 */
public class Move 
{
    //private final TeamColor Team;
    //private final PieceType Type; 
    private final BoardLocation StartLocation;
    private final BoardLocation EndLocation;
    private final Piece MovedPiece;
    private final Piece CapturedPiece;
    
    public Move(Piece movedPiece, Piece capturedPiece, BoardLocation start, BoardLocation end)
    {
        this.MovedPiece = movedPiece;
        this.CapturedPiece = capturedPiece;
        this.StartLocation = start;
        this.EndLocation = end;
    }
    
    public BoardLocation getStartLocation()
    {
        return this.StartLocation;
    }
    
    public BoardLocation getEndLocation()
    {
        return this.EndLocation;
    }
    
    public Piece getMovedPiece()
    {
        return this.MovedPiece;
    }
    
    public Piece getCapturedPiece()
    {
        return this.CapturedPiece;
    }
    
    public boolean wasPieceCaptured()
    {
        return this.CapturedPiece != null;
    }
    
    @Override
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
