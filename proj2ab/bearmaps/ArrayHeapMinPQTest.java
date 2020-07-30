package bearmaps;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static edu.princeton.cs.introcs.StdRandom.uniform;
import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
    @Test
    public void TestAddGetSmallest() {
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        test.add(1, 1);
        test.add(2, 2);
        test.add(3, 3);
        test.add(4, 4);
        test.add(5, 5);
        assertEquals(1, (int) test.getSmallest());
        assertEquals(1, (int) test.removeSmallest());
        assertEquals(2, (int) test.removeSmallest());
        assertEquals(3, (int) test.removeSmallest());

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
        test2.add("y", 9);
        assertEquals("y", test2.getSmallest());

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
        assertTrue(test.contains(2));
        assertFalse(test.contains(6));
        assertEquals(1, (int) test.removeSmallest());
        assertFalse(test.contains(1));
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
        assertEquals(5, (int) test.getSmallest());
        assertEquals(5, (int) test.removeSmallest());
        test.changePriority(1, 10);
        assertEquals(2, (int) test.removeSmallest());
    }

    @Test
    public void TimingTest() {
        ExtrinsicMinPQ<Integer> fastOne = new ArrayHeapMinPQ<>();
        ExtrinsicMinPQ<Integer> slowOne = new NaiveMinPQ<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            fastOne.add(i, i);
        }
        for (int i = 0; i < 1000; i++) {
            int x = uniform(0,  9999);
            int y = uniform(0, 9999);
            fastOne.changePriority(x, y);
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start) / 1000.0 +  " seconds.");
        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < 10000; i++) {
            slowOne.add(i, i);
        }
        for (int i = 0; i < 1000; i++) {
            int x = uniform(0,  9999);
            int y = uniform(0, 9999);
            slowOne.changePriority(x, y);
        }
        System.out.println("Total time elapsed: " + sw.elapsedTime() +  " seconds.");
    }

    @Test
    public void randomTest() {
        Random randomG = new Random(1);
        randomG.setSeed(1);
        ExtrinsicMinPQ<Integer> testOne = new ArrayHeapMinPQ<>();
        ExtrinsicMinPQ<Integer> rightOne = new NaiveMinPQ<>();
        Set<Double> priSet = new HashSet<>();
        for (int i = 0; i < 50; i++) {
            testOne.add(i, i);
            rightOne.add(i, i);
            priSet.add((double) i);
        }
        for (int i = 0; i < 500; i++) {
            double x = randomG.nextInt(5);
            double pri = randomG.nextDouble();
            if (x < 2) {
                if (!priSet.contains(pri) && !rightOne.contains(i)) {
                    testOne.add(i, pri);
                    rightOne.add(i, pri);
                    priSet.add(pri);
                }
            }
            if (x < 3) {
                if (!priSet.contains(pri) && !rightOne.contains(i)) {
                    assertEquals(testOne.removeSmallest(), rightOne.removeSmallest());
                }
            }
            if (x < 4) {
                assertEquals(testOne.size(), rightOne.size());
            }
            if (x < 5) {
                if (!priSet.contains(pri) && rightOne.contains(i)) {
                    testOne.changePriority(i, pri);
                    rightOne.changePriority(i, pri);
                    priSet.add(pri);
                }
            }
        }
    }


}
