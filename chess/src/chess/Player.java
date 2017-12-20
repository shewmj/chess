package chess;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Player represents a person playing in the Chess Game. 
 * 
 * @author Matthew Shew
 * @version 1.0
 *
 */
public class Player implements Serializable {
    
    /**
     * Generated serial version ID.
     */
    private static final long serialVersionUID = 5341847646629895518L;
    private static boolean playersYet = false;
    private String name;
    private String color;
    private Map<String, ChessPiece> piecesMap;
    
    /**
     * Constructor for a Player object. 
     * 
     * @param name The name of the Player
     */
    public Player(String name) {
        this.name = name;   
        if (playersYet == false) {
            color = "WHITE";
            playersYet = true;
        } else {
            color = "BLACK";
        }
        piecesMap = new HashMap<>();
    }

    public void addPiece(ChessPiece cp) {
        piecesMap.put(cp.getName(), cp);
    }

    public void removePiece(ChessPiece cp) {
        piecesMap.remove(cp.getName());
    }

    public ArrayList<ChessPiece> getPieces() {
        ArrayList<ChessPiece> cpList = new ArrayList<>();
        for (ChessPiece cp : piecesMap.values()) {
            cpList.add(cp);
        }
        return cpList;
    }
    
    /**
     * Gets the original color of the Tile.
     * 
     * @return The color of the Tile
     */
    public String getColor() {
        return color;
    }
    
    /**
     * Gets the name of the Player. 
     * 
     * @return name The name of the Player
     */
    public String getName() {
        return name;
    }
      
    /**
     * Compares if 2 Player objects are equal. 
     * 
     * @return if the 2 Players are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Player)) {
            return false;
        }
        if (!name.equals(((Player)obj).getName())) {
            return false;
        }
        return true;
    }
}
