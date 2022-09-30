public class CheckerGame {
    Player playerOne;
    Player playerTwo;
    Player currentPlayer;
    CheckerBoard board;
    boolean isOver;

    public CheckerGame(Player playerOne, Player playerTwo){
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        currentPlayer = this.playerOne;
        isOver = false;
        board = new CheckerBoard(playerOne, playerTwo);
        System.out.println(board.printOutBoard());

    }

    private void changeTurn(){
        currentPlayer = currentPlayer == playerOne ? playerTwo : playerOne;
    }

    public boolean makeMove(int x, int y, int dx, int dy){
        {

            if (board.board[x][y].isEmpty()) {
                System.out.println("There's no piece there");
                return false;
            } else if ((dx + dy) % 2 != 0) {
                System.out.println("Invalid move");
                return false;
            } else if (board.board[x][y].getPiece().owner != currentPlayer) {
                System.out.println("Not your turn");
                return false;
            }
            if (!board.board[x][y].getPiece().king) {
                if (currentPlayer == playerOne && dx < x) {
                    System.out.println("Invalid move. Not a king.");
                    return false;
                } else if (currentPlayer == playerTwo && dx > x) {
                    System.out.println("Invalid move. Not a king.");
                    return false;
                }
            }
            if (!board.board[dx][dy].isEmpty()) {
                System.out.println("You can't move there, there's already a piece there.");
                return false;
            }
        } // basic move validation checks

        // jumps a piece
        if (!board.board[(x+dx)/2][(y+dy)/2].isEmpty()){
            if(board.board[(x+dx)/2][(y+dy)/2].getPiece().owner != currentPlayer){
                currentPlayer.addScore();
                board.board[(x+dx)/2][(y+dy)/2].removePiece();
            }
        }
//        else {
//            System.out.println("Can't move that far.");
//            return false;
//        }

        board.movePiece(x, y, dx, dy);
        kingCheck(dx, dy);
        if (!jumpCheck()){
            changeTurn();
        }
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
    public boolean jumpCheck(){
        return false;
    }

    public void update(){
        if (playerOne.score > 11 || playerTwo.score > 11){
            isOver = true;
        }
        System.out.println("Player 1 -- " + playerOne.name + " (white): " + playerOne.score);
        System.out.println("Player 2 -- " + playerTwo.name + " (black): " + playerTwo.score);
        if (!isOver){
            System.out.println("It is " + currentPlayer.name + "'s turn");
        } else {
            System.out.println("Game Over, thanks for playing");
        }
        System.out.println(board.printOutBoard());
    }



}
