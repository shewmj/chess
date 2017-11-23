package chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;



/**
 * Board contains all the chess pieces and tiles involved in the game of chess.
 * 
 * @author Matthew Shew
 * @version 1.0
 */
public class Board extends JFrame { 
    /**
     * Size of the chess board.
     */    
    public static final int SIZE = 8;
    private static final long serialVersionUID = 1L;
    private Tile[][] board;
    private Player p1;
    private Player p2;
    private Player turn;
    private Tile select;
    private JMenuItem openOp;
    private JMenuItem saveOp;
       
    /**
     * Constructor for a new Board object that 
     * initializes the chess game from the very beginning.
     */
    public Board() {
        super();
        board = new Tile[SIZE][SIZE];
        getPlayers();
        turn = p1; 
        setFrame();
        initBoard();   
        setScreen();
        setTitle(turn.getName() + " - WHITE ");  
    }
    
    /**
     * Constructor for a Board object that uses a previously 
     * saved game to reconstruct the game at the same point. 
     * 
     * @param board copy of the board to begin the game from 
     * @param p1 the first chess player
     * @param p2 the second chess player
     * @param turn player who's turn it is
     */
    public Board(Tile[][] board, Player p1, Player p2, Player turn) {       
        super();
        this.board = board;      
        this.p1 = p1;
        this.p2 = p2;
        this.turn = turn;
        setFrame();  
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                this.add(board[x][y]);
                board[x][y].addMouseListener(new MoveListener());
            }
        }
        setScreen();
        setTitle(turn.getName() + " - " + turn.getColor());  
    }
    
    //
    // Initializes the board at the start
    //
    private void initBoard() {      
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if ((x + y) % 2 == 0) {
                    board[x][y] = new Tile(Color.darkGray, x, y);                           
                } else {
                    board[x][y] = new Tile(Color.lightGray, x, y);        
                }
                this.add(board[x][y]);
                board[x][y].addMouseListener(new MoveListener());
            }
        }
        setPieces(0, p1);
        setPawns(1, p1);
        setPawns(6, p2);
        setPieces(7, p2);
    }
    
    //
    // Sets the jframe parts of the Board object and creates
    // the menus for user to open/save
    //
    private void setFrame() {
        this.setLayout(new GridLayout(8, 8));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menu = new JMenuBar();
        JMenu open = new JMenu("open");
        JMenu save = new JMenu("save");
        menu.add(open);
        menu.add(save);
        openOp = new JMenuItem("File");
        openOp.addMouseListener(new MoveListener());
        open.add(openOp);       
        saveOp = new JMenuItem("New");
        saveOp.addMouseListener(new MoveListener());
        save.add(saveOp);
        setJMenuBar(menu);
    }
    
    //
    // Sets the size and location of the chess game on screen 
    //
    private void setScreen() {
        pack();
        Dimension dimen = Toolkit.getDefaultToolkit().getScreenSize();
        int len = (int) Math.min(dimen.width * 0.7, dimen.height * 0.7);
        Rectangle border = new Rectangle();
        border.setBounds(((dimen.width - len) / 2), ((dimen.height - len) / 2), len, len);
        setBounds(border);
        setVisible(true);
    }
      
    //
    // Asks the users for their names and sets the players
    //
    private void getPlayers() {
        String player1;
        String player2;
        do {
            player1 = JOptionPane.showInputDialog("Enter player 1 name:");
            player2 = JOptionPane.showInputDialog("Enter player 2 name");
            if (player1 == null && player2 == null) {
                JOptionPane.showMessageDialog(this, "User Quit");
                this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            }
        } while (player1.equals(player2));
        p1 = new Player(player1);
        p2 = new Player(player2);
    }
   

    //
    // Sets a row of pawns at the specified row for the specified player
    //
    // @param row The row on the board to set pawns
    // @param player The player who owns the pawns
    private void setPawns(int row, Player player) {
        for (int i = 0; i < 8; i++) {
            board[i][row].setCp(new Pawn(i, row, player));
        }
    }

    //
    // Sets the core chess pieces at the specified row for the
    // specified player
    //
    // @param row The row on the board to set the non-pawn chess pieces
    // @param player The player who owns the chess pieces
    private void setPieces(int row, Player player) {
        board[0][row].setCp(new Rook(0, row, player));
        board[1][row].setCp(new Knight(1, row, player));
        board[2][row].setCp(new Bishop(2, row, player));
        board[3][row].setCp(new Queen(3, row, player));
        board[4][row].setCp(new King(4, row, player));
        board[5][row].setCp(new Bishop(5, row, player));
        board[6][row].setCp(new Knight(6, row, player));
        board[7][row].setCp(new Rook(7, row, player));
    }
    

    //
    // Moves a chess piece from its location to another if legal.
    // If the move is not legal then nothing happens. 
    // 
    // @param x1 x-coordinate of current tile
    // @param y1 y-coordinate of current tile
    // @param x2 x-coordinate of destination tile
    // @param y2 y-coordinate of destination tile
    //
    private void movePiece(int x1, int y1, int x2, int y2) {
        ChessPiece cpSelect = board[x1][y1].getCp();
        if (cpSelect == null) {
            return;
        }
        Player currPlayer = cpSelect.getPlayer();
        if (!turn.equals(currPlayer)) {
            return;
        }
        if (!cpSelect.canMvTo(x2, y2) || !isClearPath(x1, y1, x2, y2)) {
            return;
        }
        if (cpSelect.toString().substring(1).equals("pawn") && !canPawnMove(x1, y1, x2, y2)) {
            return;
        }
        ChessPiece cpDestin = board[x2][y2].getCp();
        if (cpDestin == null || !cpDestin.getPlayer().equals(currPlayer)) {   
            if (cpDestin instanceof King) {
                JOptionPane.showMessageDialog(this, "Game Over. " + turn.getName() + " Wins! ");
                if (getContinue().equalsIgnoreCase("yes")) {
                    this.setVisible(false);
                    new Board();
                } else {
                    this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                }
            }
            board[x1][y1].remove();
            board[x2][y2].setCp(cpSelect);
            cpSelect.move(x2, y2);
            if (turn.equals(p1)) {
                turn = p2;
            } else {
                turn = p1;
            }
        }
    }
    
    //
    // Asks the user if they want to play again or not 
    // after someone wins. 
    //
    private String getContinue() {
        String ans; 
        do {
            ans = JOptionPane.showInputDialog("play again? yes/no: ");            
        } while (!ans.equalsIgnoreCase("yes") && !ans.equalsIgnoreCase("no")) ;
        return ans;
    }
    

    //
    // Determines if given a pawn at x1,y1 is x2,y2 a legal movement
    // specifically for a pawn. Only if spot in front of pawn is empty you
    // can move straight forward and only if enemy chess piece is directly
    // diagonal you can move diagonal and kill them.
    //
    // @param x1 The x-coordinate of the pawn
    // @param y1 The y-coordinate of the pawn
    // @param x2 The x-coordinate of the destination tile
    // @param y2 The y-coordinate of the destination tile 
    //
    // @return if the pawn can move to the destination tile
    private boolean canPawnMove(int x1, int y1, int x2, int y2) {
        ChessPiece opp = board[x2][y2].getCp();
        if (x1 == x2) {
            if (opp != null) {
                return false;
            }
        } else {
            Player currPlayer;
            if (turn.equals(p1)) {
                currPlayer = p1;
            } else {
                currPlayer = p2;
            }
            if (opp == null || opp.getPlayer().equals(currPlayer)) {
                return false;
            } 
        }
        return true;
    }

    //
    // Determines if path between current location and destination location
    // is a clear path. Does not care about non-linear or non-diagonal movement
    // to account for knights.
    //
    // @param x1 The x-coordinate of the chess piece
    // @param y1 The y-coordinate of the chess piece
    // @param x2 The x-coordinate of the destination tile
    // @param y2 The y-coordinate of the destination tile 
    //
    // @return if the chess piece can move to the destination tile
    private boolean isClearPath(int x1, int y1, int x2, int y2) {
        int xdiff = Math.abs(x1 - x2);
        int ydiff = Math.abs(y1 - y2);
        int iterx;
        int itery;
        //if its a knight moving or pawn moving 1 space we don't care
        if ((xdiff + ydiff == 3) || xdiff == 1) {
            return true;
        }
        if (x2 > x1) {
            iterx = 1;
        } else if (x1 > x2) {
            iterx = -1;
        } else {
            iterx = 0;
        }
        if (y2 > y1) {
            itery = 1;
        } else if (y1 > y2) {
            itery = -1;
        } else {
            itery = 0;
        }
        return isClearPathHelp(x1, y1, x2, y2, iterx, itery);
    }

    //
    // Calculates if path between x1,y1 and x2,y2 is clear using
    // iterx and itery which have been calculated to check the tiles in the
    // proper direction
    //
    // @param x1 The x-coordinate of the chess piece
    // @param y1 The y-coordinate of the chess piece
    // @param x2 The x-coordinate of the destination tile
    // @param y2 The y-coordinate of the destination tile 
    // @param iterx The direction of the chess move on x-plane
    // @param iterx The direction of the chess move on x-plane
    //
    // @return If the path between the current and destination tile is clear
    private boolean isClearPathHelp(int x1, int y1, int x2, int y2, int iterx, int itery) {
        y1 += itery;
        x1 += iterx;
        while (x1 != x2 || y1 != y2) {
            if (board[x1][y1].getCp() != null) {
                return false;
            }
            y1 += itery;
            x1 += iterx;
        }
        return true;
    }
 
    /**
     * MoveListener listens to the chess board for player movements 
     * and validates the moves when they are made before changing 
     * the board. 
     * 
     * @author Matthew Shew
     * @version 1.0
     */
    private class MoveListener implements MouseListener {
       
        private Storage save;
        
        /**
         * mouseRelease detects for chess piece movement using
         * the original location of the click. 
         * 
         * @param eve the mouse event trigger
         */
        @Override
        public void mouseReleased(MouseEvent eve) {
            if (!(eve.getSource() instanceof Tile)) {
                System.out.println("not chess move");
                return;
            }
            Tile clicked = (Tile) eve.getSource();
            setTitle(turn.getName() + " - " + turn.getColor());
            if (select == null) {
                select = clicked;
                select.setBackground(Color.green);
            } else {               
                movePiece(select.getXco(), select.getYco(), clicked.getXco(), clicked.getYco());
                clicked.repaint();
                select.setBackground(select.getColor());
                select = null;
            }
            setTitle(turn.getName() + " - " + turn.getColor());      
        }
        
        @Override
        public void mouseClicked(MouseEvent eve) {        
        }

        @Override
        public void mouseEntered(MouseEvent eve) {    
        }

        @Override
        public void mouseExited(MouseEvent eve) {
        }

        /**
         * mousePressed detects when the user clicks the open and save 
         * game options. 
         * 
         * @param eve the mouse event trigger
         */
        @Override
        public void mousePressed(MouseEvent eve) {
            if (eve.getSource() == openOp) {
                openHelp();
            } else if (eve.getSource() == saveOp) {
                saveHelp();
            }
        }
        
        //
        // openHelp prompts the user for the name of the file to open 
        // and attempts to retrieve the file. 
        //
        private void openHelp() {
            try {
                String fileName = JOptionPane.showInputDialog("Enter name of save file: ");
                FileInputStream fileIn = new FileInputStream(fileName + ".ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);      
                save = (Storage) in.readObject();
                in.close();
                fileIn.close();
            } catch (Exception exc) {
                exc.printStackTrace();
                return;
            }
            new Board(save.extract(), save.p1, save.p2, save.turn);
        }
        
        
        //
        // saveHelp prompts the user for the name of the file to save to 
        // and attempts to save the file. 
        //
        private void saveHelp() {
            try {
                String fileName = JOptionPane.showInputDialog("Enter name of save file: ");
                FileOutputStream fileOut = new FileOutputStream(fileName + ".ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                save = new Storage(board, p1, p2, turn);
                out.writeObject(save);
                out.close();
                fileOut.close();
            } catch (IOException io) {
                io.printStackTrace();
                return;
            }
        }
        
        
    }
    
    
}
