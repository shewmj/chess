package chess;

import javax.swing.ImageIcon;

/**
 * Queen is a type of chess piece that can move
 * in any direction as many spots as possible.  
 * 
 * @author Matthew Shew
 * @version 1.0 
 */
public class Queen extends ChessPiece {

    /**
     * Constructor for a Queen object. 
     * Initializes location and team of the Chess piece. 
     * 
     * @param x1 x-coordinate on the board
     * @param y1 y-coordinate on the board
     * @param player Player who owns the chess piece
     */
    public Queen(int x1, int y1, Player player, String name) {
        super(x1, y1, player, name);
        if (player.getColor().equalsIgnoreCase("white")) {
            symbol = new ImageIcon("pics/wqueen.png");
        } else {
            symbol = new ImageIcon("pics/bqueen.png");
        }
    }
    
    /**
     * Determines if destination tile is legal movement 
     * for a Queen chess piece. 
     * 
     * @param x1 x-coordinate of destination tile on the board
     * @param y1 y-coordinate of destination tile on the board
     * @return if destination tile is legal movement.
     */
    public boolean canMvTo(int x1, int y1) {
        int xdiff = Math.abs(x1 - this.x1);
        int ydiff = Math.abs(y1 - this.y1);
        if (x1 == this.x1 || y1 == this.y1) {
            return true;
        } else if (xdiff == ydiff) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Returns the String representation of the Queen 
     * and what color it is. 
     * 
     * @return the String representation of the Queen
     */
    public String toString() {
        if (player.getColor().equalsIgnoreCase("white")) {
            return "wqueen";
        } else {
            return "bqueen";
        }
    }
    

}
