package bearmaps.proj2ab;
import java.util.*;

public class MyTrieSet implements TrieSet61B {
    private Node root;
    public MyTrieSet() {
        root = new Node();
    }
    /** Clears all items out of Trie */
    public void clear() {
        root.next.clear();
        root.color = 0;
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    public boolean contains(String key) {
        Node current = root;
        for (int i = 0; i < key.length(); i++) {
            if (!current.next.containsKey(key.charAt(i))) {
                return false;
            }
            current = current.next.get(key.charAt(i));
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
            if (!current.next.containsKey(key.charAt(i))) {
                current.next.put(key.charAt(i), new Node());
            }
            current = current.next.get(key.charAt(i));
        }
        current.color = 1;
    }

    /** Returns a list of all words that start with PREFIX */
    public List<String> keysWithPrefix(String prefix) {
        Node current = root;
        List<String> list = new LinkedList<>();
        for (int i = 0; i < prefix.length(); i++) {
            if (current.next.containsKey(prefix.charAt(i))) {
                current = current.next.get(prefix.charAt(i));
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
        for (Map.Entry<Character, Node> i: root.next.entrySet()) {
            PrefixHelper(x + i.getKey(), list, i.getValue());
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
            if (current.next.containsKey(key.charAt(i))) {
                current = current.next.get(key.charAt(i));
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
        private Map<Character, Node> next;
        private int color; //when color > 0, it is a key

        public Node() {
            next = new HashMap<>();
            color = 0;
        }
    }

}
