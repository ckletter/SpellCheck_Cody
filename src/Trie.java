public class Trie {
    private final Prefix root;
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
    }
    /**
     * Checks if a given word in the text provided is misspelled based off dictionary of words
     * @param word current word being checked
     * @return true if the word exists in the dictionary, false otherwise
     */
    public boolean lookup(String word) {
        Prefix currentPrefix = root;
        // Loop through each letter in the word
        int length = word.length();
        for (int i = 0; i < word.length(); i++) {
            // Get all the suffixes of the current prefix
            Prefix[] suffixes = currentPrefix.getSuffixes();
            char letter = word.charAt(i);
            int letterIndex = indexShift(letter);
            // If the letter is not part of the alphabet or an apostrophe, returns false (not a word in dictionary)
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
    public void printTrie() {

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
}
