/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame.models;

/**
 *
 * @author David
 */
public abstract class Piece 
{
    public enum PieceType { King, Queen, Bishop, Rook, Knight, Pawn };
    
    public Piece()
    {
        
    }
    
    public abstract Boolean IsMovePatternValid(BoardLocation current, BoardLocation end);
}
