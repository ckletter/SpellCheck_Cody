/**
 * Class for containing data structure for trie
 * Contains its root, as well as insert, lookup, and print methods
 */
public class Trie {
    private final Prefix root;
    public static final int ASCII_SIZE = 256;
    public Trie() {
        root = new Prefix();
    }
    /**
     * Organize word in the dictionary into the trie in order to allow for lookups the size of each word
     * @param word the word being added to the dictionary
     */
    public void insert(String word) {
        Prefix currentPrefix = root;
        // Iterate through each letter
        int length = word.length();
        for (int i = 0; i < length; i++) {
            // Get the current letter
            int letterIndex = word.charAt(i);
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
    }
    /**
     * Checks if a given word is in the trie of words
     * @param word current word being checked
     * @return true if the word exists in the trie, false otherwise
     */
    public boolean lookup(String word) {
        Prefix currentPrefix = root;
        // Loop through each letter in the word
        int length = word.length();
        for (int i = 0; i < word.length(); i++) {
            // Get all the suffixes of the current prefix
            Prefix[] suffixes = currentPrefix.getSuffixes();
            int letterIndex = (int) word.charAt(i);
            // If the letter is not part of the ascii code, returns false (not a word in dictionary)
            if (letterIndex >= ASCII_SIZE) {
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
     * Prints each word that exists in the trie using postorder
     */
    public void printTrie() {
        traverse(root, "");
    }

    /**
     * Helper method for traversing each node in the trie, uses postorder
     */
    public void traverse(Prefix root, String prefix) {
        // Get the node's children, loop through each one at a time
        Prefix[] children = root.getSuffixes();
        for (int i = 0; i < ASCII_SIZE; i++) {
            if (children[i] != null) {
                traverse(children[i], prefix + (char) i);
            }
        }
        // Prints word if it exists
        if (root.isWord()) {
            System.out.println(prefix);
        }
    }
}
