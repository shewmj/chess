package chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 * Tile represents one square on a Chess Board which 
 * can contain a chess piece. 
 * 
 * @author Matthew Shew
 * @version 1.0
 *
 */
public class Tile extends JPanel {
    
    /**
     * Default serial version ID.
     */
    private static final long serialVersionUID = 1L;
    private static final double FIFTH = 5.0;
    private static final int CPSIZE = 50;
    private ChessPiece cp;
    private Color color;
    private int xco;
    private int yco;

    /**
     * Constructor for a Tile object that sets the color and 
     * spot on the board. 
     * 
     * @param color The color of the tile
     * @param xco the x coordinate of the tile
     * @param yco the y coordinate of the tile 
     */
    public Tile(Color color, int xco, int yco) {
        super();
        this.color = color;
        this.setBackground(color);
        this.xco = xco;
        this.yco = yco;
        Dimension dimen = Toolkit.getDefaultToolkit().getScreenSize();
        int len = (int) (1.0 / 8.0 * Math.min(dimen.width * 0.75,
                dimen.height * 0.75));
        Rectangle border = new Rectangle();
        border.setBounds(((dimen.width - len) / 2), ((dimen.height - len) / 2), len, len);
        setBounds(border);
    }

    /**
     * Gets the x coordinate of the tile. 
     * 
     * @return the x coordinate of the tile
     */
    public int getXco() {
        return xco;
    }

    /**
     * Gets the y coordinate of the tile. 
     * 
     * @return the y coordinate of the tile
     */
    public int getYco() {
        return yco;
    }

    /**
     * Gets the ChessPiece currently on the Tile.
     *
     * @return the ChessPeice on the Tile
     */
    public ChessPiece getCp() {
        return cp;
    }

    /**
     * Sets the ChessPiece that will occupy the Tile.
     * 
     * @param cp The ChessPiece that will occupy the Tile.
     */
    public void setCp(ChessPiece cp) {
        this.cp = cp;
    }

    /**
     * Removes the ChessPiece currently on the Tile.
     */
    public void remove() {
        cp = null;
    }

    /**
     * Paints the Tile with its color 
     * and chess piece if there is one. 
     * 
     * @param graphics The Graphic where to draw
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (cp != null) {
            Rectangle size = this.getBounds();
            int xshft = (int) (size.width * (1.0 / FIFTH));
            int yshft = (int) (size.height * (1.0 / FIFTH));
            graphics.drawImage(cp.character().getImage(), xshft, yshft, CPSIZE, CPSIZE, null);
        }
    }

    /**
     * Gets the color of the Tile. 
     * 
     * @return The color of the tile
     */
    public Color getColor() {
        return color;
    }

}
