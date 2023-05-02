import java.io.File;

import Classes.Dictionary;

public class Main {
    public static void main(String[] args) throws Exception {
        File file = new File("dictionary.txt");
        long start = System.currentTimeMillis();
        Dictionary dictionary = new Dictionary(file);
        long end = System.currentTimeMillis();
        System.out.println("Total: " + (end - start)/1000 + " Seconds");
        dictionary.getDictionary().printTree();
    }
}
