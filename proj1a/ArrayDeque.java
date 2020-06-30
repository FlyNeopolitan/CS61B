public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    //create an ArrayDeque
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 0;
    }

    //create a hard copy of ArrayDeque other
    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.items.length];
        System.arraycopy(other.items, 0, items, 0, other.items.length);
        size = other.size;
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
    }

    //calculate the next element's position of kth item;
    private int minusOne(int k) {
        if (k == 0) {
            return items.length - 1;
        } else {
            return (k - 1);
        }
    }

    //calculate the element's position before kth item
    private int addOne(int k) {
        return (k + 1) % items.length;
    }

    //resizing
    private void resizing(int capacity) {
        if (minusOne(nextLast) > addOne(nextFirst)) {
            T[] newItems = (T[]) new Object[capacity];
            System.arraycopy(items, addOne(nextFirst), newItems, 0, minusOne(nextLast) - addOne(nextFirst) + 1);
            items = newItems;
        } else if (size == 1 && minusOne(nextLast) == addOne(nextFirst)) {
            T[] newItems = (T[]) new Object[capacity];
            System.arraycopy(items, addOne(nextFirst), newItems, 0, 1);
            items = newItems;
        } else {
            T[] newItems = (T[]) new Object[capacity];
            System.arraycopy(items, addOne(nextFirst), newItems, 0, items.length - addOne(nextFirst));
            System.arraycopy(items, 0, newItems, items.length - addOne(nextFirst), minusOne(nextLast) + 1);
            items = newItems;
        }
    }

    //add an item at front of Deque
    public void addFirst(T item) {
        if (size + 1 == items.length) {
            resizing(size * 2);
            items[items.length - 1] = item;
            nextFirst = items.length - 2;
            nextLast = size;
            size = size + 1;
        } else {
            items[nextFirst] = item;
            size = size + 1;
            nextFirst = minusOne(nextFirst);
            if (size == 1) {
                nextLast = addOne(nextLast);
            }
        }
    }

    //add an item at last of Deque
    public void addLast(T item) {
        if (size + 1 == items.length) {
            resizing(size * 2);
            items[size] = item;
            nextLast = size + 1;
            size = size + 1;
            nextFirst = items.length - 1;
        } else {
            items[nextLast] = item;
            size = size + 1;
            nextLast = addOne(nextLast);
            if (size == 1) {
                nextFirst = minusOne(nextFirst);
            }
        }
    }

    //Is Deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    //get the size of Deque
    public int size() {
        return size;
    }

    //print out the Deque
    public void printDeque() {
        if (addOne(nextFirst) <= minusOne(nextLast)) {
            for (int k = addOne(nextFirst); k < minusOne(nextLast) + 1; k++) {
                System.out.print(items[k]);
            }
        } else {
            for (int k = addOne(nextFirst); k < items.length; k++) {
                System.out.print(items[k]);
            }
            for (int j = 0; j < minusOne(nextLast) + 1; j++) {
                System.out.print(items[j]);
            }
        }
        System.out.println(" ");
    }

    //remove the item at front of Deque
    public T removeFirst() {
        if(size == 0) {
            return null;
        }
        nextFirst = addOne(nextFirst);
        T removeItem = items[nextFirst];
        items[nextFirst] = null;
        size = size - 1;
        if (size == 0) {
            nextLast = nextFirst;
        } else if (size > 16 && size * 1.0 / items.length < 1.0 / 4) {
            resizing(items.length / 2);
            nextFirst = items.length - 1;
            nextLast = size;
        }
        return removeItem;
    }

    //remove the item at last of Deque
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = minusOne(nextLast);
        T removeItem = items[nextLast];
        items[nextLast] = null;
        size = size - 1;
        double ratio = size * 1.0 / items.length;
        if (size == 0) {
            nextFirst = nextLast;
        } else if (size > 16 && ratio < 0.25) {
            resizing(items.length / 2);
            nextFirst = items.length - 1;
            nextLast = size;
        }
        return removeItem;
    }

    //get the ith item of Deque;
    public T get(int index) {
        if (index < size) {
            if (addOne(nextFirst) < minusOne(nextLast)) {
                return items[addOne(nextFirst) + index];
            } else {
                if (index < items.length - addOne(nextFirst)) {
                    return items[addOne(nextFirst) + index];
                } else {
                    return items[index - items.length + addOne(nextFirst)];
                }
            }
        } else {
            return null;
        }
    }

}

