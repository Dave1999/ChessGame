/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chessgame.views;

/**
 *
 * @author David
 */
public interface IView 
{

    /**
     *
     * @param g
     */
    public void SetGame(chessgame.models.Game g);

    /**
     *
     */
    public void Display();
}
