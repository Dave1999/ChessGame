/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame.models;

import static chessgame.models.Piece.PieceType.Pawn;

/**
 *
 * @author Dalton
 */
public class Pawn extends Piece 
{
    public Pawn(TeamColor teamColor) 
    {
        super(teamColor);
        this.PieceType = Pawn;
    }
     
    /*
    The Pawn subclass isMovePatternValid involves a check to see what the color of
    the pawn is then it sends it down the correct move pattern path. Since Pawns can
    only move forward that involves a different direction for the black and white 
    pieces therefore the code for the white pieces would be addition checks and
    the code for the black pieces would be subtraction checks. And if the pawn fits
    neither color or an error occurs then an exception is thrown for the pawn and it 
    returns false
    */
    @Override
    public boolean isMovePatternValid(int rowStart, int colStart, int rowEnd, int colEnd)
    {
        if(super.isMovePatternValid(rowStart, colStart, rowEnd, colEnd)==false)
        {
            return false;
        }
        
        // TODO: change pawn logic to account for the fact that the board is flipped ((0,0) is top left).
        //Checks to see if the Pawn is team color white
        else if(this.getColor() == TeamColor.White)
        {
            //Checks the tile in front of the Pawn
            if(colEnd == colStart && rowEnd == rowStart - 1){
                return true;
            }
            //Returns true if the pawn is on its first move and the end tile is 2 movement away
            else if(colEnd == colStart && rowEnd == rowStart-2 && rowStart == 6)
            {   
                return true;
            }
            
            //This checks returns true for the diagonal tile on the right side of the pawn.
            if(colEnd==colStart+1&&rowEnd==rowStart+1){
                return true;
            }
            
            //This check returns true for the diagonal tile on the left side of the pawn
            else if(colEnd==colStart-1&&rowEnd==rowStart+1)
            {
                return true;
            }
            
            //If none of these checks fit for the white Piece defaults to false
            else
            {
                return false;
            }
        }
        
        //Checks to see if the pawn is teamcolor black
        else if(this.getColor() == TeamColor.Black)
        {
            //Checks to see if the Piece is one tile in front of the black Piece
            if ((colEnd == colStart) && (rowEnd == (rowStart + 1)))
            {
                return true;
            }
            //Returns true if the space is 2 in front of the black piece and it hasnt moved
            else if ((colEnd == colStart) && (rowEnd == (rowStart + 2)) && (rowStart == 1))
            {
                return true;
            }
            //Returns true if the tile is diagonal down and left of the black Piece
            else if ((colEnd == (colStart - 1)) && (rowEnd == (rowStart - 1)))
            {
                return true;
            }
            //Returns true if the tile is diagonal down and right of the black Piece
            else if((colEnd == colStart + 1) && (rowEnd == rowStart - 1))
            {
                return true;
            }
            //Default return of false if none of the move patterns follow for the black pawn
            else
            {
                return false;
            } 
        }
        //Default return if it doesn't have a color
        else
        {
            return false; //Probably Should throw an Exception Here such as invalid color
        }
    }
}
