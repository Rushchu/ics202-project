package Classes;

import java.io.File;
import java.util.Scanner;

public class Dictionary {

    private AVLTree<String> dictionary = new AVLTree<>();

    public Dictionary() {
    }

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
                if (!this.dictionary.search(value))
                    this.dictionary.insertAVL(value);
                value = readFile.nextLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public AVLTree<String> getDictionary() {
        return dictionary;
    }

    public void addWord(String word) throws WordAlreadyExistsException {
        if (this.dictionary.search(word)) {
            throw new WordAlreadyExistsException();
        }
        this.dictionary.insertAVL(word);
    }

    public boolean findWord(String word) throws WordNotFoundException {
        if (!this.dictionary.search(word)) {
            throw new WordNotFoundException();
        }
        return true;
    }

    public void deleteWord(String word) throws WordNotFoundException {
        if (!this.dictionary.search(word)) {
            throw new WordNotFoundException();
        }
        this.dictionary.deleteAVL(word);
    }
    
}
