import java.util.ArrayList;
import java.util.Arrays;

/**
 * Support class containing methods to assist with syllable counting of words
 */


public class Word {
    private static final char[] vowels = {
        'a', 'e', 'i', 'o', 'u'
    };
    private static final char[] yvowels = {
        'a', 'e', 'i', 'o', 'u', 'y'
    };


    public static String[] getWordGroups(String input){
        ArrayList<String> groups = new ArrayList<String>();

        boolean inGroup = false, isY = false;
        int start = -1, firstCons = 0, nextCons = 0;
        for(int i = 0; i < input.length(); i++){
            char ch = input.charAt(i);
            boolean isVowel = Arrays.binarySearch(vowels, ch) >= 0;
            boolean vorY = Arrays.binarySearch(yvowels, ch) >= 0;
            if((vorY && !inGroup) || i == input.length() -1){ //new vowel found;
                
                if(i == input.length() -1){
                    i++;
                    // if(Arrays.binarySearch(yvowels, input.charAt(i-2)) < 0){
                        
                    // }
                }
                
                /*
                 *  - groupEnd = i
                 *  - get the substring of [word] from lastGroup (first cons) - groupEnd (last cons)
                 */
                if (start != -1){
                    groups.add(input.substring(firstCons, i));
                }
                firstCons = nextCons;
                start++;


                inGroup = true;
                if(ch == 'y'){
                    isY = true;
                } else {
                    isY = false;
                }
            } else if (isVowel && isY && inGroup){ //prev vowel was a y; 
                isY = false;
            } else if (!isVowel && inGroup){ //first cons outside of vowel group
                nextCons = i;
                inGroup = false;
            } else if (i == input.length() - 1){ //fails all other conditions and is end of word
                 groups.add(input.substring(firstCons));
            }
        }

        String[] result = new String[groups.size()];
        for(int i = 0; i < groups.size(); i++){
            result[i] = groups.get(i);
        }
        return result;
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
            if(Sub(word, i).equals("y")){
                boolean surroundingConstants = false;
                if(i != 0){
                    if(vowels.contains(Sub(word, i-1))){
                        surroundingConstants = true;
                    }
                }
                if(i != word.length()-1){
                    if(vowels.contains(Sub(word, i+1))){
                        surroundingConstants = true;
                    }
                }
                if(!surroundingConstants){
                    vowelIndex.add(new int[]{i, i});
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
                groups.add(word.substring(vowelIndex.get(i-1)[1]+1, vowelIndex.get(i+1)[0]));
            }
            
        }
        return groups;
    }

    public static String Sub(String word, int index){
        return word.substring(index, index+1);
    }


}