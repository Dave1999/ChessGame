/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame.models;

import static chessgame.models.Piece.PieceType.Knight;

/**
 *
 * @author Dalton
 */
public class Knight extends Piece 
{
    public Knight(TeamColor color) 
    {
        super(color);
        this.PieceType = Knight;
    }
    
    /*
    The Knight isMovePatternValid function is the same as the other subclasses and
    intially checks to make sure their was not a false return from the intial checks
    in Piece. The isMovePatternValid then goes through its move pattern checks for
    the Knight piece and if any trigger true then the piece is able to move to that square.
    */
    @Override
     public boolean isMovePatternValid(int rowStart, int colStart, int rowEnd, int colEnd)
     {
        if(super.isMovePatternValid(rowStart, colStart, rowEnd, colEnd)==false)
        {
            return false;
        }
        //Checks over 2 to the Right and Up 1 tile
        else if((rowEnd == (rowStart + 1)) && (colEnd == (colStart + 2)))
        {
            return true;
        }
        //Checks Up 1 over to the Left 2
        else if((rowEnd == rowStart + 1) && (colEnd == colStart - 2))
        {
            return true;
        }
        //Checks Up 2 and over to the Right 1
        else if((rowEnd == (rowStart + 2)) && (colEnd == (colStart + 1)))
        {
            return true;
        }
        //Checks Up 2 and over to the Left 1
        else if((rowEnd == (rowStart + 2) && (colEnd == colStart - 1)))
        {
            return true;
        }
        //Checks Down 1 and over to the Right 2
        else if((rowEnd == (rowStart - 1)) && (colEnd == (colStart + 2)))
        {
            return true;
        }
        //Checks Down 1 and over to the Left 2
        else if((rowEnd == (rowStart - 1)) && (colEnd == (colStart - 2)))
        {
            return true;
        }
        //Checks Down 2 and over to the Right 1
        else if((rowEnd == (rowStart - 2)) && (colEnd == (colStart + 1)))
        {
            return true;
        }
        //Checks Down 2 and over to the Left 1
        else if ((rowEnd == (rowStart - 2)) && (colEnd == (colStart - 1)))
        {
            return true;
        }
        //If no flags trigger returns false
        else return false;
    }
     //MAY STILL NEED LOGIC FOR TAKING A PIECE
}
