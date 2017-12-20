package chess;

import javax.swing.ImageIcon;

/**
 * ChessPiece represents the basics of all chess piece types. 
 * 
 * @author Matthew Shew
 * @version 1.0
 *
 */
public abstract class ChessPiece {
    protected int x1; 
    protected int y1;
    protected String name;
    protected Player player;
    protected ImageIcon symbol;
    
    /**
     * Constructor for ChessPiece object.
     * 
     * @param x1 x-coordinate of ChessPiece
     * @param y1 y-coordinate of ChessPiece
     * @param player Player who owns ChessPiece
     */
    public ChessPiece(int x1, int y1, Player player, String name) {
        super();
        this.x1 = x1;
        this.y1 = y1;
        this.player = player;
        this.name = name;
    }
    
    /**
     * Moves the ChessPiece to the new location. 
     * 
     * @param x1 new x-coordinate
     * @param y1 new y-coordinate
     */
    public void move(int x1, int y1) {
        this.x1 = x1;
        this.y1 = y1;
    }

    public String getName() {
        return name;
    }
    
    /**
     * Gets the Player who owns the ChessPiece.
     * 
     * @return Player who owns the ChessPiece
     */
    public Player getPlayer() {
        return player;
    }
    
    /**
     * Gets the image representation of the 
     * specific chessPiece. 
     * 
     * @return the Image of the specific chess piece
     */
    public ImageIcon character() {
        return symbol;
    }
    
    /**
     * Determines if destination tile is legal movement 
     * for the specific type of chess piece. 
     * 
     * @param x1 x-coordinate of destination tile on the board
     * @param y1 y-coordinate of destination tile on the board
     * @return if destination tile is legal movement.
     */
    abstract boolean canMvTo(int x1,int y1);
        
}
