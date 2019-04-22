package chessgame.models;

import chessgame.exceptions.KingIsInCheckException;
import java.util.Timer;
import chessgame.exceptions.ObstacleCheckException;
import chessgame.models.Piece.PieceType;
import static chessgame.models.Piece.PieceType.King;
import static chessgame.models.Piece.PieceType.Knight;
import static chessgame.models.Piece.PieceType.Pawn;
import static chessgame.models.Piece.PieceType.Rook;
import static chessgame.models.Piece.PieceType.Queen;
import static chessgame.models.Piece.PieceType.Bishop;
import java.util.ArrayList;
import chessgame.models.Piece.TeamColor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author David
 */
public class Game 
{  
    public static final int BOARD_HEIGHT = 8;
    public static final int BOARD_WIDTH = 8;
    
    private Piece[][] m_Pieces;
    private int TurnCount;
    private TeamColor TurnIndicator;
    private ArrayList<Move> Moves; 
    private ArrayList<BoardLocation> ValidMoves;
    
    public Game()
    {
        // Call the InitializeGameState function to initialize a new game.
        this.InitializeGameState();
    }
    
    /** 
     * Initializes the game board to the default game state, by creating all the Piece objects and 
     * placing them where they belong.
     */
    public final void InitializeGameState()
    {
        this.Moves = new ArrayList<>();
        this.m_Pieces = new Piece[BOARD_HEIGHT][BOARD_WIDTH];
        this.TurnIndicator = TeamColor.White;
        this.TurnCount = 0;
        this.CreatePieces();
        // The turn count will be incremented after the first turn is played.
    }
    
    /**
     * Creates the game-pieces and places them in their default positions.
     */
    private void CreatePieces()
    {
        // Start out by initializing the pieces to all empty, in case the Game object is being reused.
        for (int i = 0; i < BOARD_WIDTH; i++)
        {
            for (int k = 0; k < BOARD_HEIGHT; k++)
            {
                this.m_Pieces[i][k] = null;
            }
        }
        
        // Construct the game board, starting at (0, 0) (the top left corner) and progressing downward.
        // Black back row.
        this.m_Pieces[0][0] = new Rook(TeamColor.Black);
        this.m_Pieces[0][1] = new Knight(TeamColor.Black);
        this.m_Pieces[0][2] = new Bishop(TeamColor.Black);
        this.m_Pieces[0][3] = new Queen(TeamColor.Black);
        this.m_Pieces[0][4] = new King(TeamColor.Black);
        this.m_Pieces[0][5] = new Bishop(TeamColor.Black);
        this.m_Pieces[0][6] = new Knight(TeamColor.Black);
        this.m_Pieces[0][7] = new Rook(TeamColor.Black);
        
        // Black front row - pawns.
        this.m_Pieces[1][0] = new Pawn(TeamColor.Black);
        this.m_Pieces[1][1] = new Pawn(TeamColor.Black);
        this.m_Pieces[1][2] = new Pawn(TeamColor.Black);
        this.m_Pieces[1][3] = new Pawn(TeamColor.Black);
        this.m_Pieces[1][4] = new Pawn(TeamColor.Black);
        this.m_Pieces[1][5] = new Pawn(TeamColor.Black);
        this.m_Pieces[1][6] = new Pawn(TeamColor.Black);
        this.m_Pieces[1][7] = new Pawn(TeamColor.Black);
        
        // White front row - pawns.
        this.m_Pieces[6][0] = new Pawn(TeamColor.White);
        this.m_Pieces[6][1] = new Pawn(TeamColor.White);
        this.m_Pieces[6][2] = new Pawn(TeamColor.White);
        this.m_Pieces[6][3] = new Pawn(TeamColor.White);
        this.m_Pieces[6][4] = new Pawn(TeamColor.White);
        this.m_Pieces[6][5] = new Pawn(TeamColor.White);
        this.m_Pieces[6][6] = new Pawn(TeamColor.White);
        this.m_Pieces[6][7] = new Pawn(TeamColor.White);
        
        // White back row.
        this.m_Pieces[7][0] = new Rook(TeamColor.White);
        this.m_Pieces[7][1] = new Knight(TeamColor.White);
        this.m_Pieces[7][2] = new Bishop(TeamColor.White);
        this.m_Pieces[7][3] = new Queen(TeamColor.White);
        this.m_Pieces[7][4] = new King(TeamColor.White);
        this.m_Pieces[7][5] = new Bishop(TeamColor.White);
        this.m_Pieces[7][6] = new Knight(TeamColor.White);
        this.m_Pieces[7][7] = new Rook(TeamColor.White);
    }
    
