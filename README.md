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

## About

Some (general) info on how the program works as a whole, going over each file >> vowelData.csv is the salient file here.

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
 
[paragraph on how we split by vowel groups --> CVC; initial considerations. Looking through scrabble/dict websites keyed in that the consonants surrounding each vowel group would be key to exceptions.]
 - include links to freedictionary
 - include a screenshot of the csv file, in the following section:

 [How we found exceptions, how we decided on rules vs exceptions.]

Different accents were a confounding factor for this problem. Where syllable counts for certain words were ambiguous, we first referred to internet databases to check if there was a consensus. Where there was no consensus, we went with "BBC English" pronunciation, assuming proper enunciation.

 ### The vowelData CSV
[include a screenshot]

 Rather than using a sequence of if-else statements (which would be messy and hard to read), we opted to use a sequence of regex strings to check the amount of syllables in a particular vowel group.

 If you are interested in reading the csv but lack knowledge of regex strings, we recommend you refer to [this regex cheatsheet](https://www.rexegg.com/regex-quickstart.html) to help you decode what exactly each string is checking.

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

 [information about testing/datasets goes here]  
 (since we used a rule-based approach, benchmarking isn't super important. So long as we state that it's fast due to the approach we took)