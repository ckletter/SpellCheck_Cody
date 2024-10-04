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
        // Create trie for list of words in dictionary
        Trie dictTrie = new Trie();
        // Organize dictionary into a tree of Prefix objects to allow for more efficient lookups
        organizeDictionary(dictionary, dictTrie);
        // Create an arraylist for misspelled words found
        ArrayList<String> misspelledWords = new ArrayList<String>();
        // Loop through each word in the text
        for (String word : text) {
            // If the word is misspelled and not already found as a misspelled word, adds it to arraylist
            if (!dictTrie.lookup(word)) {
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
     * Organize each word in the dictionary into a tree of prefixes in order to allow for lookups the size of each word
     * @param dictionary array of strings for each word in dictionary
     * @param dictTrie initialized trie which hasn't been filled yet
     */
    public void organizeDictionary(String[] dictionary, Trie dictTrie) {
        // For each word in the dictionary
        for (String word : dictionary) {
            dictTrie.insert(word);
        }
    }
}