    /**
     * Updates the turn indicator and increments the turn count (if necessary).
     */
    public void UpdateTurn()
    {
        if (this.TurnIndicator == TeamColor.Black)
        {
            this.TurnIndicator = TeamColor.White;
        }
        else 
        {
            this.TurnCount++;
            this.TurnIndicator = TeamColor.Black; 
        }
    }     
    
    public Piece[][] getPieces() 
    {
        return this.m_Pieces;
    }
    
    /**
     * Returns the Piece object at the specified BoardLocation.
     * @param location the location of the desired Piece.
     * @return 
     */
    public Piece getPieceAt(BoardLocation location)
    {
        return m_Pieces[location.getRow()][location.getColumn()];
    }
    
    public boolean isSpaceEmpty(BoardLocation location)
    {
        return (getPieceAt(location) == null);
    }
    
    public void setPieceAt(BoardLocation location, Piece piece)
    {
        m_Pieces[location.getRow()][location.getColumn()] = piece;
    }
    
    public TeamColor getTurnIndicator()
    {
        return this.TurnIndicator;
    }
    
    public int getTurnCount()
    {
        return this.TurnCount;
    }

    public Move getLastMove()
    {
        if (this.Moves.isEmpty())
        {
            return null;
        }
        else 
        {
            return this.Moves.get(this.Moves.size() - 1);
        }
    }
    
    /**
     * Returns the list of available moves from the given location.
     * @param selectedLocation
     * @return 
     */
    public ArrayList<BoardLocation> getAvailableMoves(BoardLocation selectedLocation)
    {
        ArrayList<BoardLocation> availableMoves = new ArrayList<>();
        Piece selectedPiece = this.getPieceAt(selectedLocation);
        
        // Only permit the move if the piece is of the correct color.
        if (selectedPiece.getColor() == TurnIndicator)
        {
            for (int i = 0; i < BOARD_HEIGHT; i++)
            {
                for (int j = 0; j < BOARD_WIDTH; j++)
                {
                    BoardLocation targetLocation = new BoardLocation(i, j);
                       
                    if (isMoveValid(selectedLocation, selectedPiece, targetLocation))
                    {
                        availableMoves.add(targetLocation);
                    }
                }
            }
            
            this.ValidMoves = availableMoves;
            return availableMoves;
        }
        else 
        {
            return null;
        }                
    }
    
    /**
     * Returns 'true' if the specified move is valid, given a start location, its corresponding Piece
     * object, and a target location.
     * @param selectedLocation the location from which the move is being made.
     * @param selectedPiece the selected Piece object.
     * @param targetLocation the location to which the move is being made.
     * @return 
     */
    public boolean isMoveValid(BoardLocation selectedLocation, Piece selectedPiece, BoardLocation targetLocation)
    {
        // First, check if the space is occupied by a piece of the same color.
        Piece pieceAtTarget = this.getPieceAt(targetLocation);
        
        if (pieceAtTarget != null)
        {
            if (pieceAtTarget.getColor() == TurnIndicator)
            {
                return false;
            }
        }
        
        if (selectedPiece.getColor() != TurnIndicator)
        {
            return false;
        }

        boolean isPatternValid = selectedPiece.isMovePatternValid(
                selectedLocation.getRow(), 
                selectedLocation.getColumn(), 
                targetLocation.getRow(), 
                targetLocation.getColumn()); 
        
        if (!isPatternValid)
        {
            return false;
        }
        
        return !areThereObstacles(selectedLocation, selectedPiece, targetLocation);
    }

    public boolean hasGameStarted()
    {
        return this.Moves.size() != 0;
    }
    
