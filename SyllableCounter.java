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

        
        ArrayList<VowelSet> vowelSets = generateVowelSets("data/vowelData.csv");
        //File f = new File("data/wordList.txt");

        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            System.out.println(calculateSyllables(sc.nextLine(), vowelSets));
        }
        sc.close();
        /* 
        Scanner sc = new Scanner(f);
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            System.err.println(line + ":");
            String[] word = Word.getWordGroups(line);
            for(String group : word){
                System.out.print(group + "\t");
            }
            System.out.println();
        }
        sc.close();
        */
    
    }

    public static int calculateSyllables(String input, ArrayList<VowelSet> vowelSets){
        ArrayList<String> word = Word.splitWordIntoGroups(input.toLowerCase());
        int syllables = 0;
        for(int i = 0; i < word.size(); i++){
            String vowelGroup = getVowelGroup(word.get(i));
            boolean isVowelSet = false;
            for(VowelSet vs : vowelSets){
                if(vs.isVowelSet(vowelGroup)){
                    syllables += vs.compareSyllable(word.get(i));
                    isVowelSet = true;
                }
            }
            if(i == word.size()-1 && word.get(i).matches("[a-z]*e") && syllables >= 1){
                // trailing e so no syllable
                isVowelSet = true;
            }
            if(!isVowelSet){
                syllables++; // if not in our vowel sets its presumed to be one syllable
            }
        }
        return syllables;
    }

    public static String getVowelGroup(String cvcGroup){
        ArrayList<String> vowels = new ArrayList<String>(Arrays.asList("a", "e", "o", "i", "u"));
        String vowelGroup = "";
            for(int i = 0; i < cvcGroup.length(); i++){
                if(vowels.contains(cvcGroup.substring(i, i+1))){
                    vowelGroup += cvcGroup.substring(i, i+1);
                }
            }
        return vowelGroup;
    }

    /**
     * Generates arraylist of vowelSet object from txt file
     * 
     * @param file name of file that the vowel sets are generated from
     * 
     * @return ArrayList of vowel sets
     */
    public static ArrayList<VowelSet> generateVowelSets(String file){
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

}