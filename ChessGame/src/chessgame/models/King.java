/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame.models;

import static chessgame.models.Piece.PieceType.King;

/**
 *
 * @author Dalton
 */
public class King extends Piece 
{
    public King(TeamColor color) 
    {
        super(color);
        this.PieceType = King;
    }
    
    /*
    The King isMovePatternValid function returns true for the each tile 1 away from
    the King either diagonal, forward,backward, or on each side.
    */
    @Override
     public boolean isMovePatternValid(int rowStart, int colStart, int rowEnd, int colEnd)
     {
        if(super.isMovePatternValid(rowStart, colStart, rowEnd, colEnd) == false)
        {
            return false;
        }
        //Returns true for tile in front of the king
        else if((colEnd == colStart) && (rowEnd == (rowStart + 1)))
        {
            return true;
        }
        //Returns true for tile behind the king
        else if((colEnd == colStart) && (rowEnd == (rowStart - 1)))
        {
            return true;
        }
        //Returns true for tile to the right of the king
        else if((colEnd == (colStart + 1)) && (rowEnd == rowStart))
        {
            return true;
        }
        //Returns true for the tile to the left of the king
        else if((colEnd == (colStart - 1)) && (rowEnd == rowStart))
        {
            return true;
        }
        //Returns true for the tile diagonal up left of the king
        else if((colEnd == (colStart - 1)) && (rowEnd == (rowStart + 1)))
        {
            return true;
        }
        //Returns true for tile diagonal down left of king
        else if((colEnd == (colStart - 1)) && (rowEnd == (rowStart - 1)))
        {
            return true;
        }
        //Returns true for tile diagonal up right of the king
        else if((colEnd == (colStart + 1)) && (rowEnd == (rowStart + 1)))
        {
            return true;
        }
        //returns true for tile diagonal down right of the king
        else if((colEnd == (colStart + 1)) && (rowEnd == (rowStart - 1)))
        {
            return true;
        }
        //Default return if no flag was triggered
        else
        {
            return false;
        }
    }
}
