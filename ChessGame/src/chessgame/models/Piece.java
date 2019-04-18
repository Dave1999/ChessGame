/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame.models;

import chessgame.exceptions.UnknownColorException;
import chessgame.exceptions.UnknownTypeException;

/**
 *
 * @author Dalton
 */
public class Piece 
{
    public enum PieceType
    {
        Rook, Bishop, King, Queen, Pawn, Knight
    }
     
    public enum TeamColor
    {
       Black, White
    }
     
    protected PieceType PieceType;  
    protected TeamColor PieceColor;
     
    public Piece(TeamColor color)
    {
        PieceColor = color;
    }
     
    public Piece(PieceType pieceType, TeamColor color)
    {
        PieceType = pieceType;
        PieceColor = color;
    }
    
    // Returns the Color of the Piece
    public TeamColor getColor()
    {
        return PieceColor;
    }
    
    //Returns the Piece Type
    public PieceType getType()
    {
         return PieceType;
    }
    
    /*The is MoveValidPattern function present in Piece makes some intial checks
    to make sure the piece is not trying to move to its current space or it is not
    trying to move to a space that is out of bounds.The isMovePatternValid takes an
    input of the current location of the piece in two coordinates the row and col
    along with the ending location in two coordinates for row and column.
    */
    public boolean isMovePatternValid(int rowStart, int colStart, int rowEnd, int colEnd)
    {
        if((rowStart == rowEnd) && (colStart == colEnd))
        {
            return false;
        }
        else if((rowEnd < 0) || (rowEnd > 7) || (colEnd < 0) || (colEnd > 7))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public static String TeamColorToString(TeamColor color)
    {
        switch (color)
        {
            case Black:
                return "Black";
            case White:
                return "White";
            default:
                throw new UnknownColorException("Invalid team color.");
        }
    }
    
    public static String PieceTypeToString(PieceType type)
    {
        switch (type)
        {
            case Rook:
                return "Rook";
            case Bishop:
                return "Bishop";
            case King:
                return "King";                
            case Queen:
                return "Queen";                
            case Pawn:
                return "Pawn";                
            case Knight:
                return "Knight";                
            default:
                throw new UnknownTypeException("Unknown piece type");
        }
    }
}
