import javax.swing.*;
import java.awt.*;

public class CheckersGUI {
    JFrame frame;
    JButton[][] button;
    JLabel label;
    CheckerGame gameManager;

    public CheckersGUI(){
        frame = new JFrame();
//        button = new JButton("click");
//        button.setBounds(130,100,100,40);

        label = new JLabel("Hello, welcome to Checkers.");
        label.setBounds(130, 100, 1000, 40);


        //frame.add(button);
        frame.add(label);

        frame.setSize(600, 700);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void addGame(CheckerGame gameManager){
        frame.remove(label);
        this.gameManager = gameManager;
        button = new JButton[8][8];

        drawBoard();

    }

    public void drawBoard(){


        JLabel playerTwo = new JLabel(gameManager.playerTwo.name + " " + gameManager.playerTwo.score);
        JLabel playerOne = new JLabel(gameManager.playerOne.name + " " + gameManager.playerOne.score);

        playerOne.setBounds(500, 50, 100, 50);
        playerTwo.setBounds(500, 150, 100, 50);

        frame.add(playerOne);
        frame.add(playerTwo);

        CheckerBoard board = gameManager.board;
        String ret = "";
        for (int x = 0; x < board.board.length; x++){
            for (int y = 0; y < board.board.length; y++){
                if (!board.board[x][y].isEmpty()){
                    if (board.board[x][y].getPiece().owner == gameManager.playerOne) {
                        ret = board.board[x][y].getPiece().king ? "B" : "b";
                    } else if (board.board[x][y].getPiece().owner == gameManager.playerTwo){
                        ret = board.board[x][y].getPiece().king ? "W" : "w";
                    }
                } else {
                    ret = " ";
                }
                button[x][y] = new JButton(ret);
                button[x][y].setForeground(Color.white);
                button[x][y].setBounds(50*y, 50*x, 50, 50);
                if ((x+y)% 2 == 0) {
                    button[x][y].setBackground(Color.RED);
                } else {
                    button[x][y].setBackground(Color.BLACK);
                }
            }
        }
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                frame.add(button[i][j]);
            }
        }

        frame.repaint();
        System.out.println("drawing board");


    }

    public void update(){
        drawBoard();
        System.out.println("Gui updated");
    }

}
