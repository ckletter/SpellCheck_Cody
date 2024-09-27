import java.util.ArrayList;

/**
 * Spell Check
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 *
 * Completed by: Cody Kletter
 * */

public class SpellCheck {


    /**
     * checkWords finds all words in text that are not present in dictionary
     *
     * @param text       The list of all words in the text.
     * @param dictionary The list of all accepted words.
     * @return String[] of all mispelled words in the order they appear in text. No duplicates.
     */
    public String[] checkWords(String[] text, String[] dictionary) {
        // Create root prefix for dictionary tree
        Prefix startPrefix = new Prefix();
        // Organize dictionary into a tree of Prefix objects to allow for more efficient lookups
        organizeDictionary(dictionary, startPrefix);
        // Create an arraylist for misspelled words found
        ArrayList<String> misspelledWords = new ArrayList<String>();
        // Loop through each word in the text
        for (String word : text) {
            // If the word is misspelled and not already found as a misspelled word, adds it to arraylist
            if (!existsInDictionary(word, startPrefix)) {
                if (!isDuplicated(misspelledWords, word)) {
                    misspelledWords.add(word);
                }
            }
        }
        // Convert arraylist to array and return all misspelled words
        String[] misspelledArray = new String[misspelledWords.size()];
        misspelledWords.toArray(misspelledArray);
        return misspelledArray;
    }

    /**
     * Checks if the current misspelled word has been found as a misspelled word already
     * @param misspelledWords current list of misspelled words found
     * @param misspelled current misspelled word
     * @return true if the misspelled word has already been found, false otherwise
     */
    public boolean isDuplicated(ArrayList<String> misspelledWords, String misspelled) {
        int length = misspelledWords.size();
        // Loop through each word in our found misspelled words
        for (int i = 0; i < length; i++) {
            String word = misspelledWords.get(i);
            // If the two strings are the same, returns that it is a duplicate
            if (word.equals(misspelled)) {
                return true;
            }
        }
        // If word does not match with any found misspelled words, returns false
        return false;
    }

    /**
     * Checks if a given word in the text provided is misspelled based off dictionary of words
     * @param word current word being checked
     * @param currentPrefix the root prefix ""
     * @return true if the word exists in the dictionary, false otherwise
     */
    public boolean existsInDictionary(String word, Prefix currentPrefix) {
        // Loop through each letter in the word
        for (int i = 0; i < word.length(); i++) {
            // Get all the suffixes of the current prefix
            Prefix[] suffixes = currentPrefix.getSuffixes();
            char letter = word.charAt(i);
            int letterIndex = indexShift(letter);
            // If the letter is not part of the alphabet or an apostraphe, returns false (not a word in dictionary)
            if (letterIndex < 0 || letterIndex > 26) {
                return false;
            }
            Prefix nextPrefix = suffixes[letterIndex];
            // If the current letter does not exist in the prefix's children, then word does not exist in dictionary
            // Returns misspelled word
            if (nextPrefix == null) {
                return false;
            }
            // Adds current letter onto current prefix in dictionary tree
            currentPrefix = nextPrefix;
        }
        // After word has been fully looped through, returns true if the word exists as a word in the dictionary tree
        return currentPrefix.isWord();
    }

    /**
     * given a char, returns it's corresponding index in the alphabet starting from 0; returns 26 if it's an apostrophe
     * @param letter current letter in the word
     * @return corresponding index
     */
    public int indexShift(char letter) {
        // Deal with edge case of apostrophe, set to index 26 in suffixes array
        int letterIndex;
        if ((int) letter == 39) {
            letterIndex = 26;
        }
        else {
            // Convert the letter to an index between 0 and 25, for each letter in the alphabet
            letterIndex = letter - 97;
        }
        return letterIndex;
    }

    /**
     * Organize each word in the dictionary into a tree of prefixes in order to allow for lookups the size of each word
     * @param dictionary array of strings for each word in dictionary
     * @param root Object prefix with no children yet
     */
    public void organizeDictionary(String[] dictionary, Prefix root) {
        Prefix currentPrefix = root;
        // For each word in the dictionary
        for (String word : dictionary) {
            // Iterate through each letter
            for (int i = 0; i < word.length(); i++) {
                // Get the current letter
                char letter = word.charAt(i);
                int letterIndex = indexShift(letter);
                Prefix[] currentSuffixes = currentPrefix.getSuffixes();
                // If that prefix does not exist in the currentPrefix's children
                // Create new child of that prefix with current letter added
                if (currentSuffixes[letterIndex] == null) {
                    currentPrefix.createPrefix(letterIndex);
                }
                // Go to the location of that newly created prefix
                currentPrefix = currentSuffixes[letterIndex];
            }
            // Once at the end of the current word, set that prefix to existing in the dictionary
            currentPrefix.becomeWord();
            // Go back to root prefix for start of next word
            currentPrefix = root;
        }
    }
}