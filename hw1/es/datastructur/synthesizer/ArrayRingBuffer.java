package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (isFull()) {
            throw  new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = increment(last);
        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T returnItem = rb[first];
        first = increment(first);
        fillCount--;
        return  returnItem;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    // return size of the buffer
    @Override
    public int capacity() {
        return rb.length;
    }

    // return number of items currently in the buffer
    @Override
    public int fillCount() {
        return fillCount;
    }

    private int increment(int x) {
        return (x + 1) % rb.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new BufferIterator<T>(rb, first, last, fillCount);
    }

    private class  BufferIterator<T> implements Iterator<T>{
        private T[] rbpointer;
        private int firs;
        private int las;
        private int cnt;

        public BufferIterator(T[] rb, int f, int l, int fill) {
            rbpointer = rb;
            firs = f;
            las = l;
            cnt = fill;
        }

        public boolean hasNext() {
          return cnt != 0;
        }
        public T next() {
            if(!hasNext()) {
                throw new RuntimeException("Ring buffer overflow");
            }
            T returnItem = rbpointer[firs];
            firs = increment(firs);
            cnt--;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != this.getClass()) {
            return false;
        }
        ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;
        if (fillCount != other.fillCount()) {
            return false;
        }
        int point = first;
        for (T item : other) {
            if (!item.equals(rb[point])) {
                return false;
            }
            point = increment(point);
        }
        return true;
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
}
    // TODO: Remove all comments that say TODO when you're done.
