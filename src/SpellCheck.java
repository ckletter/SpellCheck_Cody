import java.lang.reflect.Array;
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
        // Create TST for list of words in dictionary
        TST dictTST = new TST();
        // Organize dictionary into a TST of TSTNodes to allow for more efficient lookups while saving space
        organizeTST(dictionary, dictTST);
        // New TST for misspelled words found
        TST misspelledTST = new TST();
        // Create new arraylist for misspelled words found
        ArrayList<String> misspelledWords = new ArrayList<String>();
        // Loop through each word in the text
        for (String word : text) {
            // If the word is misspelled and not already found as a misspelled word, adds it to arraylist
            if (!dictTST.lookup(word)) {
                if (!misspelledTST.lookup(word)) {
                    misspelledTST.insert(word);
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
     * Organize each word in the dictionary into a TST in order to allow for efficient lookups while saving storage
     *
     * @param dictionary array of strings for each word in dictionary
     * @param dictTST    initialized TST which hasn't been filled yet
     */
    public void organizeTST(String[] dictionary, TST dictTST) {
        // For each word in the dictionary
        for (String word : dictionary) {
            // add word to TST
            dictTST.insert(word);
        }
    }
}
//    public String[] checkWords(String[] text, String[] dictionary) {
//        // Create trie for list of words in dictionary
//        Trie dictTrie = new Trie();
//        // Organize dictionary into a trie of Prefix objects to allow for more efficient lookups
//        organizeTrie(dictionary, dictTrie);
//        // Create a new trie for misspelled words found
//        Trie misspelledTrie = new Trie();
//        // Create new arraylist for misspelled words found
//        ArrayList<String> misspelledWords = new ArrayList<String>();
//        // Loop through each word in the text
//        for (String word : text) {
//            // If the word is misspelled and not already found as a misspelled word, adds it to arraylist
//            if (!dictTrie.lookup(word)) {
//                if (!misspelledTrie.lookup(word)) {
//                    misspelledTrie.insert(word);
//                    misspelledWords.add(word);
//                }
//            }
//        }
//        // Convert arraylist to array and return all misspelled words
//        String[] misspelledArray = new String[misspelledWords.size()];
//        misspelledWords.toArray(misspelledArray);
//        return misspelledArray;
//    }
//
//    /**
//     * Organize each word in the dictionary into a tree of prefixes in order to allow for lookups the size of each word
//     * @param dictionary array of strings for each word in dictionary
//     * @param dictTrie initialized trie which hasn't been filled yet
//     */
//    public void organizeTrie(String[] dictionary, Trie dictTrie) {
//        // For each word in the dictionary
//        for (String word : dictionary) {
//            dictTrie.insert(word);
//        }
//    }
//}