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

Some info on how the program works as a whole, going over each file >> vowelData.csv is the salient file here.

## Solving the Problem

Report-style section on how we approached the issue; ie rules-based; using regex and CSV for efficiency. <-- implement referencing

Advantages and drawbacks of this approach.
 - english language as a whole
 - early approaches (& why we didn't use them); pre/suffixes, IPA (which is still accent dependent)
 - pronunciation
 - rules & exceptions
 - problems we haven't been able to solve
 - tricky (3/4 letter vowel combos) & how they relate to linguistic stuff

 ## Testing

 [information about testing/datasets goes here]