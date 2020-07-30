package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KDTreeTest {
    @Test
    public void constructTest() {
        Point p1 = new Point(0, 0); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(1, 1);
        Point p3 = new Point(-1, -1);
        Point p4 = new Point(3, 0.5);
        Point p5 = new Point(2, 6);
        KDTree test = new KDTree(List.of(p1, p2, p3, p4, p5));
        assertEquals(2, test.nearest(3 ,7).getX(), 0.0001);
        assertEquals(6, test.nearest(3 ,7).getY(), 0.0001);
        assertEquals(0, test.nearest(0.2 ,0.4).getX(), 0.0001);
        assertEquals(0, test.nearest(0.2 ,0.4).getY(), 0.0001);
    }

    @Test
    public void RandomTest1() {
        Point p1 = new Point(0, 0); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(1, 1);
        Point p3 = new Point(-1, -1);
        Point p4 = new Point(3, 0.5);
        Point p5 = new Point(2, 6);
        Point p6 = new Point(8, 4);
        Point p7 = new Point(-3, -4);
        PointSet rightOne = new NaivePointSet(List.of(p1, p2, p3, p4, p5, p6, p7));
        KDTree testOne = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
        for (int i = 0; i < 1000; i++) {
            double x = StdRandom.uniform();
            double y =StdRandom.uniform();
            assertTrue(rightOne.nearest(x, y).equals(testOne.nearest(x, y)));
        }
    }

    @Test
    public void RandomTest2() {
        List<Point> test = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            double x = StdRandom.uniform();
            double y = StdRandom.uniform();
            test.add(new Point(x, y));
        }
        PointSet rightOne = new NaivePointSet(test);
        KDTree testOne = new KDTree(test);
        for (int i = 0; i < 1000; i++) {
            double x = StdRandom.uniform();
            double y = StdRandom.uniform();
            assertTrue(rightOne.nearest(x, y).equals(testOne.nearest(x, y)));
        }
    }

    @Test
    public void SpeedTest() {
        List<Point> test = new LinkedList<>();
        for (int i = 0; i < 100000; i++) {
            double x = StdRandom.uniform();
            double y = StdRandom.uniform();
            test.add(new Point(x, y));
        }
        //time cost for NaivePointSet
        long start = System.currentTimeMillis();
        PointSet rightOne = new NaivePointSet(test);
        for (int j = 0; j < 10000; j++) {
            double targetX = StdRandom.uniform();
            double targetY = StdRandom.uniform();
            rightOne.nearest(targetX, targetY);
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start)/1000.0 +  " seconds.");
        double cost1 =  (end - start)/1000.0;
        //time cost for KDTree
        Stopwatch sw = new Stopwatch();
        KDTree testOne = new KDTree(test);
        for (int j = 0; j < 10000; j++) {
            double targetX = StdRandom.uniform();
            double targetY = StdRandom.uniform();
            testOne.nearest(targetX, targetY);
        }
        System.out.println("Total time elapsed: " + sw.elapsedTime() +  " seconds.");
        double cost2 = sw.elapsedTime();
        assertTrue(1.0 * cost1 / cost2 > 40);
    }


    // NaivePointSet : 3.172seconds
    // KDTree : 0.013 seconds
    //this test is copied from solution in gitHub.
    @Test
    public void speedTime2() {
        ArrayList<Point> temp3 = new ArrayList<>();
        HashSet<Double> X = new HashSet<>();
        HashSet<Double> Y = new HashSet<>();
        List<Point> temp = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            double x = edu.princeton.cs.algs4.StdRandom.uniform(-300000.0, 300000);
            double y = edu.princeton.cs.algs4.StdRandom.uniform(-300000.0, 300000);
            if (!X.contains(x) && !Y.contains(y)) {
                X.add(x);
                Y.add(y);
                temp.add(new Point(x, y));
            }
        }
        NaivePointSet test1 = new NaivePointSet(temp);
        KDTree test2 = new KDTree(temp);
        for (int i = 0; i < 10000; i++) {
            double x2 = edu.princeton.cs.algs4.StdRandom.uniform(-300000, 300000);
            double y2 = edu.princeton.cs.algs4.StdRandom.uniform(-300000, 300000);
            temp3.add(new Point(x2, y2));
        }
        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < 10000; i++) {
            Point temp2 = temp3.get(i);
            test1.nearest(temp2.getX(), temp2.getY());
        }
        System.out.println("Total time elapsed: " + sw.elapsedTime() + " seconds.");

        sw = new Stopwatch();
        for (int i = 0; i < 10000; i++) {
            Point temp2 = temp3.get(i);
            test2.nearest(temp2.getX(), temp2.getY());
        }
        System.out.println("Total time elapsed: " + sw.elapsedTime() + " seconds.");
    }


}
