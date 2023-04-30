package Classes;

public class WordAlreadyExistsException extends Exception {
    public WordAlreadyExistsException() {
        super("Word already exists in the dictionary");
    }
}
