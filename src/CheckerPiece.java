public class CheckerPiece {
    Player owner;
    boolean king;


    public CheckerPiece(Player owner){
        this.owner = owner;
        king = false;
    }

    public void kingMe(){
        king = true;
    }

}
