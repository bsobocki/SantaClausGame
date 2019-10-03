import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Kid extends ObjectsInGame implements Runnable {
    static int kidsWithPresents = 0;
    private boolean havePresent;
    private int steps;

    //constructor

    Kid(Field localisation, int n, int m) throws IOException {
        super("kid.png",localisation, 40, n, m,'k');
        steps = 0;
        havePresent = false;
    }

    Kid(Kid kid){
        super(kid);
        havePresent = kid.havePresent;
        steps = kid.steps;
    }

    //methods

    //Kid's moving
    private synchronized boolean move(){
        //go to sleep
        if(steps>5){
            sleep();
        }//being an awake child
        else{
            try {
                //waiting random time
                Thread.sleep(300+new Random().nextInt(500));
                //set an image to the awake Kid
                setImg(ImageIO.read(new File("kid.png")));
            } catch (InterruptedException | IOException e) { System.out.println(e.getMessage()); }

            //variable to get localisation of something kid's looking for
            Integer[] coordinates = null;

            //looking for the Santa next to the Kid
            coordinates = lookingFor(1,'s',coordinates);
            if(coordinates!=null){
                try {
                    setImg(ImageIO.read(new File("santa2.png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //try {
                //   Thread.sleep(500);
                //} catch (InterruptedException e) {
                //    e.printStackTrace();
                //}
                //Game.setGameOver(true);
                //System.out.println("You loose!");
                havePresent = true;
                return true;
            }

            //looking for a present next to the Kid
            coordinates = lookingFor(1,'p',coordinates);
            if(coordinates!=null) {
                try {
                    setImg(ImageIO.read(new File("kid2.png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                havePresent = true;
                getLocalisation().setWhoIsThere('n');
                setLocalisation(Game.board.getFields()[coordinates[1]][coordinates[0]]);
                kidsWithPresents++;
                if(kidsWithPresents==12) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Game.setGameOver(true);
                    System.out.print("You Won!");
                }
                return true;
            }

            //looking for the santa not too far from the kid
            coordinates = lookingFor(5, 's',coordinates);
            if(coordinates !=null){
                goToTheSanta(coordinates[0],coordinates[1]);
                //chooseMove();
            }

            //if santa isn't not too far from the Kid
            else
                chooseMove();
            //next step has been done
            steps++;
        }
        return true;
    }
    private void sleep(){
        //set an image to the sleeping Kid
        try{
            setImg(ImageIO.read(new File("sleep.png")));
            localisation.setWhoIsThere('k');
            Thread.sleep(5000 + new Random().nextInt(300));
        }catch(IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }
        steps = 0;
    }
    private void chooseMove(){
        Random generator = new Random();
        int number = generator.nextInt(4);
        switch(number){
            case 0:
                moveUp();
                break;
            case 1:
                moveDown();
                break;
            case 2:
                moveLeft();
                break;
            case 3:
                moveRight();
        }
    }
    //check if santa is not next to the Kid and return the santa's localisation
    private Integer[] lookingFor(int n, char who, Integer[] coordinates){
        int row = localisation.getRow();
        int column = localisation.getColumn();
        int maxColumn = Game.board.getColumns();
        int maxRow = Game.board.getRows();
        int x;
        int y;
        coordinates = new Integer[2];

        for(int i=row-n; i<=row+n; i++) {
            y = i;
            if(y<0)
                y+=maxRow;
            if(y>=maxRow)
                y-=maxRow;
            for(int j = column-n; j<=column + n; j++) {
                x = j;
                if(x<0)
                    x+=maxColumn;
                if(x>=maxColumn)
                    x-=maxColumn;
                if (Game.board.getFields()[y][x].getWhoIsThere() == who) {
                    if(who=='p')
                        Game.board.getFields()[y][x].setWhoIsThere('c');
                    coordinates[0] = x;
                    coordinates[1] = y;
                    return coordinates;
                }
            }
        }
        return null;
    }
    //go to the point where the santa is
    private void goToTheSanta(int x, int y){
        if(!goToTheSantaX(x,y))
            chooseMove();
    }
    private boolean goToTheSantaX(int x, int y){
        if(localisation.getColumn()<x) {
            if (!moveRight())
                return goToTheSantaY(y);
            else
                return true;
        }
        else if(localisation.getColumn()>x)
            if (!moveLeft())
                return goToTheSantaY(y);
            else
                return true;
        return goToTheSantaY(y);
    }
    private boolean goToTheSantaY(int y){
        if(localisation.getRow()<y)
            return moveDown();
        else if(localisation.getRow()>y)
            return moveUp();
        else
            return false;
    }

    @Override
    public void run() {
        while (!havePresent) {
            synchronized (this) {
                move();
            }
        }
    }
}
