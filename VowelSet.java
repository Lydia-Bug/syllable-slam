import java.util.Scanner;
import java.util.ArrayList;
/**Syllable class object */

public class VowelSet{
    /* Description on this regex here. Sorry in advance :(
     * alphabetical -> whitespace -> digit -> whitespace -> digit -> whitespace -> regex string (non-whitespace)
     */
    private static final String IN_FORMAT = "[A-Za-z]+\\s\\d+\\s\\d+\\s[^\\s]*";
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
        if (!isValidInput(inputString)){
            System.err.println("Not a valid input! Too few arguments...");

        } else {
            String[] input = inputString.split(" ");
            chars = input[0];
            defaultCount = Integer.parseInt(input[1]);
            exceptionCount = Integer.parseInt(input[2]);

            
            for (String reg : input[3].split(",")) { //TODO - try this with non-except
                exceptPatterns.add(reg);
            }
        }
        /*
         * Scanner to handle csv input; first three are chars>defaultcount>exception count
         * regex exceptions should have comma delim, add periodically into exceptPatterns
         *can make functions that do this for you 
         */
        
        /*
         * Have handling for inputs with no reg-exceptions
         */ 
        
    }

    /**
     * Checks if the string input as part of the constructor is sufficient to
     * initialise the class.
     * 
     * @param s String input
     * 
     * @return true if valid, false if not
     */
    private boolean isValidInput(String s){
        return s.matches(IN_FORMAT);
    }

    /**
     * toString method for VowelSet. Mostly for testing purposes.
     * 
     * @return String representation of this object
     */
    public String toString(){
        return("Combo: " + chars + "\tDefault: " + defaultCount + "\tExc: " 
                + exceptionCount + "\tNum Exceptions: " + exceptPatterns.size());
    }
}