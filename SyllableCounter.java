import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to read (presumably) english words from stdin, outputting a syllable
 * count to stdout.
 * 
 */
public class SyllableCounter {

    static ArrayList<VowelSet> vowelSets = generateVowelSets("vowelData.csv");

    public static void main(String[] args) throws FileNotFoundException{


        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            System.out.println(calculateSyllables(sc.nextLine()));
        }
        sc.close();
    
    }

    /**
     * Calculates the number of syllables for a given word.
     * 
     * @param input a single word, not including whitespaces
     * 
     * @return syllable count for the whole word
     */
    public static int calculateSyllables(String input){
        ArrayList<String> word = Word.splitWordIntoGroups(input.toLowerCase());
        int syllables = 0;
        for(int i = 0; i < word.size(); i++){
            syllables += sylCount(word.get(i));
        }

        if (syllables >= 1){
            return syllables;
        }
        return 1; //default if count is 0
    }

    /**
     * Gets the syllable count for a particular vowel group based on the
     * vowelSets data field.
     * 
     * @param input A vowel group, including surrounding consonants
     * 
     * @return Syllable count for the group, or 1 if no match is found
     */
    public static int sylCount(String input){
        String vowelGroup = Word.getVowelGroup(input);
        for(VowelSet vs : vowelSets){
            if(vs.isVowelSet(vowelGroup)){
                return vs.compareSyllable(input);
            }
        }
        return 1; //assumes 1 syllable if the vowel combo isn't in our vowel sets
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