/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame.models;

import chessgame.models.Piece.PieceType;
import chessgame.models.Game.TeamColor;
import static chessgame.models.Game.TeamColor.Black;
import static chessgame.models.Game.TeamColor.White;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author grady
 */


public class ImageManager 
{
    BufferedImage whiteKing;
    BufferedImage blackKing;
    BufferedImage whiteQueen;
    BufferedImage blackQueen;
    BufferedImage whiteBishop;
    BufferedImage blackBishop;
    BufferedImage whiteRook;
    BufferedImage blackRook;
    BufferedImage whiteKnight;
    BufferedImage blackKnight;
    BufferedImage whitePawn;
    BufferedImage blackPawn;
    
    BufferedImage image;
    Graphics g;
    
    public ImageManager() throws IOException {
        
        whiteKing = ImageIO.read(new File("/White King.png"));
        whiteQueen = ImageIO.read(new File("/White Queen.png"));
        whiteBishop = ImageIO.read(new File("/White Bishop.png"));
        whiteRook = ImageIO.read(new File("/White Rook.png"));
        whiteKnight = ImageIO.read(new File("/White Knight.png"));
        whitePawn = ImageIO.read(new File("/White Pawn.png"));

        blackKing = ImageIO.read(new File("/Black King.png"));
        blackQueen = ImageIO.read(new File("/Black Queen.png"));
        blackBishop = ImageIO.read(new File("/Black Bishop.png"));
        blackRook = ImageIO.read(new File("/Black Rook.png"));
        blackKnight = ImageIO.read(new File("/Black Knight.png"));
        blackPawn = ImageIO.read(new File("/Black Pawn.png"));
    }
    
    public BufferedImage getImage(TeamColor pieceColor, PieceType pieceType) {
        
        switch(pieceType) {
            case Pawn:
                if (pieceColor == White) { //White
                    return whitePawn;
                }
                else if (pieceColor == Black) { //Black
                    return blackPawn;
                }
                else
                    //throw new UnknownColorException;
                break;
            case Knight:
                if (pieceColor == White) { //White
                    return whiteKnight;
                }
                else if (pieceColor == Black) { //Black
                    return blackKnight;
                }
                else
                    //throw new UnknownColorException;
                break;
            case Rook:
                if (pieceColor == White) { //White
                    return whiteRook;
                }
                else if (pieceColor == Black) { //Black
                    return blackRook;
                }
                else
                    //throw new UnknownColorException;
                break;
            case Bishop:
                if (pieceColor == White) { //White
                    return whiteBishop;
                }
                else if (pieceColor == Black) { //Black
                    return blackBishop;
                }
                else
                    //throw new UnknownColorException;
                break;
            case Queen:
                if (pieceColor == White) { //White
                    return whiteQueen;
                }
                else if (pieceColor == Black) { //Black
                    return blackQueen;
                }
                else
                    //throw new UnknownColorException;
                break;
            case King:
                if (pieceColor == White) { //White
                    return whiteKing;
                }
                else if (pieceColor == Black) { //Black
                    return blackKing;
                }
                else
                    //throw new UnknownColorException;
                break;
            default:
                System.out.println("Unknown type");
                //throw new UnknownTypeException
                break;
        }
        
        return null;
    }
}
   