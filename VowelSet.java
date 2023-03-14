import java.util.Scanner;
import java.util.ArrayList;
/**Syllable class object */

public class VowelSet{
    /* Description on this regex here. Sorry in advance :( */
    private static final String IN_FORMAT = ""
    private int defaultCount, exceptionCount;
    private String chars;
    private ArrayList<String> exceptPatterns = new ArrayList<String>();
    
    /**
     * Creates a syllable object from a line of input.
     * 
     * @param inputString input string - should have four arguments, separated by
     *          whitespace. Should be in the format:
     *          [chars] [defaultCount] [exceptionCount] [exceptPatterns]
     *          defaultCount and exceptionCount are digits, and 
     *          exceptPatterns is a comma-separated list of regex patterns.
     */
    public VowelSet(String inputString){
        /*
         * Scanner to handle csv input; first three are chars>defaultcount>exception count
         * regex exceptions should have comma delim, add periodically into exceptPatterns
         * can make functions that do this for you 
         */
        String[] input = inputString.split(" ");
        try{
            break;
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Invalid input - too few arguments!");
            e.printStackTrace();
        }
        
    }

    /**
     * Checks if the string input as part of the constructor is sufficient to
     * initialise the class.
     * 
     * @param s String input
     * 
     * @return true if valid, false if not
     */
    private boolean checkInput(String s){

    }
}