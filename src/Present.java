import java.io.IOException;

class Present extends ObjectsInGame {

    Present(Field loc) throws IOException {
        super("present.png",loc,40,0,0,'p');
    }
    Present(Present p){
        super(p);
    }
}
