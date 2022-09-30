public class CheckerBoard {
    static boolean DEBUGGING = true;
    Space[][] board;
    Player one;
    Player two;

    /**
     * Creates an empty Checkerboard
     */
    public CheckerBoard(){
        board = new Space[8][8];
    }

    /**
     * Sets up a new checkerboard between two players
     *
     * @param playerOne Player using the white pieces, will go first
     * @param playerTwo Player using the black pieces, will go second
     */
    public CheckerBoard(Player playerOne, Player playerTwo){
        one = playerOne;
        two = playerTwo;
        board = new Space[8][8];
        for (int x = 0; x < board.length; x++){
            for (int y = 0; y < board[x].length; y++){
                board[x][y] = new Space();
                if ((x+y)%2 == 0) {     // black square
                    if (x < 3) {        // player one side
                        board[x][y].putPiece(new CheckerPiece(one));
                    } else if (x > 4 ) {
                        board[x][y].putPiece(new CheckerPiece(two));
                    }
                }
            }
        }
    }

    /**
     * Moves a piece from any space to any other space
     * @param x current x-coordinate of the piece to be moved
     * @param y current y-coordinate of the piece to be moved
     * @param dx x-coordinate the piece is being moved to
     * @param dy y-coordinate the piece is being moved to
     */
    public void movePiece(int x, int y, int dx, int dy){
        CheckerPiece temp = board[x][y].getPiece();
        board[dx][dy].putPiece(temp);
        board[x][y].removePiece();

    }

    /**
     * The return string method of the current board state. King pieces are represented by
     * being capitalized. Lowercase are normal pieces
     *
     * @return String representation of the current board state
     */
    public String printOutBoard(){
        String ret = "";
        if (DEBUGGING) { ret = "    0  1  2  3  4  5  6  7 \n"; }
        for (int x = 0; x < board.length; x++){
            if (DEBUGGING) {
                ret += " " + x + " ";
            }
            for (int y = 0; y < board.length; y++){
                if (!board[x][y].isEmpty()){
                    if (board[x][y].getPiece().owner == one) {
                        ret += board[x][y].getPiece().king ? "[W]" : "[w]";
                    } else if (board[x][y].getPiece().owner == two){
                        ret += board[x][y].getPiece().king ? "[B]" : "[b]";
                    }
                } else {
                    ret += "[ ]";
                }
            }
            ret += "\n";
        }

        return ret;
    }
}
