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
public class Board 
{
    private final int HEIGHT = 8;
    private final int WIDTH = 8;

    private Piece[][] m_Pieces;
    
    public Board()
    {
        this.m_Pieces = new Piece[HEIGHT][WIDTH];
    }
    
    /** 
     * Initializes the game board to the default game state, by creating all the Piece objects and 
     * placing them where they belong.
     */
    // Initializes the game board to the d
    public void InitializeGameState()
    {

    }
}
