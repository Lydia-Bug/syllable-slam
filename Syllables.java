import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Syllables{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            System.out.println(SyllableChecker(sc.nextLine()));
        }
        sc.close();
    }

    public static int SyllableChecker(String word){
        word = word.toLowerCase();
        String[] wordArr = word.split("");
        int syllables = 0;
        ArrayList<String> vowels = new ArrayList<String>(Arrays.asList("a", "e", "i", "o", "u"));
        ArrayList<String> vowls = new ArrayList<String>(Arrays.asList("a", "i", "o", "u"));
        ArrayList<String> consonants = new ArrayList<String>(Arrays.asList("b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z"));
        ArrayList<String> blends = new ArrayList<String>(Arrays.asList("bl", "br", "cl", "cr", "dr", "fr", "tr", "fl", "gl", "gr", "pl", "pr", "sl", "sm", "sp", "st"));

        for(int i = 0; i < word.length(); i++){
            if(vowels.contains(Sub(word, i))){
                if(Sub(word, i+1).equals("r")){
                    syllables++;
                } else if(Sub(word, i).equals("u")){
                    if(Sub(word, i+1).equals("a") && !Sub(word, i-1).equals("q")){
                        syllables++;
                    } else if(Sub(word, i + 1).equals("o") && Sub(word,i+2).equals("u")){
                        syllables++;
                    }
                }
            }
            if(i == word.length() - 3){
                if(consonants.contains(Sub(word, i)) && Sub(word, i+1).equals("l") && Sub(word,i+2).equals("e")){
                    syllables++;
                }
            }
        }

        return syllables;
    }

    public static String Sub(String word, int index){
        return word.substring(index, index+1);
    }
}