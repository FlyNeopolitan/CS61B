public class LinkedListDeque<T> implements Deque<T> {

    private static class node<T> {
        T item;
        node<T> next;
        node<T> prev;
        node(T x, node<T> y, node<T> z) {
            item = x;
            prev = y;
            next = z;
        }
        node(node<T> y, node<T> z) {
            prev = y;
            next = z;
        }
    }

    private node<T> sentinel;
    private int size;

    //create an empty LLDeque
    public LinkedListDeque() {
        sentinel = new node<T>(null, null);
        size = 0;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    //create a hard copy of LLDeque other
    public LinkedListDeque(LinkedListDeque<T> other) {
        sentinel = new node<T>(null, null);
        size = 0;
        node<T> p = other.sentinel;
        while (p.next != sentinel) {
            p = p.next;
            this.addLast(p.item);
        }
    }

    @Override
    //add an item at first of LLDeque
    public void addFirst(T newItem) {
        sentinel.next = new node<T>(newItem, sentinel, sentinel.next);
        sentinel.next.prev = sentinel.next;
        size += 1;
    }

    @Override
    //add an item at last of LLDeque
    public void addLast(T newItem) {
        sentinel.prev.next = new node<T>(newItem, sentinel.prev, sentinel);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }


    @Override
    // return the size of LLDeque
    public int size() {
        return size;
    }


    @Override
    //print LLDeque;
    public void printDeque() {
        node<T> q = sentinel;
        while (q.next != sentinel) {
            q = q.next;
            System.out.print(q.item + " ");
        }
        System.out.println(" ");
    }


    @Override
    //remove the first item of LLDeque
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T firstItem = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return firstItem;
        }
    }


    @Override
    //remove the last item of LLDeque
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T lastItem = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return lastItem;
        }
    }

    @Override
    //get the ith item of LLDeque,and if there is no such an item, return null;
    public T get(int index) {
        if (size == 0 || index > size - 1) {
            return null;
        } else {
            node<T> p = sentinel.next;
            for (int k = 0; k < index; k++) {
                p = p.next;
            }
            return p.item;
        }
    }

}
