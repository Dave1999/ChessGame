/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame.models;
        
import chessgame.exceptions.BoardLocationException;

/**
 *
 * @author samuel
 */
public class BoardLocation 
{
    final int MAX_WIDTH = 8;
    final int MAX_HIGHT = 8;
    private int Row;
    private int Column;
    
    public BoardLocation (int row, int column)
    {
       if((row > MAX_WIDTH) || (row < 0))
           throw new BoardLocationException("Error, Row not on board");
       if((column > MAX_HIGHT) || (column < 0))
           throw new BoardLocationException("Error, Column not on board");
       
       Row = row;
       Column = column;
    }
    
    public int getRow()
    {
        return Row;
    }

    public int getColumn() 
    {
        return Column;
    }
    
    @Override
    public String toString()
    {
        return "(" + Integer.toString(this.Row) + ", " + Integer.toString(this.Column) + ")";
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (o == this)
        {
            return true;
        }
        if (!(o instanceof BoardLocation))
        {
            return false;
        }
        
        BoardLocation location = (BoardLocation) o;
        return (this.Row == location.getRow()) && (this.Column == location.getColumn());
    }
}
