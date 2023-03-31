import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class WordListTest {
    static int wordsChecked = 0;
    static int correctWords = 0;
    // ambiguous words are words with two different amount of syllables
    static int ambiguousWordsChecked = 0;
    static int ambiguousWordsCorrect = 0;
    static ArrayList<ArrayList<String>> incorrectWords = new ArrayList<ArrayList<String>>();
    static ArrayList<String> ambiguousIncorrectWords = new ArrayList<String>();

    public static void main(String[] args){
        readWordFile("wordsAndSyllables.txt");

        System.out.println("[word]-[calculated syllables] [actual amount of syllables]\n");
        for(int i = 1; i < incorrectWords.size(); i++){
            System.out.println("Words " + i + " syllable off: ");
            System.out.println(incorrectWords.get(i).toString() +"\n");
        }
        if(ambiguousIncorrectWords.size() > 0){
            System.out.println("Ambiguous words incorrect:");
            System.out.println(ambiguousIncorrectWords.toString() +"\n");
        }
        System.out.println("Words checked: " + wordsChecked);
        System.out.println("Words correct: " + correctWords);
        System.out.println("Ambiguous words checked: " + ambiguousWordsChecked);
        System.out.println("Ambiguous words correct: " + ambiguousWordsCorrect);
        double percentage = 100*(correctWords+0.0)/wordsChecked ;
        System.out.println("Percent of words correct: " + String.format("%.2f", percentage) + "%\n");
    }
    
     /**
     * Reads in the lines from the file, and then checks them
     * 
     * 
     * @param fileName name of file with data of words and their amount of syllables
     */
    public static void readWordFile(String fileName){
        try {
          File myObj = new File(fileName);
          Scanner myReader = new Scanner(myObj);
          while (myReader.hasNextLine()) {
            String[] line = myReader.nextLine().split(",");
            int[] syllables = new int[line.length - 1];
            for(int i = 1; i < line.length; i++){
                syllables[i-1] = Integer.parseInt(line[i]);
            }
            checkWord(line[0], syllables);
          }
          myReader.close();
        } catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
    }

    /**
     * Runs our code to calculate amount of syllables and checks against actual amount of syllables
     * 
     * 
     * @param word word to check the amount of syllables of
     * 
     * @param syllables amount of syllables in word, this can be an array of size 2 if it's an ambiguous word
     */
    public static void checkWord(String word, int[] syllables){
        int calculatedSyllables = SyllableCounter.calculateSyllables(word);
        boolean correctAmountOfSyllables = false;
        for(int i = 0; i < syllables.length; i++){
            if(calculatedSyllables == syllables[i]){
                correctWords++;
                correctAmountOfSyllables = true;
            }
        }
        if(syllables.length == 2){
            ambiguousWordsChecked++;
            if(correctAmountOfSyllables){
                ambiguousWordsCorrect++;
            }else{
                ambiguousIncorrectWords.add(word + "-" + calculatedSyllables + " " + syllables[0] + " " + syllables[1]);
            }
        }else{
            if(!correctAmountOfSyllables){
                int syllablesOff = Math.abs(calculatedSyllables - syllables[0]);
                while(incorrectWords.size() < syllablesOff+1){
                    incorrectWords.add(new ArrayList<String>());
                }
                incorrectWords.get(syllablesOff).add(word + "-" + calculatedSyllables + " " + syllables[0]);
            }
        }
        wordsChecked++;
    }
}
