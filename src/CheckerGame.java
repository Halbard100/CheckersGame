public class CheckerGame {

    static boolean DEBUGGING = true;
    Player playerOne;
    Player playerTwo;
    Player currentPlayer;
    CheckerBoard board;
    boolean isOver;
    boolean forcedMove = false;
    String debugPrintStatement;

    public CheckerGame(Player playerOne, Player playerTwo){
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        currentPlayer = this.playerOne;
        isOver = false;
        board = new CheckerBoard(playerOne, playerTwo);
        System.out.println(board.printOutBoard());

    }

    public void setUp(Player playerOne, Player playerTwo){
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        currentPlayer = this.playerTwo;
        isOver = false;
        board = new CheckerBoard(playerOne, playerTwo);
        System.out.println(board.printOutBoard());
    }

    private void changeTurn(){
        currentPlayer = currentPlayer == playerOne ? playerTwo : playerOne;
    }

    public boolean makeMove(int x, int y, int dx, int dy){

        if (x > 8 || y > 8 || y < 0 || x < 0){
            debugMessage("Space out of bounds.");
            return false;
        }
        if (dx > 8 || dy > 8 || dy < 0 || dx < 0){
            debugMessage("Move out of bounds.");
            return false;
        }

        {

            if (board.board[x][y].isEmpty()) {
                debugMessage("There's no piece there");
                return false;
            } else if ((dx + dy) % 2 != 1) {
                debugMessage("Invalid move");
                return false;
            } else if (board.board[x][y].getPiece().owner != currentPlayer) {
                debugMessage("Not your turn");
                return false;
            }
            if (!board.board[x][y].getPiece().king) {
                if (currentPlayer == playerOne && dx < x) {
                    debugMessage("Invalid move. Not a king.");
                    return false;
                } else if (currentPlayer == playerTwo && dx > x) {
                    debugMessage("Invalid move. Not a king.");
                    return false;
                }
            }
            if (!board.board[dx][dy].isEmpty()) {
                debugMessage("You can't move there, there's already a piece there.");
                return false;
            }
        } // basic move validation checks

        // jumps a piece
        {
            if (Math.abs(x - dx) == 1) {
                // valid single move
                debugMessage(currentPlayer + " moved from (" + x + "," + y + ") to " + dx + "," + dy + ")");
            } else if (Math.abs(x - dx) == 2 && !board.board[(x + dx) / 2][(y + dy) / 2].isEmpty()) {
                if (board.board[(x + dx) / 2][(y + dy) / 2].getPiece().owner == currentPlayer) {
                    debugMessage("You can't jump your own pieces");
                    return false;
                }
                // Jump logic for king pieces
                if (board.board[(x + dx) / 2][(y + dy) / 2].getPiece().owner != currentPlayer) {
                    if (!board.board[(x + dx) / 2][(y + dy) / 2].getPiece().king) {
                        // if the piece being jumped is not a king, anything can jump it
                        currentPlayer.addScore();
                        board.board[(x + dx) / 2][(y + dy) / 2].removePiece();
                    } else if (board.board[(x + dx) / 2][(y + dy) / 2].getPiece().king && board.board[x][y].getPiece().king) {
                        // if both pieces are kings
                        currentPlayer.addScore();
                        board.board[(x + dx) / 2][(y + dy) / 2].removePiece();
                    } else {
                        debugMessage("Only kings can take kings.");
                        return false;
                    }
                }
            } else {
                debugMessage("Moved too far.");
                return false;
            }
        }
        board.movePiece(x, y, dx, dy);

        if (!jumpCheck(dx, dy)){
            changeTurn();
        }

        // after current player moves, if it wasn't a jump, don't check for a jump for currentPlayer
        // search for a jump after player change
        // if the current player makes a jump, don't change turns until after checking for a jump from that particular piece

        kingCheck(dx, dy);
        return true;
    }

    private void kingCheck(int x, int y){
        CheckerPiece piece = board.board[x][y].getPiece();
        if ((x == 7 && piece.owner == playerOne) && !piece.king){
            piece.kingMe();
            System.out.println(currentPlayer.name + " got a king!");
        }
        if ((x == 0 && piece.owner == playerTwo) && !piece.king){
            piece.kingMe();
            System.out.println(currentPlayer.name + " got a king!");
        }
    }
    public boolean jumpCheck(int x, int y){
        return forcedMove;
    }

    public void update(){
        if (playerOne.score > 11 || playerTwo.score > 11){
            isOver = true;
        }
        System.out.println(playerOne.name + " (black): " + playerOne.score);
        System.out.println(playerTwo.name + " (white): " + playerTwo.score);

        if (!isOver){
            System.out.println("It is " + currentPlayer.name + "'s turn");
        } else {
            System.out.println("Game Over, thanks for playing");
        }
        System.out.println(board.printOutBoard());
    }

    private void debugMessage(String msg){
        if (DEBUGGING){
            debugPrintStatement = msg;
            System.out.println(msg);
        }
    }



}