    // Returns false if no obstacles
    private boolean areThereObstacles(BoardLocation selectedLocation, Piece selectedPiece, BoardLocation targetLocation) 
    {
        PieceType type = selectedPiece.getType();
   
        int colStart = selectedLocation.getColumn();
        int colEnd = targetLocation.getColumn();

        if (type != null)
        {
            // Special rules for Pawn pieces.
            switch (type)
            {
                case Knight:
                case King:
                    return false;
                case Pawn:
                    if (colStart == colEnd)
                    {
                        // The move is vertical. Therefore, if there is a piece in front of the pawn, it is invalid.
                        return !this.isSpaceEmpty(targetLocation);
                    }
                    else
                    {
                        // The move is diagonal. Therefore, there are "obstacles" to the pawn's progress if the
                        // space is empty.
                        return this.isSpaceEmpty(targetLocation);
                    }
                case Queen:
                    return areThereHorizontalObstacles(selectedLocation, selectedPiece, targetLocation) &&
                           areThereVerticalObstacles(selectedLocation, selectedPiece, targetLocation) && 
                           areThereDiagonalObstacles(selectedLocation, selectedPiece, targetLocation);
                case Rook:
                    return areThereHorizontalObstacles(selectedLocation, selectedPiece, targetLocation) &&
                            areThereVerticalObstacles(selectedLocation, selectedPiece, targetLocation);
                case Bishop:
                    return areThereDiagonalObstacles(selectedLocation, selectedPiece, targetLocation);
                default:
                    break;
            }
        }
        
        throw new ObstacleCheckException("areThereObstacles() in class Game reached the end of the method without returning.");
    }
    
    public static TeamColor getOppositeColor(TeamColor color)
    {
        if (color == TeamColor.Black)
        {
            return TeamColor.White;
        }
        else 
        {
            return TeamColor.Black; 
        }
    }
    
    private boolean areThereVerticalObstacles(BoardLocation selectedLocation, Piece selectedPiece, BoardLocation targetLocation)
    {
        int rowStart = selectedLocation.getRow();
        int colStart = selectedLocation.getColumn();
        int rowEnd = targetLocation.getRow();
        int colEnd = targetLocation.getColumn();
        
        int i = rowStart;
        int k = colStart;
        boolean homeSpace = true; //This is used to make sure the piece doesn't think it's blocking itself
        
        //Vertical move
        if (colStart == colEnd)
        {
            if (rowStart > rowEnd)
            {
                //Movement up
                while (i > rowEnd)
                {
                    if (homeSpace == true) 
                    {
                        homeSpace = false;
                        i--;
                        continue;
                    }
                    else if (this.m_Pieces[i][k] == null) {}
                    else 
                    {
                        return true;
                    }
                    i--;
                }
                return false;
            }
            else if (rowStart < rowEnd)
            {
                //Movement down
                while (i < rowEnd)
                {
                    if (homeSpace == true) 
                    {
                        homeSpace = false;
                        i++;
                        continue;
                    }
                    else if (this.m_Pieces[i][k] == null) {}
                    else 
                    {
                        return true;
                    }
                    i++;
                }
                return false;
            }
        }
        else 
        {
            return true;
        }
        
        // Should never reach here.
        return true;
    }
    
    private boolean areThereHorizontalObstacles(BoardLocation selectedLocation, Piece selectedPiece, BoardLocation targetLocation)
    {
        int rowStart = selectedLocation.getRow();
        int colStart = selectedLocation.getColumn();
        int rowEnd = targetLocation.getRow();
        int colEnd = targetLocation.getColumn();
        
        int i = rowStart;
        int k = colStart;
        boolean homeSpace = true; //This is used to make sure the piece doesn't think it's blocking itself
        
        if (rowStart == rowEnd)
        {
            if (colStart > colEnd) 
            {
                //Movement left
                while (k > colEnd)
                {
                    if (homeSpace == true) 
                    {
                        homeSpace = false;
                        k--;
                        continue;
                    }
                    else if (this.m_Pieces[i][k] == null){}
                    else 
                    {
                        return true;
                    }
                    k--;
                }
                return false;
            }
            else if (colStart < colEnd) 
            {
                //Movement right
                while (k < colEnd)
                {
                    if (homeSpace == true) 
                    {
                        homeSpace = false;
                        k++;
                        continue;
                    }
                    else if (this.m_Pieces[i][k] == null)
                    {
                    }
                    else 
                    {
                        return true;
                    }
                    k++;
                }
                return false;
            }
        }
        else 
        {
            return true;
        }
        
        // Should never reach here.
        return true;
    }

