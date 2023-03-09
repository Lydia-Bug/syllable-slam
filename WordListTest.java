import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class WordListTest {
    static int wordsChecked = 0;
    static int correctWords = 0;
    static ArrayList<ArrayList<String>> incorrectWords = new ArrayList<ArrayList<String>>();

    public static void main(String[] args){
        readWordFile("testWords.txt");
        System.out.println("Words checked: " + wordsChecked);
        System.out.println("Words correct: " + correctWords);
        double percentage = 100*(correctWords+0.0)/wordsChecked ;
        System.out.println("Percent of words correct: " + String.format("%.2f", percentage) + "%\n");
        for(int i = 1; i < incorrectWords.size(); i++){
            System.out.println("Words " + i + " syllable off: ");
            System.out.println(incorrectWords.get(i).toString() +"\n");
        }
    }
    
    public static void readWordFile(String fileName){
        try {
          File myObj = new File(fileName);
          Scanner myReader = new Scanner(myObj);
          while (myReader.hasNextLine()) {
            String[] line = myReader.nextLine().split(" ");
            checkWord(line[0], Integer.parseInt(line[1]));
          }
          myReader.close();
        } catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
    }

    public static void checkWord(String word, int syllables){
        int calculatedSyllables = Syllables.SyllableChecker(word);
        if(calculatedSyllables == syllables){
            correctWords++;
        }else{
            int syllablesOff = Math.abs(calculatedSyllables - syllables);
            while(incorrectWords.size() < syllablesOff+1){
                incorrectWords.add(new ArrayList<String>());
            }
            incorrectWords.get(syllablesOff).add(word + " " + calculatedSyllables + " " + syllables);
        }
        wordsChecked++;
    }
}
