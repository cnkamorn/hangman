import java.util.Scanner;

import javax.swing.InputMap;
import javax.swing.text.AbstractDocument.LeafElement;

import java.util.Arrays;

public class Hangman {
    static Scanner scan = new Scanner(System.in);

    public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
    "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
    "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
    "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
    "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon", 
    "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
    "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
    "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
    "wombat", "zebra"};

    public static String[] gallows = {"+---+\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|   |\n" +
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + //if you were wondering, the only way to print '\' is with a trailing escape character, which also happens to be '\'
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" +
    "/    |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + 
    "/ \\  |\n" +
    "     |\n" +
    " =========\n"};

    public static void main(String[] args) {
        String randomWord = randomWord(words);
        char[] wordToArray = randomWord.toCharArray();
        //System.out.println(randomWord);//
        System.out.println(gallows[0]);
        char[] wordToUnderscore = toUnderscore(wordToArray);
        char[] Missess = new char[6];
        char[] checkWin = new char[wordToArray.length];
        printUnderscore(wordToUnderscore);
        System.out.println();
        
        System.out.print("Misses: " + "\n");

        System.out.print("Guess: ");
        char input = scan.next().charAt(0);
        boolean check = guess(wordToArray,input);
     
        checkWin = checkWin(wordToArray,checkWin,input);    

        int wrong = 1;
        
        int miss = 0;
        for (int i = 0; i < wordToUnderscore.length + 6; i++) {
            while (check) {
            checkWin = checkWin(wordToArray,checkWin,input);    
            System.out.println(gallows[miss]);
            checkInputIndex(wordToArray,wordToUnderscore,input);
            System.out.print("Word: ");
            printUnderscore(wordToUnderscore);    
            System.out.println();
            System.out.println("Misses:" + Missess[0] + " " + Missess[1]+ " " + Missess[2]+ " " + Missess[3] + " "+ Missess[4] + " " + Missess[5]);
            System.out.print("Guess: ");
            input = scan.next().charAt(0);
            check = guess(wordToArray,input);

            checkWin = checkWin(wordToArray,checkWin,input);   
 
            if (!check) {
                break;
            }    
            System.out.println(""+new String(checkWin));
            if (Arrays.equals(checkWin,wordToArray)) {
                System.out.println("You win!");
                System.out.println("The word was: " + randomWord + "\n");

                System.exit(0);
                break;
            }
      

        

        } 
        while (!check) {
            wrong++;
            miss++;

            System.out.println(gallows[miss]);
            System.out.print("Word: ");
            printUnderscore(wordToUnderscore);  
            System.out.println();  
            Missess = addWrong(Missess,input,miss);
            System.out.println("Misses:" + Missess[0] + " " + Missess[1]+ " " + Missess[2]+ " " + Missess[3] + " "+ Missess[4] + " " + Missess[5]);
            System.out.print("Guess: ");
            input = scan.next().charAt(0);
            check = guess(wordToArray,input);
       
            if (check) {
                break;
            }
            if (wrong == 6) {
                miss++;
                System.out.println(gallows[miss]);
                System.out.println("The word was: " + randomWord + "\n");

                System.out.println("You lose!");
                System.exit(0);
                break;
            }
        }
        checkWin = checkWin(wordToArray,checkWin,input);   

        if (Arrays.equals(checkWin,wordToArray)) {
            System.out.println("You win!");
            System.out.println("The word was: " + randomWord + "\n");

            System.exit(0);
            break;
        }

        if (wrong == 6) {
            miss++;
            System.out.println(gallows[miss]);
            System.out.println("You lose!");
            System.out.println("The word was: " + randomWord + "\n");
            System.exit(0);
            break;
        }
        }}
    
    public static String randomWord (String[] list) {
        double random = Math.random() * list.length;
        int convert = (int) random;
        String createNewArray = list[convert];
        return createNewArray;
    }

    public static char[] toUnderscore (char[] wordToArray) {
        char underScore = '_';
        char [] wordUnderScore = new char [wordToArray.length];
        System.out.print("Word: ");
        for (int i = 0; i < wordToArray.length; i++) {
            wordUnderScore[i] = underScore;
        }
        return wordUnderScore;
    }
    public static void printUnderscore (char [] wordToUnderscore) {
        for (int i = 0; i < wordToUnderscore.length; i++) {
            System.out.print(wordToUnderscore[i] + " ");
         }
    }

    public static boolean guess (char[] wordToArray,char input) {
        for (int i = 0; i < wordToArray.length; i++) {
            if (input == wordToArray[i]) {
                return true;
            } 
        }
        return false;
    }

    public static void checkInputIndex (char[] wordToArray, char[] wordToUnderscore, char input) {
         for (int i = 0; i < wordToArray.length; i++) {
            if (input == wordToArray[i]) {
                wordToUnderscore[i] = wordToArray[i];
            }
         }
    }
    
    public static char[] addWrong (char[] Missess,char input,int wrong) {
        Missess[wrong] = input;
        return Missess;
    }
            
     

    public static char[] checkWin (char[] wordToArray, char[]checking,char input) {
            for (int i = 0; i < wordToArray.length; i++) {
                if (input == wordToArray[i]) {
                    checking[i] = wordToArray[i];
                }
            }
        return checking;
        }
    }
 


    
        