    private boolean areThereDiagonalObstacles(BoardLocation selectedLocation, Piece selectedPiece, BoardLocation targetLocation)
    {
        int rowStart = selectedLocation.getRow();
        int colStart = selectedLocation.getColumn();
        int rowEnd = targetLocation.getRow();
        int colEnd = targetLocation.getColumn();
        
        int i = rowStart;
        int k = colStart;
        boolean homeSpace = true; //This is used to make sure the piece doesn't think it's blocking itself
        
        // If the move is not diagonal, then it's not valid, and obstacles exist.
        if (Math.abs(rowStart - rowEnd) != (Math.abs(colStart - colEnd)))
        {
            return true;
        }

        if (rowStart > rowEnd && colStart > colEnd)
        {
            //Movement northwest
            while (i > rowEnd && k > colEnd)
            {
                if (homeSpace == true) 
                {
                    homeSpace = false;
                    i--;
                    k--;
                    continue;
                }
                else if (this.m_Pieces[i][k] == null) {}
                else 
                {
                    return true;
                }
                i--;
                k--;
            }
            return false;
        }
        else if (rowStart < rowEnd && colStart > colEnd)
        {
            //Movement southwest
            while (i < rowEnd && k > colEnd)
            {
                if (homeSpace == true) 
                {
                    homeSpace = false;
                    i++;
                    k--;
                    continue;
                }
                else if (this.m_Pieces[i][k] == null) {}
                else 
                {
                    return true;
                }
                i++;
                k--;
            }
            return false;
        }
        else if (rowStart > rowEnd && colStart < colEnd)
        {
            //Movement northeast
            while (i > rowEnd && k < colEnd)
            {
                if (homeSpace == true) 
                {
                    homeSpace = false;
                    i--;
                    k++;
                    continue;
                }
                else if (this.m_Pieces[i][k] == null) {}
                else 
                {
                    return true;
                }
                i--;
                k++;
            }
            return false;
        }
        else if (rowStart < rowEnd && colStart < colEnd)
        {
            //Movement southeast
            while (i < rowEnd && k < colEnd)
            {
                if (homeSpace == true) 
                {
                    homeSpace = false;
                    i++;
                    k++;
                    continue;
                }
                else if (this.m_Pieces[i][k] == null) {}
                else 
                {
                    return true;
                }
                i++;
                k++;
            }
            return false;
        }
                
        // Should never reach here.
        return false;
    }
    
    private boolean doesKnightThreaten(BoardLocation start, BoardLocation end)
    {
        int rowStart = start.getRow();
        int colStart = start.getColumn();
        int rowEnd = end.getRow();
        int colEnd = end.getColumn();
        
        // The Knight can threaten from this position if the 
        return ((Math.abs(rowStart - rowEnd) == 2) && (Math.abs(colEnd - colStart) == 1)) || 
               ((Math.abs(rowStart - rowEnd) == 1) && (Math.abs(colEnd - colStart) == 2));
    }
    
    /** 
     * Auxiliary 
     * @param start
     * @param end
     * @return 
     */
    private boolean doesKingThreaten(BoardLocation start, BoardLocation end)
    {
        int rowStart = start.getRow();
        int colStart = start.getColumn();
        int rowEnd = end.getRow();
        int colEnd = end.getColumn();
        
        // If the two pieces are within 1 square in any direction, then     
        return (Math.abs(rowStart - rowEnd) <= 1) && (Math.abs(colStart - colEnd) <= 1);
    }
    
    /**
     * Performs a move based on the specified Move object, and stores it inside the game.
     * @param move the move to perform.
     * @return a status flag indicating whether or not the move worked.
     */
    public boolean PerformMove(Move move)
    {
        BoardLocation start = move.getStartLocation();
        BoardLocation end = move.getEndLocation();
        Piece selectedPiece = move.getMovedPiece();
        
        /* If, for some reason, the list of valid moves hasn't been computed in real-time, 
         * and the list doesn't contain the move, then re-calculate whether or not the move is valid. */
        if (ValidMoves.contains(move.getEndLocation()) || isMoveValid(start, selectedPiece, end))
        {
            // Actually make the move by moving pieces around within the array.
            this.setPieceAt(start, null);
            this.setPieceAt(end, selectedPiece);
            
            // Evaluate special moves.
            EvaluateSpecialMoves(start, selectedPiece, end);

            boolean isOffenseInCheck = isCheck(this.TurnIndicator);
            
            if (isOffenseInCheck)
            {
                // If the move resulted in the offense being in check, undo it. Then, return false.
                UndoMove(move);
                throw new KingIsInCheckException("Your king is in check!");
            }

            // Finally, add the move to the list of moves.
            this.Moves.add(move);
            
            // Update the turn counter and the turn indicator.
            this.UpdateTurn();
            return true;
        }
        else 
        {
            return false;
        }
    }
    
