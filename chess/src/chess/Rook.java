package chess;

import javax.swing.ImageIcon;

/**
 * Rook is a type of chess piece that can only 
 * move in straight linear lines(no diagonals).
 * 
 * @author Matthew Shew
 * @version 1.0
 */
public class Rook extends ChessPiece {

    /**
     * Constructor for a Rook object. 
     * Initializes location and team of the Chess piece. 
     * 
     * @param x1 x-coordinate on the board
     * @param y1 y-coordinate on the board
     * @param player Player who owns the chess piece
     */
    public Rook(int x1, int y1, Player player, String name) {
        super(x1, y1, player, name);
        if (player.getColor().equalsIgnoreCase("white")) {
            symbol = new ImageIcon("pics/wrook.png");
        } else {
            symbol = new ImageIcon("pics/brook.png");
        }
    }
    
    /**
     * Determines if destination tile is legal movement 
     * for a Rook chess piece. 
     * 
     * @param x1 x-coordinate of destination tile on the board
     * @param y1 y-coordinate of destination tile on the board
     * @return if destination tile is legal movement.
     */
    public boolean canMvTo(int x1, int y1) {
        if (x1 == this.x1 || y1 == this.y1) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Returns the String representation of the Rook 
     * and what color it is. 
     * 
     * @return the String representation of the Rook
     */
    public String toString() {
        if (player.getColor().equalsIgnoreCase("white")) {
            return "wrook";
        } else {
            return "brook";
        }
    }
    
    
    
}
