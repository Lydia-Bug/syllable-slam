import java.util.Scanner;

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

        if(word.length() > 0){
            syllables++;
        }
        
        for(int i = 0; i < word.length(); i++){
            if(Sub(word, i).equals("u") && Sub(word, i+1).equals("a") && !Sub(word, i-1).equals("q")){
                syllables++;
            }
        }

        return syllables;
    }

    public static String Sub(String word, int index){
        return word.substring(index, index+1);
    }
}