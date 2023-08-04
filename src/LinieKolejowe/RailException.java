package LinieKolejowe;

public class RailException extends Exception{
    public RailException(){};
    public RailException(String komunikat) {
        super(komunikat);
    }
}
