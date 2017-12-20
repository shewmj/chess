package chess;

import javax.swing.ImageIcon;

/**
 * Pawn is a type of chess piece that can only move one 
 * spot forwards or 2 spots only on the first move, and can 
 * attack 1 spot diagonal if an enemy piece is present. 
 * 
 * @author Matthew Shew
 * @version 1.0
 */
public class Pawn extends ChessPiece {
    
    private boolean moved;
    private String direction;
    
    /**
     * Constructor for a Pawn object. 
     * Initializes location and team of the Chess piece. 
     * 
     * @param x1 x-coordinate on the board
     * @param y1 y-coordinate on the board
     * @param player Player who owns the chess piece
     */
    public Pawn(int x1, int y1, Player player, String name) {
        super(x1, y1, player, name);
        moved = false;
        if (y1 < 5) {
            direction = "down";
        } else {
            direction = "up"; 
        }
        if (player.getColor().equalsIgnoreCase("white")) {
            symbol = new ImageIcon("pics/wpawn.png");
        } else {
            symbol = new ImageIcon("pics/bpawn.png");
        }
    }
    
    
    /**
     * Determines if destination tile is legal movement 
     * for a Pawn chess piece. Works with board.canPawnMove() 
     * to validate valid pawn movement. canMvTo does 
     * not validate if attack move has enemy. 
     * 
     * @param x1 x-coordinate of destination tile on the board
     * @param y1 y-coordinate of destination tile on the board
     * @return if destination tile is legal movement.
     */
    public boolean canMvTo(int x1, int y1) {
        int xdiff = Math.abs(this.x1 - x1);
        if ((direction.equals("down") && this.y1 >= 2) 
                || (direction.equals("up") && this.y1 <= 5)) {
            moved = true;
        }
        if (!moved && this.x1 == x1) {
            if (direction.equals("down")) {
                if (y1 <= this.y1 + 2) {
                    return true;
                }      
            } else {
                if (y1 >= this.y1 - 2) {
                    return true;
                }
            }
        }
        if (xdiff <= 1) {
            if (direction.equals("up") && y1 == this.y1 - 1) {
                return true;
            } else if (y1 == this.y1 + 1) {
                return true;
            } 
        }
        return false;    
    }
    
    /**
     * Returns the String representation of the bishop 
     * and what color it is. 
     * 
     * @return the String representation of the bishop
     */
    public String toString() {
        if (player.getColor().equalsIgnoreCase("white")) {
            return "wpawn";
        } else {
            return "bpawn";
        }
    }

}
