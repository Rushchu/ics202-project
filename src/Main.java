import java.io.File;
import java.util.Scanner;

import Classes.Dictionary;

public class Main {
    public static void main(String[] args) throws Exception {
        File file = new File("dictionary.txt");
        Scanner input = new Scanner(System.in);
        long start = System.currentTimeMillis();
        Dictionary dictionary = new Dictionary(file);
        long end = System.currentTimeMillis();
        System.out.println("Total: " + (end - start) / 1000 + " Seconds");
        menu();
        int choice = input.nextInt();
        while (choice != 5) {
            if (choice == 1) {
                System.out.print("Add new word: ");
                String word = input.next();
                try {
                    dictionary.addWord(word);
                    System.out.println("Word added succesfully.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (choice == 2) {
                System.out.print("Find a word: ");
                String word = input.next();
                try {
                    if (dictionary.findWord(word))
                        System.out.println("Word found");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (choice == 3) {
                System.out.print("Delete a word: ");
                String word = input.next();
                try {
                    dictionary.deleteWord(word);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("Word deleted");
            } else if (choice == 4) {
                System.out.print("Find similar words: ");
                String word = input.next();
                try {
                    String[] similarWords = dictionary.findSimilar(word);
                    for (String string : similarWords) {
                        System.out.println(string);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("No operation found");
            }
            menu();
            choice = input.nextInt();
        }
    }

    public static void menu() {
        System.out.print("1. Add a new word.\n2. Find a word in the dictionary."
                + "\n3. Delete a word.\n4. Find similar words."
                + "\n5. Exit.\n");
        System.out.print("Choice: ");
    }

}
