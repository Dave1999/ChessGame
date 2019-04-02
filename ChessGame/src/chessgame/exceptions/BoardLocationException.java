/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame.exceptions;

/**
 *
 * @author samuel
 */
public class BoardLocationException extends RuntimeException 
{
    public BoardLocationException(String msg) 
    {
        super(msg);
    }
}
