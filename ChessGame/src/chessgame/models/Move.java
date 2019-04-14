/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame.models;

import chessgame.models.Piece.TeamColor;
import chessgame.models.Piece.PieceType;

/**
 *
 * @author David
 */
public class Move 
{
    private final TeamColor Team;
    private final PieceType Type; 
    private final BoardLocation StartLocation;
    private final BoardLocation EndLocation;
    
    public Move(TeamColor team, PieceType type, BoardLocation start, BoardLocation end)
    {
        this.Team = team;
        this.Type = type;
        this.StartLocation = start;
        this.EndLocation = end;
    }
    
    public TeamColor getTeam()
    {
        return this.Team;
    }
    
    public PieceType getType()
    {
        return this.Type;
    }
    
    public BoardLocation getStartLocation()
    {
        return this.StartLocation;
    }
    
    public BoardLocation getEndLocation()
    {
        return this.EndLocation;
    }
    
    public String toString()
    {
        return "";
    }
}