    /**
     * This function undoes a move, for example, if the game determines that performing the move 
     * puts the offensive team into check.
     * @param move the move to undo.
     */
    private void UndoMove(Move move)
    {
        this.setPieceAt(move.getStartLocation(), move.getMovedPiece());
        this.setPieceAt(move.getEndLocation(), move.getCapturedPiece());
        
        this.Moves.remove(move);
    }

    private void EvaluateSpecialMoves(BoardLocation selectedLocation, Piece selectedPiece, BoardLocation targetLocation)
    {
        // Check for pawn promotion.
        if (selectedPiece.getType() == Pawn)
        {
            if ((selectedPiece.getColor() == TeamColor.Black) && (targetLocation.getRow() == 7))
            {
                // If a Black pawn has reached the last row, promote it to a Queen.
                setPieceAt(targetLocation, new Queen(TeamColor.Black));
            }
            else if ((selectedPiece.getColor() == TeamColor.White) && (targetLocation.getRow() == 0))
            {
                // If a White pawn has reached the last row, promote it to a Queen.
                setPieceAt(targetLocation, new Queen(TeamColor.White));
            }
        }
        
        // Check for castling.
        
        // Check for en passant.
    }
    
    /**
     * Returns 'true' if the specified team is in check.
     * @param colorToCheck
     * @return 
     */
    public boolean isCheck(TeamColor colorToCheck)
    {
        try
        {
            TeamColor oppositeColor;
            if (colorToCheck == TeamColor.White)
            {
                oppositeColor = TeamColor.Black;
            }
            else 
            {
                oppositeColor = TeamColor.White;
            }

            BoardLocation kingLocation = FindKing(colorToCheck);

            for (int i = 0; i < 8; i++)
            {
                for (int k = 0; k < 8; k++)
                {
                    BoardLocation iteratedLocation = new BoardLocation(i, k);
                    Piece p = this.getPieceAt(iteratedLocation);

                    if ((p != null) && p.getColor() == oppositeColor)
                    {
                        PieceType type = p.getType();

                        if ((type == Queen) || (type == Bishop) || (type == Rook))
                        {
                            if (!areThereObstacles(iteratedLocation, p, kingLocation))
                            {
                                return true;
                            }
                        }
                        else if (type == Pawn)
                        {
                            // First, check if they are separated by only one column.
                            boolean doesThreaten = Math.abs(kingLocation.getColumn() - iteratedLocation.getColumn()) == 1; 

                            /* If the pawn is White, it will threaten the Black king when the pawn is on
                             * the row that is 1 greater than the king. For a Black pawn and a White 
                             * King, the pawn will threaten when its row is 1 less than the king's. */
                            if (p.getColor() == TeamColor.White)
                            {
                                doesThreaten &= ((iteratedLocation.getRow() - kingLocation.getRow()) == 1);
                            }
                            else 
                            {
                                doesThreaten &= ((kingLocation.getRow() - iteratedLocation.getRow()) == 1);
                            }
                                
                            if (doesThreaten)
                            {
                                return true;
                            }
                        }
                        else if (type == Knight)
                        {
                            /* If the piece is a Knight, return 'true' if it is in a position that threatens
                             * the King. */
                            if (doesKnightThreaten(iteratedLocation, kingLocation))
                            {
                                return true;
                            }
                        }
                        else if (type == King)
                        {
                            if (doesKingThreaten(iteratedLocation, kingLocation))
                            {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        
        return false;
    }
        
    /**
     * Returns the location of the specified color's King.
     * @param color the color whose king to check for. 
     * @return a BoardLocation holding the King's coordinates.
     */
    private BoardLocation FindKing(TeamColor color)
    {
        for (int i = 0; i < 8; i++)
        {
            for (int k = 0; k < 8; k++)
            {
                BoardLocation location = new BoardLocation(i, k);
                Piece p = this.getPieceAt(location);

                if (p != null)
                {
                    if ((p.getType() == King) && (p.getColor() == color))
                    {
                        return location;
                    }
                }
            }
        }
        
        return null;    
    }
}