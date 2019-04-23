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
public class Rook extends Piece {

    /**
     * Constructor for the Rook class.
     * @param color
     */
    public Rook(TeamColor color)
    {
        super(color);
        this.PieceType = PieceType.Rook;
    }
    
    /*
     * The Rook isMovePatternValid check only checks to make sure the End Location
     * is in the same row or the same column as the starting location and also checks
     * the intial checks from Piece.
     * @param rowStart
     * @param colStart
     * @param rowEnd
     * @param colEnd
     * @return true if the pattern is valid, false if it is not.
     */
    @Override
    public boolean isMovePatternValid(int rowStart, int colStart, int rowEnd, int colEnd)
    {
        if(super.isMovePatternValid(rowStart, colStart, rowEnd, colEnd) == false)
        {
            return false;
        }
        
         //Checks to make sure the rowEnd or colEnd is the Same as starting location
        else if((rowEnd == rowStart) || (colEnd == colStart))
        {
            return true;
        }
        
        //Default return of false
        else
        {
            return false;
        }
    }
}
