import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class generateTestFile{
  public static void main(String[] args) throws IOException{
    readWordFile("wordList.txt");
  }

  public static void readWordFile(String fileName) throws IOException{
    try {
      File myObj = new File(fileName);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        addWordAndSyllableToTextFile(myReader.nextLine(), "testWords.txt");
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public static void addWordAndSyllableToTextFile(String word, String fileName) throws IOException{
      FileWriter fw = new FileWriter(fileName, true);
      BufferedWriter bw = new BufferedWriter(fw);
      int syllables = getSyllables(word);
      if(syllables != 0){
        bw.write(word + " " + syllables);
        bw.newLine();
      }
      bw.close();
  }

  public static int getSyllables(String word) throws IOException{
    String urlString = "https://www.dictionaryapi.com/api/v3/references/collegiate/json/" + word + "?key=4ad66567-38f9-4f67-a045-c01421e84bd2";
    URL url = new URL(urlString);
    
    try (InputStream input = url.openStream()) {
      InputStreamReader isr = new InputStreamReader(input);
      BufferedReader reader = new BufferedReader(isr);
      StringBuilder json = new StringBuilder();
      int c;
        while ((c = reader.read()) != -1) {
          json.append((char) c);
        }

      String jsonString = json.toString() ;
      String[] stringSplit = jsonString.split("\"mw\":");
      if(stringSplit.length == 1){
        return 0;
      }
      stringSplit = stringSplit[1].split("\"");
     
      String[] wordSyllables = stringSplit[1].split("-");
      
      return wordSyllables.length;
    }
    
  }
}