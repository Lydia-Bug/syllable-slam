import requests
import re
import json

def addWordAndAmountOfSyllables(word):
   amountOfSyllables = getAmountOfSyllables(word)
   if amountOfSyllables != False:
      outputLine = word.strip()
      for syllables in amountOfSyllables:
         outputLine = outputLine + "," + str(syllables)
      print(outputLine)
      words_and_syllables.write(outputLine + "\n")
    
def getWordPronunciation(word):
   # merriam webster api url
   url = "https://www.dictionaryapi.com/api/v3/references/collegiate/json/" + word + "?key=4ad66567-38f9-4f67-a045-c01421e84bd2"
   request = requests.get(url)
   wordJson = request.json()

   dictWord = wordJson[0]
   
   # the resulting json structure isn't the same for all words. Some words like cleaning doesn't have it's own definition 
   # because it's just the present tense of clean, so it doesn't have a way to pronounce it
   # So thats why these checks are here rather then just getting the pronunciation 
   if "hwi" in dictWord:
      dictWord = dictWord["hwi"]
   else:
      return False
    
   if "prs" in dictWord:
      wordPronunciation = dictWord["prs"][0]["mw"]
      wordInDictionary = dictWord["hw"].replace("*", "")
      if wordInDictionary != word.strip():
         return False
   else:
      return False
   
   return wordPronunciation

def getAmountOfSyllables(word):
   wordPronunciation = getWordPronunciation(word)

   if wordPronunciation == False:
      return False

   otherWordPronunciation = re.sub("\(.*\)", "", wordPronunciation) # If something is in brackets it may or may not be pronounced, so the word can have multiple pronunciations

   # hyphens are used to separate syllables
   wordSyllables = wordPronunciation.split("-") 
   otherWordSyllables = otherWordPronunciation.split("-")

   if len(wordSyllables) == len(otherWordSyllables):
      return [len(wordSyllables)]
 
   return [len(wordSyllables), len(otherWordSyllables)]

words = open("wordList.txt", "r")

words_and_syllables = open('wordsAndSyllables.txt', 'a')

for word in words:
  addWordAndAmountOfSyllables(word)

words_and_syllables.close();

