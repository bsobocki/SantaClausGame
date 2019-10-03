import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

abstract class ObjectsInGame implements Serializable {
    protected transient BufferedImage img;
    protected int x;
    protected int y;
    protected int dim;
    protected int move;
    protected int numOfRows;
    protected int numOfColumns;
    protected Field localisation;
    protected char who;
    private String src;

    //constructor
    ObjectsInGame(String src, Field localisation, int dim, int rows, int columns,char who) throws IOException {
        this.src = src;
        img = ImageIO.read(new File(src));
        setLocalisation(localisation);
        this.dim = dim;
        this.numOfRows = rows;
        this.numOfColumns = columns;
        this.who = who;
    }
    ObjectsInGame(ObjectsInGame oig){
        src = oig.src;
        who = oig.who;
        localisation = new Field(oig.localisation);
        numOfColumns = oig.numOfColumns;
        numOfRows = oig.numOfRows;
        move = oig.move;
        dim = oig.dim;
        x = oig.x;
        y = oig.y;
    }

    //methods

    //moving
    boolean moveUp(){
        Field[][] fields = Game.board.getFields();
        if(this.localisation.getRow() != 0)
           return setField(fields[localisation.getRow()-1][localisation.getColumn()]);
        else
           return setField(fields[numOfRows-1][localisation.getColumn()]);
    }
    boolean moveDown(){
        Field[][] fields = Game.board.getFields();
        if(this.localisation.getRow()+1 < numOfRows)
           return setField(fields[localisation.getRow()+1][localisation.getColumn()]);
        else
           return setField(fields[0][localisation.getColumn()]);
    }
    boolean moveLeft(){
        Field[][] fields = Game.board.getFields();
        if(this.localisation.getColumn() != 0)
           return setField(fields[localisation.getRow()][localisation.getColumn()-1]);
        else
           return setField(fields[localisation.getRow()][numOfColumns-1]);
    }
    boolean moveRight(){
        Field[][] fields = Game.board.getFields();
        if(this.localisation.getColumn()+1 < numOfColumns)
           return setField(fields[localisation.getRow()][localisation.getColumn()+1]);
        else
           return setField(fields[localisation.getRow()][0]);
    }
    //predicates
    boolean isSthOnField(Field field){
        return field.getWhoIsThere()!='n';
    }
    //change fields
    boolean setField(Field field){
        if(!isSthOnField(field)) {
            //during getting a present santa is on field where he's doing it
            if (localisation.getWhoIsThere() != 'p')
                localisation.setWhoIsThere('n');
            setLocalisation(field);
            return true;
        }
        return false;
    }

    //getters
    int getX() { return x; }
    int getY() { return y; }
    int getDim() { return dim; }
    BufferedImage getImg(){ return img; }
    Field getLocalisation() { return localisation; }

    //setters
    void setImg(BufferedImage img) { this.img = img; }
    void setLocalisation(Field loc) {
        loc.setWhoIsThere(who);
        this.localisation = loc;
        this.x = localisation.getX();
        this.y = localisation.getY();
        this.move = localisation.getDim();
    }
}
