package bearmaps;

import java.util.*;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private Node[] items;
    private int size;
    private int resizeFACTOR = 3;
    private double resizeLowerFACTOR = 0.5;
    private Map<T, Integer> matchingMap;

    public ArrayHeapMinPQ() {
        this(10);
    }

    public ArrayHeapMinPQ(int length) {
        items = (Node[]) new ArrayHeapMinPQ<?>.Node[length];
        size = 0;
        matchingMap = new HashMap<>();
    }

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentExceptionb if item is already present.
     * You may assume that item is never null. */

    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        matchingMap.put(item, size);
        items[size] = new Node(item, priority);
        checkUP(size);
        size++;
        if (size + 1 == items.length) {
            resize(resizeFACTOR);
        }
    }

    private void resize(double x) {
        int newLength = (int) Math.floor(x * items.length);
        Node[] newItems = (Node[]) new ArrayHeapMinPQ<?>.Node[newLength];
        System.arraycopy(items, 0, newItems, 0, size);
        items = newItems;
    }
    /* Returns true if the PQ contains the given item. */
    public boolean contains(T item) {
        return matchingMap.containsKey(item);
    }

    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T getSmallest() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return (T)items[0].item;
    }

    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T removeSmallest() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        matchingMap.remove(items[0].item);
        matchingMap.put(items[size - 1].item, 0);
        T returnOne = items[0].item;
        Switch(items[0], items[size - 1]);
        items[size - 1] = null;
        size--;
        checkDown(0);
        if (size * 1.0 / items.length < 1.0 / 4) {
            resize(resizeLowerFACTOR);
        }
        return returnOne;
    }

    /* Returns the number of items in the PQ. */
    public int size() {
        return size;
    }
    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    public void changePriority(T item, double priority) {
        if(!contains(item)) {
            throw new NoSuchElementException();
        }
        int index = matchingMap.get(item);
        items[index].priority = priority;
        if (!isUPOk(index)) {
            checkUP(index);
        }
        if (!isDownOk(index)) {
            checkDown(index);
        }
    }



    private void checkUP(int pos) {
        if (isUPOk(pos)) {
            return;
        } else {
            matchingMap.put(items[pos].item, parent(pos));
            matchingMap.put(items[parent(pos)].item, pos);
            Switch(items[pos], items[parent(pos)]);
            checkUP(parent(pos));
        }
    }

    private void checkDown(int pos) {
        if (isDownOk(pos)) {
            return;
        } else {
            matchingMap.put(items[pos].item, smallerChild(pos));
            matchingMap.put(items[smallerChild(pos)].item, pos);
            Switch(items[pos], items[smallerChild(pos)]);
            checkDown(smallerChild(pos));
        }
    }

    private boolean isUPOk(int position) {
        if (position == 0) {
            return true;
        }
        return items[position].compareTo(items[parent(position)]) > 0;
    }

    private boolean isDownOk(int position) {
        if (leftSon(position) >= size) {
            return true;
        }
        return items[position].compareTo(items[smallerChild(position)]) < 0;
    }

    private int smallerChild(int position) {
        if (leftSon(position) == size - 1) {
            return leftSon(position);
        }
        if (items[rightSon(position)].compareTo(items[leftSon(position)]) > 0) {
            return leftSon(position);
        } else {
            return rightSon(position);
        }
    }

    private void Switch(Node x, Node y) {
        Node m = new Node(x.item, x.priority);
        x.item = y.item;
        x.priority = y.priority;
        y.item = m.item;
        y.priority = m.priority;
    }

    private class Node implements Comparable<Node>{
        double priority;
        T item;

        private Node () {
            item = null;
        }
        private Node(T item, double priority) {
            this.priority = priority;
            this.item = item;
        }

        @Override
        public int compareTo(Node other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(priority, other.priority);
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) {
                return false;
            }
            if (other.getClass() != this.getClass()) {
                return false;
            }
            Node object = (Node) other;
            return object.priority == priority && object.item.equals(item);
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }

    //return parent of items[x]
    private int parent(int x) {
        if ( x == 0) {
            return 0;
        }
        return (x - 1) / 2;
    }

    //return leftSon of items[x]
    private int leftSon(int x) {
        return 2 * x + 1;
    }

    //return rightSon of items[x]
    private int rightSon(int x) {
        return 2 * (x + 1);
    }
}
