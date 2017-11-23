package chess;

import java.awt.Color;

/**
 * Storage is used to store the state of a chess game 
 * as a 2 dimensional String array to save space. 
 * 
 * @author Matthew Shew
 * @version 1.0
 */
public class Storage implements java.io.Serializable {
    /**
     * Generated serial version ID.
     */
    private static final long serialVersionUID = 4334572033429989227L;
    protected String[][] saved;
    protected Player p1;
    protected Player p2;
    protected Player turn;
    
    /**
     * Constructor for a Storage object which stores the state of a chess 
     * game in a 2-dimensional String array.
     * 
     * @param board a copy of the board to be saved
     * @param p1 Player 1 in the game
     * @param p2 Player 2 in the game
     * @param turn Player who's turn it is
     */
    public Storage(Tile[][] board, Player p1, Player p2, Player turn) {
        super();
        saved = new String[Board.SIZE][Board.SIZE];
        this.p1 = p1;
        this.p2 = p2;
        this.turn = turn;
        convert(board, saved);
    }
    
    //
    // Converts the board game into an equivalent 
    // state but using Strings to save space
    //
    private void convert(Tile[][] board, String[][] saved) {
        ChessPiece temp;
        for (int y = 0; y < Board.SIZE; y++) {
            for (int x = 0; x < Board.SIZE; x++) {
                if ((temp =  board[x][y].getCp()) != null) {
                    saved[x][y] = temp.toString();
                } else {
                    saved[x][y] = "";
                }             
            }
        }
    }
    
    /**
     * Extracts the info from the 2-dimensional string saved
     * to create a two dimensional array of tiles with the same 
     * chess piece placement. 
     * 
     * @return a copy of a the chess game that was saved in this 
     */
    public Tile[][] extract() {
        if (saved == null) {
            return null;
        }
        ChessPiece cp;
        Tile[][] savedBoard = new Tile[Board.SIZE][Board.SIZE];      
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if ((x + y) % 2 == 0) {
                    savedBoard[x][y] = new Tile(Color.darkGray, x, y);                           
                } else {
                    savedBoard[x][y] = new Tile(Color.lightGray, x, y);        
                }
                if ((cp = type(saved[x][y], x, y)) != null) {
                    savedBoard[x][y].setCp(cp);
                }       
            }
        }
        return savedBoard;
    }
    
    //
    // type returns the chess piece that the given 
    // string represents. 
    //
    private ChessPiece type(String piece, int xco, int yco) {
        if (piece.equals("")) {
            return null;
        }
        String color = piece.substring(0,1);
        String type = piece.substring(1);
        Player owner;
        if (color.equalsIgnoreCase(p1.getColor().substring(0, 1))) {
            owner = p1;
        } else {
            owner = p2;
        }
        if (type.equalsIgnoreCase("pawn")) {
            return new Pawn(xco, yco, owner);
        } else if (type.equalsIgnoreCase("rook")) {
            return new Rook(xco, yco, owner);
        } else if (type.equalsIgnoreCase("bishop")) {
            return new Bishop(xco, yco, owner);
        } else if (type.equalsIgnoreCase("knight")) {
            return new Knight(xco, yco, owner);
        } else if (type.equalsIgnoreCase("king")) {
            return new King(xco, yco, owner);
        } else if (type.equalsIgnoreCase("queen")) {
            return new Queen(xco, yco, owner);
        } else {
            return null;
        }
    }
        
}
