import java.util.*;

public class MyTrieSet implements TrieSet61B {
    public static final int R = 256;
    private Node root;

    public MyTrieSet() {
        root = new Node();
    }
    /** Clears all items out of Trie */
    public void clear() {
        for (int i = 0; i < R; i++) {
            root.next[i] = null;
        }
        root.color = 0;
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    public boolean contains(String key) {
        Node current = root;
        for (int i = 0; i < key.length(); i++) {
            if (current.next[key.charAt(i)] == null) {
                return false;
            }
            current = current.next[key.charAt(i)];
        }
        return current.color == 1;
    }

    /** Inserts string KEY into Trie */
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node current = root;
        for (int i = 0; i < key.length(); i++) {
            if (current.next[key.charAt(i)] == null) {
                current.next[key.charAt(i)] = new Node();
            }
            current = current.next[key.charAt(i)];
        }
        current.color = 1;
    }

    /** Returns a list of all words that start with PREFIX */
    public List<String> keysWithPrefix(String prefix) {
        Node current = root;
        List<String> list = new LinkedList<>();
        for (int i = 0; i < prefix.length(); i++) {
            if (current.next[prefix.charAt(i)] != null) {
                current = current.next[prefix.charAt(i)];
            } else {
                return list;
            }
        }
        PrefixHelper(prefix, list, current);
        return list;
    }

    private void PrefixHelper(String x, List<String> list, Node root) {
        if (root.color == 1) {
            list.add(x);
        }
        for (int i = 0; i < R; i++) {
            if (root.next[i] != null) {
                PrefixHelper(x + (char) i, list, root.next[i]);
            }
        }
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public String longestPrefixOf(String key) {
        Node current = root;
        String longestPrefix = null;
        String longestBuilder = "";
        for (int i = 0; i < key.length(); i++) {
            if (current.next[key.charAt(i)] != null) {
                current = current.next[key.charAt(i)];
                longestBuilder += (char) key.charAt(i);
                if (current.color > 0) {
                    //longestPrefix = key.substring(0, i + 1);  //well, it's a trade-off to use this method: it will save memory, but it may be slower.
                    longestPrefix = longestBuilder;
                }
            } else {
                break;
            }
        }
        return longestPrefix;
    }

    private class Node {
        private Node[] next;
        private int color; //when color > 0, it is a key

        public Node() {
            next = new MyTrieSet.Node[R];
            color = 0;
        }
    }

}
