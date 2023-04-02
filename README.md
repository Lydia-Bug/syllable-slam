# Syllable Slam

Description of this program...

## Running & Compiling

The program can be compiled by running the following commands in the project directory:
```
javac SyllableCounter.java VowelSet.java Word.java
java SyllableCounter < [filename]
```

Inputs should be single words, separated by a newline character.

Alternatively, you can run SyllableCounter without piping stdin. In this case, SyllableCounter will read lines sequentially from stdin as they are entered into the console, outputting syllable counts to stdout.

## Solving the Problem

Report-style section on how we approached the issue; ie rules-based; using regex and CSV for efficiency. <-- implement referencing

Advantages and drawbacks of this approach.
 - english language as a whole
 - early approaches (& why we didn't use them); pre/suffixes, IPA (which is still accent dependent)
 - pronunciation
 - rules & exceptions
 - problems we haven't been able to solve
 - tricky (3/4 letter vowel combos) & how they relate to linguistic stuff



 ### Defining and Detecting Syllables
 
[paragraph on how we split by vowel groups --> CVC; [define what a cvc group is] initial considerations. Looking through scrabble/dict websites keyed in that the consonants surrounding each vowel group would be key to exceptions.]
 - include links to freedictionary
 - include a screenshot of the csv file, in the following section:

 [How we found exceptions, how we decided on rules vs exceptions.]

Different accents were a confounding factor for this problem. Where syllable counts for certain words were ambiguous, we first referred to internet databases to check if there was a consensus. Where there was no consensus, we went with "BBC English" pronunciation, assuming proper enunciation.

 ### The vowelData CSV
[include a screenshot]

 Rather than using a sequence of if-else statements (which would be messy and hard to read), we opted to use a sequence of regex strings to check the amount of syllables in a particular vowel group.  
 The result was an ArrayList of `VowelSet` objects, which could be iteratively checked through to compare a given CVC group to each vowel pattern[^1].  

 Using this, we can count syllables based on the pattern of vowels and their surrounding syllables; if a given CVC group matches any of the regex patterns in the fourth column of the 

 If you are interested in reading the csv but lack knowledge of regex strings, we recommend you refer to [this regex cheatsheet](https://www.rexegg.com/regex-quickstart.html) to help you decode what exactly each string is checking.

 [^1]: An important note here for those who would edit this file: vowel groups must be added in order of decreasing group length (i.e. 4-vowel groups should be placed higher than 3-vowel groups, etc.)

 ### Advantages of this Approach
 - speed (vs ML approach)
 - don't need to rely on large databases (vs Dict approach)
 - flexible when eg changing counting rules (for eg if we want to change the language when counting; so long as the csv is formatted correctly it should still work with the class)


 ### Challenges

[Paragraph on the nature of the english language as opposed to, say, spanish or transcribed maori/japanese/etc] Mainly to complain about single exceptions like cafe which is a french borrow-word and which can only be detected by hard-coding. Due to the sheer number of words present in the english language, coding for single edge-cases would overcomplicate significantly for an infinitisemal increase in accuracy.]


 Additionally, based on our current implementation, it is difficult to account for compound words in a given dataset. Typically this doesn't result in a miscount, but tends to cause problems when the two composite words end and start with a vowel, respectively.

 For example, the word "retroactive" would count as 3 syllables in our current system, despite typical pronunciation being 4 syllables.

 This also causes problems with some prefixes; see e.g. preemptive and coauthor.

 ## Testing & Benchmarking

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
