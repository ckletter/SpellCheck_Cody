/**
 * Class for holding datatype TST
 * Contains its root, and methods for inserting and looking up words
 */
public class TST {
    private final TSTNode root;
    public TST() {
        root = new TSTNode();
        // Set root letter to m so equally goes to the left and right same amount of time
        root.setLetter('m');
    }

    /**
     * Given a word in the dictionary, inserts that word into the TST for efficient lookups
     * @param word word in the dictionary
     */
    public void insert(String word) {
        TSTNode currentNode = root;
        // Keep looping until end of the word is reached
        int length = word.length();
        int i = 0;
        while (i < length) {
            char currentLetter = word.charAt(i);
            // If the current node's letter does not exist
            if (currentNode.getLetter() == '\u0000') {
                // Set the letter of the node to the current letter
                currentNode.setLetter(currentLetter);
                // Go to next letter in word
                i += 1;
                // if the middle child doesn't exist, create the node
                if (currentNode.getMiddle() == null) {
                    currentNode.setMiddle(new TSTNode());
                }
                // Go down to node's middle child
                currentNode = currentNode.getMiddle();
                // skip rest of loop, go to next letter in word
                continue;
            }
            char tstLetter = currentNode.getLetter();
            // Compare letter to current node in TST
            // If letter is the same as TST letter, current node becomes middle child
            if (currentLetter == tstLetter) {
                // if the middle child doesn't exist, create the node
                if (currentNode.getMiddle() == null) {
                    currentNode.setMiddle(new TSTNode());
                }
                currentNode = currentNode.getMiddle();
                // "accept" letter, go to next letter in the word
                i += 1;
            }
            // If the letter is greater than TST letter, current node goes to right child
            else if (currentLetter > tstLetter) {
                // if the right child doesn't exist, create the node
                if (currentNode.getRight() == null) {
                    currentNode.setRight(new TSTNode());
                }
                currentNode = currentNode.getRight();
            }
            // If the letter is less than TST letter, current node goes to left child
            else {
                // if the left child doesn't exist, create the node
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(new TSTNode());
                }
                currentNode = currentNode.getLeft();
            }
        }
        // Set the final node to true, suggesting that the route of its parents are a word
        currentNode.becomeWord();
    }

    /**
     * Lookup whether a given word is present in the TST
     * @param word word in the given text
     * @return true if the word exists in TST, false otherwise
     */
    public boolean lookup(String word) {
        TSTNode currentNode = root;
        // Keep looping through each letter until end of the word is reached
        int length = word.length();
        int i = 0;
        while (i < length) {
            char currentLetter = word.charAt(i);
            // If current node does not exist (has reached a leaf), return false (search miss)
            if (currentNode == null) {
                return false;
            }
            char tstLetter = currentNode.getLetter();
            // If letter is the same as TST node letter, current node becomes middle child
            if (currentLetter == tstLetter) {
                // Set current node to middle child (choose to accept the letter)
                i += 1;
                currentNode = currentNode.getMiddle();
            }
            // If letter is less than TST letter, current node becomes left child
            else if (currentLetter < tstLetter) {
                currentNode = currentNode.getLeft();
            }
            // If letter is greater than TST letter, current node becomes right child
            else {
                currentNode = currentNode.getRight();
            }
        }
        // Once the end of the word has been reached, if the current node is a word, then return true
        return currentNode.isWord();
    }
}
