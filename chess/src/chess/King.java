package chess;

import javax.swing.ImageIcon;

/**
 * King is a type of Chess piece that can only move 1 spot 
 * in any direction and is the only chess piece required to
 * stay in then game of Chess.  
 * 
 * @author Matt
 * @version 1.0
 */
public class King extends ChessPiece {
    /**
     * Constructor for a King object. 
     * Initializes location and team of the Chess piece. 
     * 
     * @param x1 x-coordinate on the board
     * @param y1 y-coordinate on the board
     * @param player Player who owns the chess piece
     */
    public King(int x1, int y1, Player player) {
        super(x1, y1, player);
        if (player.getColor().equalsIgnoreCase("white")) {
            symbol = new ImageIcon("pics/wking.png");
        } else {
            symbol = new ImageIcon("pics/bking.png");
        }
    }
    
    /**
     * Determines if destination tile is legal movement
     * for a King chess piece.
     * 
     * @param x1 x-coordinate of destination tile on the board
     * @param y1 y-coordinate of destination tile on the board
     * @return if destination tile is legal movement.
     */
    public boolean canMvTo(int x1, int y1) {
        int xdiff = Math.abs(x1 - this.x1);
        int ydiff = Math.abs(y1 - this.y1);   
        if (xdiff <= 1 && ydiff <= 1) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Returns the String representation of the King 
     * and what color it is. 
     * 
     * @return the String representation of the King
     */
    public String toString() {
        if (player.getColor().equalsIgnoreCase("white")) {
            return "wking";
        } else {
            return "bking";
        }
    }
    

}
