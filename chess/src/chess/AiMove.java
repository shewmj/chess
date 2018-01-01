package chess;



public class AiMove implements Comparable<AiMove> {

    protected ChessPiece cp;
    protected int xCo;
    protected int yCo;
    protected int score;//need string for comparator

    public AiMove(ChessPiece cp, int x, int y, int score) {
        this.cp = cp;
        xCo = x;
        yCo = y;
        this.score = score;
    }

    @Override
    public int compareTo(AiMove a) {
        return a.score - this.score;
    }



}
