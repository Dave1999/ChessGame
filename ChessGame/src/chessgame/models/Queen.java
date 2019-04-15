/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame.models;

/**
 *
 * @author Dalton
 */
public class Queen extends Piece 
{
    public Queen(TeamColor color) 
    {
        super(color);
        this.PieceType = PieceType.Queen;
    }
    
    /*
    The Queen isMovePatternValid function returns true for the spaces that are within
    the queens move pattern which is the Rook and Bishop's move pattern. The Queen
    makes the same checks and the Rook and Bishop Pieces along with the intial checks from
    Piece.
    */
    @Override
    public boolean isMovePatternValid(int rowStart, int colStart, int rowEnd, int colEnd)
    {
        //boolean bValue=false;
        if(super.isMovePatternValid(rowStart, colStart, rowEnd, colEnd)==false)
        {
            return false;
        }
        
        //The Check for same row and col as starting position (Rook Check)
        else if((rowEnd == rowStart) || (colEnd == colStart))
        {
             return true;
        }
        //Check for the Diagonal Spaces forward and back from current location (Bishop Check)
        else
        {
            // Return true if the square is a diagonal from the current position.
            return Math.abs(rowEnd-rowStart) == Math.abs(colEnd-colStart);
        }
    }   
}
