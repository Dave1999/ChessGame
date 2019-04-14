/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame.models;

import chessgame.models.Piece.PieceType;
import chessgame.models.Piece.TeamColor;
import static chessgame.models.Piece.TeamColor.Black;
import static chessgame.models.Piece.TeamColor.White;
import javafx.scene.image.Image;

/**
 *
 * @author grady
 */

public class ImageManager 
{
    Image whiteKing;
    Image blackKing;
    Image whiteQueen;
    Image blackQueen;
    Image whiteBishop;
    Image blackBishop;
    Image whiteRook;
    Image blackRook;
    Image whiteKnight;
    Image blackKnight;
    Image whitePawn;
    Image blackPawn;
    
    public ImageManager() 
    {
        whiteKing = new Image(getClass().getResourceAsStream("/Images/White King.png"));
        whiteQueen = new Image(getClass().getResourceAsStream("/Images/White Queen.png"));
        whiteBishop = new Image(getClass().getResourceAsStream("/Images/White Bishop.png"));
        whiteRook = new Image(getClass().getResourceAsStream("/Images/White Rook.png"));
        whiteKnight = new Image(getClass().getResourceAsStream("/Images/White Knight.png"));
        whitePawn = new Image(getClass().getResourceAsStream("/Images/White Pawn.png"));

        blackKing = new Image(getClass().getResourceAsStream("/Images/Black King.png"));
        blackQueen = new Image(getClass().getResourceAsStream("/Images/Black Queen.png"));
        blackBishop = new Image(getClass().getResourceAsStream("/Images/Black Bishop.png"));
        blackRook = new Image(getClass().getResourceAsStream("/Images/Black Rook.png"));
        blackKnight = new Image(getClass().getResourceAsStream("/Images/Black Knight.png"));
        blackPawn = new Image(getClass().getResourceAsStream("/Images/Black Pawn.png"));
    }
    
    public Image getImage(TeamColor pieceColor, PieceType pieceType) {
        
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
   