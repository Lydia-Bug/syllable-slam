# Syllable Slam

A program designed to read in lines consisting of single english words, outputting a syllable count for each.

## Running & Compiling

The program can be compiled by running the following commands in the project directory:
```
javac SyllableCounter.java VowelSet.java Word.java
java SyllableCounter < [filename]
```

Inputs should be single words, separated by a newline character.

Alternatively, you can run SyllableCounter without piping stdin. In this case, SyllableCounter will read lines sequentially from stdin as they are entered into the console, outputting syllable counts to stdout.

## Solving the Problem
---

 ### Defining and Detecting Syllables
 
 In order to come up with a comprehensive ruleset, we first had to find a way to best define a syllable. [The Wikipedia page on syllables](https://en.wikipedia.org/wiki/Syllable#Components) provided a good starting point.  
 Based on this, we determined that the best way to start was by looking at the vowel groups within a word. This provided an easy way to break up words into manageable segments, though it didn't count with any great accuracy. Typically, any given combination of 1-3 vowels would have a (partially) consistent syllable count. This was determined manually, by scouring [freedictionary.com](#).

 However, many combinations would contain exceptions to any given 'rule' e.g. most trailing 'e's. We determined that the key to finding these exceptions was to look not at the vowel group but the consonants surrounding it. Using this knowledge we could generate a list of 'consensus' syllable counts for vowel combos, and patterns where this was broken (with the corresponding count.)
 
 We determined which count was "normal" vs "exceptional" based on the proportion of words with each pronunciation, and their frequency of use in english.

 Different accents were also confounding factor for this problem. Where syllable counts for certain words were ambiguous, we first referred to internet databases to check if there was a consensus. Where there was no consensus, we went with "BBC English" pronunciation, assuming proper enunciation.


 ### The vowelData CSV

 Rather than using a sequence of if-else statements (which would be messy and hard to read), we opted to use a sequence of regex strings to check the amount of syllables in a particular vowel group.  
 The result was an ArrayList of `VowelSet` objects, which could be iteratively checked through to compare a given CVC group to each vowel pattern[^1].  

 Using this, we can count syllables based on the pattern of vowels and their surrounding syllables; if a given CVC group matches any of the regex patterns in the fourth column of the corresponding line, it will add the "exception count" (the rightmost number) to the syllable count of the word being processed. If there is no regex match, it will instead add the default count. Vowel groups not present in `vowelData.csv` are assumed to be 2 syllables.

 If you are interested in reading the csv but lack knowledge of regex strings, we recommend you refer to [this regex cheatsheet](https://www.rexegg.com/regex-quickstart.html) to help you decode what exactly each string is checking.

 [^1]: An important note here for those who would edit this file: vowel groups must be added in order of decreasing group length (i.e. 4-vowel groups should be placed higher than 3-vowel groups, etc.)

 ### Advantages of this Approach

Going with a ruleset-based approach brings a couple of main advantages. Most notably, it is a relatively fast approach, as it doesn't require the overhead that e.g. a machine-learning based approach may have.   
Additionally, the dataset the program relies on is small. While the difference in memory cost is negligible (we're not doing anything complex), this makes our dataset fairly easy to parse and change; e.g., if we wanted to alter or recreate the dataset for syllable counting in another language.


 ### Drawbacks & Challenges

Based on our current implementation, it is difficult to account for compound words in a given dataset. Typically this doesn't result in a miscount, but tends to cause problems when the two composite words end and start with a vowel, respectively.

 For example, the word "retroactive" would count as 3 syllables in our current system, despite typical pronunciation being 4 syllables.

 This also causes problems with some prefixes; see e.g. preemptive and coauthor.

 This is reflective of the limitations of rule-based approaches in general. Ultimately, the english language is inconsistent and contradictory at the best of times (despite what primary school teachers may tell you.)   
 Regardless of how comprehensive a given ruleset may be, there will always be edge-cases that break these rules, and it is impossible to account for all of these without either changing the approach or hard-coding counts. 

 ## Testing & Benchmarking
---
### Testing

The testing program can be compiled and run by running the following commands in the project directory:

```
javac tests/WordListTest.java
java tests/WordListTest
```
The test checks our code, against a set of test data. And says how many words were checked and correct, how many ambiguous words (words that could be pronounced with a different amount of syllables) were checked and correct. And the overall percentage that were correct. It also prints out all the incorrect words. This allowed us to check that our code was reasonably accurate. And check what type of words we were calculating wrong.

I used a set of 10,000 words that I found from [Sherwood School](http://sherwoodschool.ru/en/vocabulary/proficiency/). And I got the actual amount of syllables from Merriam-Webster using the [Merriam-Webster dictionary API](https://dictionaryapi.com/). This allowed me to write a script to automatically get the syllable amounts rather than manually creating my own test data.

Merriam-Webster doesn't say how many syllables each word has, but it does say how to pronounce a word using hyphens as breaks between syllables. So using that I can find out how many syllables are in a word. Merriam-Webster also uses brackets to indicate that what's inside the brackets is sometimes not said, depending on how you pronounce the word. So, the amount of syllables a word has is sometimes ambiguous. This means in my data set some words have two different amount of syllables, and the test will check if either one of them matches our calculated value.

There were some issues with the Merriam-Webster API. Not all words have their own definition like 'cleaning' which is just a different tense of 'clean', so it doesn't say how to pronounce the word. For words like this, they just didn’t get added to the dataset. I wasn't able to get the syllables of all 10,000 of my original words my final test data ended up having 9185 words.

### Benchmarking

As the program is rules-based and doesn't have any noticeable problems (even with speed), benchmarking is not really necessary as it may be with more processing-intensive implementations.

##Footnotes