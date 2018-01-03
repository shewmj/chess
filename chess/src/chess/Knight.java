package chess;

import javax.swing.ImageIcon;

/**
 * Knight is a chess piece that only 
 * moves in L shaped movements. 
 * 
 * @author Matthew Shew
 * @version 1.0
 */
public class Knight extends ChessPiece {

    /**
     * Constructor for a Knight chess piece and 
     * initializes location and team of the Chess piece. 
     * 
     * @param x1 x-coordinate on the board
     * @param y1 y-coordinate on the board
     * @param player Player who owns the chess piece
     */
    public Knight(int x1, int y1, Player player, String name) {
        super(x1, y1, player, name);
        if (player.getColor().equalsIgnoreCase("white")) {
            symbol = new ImageIcon("pics/wknight.png");
        } else {
            symbol = new ImageIcon("pics/bknight.png");
        }   
    }

    /**
     * Determines if destination tile is legal movement 
     * for a Knight chess piece. 
     * 
     * @param x1 x-coordinate of destination tile on the board
     * @param y1 y-coordinate of destination tile on the board
     * @return if destination tile is legal movement.
     */
    public boolean canMvTo(int x1, int y1) {
        int xdiff = Math.abs(x1 - this.x1);
        int ydiff = Math.abs(y1 - this.y1);
        if (xdiff == 1 && ydiff == 2) {
            return true;
        }
        if (ydiff == 1 && xdiff == 2) {
            return true;
        }
        return false;       
    }
    
    /**
     * Returns the String representation of the Knight 
     * and what color it is. 
     * 
     * @return the String representation of the Knight
     */
    public String toString() {
        if (player.getColor().equalsIgnoreCase("white")) {
            return "wknight";
        } else {
            return "bknight";
        }
    }

}
