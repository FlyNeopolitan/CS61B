import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private int size;
    private int bucketSize;
    private ArrayList<Entry>[] hashTable;
    private double loadFactor;
    private int MutiFACTOR = 2;

    private class Entry<K, V> {
        K key;
        V value;
        private Entry(K k, V v) {
            key = k;
            value = v;
        }
        @Override
        public boolean equals(Object o) {
            if (this.getClass() != o.getClass()) {
                return false;
            }
            Entry object = (Entry) o;
            return key == object.key && value == object.value;
        }
    }

    public MyHashMap() {
        bucketSize = 16;
        size = 0;
        loadFactor = 0.75;
        hashTable = (ArrayList<Entry>[]) new Object[bucketSize];
        hashTable = new ArrayList[bucketSize];
        for (int i = 0; i < bucketSize; i++) {
            hashTable[i] = new ArrayList<>();
        }
    }
    public MyHashMap(int initialSize) {
        bucketSize = initialSize;
        size = 0;
        loadFactor = 0.75;
        hashTable = new ArrayList[bucketSize];
        for (int i = 0; i < bucketSize; i++) {
            hashTable[i] = new ArrayList<>();
        }
    }
    public MyHashMap(int initialSize, double loadFactor) {
        bucketSize = initialSize;
        size = 0;
        this.loadFactor = loadFactor;
        hashTable = new ArrayList[bucketSize];
        for (int i = 0; i < bucketSize; i++) {
            hashTable[i] = new ArrayList<>();
        }
    }

    /** Removes all of the mappings from this map. */
    public void clear() {
        for (int i = 0; i < bucketSize; i++) {
            hashTable[i].clear();
        }
        size = 0;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        int code = Math.floorMod(key.hashCode(), bucketSize);
        for (Entry i : hashTable[code]) {
            if (key.equals(i.key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        int code = Math.floorMod(key.hashCode(), bucketSize);
        for (Entry i : hashTable[code]) {
            if (key.equals(i.key)) {
                return (V) i.value;
            }
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value) {
        int code = Math.floorMod(key.hashCode(), bucketSize);
        for (Entry<K, V> i : hashTable[code]) {
            if (i.key.equals(key)) {
                i.value = value;
                return;
            }
        }
        size++;
        hashTable[code].add(new Entry(key, value));
        if (ifResize()) {
            resize();
        }
    }

    private boolean ifResize() {
        return 1.0 * size / bucketSize > loadFactor;
    }

    private void resize() {
        ArrayList<Entry>[] newTable = new ArrayList[MutiFACTOR * bucketSize];
        for (int i = 0; i < MutiFACTOR * bucketSize; i++) {
            newTable[i] = new ArrayList<>();
        }
        for (int i = 0; i < bucketSize; i++) {
            for (Entry j : hashTable[i]) {
                int code = Math.floorMod(j.key.hashCode(), MutiFACTOR * bucketSize);
                newTable[code].add(new Entry(j.key, j.value));
            }
        }
        hashTable = newTable;
        bucketSize = MutiFACTOR * bucketSize;
    }


    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        Set<K> returnSet = new HashSet<K>();
        for (int i = 0; i < bucketSize; i++) {
            for (Entry<K, V> j : hashTable[i]) {
                returnSet.add(j.key);
            }
        }
        return returnSet;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key) {
        int code = Math.floorMod(key.hashCode(), bucketSize);
        for (Entry i : hashTable[code]) {
            if (i.key.equals(key)) {
                V v = (V) i.value;
                hashTable[code].remove(new Entry(key, v));
                return v;
            }
        }
        return null;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value) {
        int code = Math.floorMod(key.hashCode(), bucketSize);
        for (Entry i : hashTable[code]) {
            if (i.key.equals(key) && i.value.equals(value)) {
                V v = (V) i.value;
                hashTable[code].remove(new Entry(key, v));
                return v;
            }
        }
        return null;
    }

    public Iterator<K> iterator() {
        return new HashIterator();
    }

    private class HashIterator implements Iterator<K> {
        Iterator<K> localIterator;
        HashIterator() {
            localIterator = keySet().iterator();
        }
        public boolean hasNext() {
            return localIterator.hasNext();
        }
        public K next() {
            return localIterator.next();
        }
    }

}
