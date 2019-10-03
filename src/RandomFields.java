import javafx.util.Pair;

import java.util.Random;

public class RandomFields {
    private static int howMuch = 13;
    //one object on one field (
    //if sth is on the field[i][j] -> occupied[i][j] = 1
    private static int[][] occupiedFields = new int[13][13];
    private static Random generator = new Random();

    static Pair[] getIndexes(Field _protected){
        for(int i=_protected.getRow()-2; i<_protected.getRow()+2; i++)
            for(int j=_protected.getColumn()-2; j<_protected.getColumn()+2; j++)
                occupiedFields[i][j] = 1;

        Pair<Integer,Integer>[] pairs = new Pair[howMuch];
        int index1=6,index2=6;
        for(int i=0; i<howMuch; i++){
            while(occupiedFields[index1][index2]==1){
                index1 = generator.nextInt(howMuch);
                index2 = generator.nextInt(howMuch);
            }
            pairs[i] = new Pair<>(index1,index2);
            occupiedFields[index1][index2]=1;
        }
        return pairs;
    }
}
