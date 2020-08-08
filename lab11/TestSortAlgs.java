import edu.princeton.cs.algs4.Queue;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestSortAlgs {

    @Test
    public void testMergeSort1() {
        Queue<Integer> test = new Queue<>();
        test.enqueue(8);
        test.enqueue(3);
        test.enqueue(7);
        test.enqueue(2);
        test.enqueue(1);
        test = MergeSort.mergeSort(test);
        while (!test.isEmpty()) {
            int x = test.dequeue();
            if (!test.isEmpty()) {
                assertTrue(x < test.peek());
            }
        }
    }

    @Test
    public void testQucikSortSimple() {
        Queue<Integer> test = new Queue<>();
        test.enqueue(6);
        test.enqueue(5);
        test.enqueue(8);
        test.enqueue(12);
        test.enqueue(1);
        test = QuickSort.quickSort(test);
        while (!test.isEmpty()) {
            int x = test.dequeue();
            if (!test.isEmpty()) {
                assertTrue(x < test.peek());
            }
        }
    }

    @Test
    public void testQuickSort2() {
        Queue<Integer> test = new Queue<>();
        Queue<Integer> test1 = new Queue<>();
        for (int i = 0; i < 10000; i++) {
            int temp = StdRandom.uniform(1, 10000);
            test.enqueue(temp);
            test1.enqueue(temp);
        }
        Stopwatch sw = new Stopwatch();
        test = QuickSort.quickSort(test);
        System.out.println("Time spent: " + sw.elapsedTime() + " s");
        test1 = MergeSort.mergeSort(test1);
        System.out.println("Time spent: " + sw.elapsedTime() + " s");
        assertTrue(isSorted(test));
    }

    @Test
    public void testQuickSort3() {
        Queue<Integer> test = new Queue<>();
        Queue<Integer> test1 = new Queue<>();
        for (int i = 0; i < 100000; i++) {
            int temp = StdRandom.uniform(1, 100000);
            test.enqueue(temp);
            test1.enqueue(temp);
        }
        Stopwatch sw = new Stopwatch();
        test = QuickSort.quickSort(test);
        System.out.println("Time spent: " + sw.elapsedTime() + " s");
        test1 = MergeSort.mergeSort(test1);
        System.out.println("Time spent: " + sw.elapsedTime() + " s");
        assertTrue(isSorted(test));
    }

    @Test
    public void testQuickSort4() {
        Queue<Integer> test = new Queue<>();
        Queue<Integer> test1 = new Queue<>();
        for (int i = 0; i < 1000000; i++) {
            int temp = StdRandom.uniform(1, 1000000);
            test.enqueue(temp);
            test1.enqueue(temp);
        }
        Stopwatch sw = new Stopwatch();
        test = QuickSort.quickSort(test);
        System.out.println("Time spent: " + sw.elapsedTime() + " s");
        test1 = MergeSort.mergeSort(test1);
        System.out.println("Time spent: " + sw.elapsedTime() + " s");
        assertTrue(isSorted(test));
    }

    @Test
    public void testQuickSort() {
        Queue<Integer> test = new Queue<>();
        Queue<Integer> test1 = new Queue<>();
        for (int i = 0; i < 10000000; i++) {
            int temp = StdRandom.uniform(1, 10000000);
            test.enqueue(temp);
            test1.enqueue(temp);
        }
        Stopwatch sw = new Stopwatch();
        test = QuickSort.quickSort(test);
        System.out.println("Time spent: " + sw.elapsedTime() + " s");
        test1 = MergeSort.mergeSort(test1);
        System.out.println("Time spent: " + sw.elapsedTime() + " s");
        assertTrue(isSorted(test));
    }

    @Test
    public void testMergeSort() {
        Queue<Integer> test = new Queue<>();
        for (int i = 0; i < 10000000; i++) {
            test.enqueue(StdRandom.uniform(1, 10000000));
        }           //10000000
        Stopwatch sw = new Stopwatch();
        test = MergeSort.mergeSort(test);
        System.out.println("Time spent: " + sw.elapsedTime() + " s");
        assertTrue(isSorted(test));
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
