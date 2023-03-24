import java.util.Scanner;
import java.util.ArrayList;
/**Syllable class object */

public class VowelSet{
    /* Description on this regex here. Sorry in advance :(
     * alphabetical -> whitespace -> digit -> whitespace -> digit -> whitespace -> regex string (non-whitespace)
     */
    private static final String IN_FORMAT = "[a-z]+ +\\d+ +\\d+ *[^\\s]*";
    private int defaultCount, exceptionCount;
    private String chars;
    private ArrayList<String> exceptPatterns = new ArrayList<String>(); //not sure if this will cause errors if empty
    
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
            System.err.println(inputString + "Not a valid input! Too few arguments...");

        } else {
            String[] input = inputString.split(" +");
            
            chars = input[0];
            defaultCount = Integer.parseInt(input[1]);
            exceptionCount = Integer.parseInt(input[2]);

            try {
                for (String reg : input[3].split(",")) { //TODO - try this with non-except
                    exceptPatterns.add(reg);
                }
            } catch (ArrayIndexOutOfBoundsException e){
                //System.err.println("[line] did not have a regex list");

            }
            
        }
        
        /*
         * Have handling for inputs with no reg-exceptions
         */ 
        
    }

    /**
     * Method to compare a given cons-vowel-cons combo with the regex patterns
     * found in this object.
     * 
     * @param cvcGroup String of chars to be checked against exceptPatterns
     * 
     * @return exceptionCount is cvcGroup matches any pattern inside
     *          exceptPatterns; defaultCount if no match is found.
     */
    public int compareSyllable(String cvcGroup){
        for(String pattern : exceptPatterns){
            if (cvcGroup.matches(pattern)){
                return exceptionCount;
            }
        }
        return defaultCount;
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
     * toString method for VowelSet.
     * 
     * @return chars
     */
    public String toString(){
        return chars;
    }

    /**
     * Method that returns information about this object.
     * 
     * @return String containing information on this object
     */
    public String debugString(){
        return("Combo: " + chars + "\tDefault: " + defaultCount + "\tExc: " 
                + exceptionCount + "\tNum Exceptions: " + exceptPatterns.size());
    }
}