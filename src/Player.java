public class Player {
    int score;
    String name;
    int id;

    public Player(String name){
        score = 0;
        this.name = name;
    }

    public void addScore(){
        score++;
    }

    public void addScore(int points){
        score += score;
    }

    public String toString(){
        return name;
    }

}
