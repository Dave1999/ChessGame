/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame.controllers;

import chessgame.models.Game;
import static chessgame.models.Game.TeamColor.White;
import chessgame.models.ImageManager;
import chessgame.models.Piece;
import javafx.scene.layout.StackPane;

/**
 *
 * @author David and Grady.
 */

public class GameController extends javafx.scene.layout.StackPane
{
    private ImageManager images;
    private Game game;
    private boolean IsPieceSelected;
    
    public GameController() 
    {
        super();
        
        for (int i = 0; i < 8; i++) 
        {
            for (int k = 0; k < 8; k++) 
            {
                
                //boardCoords[i][k] = new JPanel();
            }
        }
    }
     
    public void PieceButton_Click()
    {
        // If a piece is already selected, 
    }
    
    public void Display()
    { 
        /*
        Piece[][] pieces = this.game.getPieces();
        
        for(int i = 0; i < 8; i++) 
        {
            for(int k = 0; k < 8; k++) 
            {
                if(pieces[i][k] == null)
                    continue;
                
                switch(pieces[i][k].getType()) {
                    
                    case Pawn:
                        if (pieces[i][k].getColor() == White) { //White
                            super.paintComponent(g);
                            g.drawImage(images.getImage(pieces[i][k].getColor(),
                                    pieces[i][k].getType()), 300 * i, 300 * k, 300, 300, null);
                        }
                        else if (pieces[i][k].getColor() == Black) { //Black
                            super.paintComponent(g);
                            g.drawImage(images.getImage(pieces[i][k].getColor(), 
                                    pieces[i][k].getType()), 300 * i, 300 * k, 300, 300, null);
                        }
                        else
                            //throw new UnknownColorException;
                        break;
                        
                    case Knight:
                        if (pieces[i][k].getColor() == White) { //White
                            super.paintComponent(g);
                            g.drawImage(images.getImage(pieces[i][k].getColor(), 
                                    pieces[i][k].getType()), 300 * i, 300 * k, 300, 300, null);
                        }
                        else if (pieces[i][k].getColor() == Black) { //Black
                            super.paintComponent(g);
                            g.drawImage(images.getImage(pieces[i][k].getColor(), 
                                    pieces[i][k].getType()), 300 * i, 300 * k, 300, 300, null);
                        }
                        else
                            //throw new UnknownColorException;
                        break;
                        
                    case Rook:
                        if (pieces[i][k].getColor() == White) { //White
                            super.paintComponent(g);
                            g.drawImage(images.getImage(pieces[i][k].getColor(), 
                                    pieces[i][k].getType()), 300 * i, 300 * k, 300, 300, null);
                        }
                        else if (pieces[i][k].getColor() == Black) { //Black
                            super.paintComponent(g);
                            g.drawImage(images.getImage(pieces[i][k].getColor(), 
                                    pieces[i][k].getType()), 300 * i, 300 * k, 300, 300, null);
                        }
                        else
                            //throw new UnknownColorException;
                        break;
                        
                    case Bishop:
                        if (pieces[i][k].getColor() == White) { //White
                            super.paintComponent(g);
                            g.drawImage(images.getImage(pieces[i][k].getColor(), 
                                    pieces[i][k].getType()), 300 * i, 300 * k, 300, 300, null);
                        }
                        else if (pieces[i][k].getColor() == Black) { //Black
                            super.paintComponent(g);
                            g.drawImage(images.getImage(pieces[i][k].getColor(), 
                                    pieces[i][k].getType()), 300 * i, 300 * k, 300, 300, null);
                        }
                        else
                            //throw new UnknownColorException;
                        break;
                        
                    case Queen:
                        if (pieces[i][k].getColor() == White) { //White
                            super.paintComponent(g);
                            g.drawImage(images.getImage(pieces[i][k].getColor(), 
                                    pieces[i][k].getType()), 300 * i, 300 * k, 300, 300, null);
                        }
                        else if (pieces[i][k].getColor() == Black) { //Black
                            super.paintComponent(g);
                            g.drawImage(images.getImage(pieces[i][k].getColor(), 
                                    pieces[i][k].getType()), 300 * i, 300 * k, 300, 300, null);
                        }
                        else
                            //throw new UnknownColorException;
                        break;
                        
                    case King:
                        if (pieces[i][k].getColor() == White) { //White
                            super.paintComponent(g);
                            g.drawImage(images.getImage(pieces[i][k].getColor(), 
                                    pieces[i][k].getType()), 300 * i, 300 * k, 300, 300, null);
                        }
                        else if (pieces[i][k].getColor() == Black) { //Black
                            super.paintComponent(g);
                            g.drawImage(images.getImage(pieces[i][k].getColor(), 
                                    pieces[i][k].getType()), 300 * i, 300 * k, 300, 300, null);
                        }
                        else
                            //throw new UnknownColorException;
                        break;
                        
                    default:
                        System.out.println("Unknown type");
                        //throw new UnknownTypeException
                        break;
                }
            }
        }
        */
    }
}