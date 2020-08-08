import edu.princeton.cs.algs4.BST;

import javax.swing.text.html.HTMLDocument;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    Node root;
    int size;

    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        Node(K k, V val, Node l, Node r) {
            key = k;
            value = val;
            left = l;
            right = r;
        }
    }

    public BSTMap() {
        root = null;
        size = 0;
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }


    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return containsKeyHelper(root, key);
    }
    private boolean containsKeyHelper(Node T, K k) {
        if (T == null) {
            return false;
        }
        if (T.key.compareTo(k) == 0) {
            return true;
        }
        return containsKeyHelper(T.left, k) || containsKeyHelper(T.right, k);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(root, key);
    }
    private V getHelper(Node T, K k) {
        if (T == null) {
            return null;
        }
        if (k.compareTo(T.key) < 0) {
            return getHelper(T.left, k);
        } else if (k.compareTo(T.key) == 0) {
            return T.value;
        } else {
            return getHelper(T.right, k);
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }
    private int sizeHelper(Node T) {
        if (T == null) {
            return 0;
        }
        return 1+ sizeHelper(T.right) + sizeHelper(T.left);
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        /*if (!containsKey(key)) {
            size++;
        }*/
        int[] flag = {0};
        root = putHelper(root, key, value, flag);
        size += flag[0];
    }
    private Node putHelper(Node T, K key, V value, int[] flag) {
        if (T == null) {
            flag[0] = 1;
            return new Node(key, value, null, null);
        }
        if (T.key.compareTo(key) == 0) {
            T.value = value;
        } else if (key.compareTo(T.key) < 0) {
            T.left = putHelper(T.left, key, value, flag);
        } else {
            T.right = putHelper(T.right, key, value, flag);
        }
        return T;
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> returnSet = new HashSet<>();
        keySetHelper(returnSet, root);
        return returnSet;
    }
    private void keySetHelper(Set<K> M, Node T) {
        if (T == null) {
            return;
        }
        keySetHelper(M, T.left);
        M.add(T.key);
        keySetHelper(M, T.right);
    }


    public void printInOrder() {
        printInOrderHelper(root);
    }
    private void printInOrderHelper(Node T) {
        if (T == null) {
            return;
        }
        printInOrderHelper(T.left);
        System.out.print(T.key);
        printInOrderHelper(T.right);
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        V[] keyVal = (V[]) new Object[1];
        removeHelper(root, key, keyVal);
        return  keyVal[0];
    }
    private Node removeHelper(Node T, K key, V[] val) {
        if (T == null) {
            val[0] = null;
            return null;
        }
        if (key.compareTo(T.key) == 0) {
            if (T.right == null && T.left == null) {
                val[0] = T.value;
                return null;
            }
            if (T.right == null) {
                val[0] = T.value;
                return T.left;
            }
            if (T.left == null) {
                val[0] = T.value;
                return T.right;
            }
            else {
                Node p= T.left;
                Node maxNodeOfLeft = T.left;
                int cnt = 0;
                while (maxNodeOfLeft.right != null) {
                    maxNodeOfLeft = maxNodeOfLeft.right;
                    if (cnt > 0) {
                        p = p.right;
                    }
                    cnt = 1;
                }
                if (p == T.left) {
                    T.left = null;
                    return T;
                } else {
                    p.right = maxNodeOfLeft.left;
                    maxNodeOfLeft.left = T.left;
                    maxNodeOfLeft.right = T.right;
                    return maxNodeOfLeft;
                }
            }
        } else if (key.compareTo(T.key) < 0){
            T.left = removeHelper(T.left, key, val);
        } else {
            T.right = removeHelper(T.right, key, val);
        }
        return T;
    }


    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTIterator(root);
    }

    private class BSTIterator implements Iterator<K>{
        Node head;
        Set<K> X;
        Iterator<K> helper;
        BSTIterator(Node m) {
            head = m;
            X = new HashSet<>();
            keySetHelper(X, m);
            helper = X.iterator();
        }
        public boolean hasNext(){
            return helper.hasNext();
        }
        public K next(){
            return helper.next();
        }
    }

    public static void main(String[] args) {
        BSTMap<String, Integer> test = new BSTMap<String, Integer>();
        test.put("a", 1);
        test.put("b", 2);
        test.put("c", 3);
        test.put("d", 4);
        test.put("f", 4);
        test.put("g", 4);
        test.put("h", 4);
        test.printInOrder();
        System.out.println();
        for (String i : test) {
            System.out.println(i);
        }
        test.remove("b");
        test.remove("f");
        test.printInOrder();
    }


}
