/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame.exceptions;

/**
 *
 * @author David
 */
public class KingIsInCheckException extends RuntimeException 
{
    public KingIsInCheckException(String msg) 
    {
        super(msg);
    }
}
