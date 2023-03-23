import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SyllableCounter {
    public static void main(String[] args) throws FileNotFoundException{
        /**
         * generate arraylist of VowelSet
         * NOTE FOR CSV REGEX: need to escape backslash; not sure how well this will work yet
         * 
         * while input:
         *      split line into syllables based on vowel groups
         *      have some kind of handling for y
         *      extract vowels
         *      find vowels in vowelset arraylist
         *          foreach vowel group: misinterpret
         *                  [special if statement for handling final syllable exceptions]
         *                  add to count based on rule matching
         *                  add DEFAULT_SYLLABLES (2) if vowel combo not found
         *          output count to stdout
         */

        
        ArrayList<VowelSet> vowelSets = generateArrayListOfVowelSets("test.txt");
        
        


    }

    /**
     * Generates arraylist of vowelSet object from txt file
     * 
     * @param file name of file that the vowel sets are generated from
     * 
     * @return ArrayList of vowel sets
     */
    public static ArrayList<VowelSet> generateArrayListOfVowelSets(String file){
        ArrayList<VowelSet> vowelSets = new ArrayList<VowelSet>();
        BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while (line != null) {
				vowelSets.add(new VowelSet(line));
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return vowelSets;
    }

    /**
     * Splits the word into groups, each group has a group of consecutive vowels and all surrounding constanants
     * 
     * @param word Word to split up
     * 
     * @return ArrayList of word groups
     */
    public static ArrayList<String> splitWordIntoGroups(String word){
        ArrayList<int[]> vowelIndex = getIndexesOfVowelGroups(word);

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

    /**
     * Finds the indexes of consecutive vowel groups in a word
     * 
     * @param word Word to get indexes of vowel groups
     * 
     * @return ArrayList of int array of vowel group indexes
     */
    public static ArrayList<int[]> getIndexesOfVowelGroups(String word){
        ArrayList<String> vowels = new ArrayList<String>(Arrays.asList("a", "e", "i", "o", "u"));

        ArrayList<int[]> vowelIndex = new ArrayList<int[]>();
        for(int i = 0; i < word.length(); i++){
            if(vowels.contains(word.substring(i, i))){//current letter is vowel
                int j = i;
                while(vowels.contains(word.substring(j, j)) && j != word.length()-1){
                    j++;
                }
                if(j == word.length()-1){//if vowel group goes to end of word
                    if(vowels.contains(word.substring(j, j))){
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
            //if y is surrounded by constants then its counted as a vowel
            if(word.substring(i, i).equals("y")){
                boolean surroundingConstants = false;
                if(i != 0){
                    if(vowels.contains(word.substring(i-1, i-1))){
                        surroundingConstants = true;
                    }
                }
                if(i != word.length()-1){
                    if(vowels.contains(word.substring(i+1, i+1))){
                        surroundingConstants = true;
                    }
                }
                if(!surroundingConstants){
                    vowelIndex.add(new int[]{i, i});
                }
            }
        }
        return vowelIndex;
    }
}
