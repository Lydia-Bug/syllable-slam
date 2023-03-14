import requests
import json

def addWordAndAmountOfSyllables(word):
    print(word.strip())
    amountOfSyllables = getAmountOfSyllables(word)
    if amountOfSyllables != False:
        words_and_syllables.write(word.strip() + " " + str(amountOfSyllables) + "\n")
    
def getAmountOfSyllables(word):
    hasWordPronunciation = False

    url = "https://www.dictionaryapi.com/api/v3/references/collegiate/json/" + word + "?key=4ad66567-38f9-4f67-a045-c01421e84bd2"
    request = requests.get(url)
    wordJson = request.json()

    dictWord = wordJson[0]
    
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

    wordSyllables = wordPronunciation.split("-")
    return len(wordSyllables)

#words = open("Test/wordList.txt", "r")
words = open("Test/wordList.txt", "r")

words_and_syllables = open('Test/wordsAndSyllables.txt', 'a')

for word in words:
  addWordAndAmountOfSyllables(word)

words_and_syllables.close();

