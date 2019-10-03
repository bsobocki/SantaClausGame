import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.Serializable;

class Board extends JPanel implements KeyListener, Serializable {
    private int presentsOnBoard;
    private int rows;
    private int columns;
    private Santa santa;
    private Kid[] kids;
    private Field[][] fields;
    private Present[] presents;
    Thread[] threads;

    Board(int rows, int columns) throws IOException {
        presentsOnBoard=0;

        //create object arrays
        kids = new Kid[12];
        presents = new Present[12];
        fields = new Field[rows][columns];
        threads = new Thread[12];

        //save dimensions of the Board
        this.rows = rows;
        this.columns = columns;

        //create objects
        //fields
        for(int i = 0; i< this.rows; i++)
            for (int j = 0; j< columns; j++) {
                fields[i][j] = new Field("field.png", i, j,'n');
            }
        //santa
        santa = new Santa(fields[this.rows /2][columns /2], this.rows, columns);
        fields[this.rows /2][this.columns /2].setWhoIsThere('s');
        //kids
        Pair<Integer,Integer>[] indexes = RandomFields.getIndexes(santa.getLocalisation());
        for(int i=0; i<12; i++) {
            kids[i] = new Kid(fields[indexes[i].getKey()][indexes[i].getValue()], this.rows, columns);
            fields[indexes[i].getKey()][indexes[i].getValue()].setWhoIsThere('k');
            threads[i] = new Thread(kids[i]);
        }

        setFocusable(true);
        addKeyListener(this);
    }

    Board(Board board){
        rows = board.getRows();
        columns = board.getColumns();
        presentsOnBoard = board.presentsOnBoard;
        santa = new Santa(board.getSanta());
        //kids & presents
        kids = new Kid[12];
        presents = new Present[12];
        for(int i=0; i<presentsOnBoard; i++) {
            presents[i] = new Present(board.presents[i]);
            kids[i] = new Kid(board.getKids()[i]);
        }
        //fields
        fields = new Field[board.getRows()][getColumns()];
        for(int i=0; i<board.getRows(); i++)
            for(int j=0; j<board.getColumns(); j++)
                fields[i][j] = new Field(board.fields[i][j]);

        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        final int distFromFieldEdge = 5;

        //fields
        for(int i = 0; i< rows; i++)
            for (int j = 0; j< columns; j++)
                g.drawImage(fields[i][j].getImg(), fields[i][j].getX(), fields[i][j].getY(),fields[i][j].getDim(),fields[i][j].getDim(),this);

        //presents
        for(int i=0; i<presentsOnBoard; i++) {
            g.drawImage(presents[i].getImg(), presents[i].getX() + distFromFieldEdge, presents[i].getY() + distFromFieldEdge, presents[i].getDim(), presents[i].getDim(), this);
        }

        //santa
        g.drawImage(santa.getImg(), santa.getX()+distFromFieldEdge, santa.getY()+distFromFieldEdge, santa.getDim(), santa.getDim(),this);

        //kids
        for(int i=0; i<12; i++) {
           try{
               new Thread(kids[i]).join();
           }catch(InterruptedException e){
               System.out.println(e.getMessage());
           }
            g.drawImage(kids[i].getImg(), kids[i].getX() + distFromFieldEdge, kids[i].getY() + distFromFieldEdge, kids[i].getDim(), kids[i].getDim(), this);
        }
    }

    //Santa's moving
    @Override
    public synchronized void keyTyped(KeyEvent e) {
            switch (e.getKeyChar()) {
                case 'w':
                    santa.moveUp();
                    break;
                case 's':
                    santa.moveDown();
                    break;
                case 'a':
                    santa.moveLeft();
                    break;
                case 'd':
                    santa.moveRight();
                    break;
                case ' ':
                    if (presentsOnBoard < 12)
                        try {
                            presents[presentsOnBoard] = new Present(santa.getLocalisation());
                            fields[santa.getLocalisation().getRow()][santa.getLocalisation().getColumn()].setWhoIsThere('p');
                            presentsOnBoard++;
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
            }
    }

    @Override
    public void keyPressed(KeyEvent e){}

    @Override
    public void keyReleased(KeyEvent e){}

    //getters
    int getFieldDim(){ return fields[0][0].getDim(); }
    Thread[] getThreads(){ return threads; }
    Santa getSanta() { return santa; }
    Kid[] getKids() { return kids; }
    public Present[] getPresents() { return presents; }
    public Field[][] getFields() { return fields; }
    public int getRows() { return rows; }
    public int getColumns() { return columns; }
}
