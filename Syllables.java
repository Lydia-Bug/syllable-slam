import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Syllables{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
           System.out.println(SyllableChecker(sc.nextLine()));
           //System.out.println(splitWordIntoGroups(sc.nextLine()));
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
        ArrayList<String> esException = new ArrayList<String>(Arrays.asList("c","x","g"));

        //a group is a group of vowels and surrounding constantants; eg banana [ban, nan, na]
        //the base rule is that every group is one syllable
        ArrayList<String> groups = splitWordIntoGroups(word); 
        //Saves last word group to variable
        String lastGroup = groups.get(groups.size()-1);
        //Splits last word group into individual character strings and puts them into lastWordGroup
        String[] lastWordGroup = lastGroup.split("(?!^)");
        

        for(int i = 0; i < groups.size(); i++){
            //'u|ou' e.g. continuous, superfluous, etc
            Pattern p = Pattern.compile(".*uou.*");
            Matcher m = p.matcher(groups.get(i));
            if(m.matches()){
                syllables += 2;
            }else{
                syllables++;
            }
            
        }

    
        if (lastWordGroup[lastWordGroup.length-1].equals("e")){
            syllables --;
        }else if (lastWordGroup[lastWordGroup.length-2].equals("e") &&  lastWordGroup[lastWordGroup.length-1].equals("s")){
            if(!lastWordGroup[lastWordGroup.length-3].equals("g")&&!lastWordGroup[lastWordGroup.length-3].equals("x")&&!lastWordGroup[lastWordGroup.length-3].equals("c")){
                syllables --;   
            }
        }
        

    
        
        /* 
        for(int i = 0; i < word.length(); i++){
            if(vowels.contains(Sub(word, i))){ // if the current letter is a vowel
                if(Sub(word, i+1).equals("r")){ // if the next letter is "r" eg. "ar", "er"
                    syllables++;
                } else if(Sub(word, i).equals("u")){ // If the current letter is "u"
                    if(Sub(word, i+1).equals("a") && !Sub(word, i-1).equals("q")){ // if the next letter is "a" and the previous letter is NOT "q"
                        syllables++;
                    } else if(Sub(word, i + 1).equals("o") && Sub(word,i+2).equals("u")){ // if the next two letters are "uo"
                        syllables++;
                    }
                }
            }
            if(i == word.length() - 3){
                if(consonants.contains(Sub(word, i)) && Sub(word, i+1).equals("l") && Sub(word,i+2).equals("e")){ // if the word ends in a consonant and then "le"
                    syllables++;
                }
            }
        }
        */

        return syllables;
    }

    public static ArrayList<String> splitWordIntoGroups(String word){
        ArrayList<String> vowels = new ArrayList<String>(Arrays.asList("a", "e", "i", "o", "u"));

        ArrayList<int[]> vowelIndex = new ArrayList<int[]>();
        for(int i = 0; i < word.length(); i++){
            if(vowels.contains(Sub(word, i))){//current letter is vowel
                int j = i;
                while(vowels.contains(Sub(word, j)) && j != word.length()-1){
                    j++;
                }
                if(j == word.length()-1){
                    if(vowels.contains(Sub(word, j))){
                        vowelIndex.add(new int[]{i, j});
                    }else{
                        vowelIndex.add(new int[]{i, j-1}); 
                    }
                    break;
                }else{
                    vowelIndex.add(new int[]{i, j-1});
                    i = j-1;
                }
                
            }
        }
        ArrayList<String> groups = new ArrayList<String>();
        for(int i = 0; i < vowelIndex.size(); i++){
            if(vowelIndex.size() == 1){
                groups.add(word);
            }else if(i == 0){
                groups.add(word.substring(0, vowelIndex.get(i+1)[0]));
            }else if(i == vowelIndex.size()-1){
                groups.add(word.substring(vowelIndex.get(i-1)[1]+1));
            }else{
                groups.add(word.substring(vowelIndex.get(i-1)[0]+1, vowelIndex.get(i+1)[1]));
            }
            
        }
        return groups;
    }

    public static String Sub(String word, int index){
        return word.substring(index, index+1);
    }


}