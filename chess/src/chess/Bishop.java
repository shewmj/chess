package chess;

import javax.swing.ImageIcon;

/**
 * Bishop is a type of chess piece that can only 
 * move in diagonal directions.
 * 
 * @author Matthew Shew
 * @version 1.0
 */
public class Bishop extends ChessPiece {

    /**
     * Constructor for bishop object and 
     * initializes location and team of the Chess piece. 
     * 
     * @param x1 x-coordinate on the board
     * @param y1 y-coordinate on the board
     * @param player Player who owns the chess piece
     */
    public Bishop(int x1, int y1, Player player, String name) {
        super(x1, y1, player, name);
        if (player.getColor().equalsIgnoreCase("white")) {
            symbol = new ImageIcon("pics/wbishop.png");
        } else {
            symbol = new ImageIcon("pics/bbishop.png");
        }    
    }
    
    /**
     * Determines if destination tile is legal movement
     * for a Bishop chess piece.
     * 
     * @param x1 x-coordinate of destination tile on the board
     * @param y1 y-coordinate of destination tile on the board
     * @return if destination tile is legal movement.
     */
    public boolean canMvTo(int x1, int y1) {
        if (Math.abs(x1 - this.x1) == Math.abs(y1 - this.y1)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Returns the String representation of the Bishop 
     * and what color it is. 
     * 
     * @return the String representation of the Bishop
     */
    public String toString() {
        if (player.getColor().equalsIgnoreCase("white")) {
            return "wbishop";
        } else {
            return "bbishop";
        }
    }
    
    
   
}
