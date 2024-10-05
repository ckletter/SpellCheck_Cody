/**
 * Class for each node in TST
 * contains left, right, and middle children, letter, and whether the route of its parents a word
 */
public class TSTNode {
    private TSTNode left, middle, right;
    private char letter;
    private boolean isWord;
    public TSTNode() {
    }
    // Getter and setter methods for children and letter
    public char getLetter() {
        return letter;
    }
    public void setLetter(char letter) {
        this.letter = letter;
    }

    public TSTNode getLeft() {
        return left;
    }

    public void setLeft(TSTNode left) {
        this.left = left;
    }

    public void setMiddle(TSTNode middle) {
        this.middle = middle;
    }

    public void setRight(TSTNode right) {
        this.right = right;
    }

    public TSTNode getMiddle() {
        return middle;
    }

    public TSTNode getRight() {
        return right;
    }

    public boolean isWord() {
        return isWord;
    }

    /**
     * Sets the node to being a word, meaning route of its parents are a word
     */
    public void becomeWord() {
        isWord = true;
    }
}
