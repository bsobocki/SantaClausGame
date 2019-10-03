import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class Field implements Serializable {
    private int x;
    private int y;
    private int row;
    private int column;
    private int dim;
    private char whoIsThere; //'n' - nobody, 's' - santa, 'k' - kid
    private transient BufferedImage img;
    private String src;

    Field(String src, int row, int column, char whoIsThere) throws IOException {
        this.src = src;
        this.dim = 50;
        this.row = row;
        this.column = column;
        this.x = column*this.dim;
        this.y = row*this.dim;
        img = ImageIO.read(new File(src));
        this.whoIsThere = whoIsThere;
    }

    Field(Field f){
        src = f.src;
        dim = 50;
        row = f.row;
        column = f.column;
        x = column*dim;
        y = row*dim;
        whoIsThere = f.whoIsThere;
    }

    //getters
    int getX() { return x; }
    int getY() { return y; }
    int getRow() { return row; }
    int getColumn() { return column; }
    int getDim() { return dim; }
    BufferedImage getImg() { return img; }
    public char getWhoIsThere() { return whoIsThere; }

    //setters
    public void setWhoIsThere(char whoIsThere) { this.whoIsThere = whoIsThere; }
    public void setImg(BufferedImage img){ this.img = img; }
}