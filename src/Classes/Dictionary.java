package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Dictionary {

    private AVLTree<String> dictionary = new AVLTree<>();

    public Dictionary() {
    }

    public Dictionary(String word) {
        this.dictionary.insertAVL(word);
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
        File file = new File("dictionary.txt");
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.append(word + "\n");
        } catch (IOException e) {
        }
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
        File file = new File("dictionary.txt");
        String input = null;
        try (Scanner sc = new Scanner(file)) {
            StringBuffer sb = new StringBuffer();
            while (sc.hasNextLine()) {
                input = sc.nextLine();
                sb.append(input + "\n");
            }
            String result = sb.toString();
            result = result.replaceAll(word + "\n", "");
            try (PrintWriter printWriter = new PrintWriter(file)) {
                printWriter.append(result);
                printWriter.flush();
            } catch (Exception e) {
            }
        } catch (FileNotFoundException e) {
        }
    }

    public String[] findSimilar(String word) {
        SLL<String> sllWords = new SLL<>();
        int wordLength = word.length();
        for (int i = 0; i < wordLength; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (word.charAt(i) == c)
                    continue;
                String newWord = word.substring(0, i) + c + word.substring(i + 1, wordLength);
                String newWordChar = word.substring(0, i) + c + word.substring(i, wordLength);
                try {
                    if (this.findWord(newWord)) {
                        sllWords.addToHead(newWord);
                    }
                } catch (WordNotFoundException e) {
                }
                try {
                    if (this.findWord(newWordChar)) {
                        sllWords.addToHead(newWordChar);
                    }
                } catch (WordNotFoundException e) {
                }
            }
            String newWordNoChar = word.substring(0, i) + word.substring(i + 1, wordLength);
            try {
                if (this.findWord(newWordNoChar)) {
                    sllWords.addToHead(newWordNoChar);
                } else
                    continue;
            } catch (WordNotFoundException e) {
            }
        }
        for (char c = 'a'; c <= 'z'; c++) {
            String newWord = word + c;
            try {
                if (this.findWord(newWord)) {
                    sllWords.addToHead(newWord);
                }
            } catch (WordNotFoundException e) {
            }
        }
        String[] similarWords = new String[sllWords.size()];
        SLLNode<String> temp = sllWords.head;
        for (int i = 0; i < similarWords.length; i++) {
            similarWords[i] = temp.info;
            temp = temp.next;
        }
        return similarWords;
    }
}
