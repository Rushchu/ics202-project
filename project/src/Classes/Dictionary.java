package Classes;

import java.io.File;
import java.util.Scanner;

public class Dictionary {

    private AVLTree<String> dictionary = new AVLTree<>();

    public Dictionary() {}

    public Dictionary(String word) {
        this.dictionary.insert(word);
    }

    public Dictionary(File file) {
        loadFile(file);
    }

    private void loadFile(File file) {
        try (Scanner readFile = new Scanner(file);) {
            String value = readFile.nextLine();
            while (value != null) {
                this.dictionary.insert(value);
                value = readFile.nextLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
