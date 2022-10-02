import java.util.Scanner;

public class main {
    public static void main(String [] args){



        CheckersGUI gui = new CheckersGUI();

        System.out.println("Hello welcome to playing Checkers.");
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter player names followed by enter: ");
        Player one = new Player(scan.nextLine());
        Player two = new Player(scan.nextLine());

        CheckerGame game = new CheckerGame(one, two);
        gui.addGame(game);
        game.update();


        while(!game.isOver){
            game.makeMove(scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt() );
            game.update();
            gui.update();
            gui.drawBoard();
        };
    }
}
