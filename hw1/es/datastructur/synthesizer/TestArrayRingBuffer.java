package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        assertTrue(arb.isEmpty());
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        assertEquals(1, arb.dequeue());
        assertEquals(2, arb.peek());
        assertEquals(2, arb.dequeue());

        ArrayRingBuffer actual = new ArrayRingBuffer(2);
        actual.enqueue(1);
        actual.enqueue(2);
        assertEquals(1, actual.dequeue());
        actual.enqueue(3);
        assertTrue(actual.isFull());
        assertEquals(2, actual.dequeue());
        assertEquals(3, actual.dequeue());
        assertTrue(actual.isEmpty());
    }

    @Test
    public void TestEquals() {
        ArrayRingBuffer actual1 = new ArrayRingBuffer<Integer>(3);
        ArrayRingBuffer actual2 = new ArrayRingBuffer<Integer>(3);
        for (int i = 0; i < 3; i++) {
            actual1.enqueue(i);
            actual2.enqueue(i);
        }
        assertTrue(actual1.equals(actual2));
        actual1.dequeue();
        assertFalse(actual1.equals(actual2));
        actual2.dequeue();
        assertTrue(actual1.equals(actual2));
        int a = 3;
        assertFalse(actual1.equals(a));
    }

    @Test
    public void TestEnhancedLoop() {
        ArrayRingBuffer<Integer> actual1 = new ArrayRingBuffer<Integer>(10);
        for (int i = 0; i < 3; i++) {
            actual1.enqueue(i);
        }
        for (Integer i : actual1) {
            System.out.println(i);
        }
    }
}
