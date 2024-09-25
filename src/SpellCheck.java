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
        Prefix startPrefix = new Prefix();
        organizeDictionary(dictionary, startPrefix);
        ArrayList<String> misspelledWords = new ArrayList<String>();
        for (String word : text) {
            if (!checkMisspelled(word, startPrefix)) {
                if (!isDuplicated(misspelledWords, word)) {
                    misspelledWords.add(word);
                }
            }
        }
        String[] misspelledArray = new String[misspelledWords.size()];
        misspelledWords.toArray(misspelledArray);
        for (String word : misspelledArray) {
            System.out.println(word);
        }
        return misspelledArray;
    }
    public boolean isDuplicated(ArrayList<String> misspelledWords, String misspelled) {
        int length = misspelledWords.size();
        for (int i = 0; i < length; i++) {
            String word = misspelledWords.get(i);
            if (word.equals(misspelled)) {
                return true;
            }
        }
        return false;
    }
    public boolean checkMisspelled(String word, Prefix currentPrefix) {
        // Loop through each letter in the word
        for (int i = 0; i < word.length(); i++) {
            Prefix[] suffixes = currentPrefix.getSuffixes();
            char letter = word.charAt(i);
            int letterIndex = indexShift(letter);
            if (letterIndex < 0 || letterIndex > 26) {
                return false;
            }
            Prefix nextPrefix = suffixes[letterIndex];
            if (nextPrefix == null) {
                return false;
            }
            currentPrefix = nextPrefix;
        }
        return currentPrefix.isWord();
    }
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