package Classes;

public class WordNotFoundException extends Exception {
    public WordNotFoundException() {
        super("Word not found in the dictionary");
    }
}
