public class Space {

    private boolean empty;
    private CheckerPiece occupied;

    /**
     * Creates an empty board space
     */
    public Space(){
        empty = true;
        occupied = null;
    }

    /**
     *
     * @return True if the space does not have a checker piece on it
     */
    public boolean isEmpty(){
        return empty;
    }

    /**
     * Assigns a Checker Piece to the space
     * @param piece CheckerPiece being placed on the space
     */
    public void putPiece(CheckerPiece piece){
        empty = false;
        occupied = piece;
    }

    /**
     * removes the piece from the space
     */
    public void removePiece(){
        empty = true;
        occupied = null;
    }

    /**
     *
     * @return The CheckerPiece currently occupying the space
     */
    public CheckerPiece getPiece(){
        return occupied;
    }
}
