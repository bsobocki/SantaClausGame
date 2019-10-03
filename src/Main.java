import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main extends JFrame {

    Main(){
        JPanel panel = new JPanel();

        //SET
        setBounds(300,300,200,150);
        panel.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        //START
        JButton buttonStart = new JButton("start new game");
        buttonStart.setBounds(10,50,100,50);
        buttonStart.addActionListener((ActionEvent e)->{
            try {
                new Game(13,16);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        //EXIT
        JButton buttonExit = new JButton("exit");
        buttonExit.setBounds(10,150, 100, 50);
        buttonExit.addActionListener((ActionEvent e)->{
            System.exit(0);
        });

        //RESUME
        JButton buttonResume = new JButton("resume the last game");
        buttonResume.setBounds(10,100,100,50);
        buttonResume.addActionListener((ActionEvent e) -> {
            try {
                ObjectInputStream input = new ObjectInputStream( new BufferedInputStream( new FileInputStream("resume.ser")));
                Board board = (Board) input.readObject();
                //new Game(board);
            } catch (IOException | ClassNotFoundException e1) {
                System.out.print("Cannot resume the last game. The last game is ended.");
                e1.printStackTrace();
            }
        });

        //ADD
        panel.add(buttonStart);
        panel.add(buttonResume);
        panel.add(buttonExit);
        add(panel);

        //SET
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        //co do serializacji to mozna dodac klase przechowujaca tylko polozenia i statystyki i to ja serializowac,
        //a potem na jej podstawie odtworzyc grew
       new Main();
    }
}
