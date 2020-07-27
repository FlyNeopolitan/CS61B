package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {
    @Test
    public void TestAddGetSmallest() {
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        test.add(1, 1);
        test.add(2, 2);
        test.add(3, 3);
        test.add(4, 4);
        test.add(5, 5);
        assertEquals(1, (int)test.getSmallest());
        assertEquals(1, (int)test.removeSmallest());
        assertEquals(2, (int)test.removeSmallest());
        assertEquals(3, (int)test.removeSmallest());

        ArrayHeapMinPQ<String> test2 = new ArrayHeapMinPQ<>(4);
        test2.add("a", 3);
        test2.add("b", 1);
        test2.add("c", 4);
        test2.add("d", 1.5);
        test2.add("fff", 7);
        assertEquals("b", test2.removeSmallest());
        assertEquals("d", test2.removeSmallest());
        assertEquals("a", test2.removeSmallest());
        assertEquals("c", test2.removeSmallest());
        assertEquals("fff", test2.removeSmallest());
    }

    @Test
    public void TestSize() {
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        test.add(2, 1);
        test.add(3, 2);
        test.add(4, 3);
        assertEquals(3, test.size());
        test.removeSmallest();
        assertEquals(2, test.size());
    }

    @Test
    public void TestContains() {
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        test.add(1, 1);
        test.add(2, 2);
        test.add(3, 3);
        test.add(4, 4);
        test.add(5, 5);
        assertEquals(true, test.contains(2));
        assertEquals(false, test.contains(6));
        assertEquals(1, (int)test.removeSmallest());
        assertEquals(false, test.contains(1));
    }


    @Test
    public void TestChangeP() {
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        test.add(1, 1);
        test.add(2, 2);
        test.add(3, 3);
        test.add(4, 4);
        test.add(5, 5);
        test.changePriority(5, 0);
        assertEquals(5, (int)test.getSmallest());
        assertEquals(5, (int)test.removeSmallest());
        test.changePriority(1, 10);
        assertEquals(2, (int)test.removeSmallest());
    }


}
