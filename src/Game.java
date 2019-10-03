import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

class Game extends JFrame implements Serializable {
    static Board board;
    private static boolean gameOver;

    Game(int rows, int columns) throws IOException {
        board = new Board(rows,columns);
        init(board);
    }

    Game(Board board){
        this.board = board;
        init(board);
    }

    private void init(Board board){
        int columns = board.getColumns();
        int rows = board.getRows();

        final int heightOfTitleBar = 39;
        final int widthOfWindowSides = 17;
        gameOver = false;
        setBounds(100,50,columns*board.getFieldDim()+widthOfWindowSides,rows*board.getFieldDim()+heightOfTitleBar);
        add(board);

        //set timer (1/20 sec -> repaint)
        Timer timer = new Timer(1000/20, e -> {
            repaint();
            if(gameOver)
                System.exit(0);
        });
        timer.start();
        //set
        Thread[] threads = board.getThreads();
        for(int i=0; i<12; i++)
            threads[i].start();

        //SET
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                serialize();
                super.windowClosing(e);
            }
        });
        setVisible(true);
    }

    private void serialize(){
        int confirm = JOptionPane.showOptionDialog(
                null, "Are You Want To Save Progress?",
                "Exit and Save Confirmation", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
        if(confirm==0) {
            try {
                ObjectOutputStream output = new ObjectOutputStream( new BufferedOutputStream( new FileOutputStream("resume.ser")));
                output.writeObject(new Board(board));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    //setters
    static void setGameOver(boolean val){ gameOver = val; }

}
