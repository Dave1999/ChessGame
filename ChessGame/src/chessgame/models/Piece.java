/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame.models;

import chessgame.models.Game.TeamColor;

/**
 *
 * @author David
 */
public abstract class Piece 
{
    public enum PieceType { King, Queen, Bishop, Rook, Knight, Pawn };
    
    public PieceType Type;
    public TeamColor Color;
    
    public Piece()
    {
    }
    
    public abstract Boolean IsMovePatternValid(BoardLocation current, BoardLocation end);
    
    public PieceType getType()
    {
        return Type;
    }
    
    public TeamColor getColor()
    {
        return Color;
    }
}
