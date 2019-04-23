/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame.models;

import chessgame.models.Piece.TeamColor;
import static chessgame.models.Piece.PieceType.Bishop;

/**
 *
 * @author Dalton
 */
public class Bishop extends Piece 
{
    /** 
     * Constructor for the Bishop class
     * @param teamColor the color of the Bishop.
     */
    Bishop(TeamColor teamColor) 
    {
        super(teamColor);
        this.PieceType = Bishop;
    }
    
    /**
     * 
     * The isMovePatternValid for the Bishop subclass shows the spaces diagonal forward
     * and backward from the Bishops current space that the piece can move. First the
     * checks to make sure the initial checks did not return false from the Piece 
     * isMovePatternValid function. The function also returns a bValue of either true
     * or false depending on if one of the flags triggered setting bValue to true.
     * @param rowStart
     * @param colStart
     * @param rowEnd
     * @param colEnd
     * @return
     */
    @Override
    public boolean isMovePatternValid(int rowStart, int colStart, int rowEnd, int colEnd)
    {
        boolean bValue = false;
        if(super.isMovePatternValid(rowStart, colStart, rowEnd, colEnd) == false)
        {
            return false;
        }
        else
        {
            // Return true if the square is a diagonal from the current position.
            bValue = Math.abs(rowEnd-rowStart) == Math.abs(colEnd-colStart);
            return bValue;
        }
    }
}
