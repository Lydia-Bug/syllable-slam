public class SyllableCounter {
    public static void main(String[] args){
        /**
         * generate arraylist of VowelSet
         * NOTE FOR CSV REGEX: need to escape backslash; not sure how well this will work yet
         * 
         * while input:
         *      split line into syllables based on vowel groups
         *      have some kind of handling for y
         *      extract vowels
         *      find vowels in vowelset arraylist
         *          foreach vowel group:
         *                  [special if statement for handling final syllable exceptions]
         *                  add to count based on rule matching
         *                  add DEFAULT_SYLLABLES (2) if vowel combo not found
         *          output count to stdout
         */

        VowelSet v = new VowelSet("aai 1 2 ^\\s+aaing,aai[xc]");
        System.out.println(v.toString());
    }
}
