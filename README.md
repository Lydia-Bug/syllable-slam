# Testing

To run the test in command line, enter these lines

```
javac tests/WordListTest.java
java tests/WordListTest
```
The test checks our code, against a set of test data. And says how many words were checked and correct, how many ambiguous words (words that could be pronounced with the different amount of syllables) were checked and correct. And the percentage overall the were correct. It also prints out all the inccorect words. This allowed us to check that our code was reasonably accurate. And check what type of words we were calculating wrong.

I used a set of 10,000 words from [Sherwood School](http://sherwoodschool.ru/en/vocabulary/proficiency/). And I got the autuall amount of syllables from Merriam-Webster using the [Merriam-Webster dictionary API](https://dictionaryapi.com/). This allowed me to write a script to automacticly get the syllable amounts rather then manually creating my own test data.

Merriam-Webster doesn't say how many syllables each word has, but it does say how to pronouce a word using hyphens as breaks between syllables. So using that I can find out how many syllables are in a word. Merriam-Webster also uses brackets to indicate that what's inside the brackets is sometimes not said, depending on how you pronouce the word. So the amount of syllables a word has is sometimes ambiguous. This means data for some of my words has two different amount of syllables. 

There were some issues with the Merrian-Webster API, I wasn't able to get the syllables of all 10,000 of my origional words my final test data ended up having 9185 words. Not all words have their own defination like 'cleaning' which is just a different tense of 'clean', so it doesn't say how to pronouce the word. 
