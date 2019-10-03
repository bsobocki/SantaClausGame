import java.io.IOException;

public class Santa extends ObjectsInGame{

    Santa(Field whereItIs, int n, int m) throws IOException {
        super("santa.png",whereItIs,40,n,m,'s');
    }
    Santa(Santa s){
        super(s);
    }
}
