import edu.princeton.cs.algs4.Average;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1() {
        Random r = new Random();
        BST<Integer> test = new BST<>();
        List<Double> yValue1 = new ArrayList<>();
        List<Double> yValue2 = new ArrayList<>();
        List<Integer> xValue = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            int addItem = r.nextInt();
            test.add(addItem);
            yValue1.add(test.AverageDepth());
            yValue2.add(ExperimentHelper.optimalAverageDepth(i + 1));
            xValue.add(i + 1);
        }
        XYChart chart = new XYChartBuilder().width(5000).height(20).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("randomAverageDepth", xValue, yValue1);
        chart.addSeries("optimalAverageDepth", xValue, yValue2);

        new SwingWrapper(chart).displayChart();

    }

    public static void experiment2() {
        Random r = new Random();
        BST<Integer> test = new BST<>();
        List<Double> yValue = new ArrayList<>();
        List<Integer> xValue = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            int addItem = r.nextInt(Integer.MAX_VALUE);
            test.add(addItem);
        }
        yValue.add(test.AverageDepth());
        xValue.add(0);
        for (int j = 0; j < 100000; j++) {
            test.deleteTakingSuccessor(test.getRandomKey());
            int addOne = r.nextInt(Integer.MAX_VALUE);
            while (test.contains(addOne)) {
                addOne = r.nextInt();
            }
            test.add(addOne);
            yValue.add(test.AverageDepth());
            xValue.add(j + 1);
        }
        XYChart chart = new XYChartBuilder().width(100000).height(30).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("randomAverageDepth", xValue, yValue);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment3() {
        Random r = new Random();
        BST<Integer> test = new BST<>();
        List<Double> yValue = new ArrayList<>();
        List<Integer> xValue = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int addItem = r.nextInt();
            test.add(addItem);
        }
        yValue.add(test.AverageDepth());
        xValue.add(0);
        for (int j = 0; j < 100000; j++) {
            test.deleteTakingRandom(test.getRandomKey());
            int addOne = r.nextInt(Integer.MAX_VALUE);
            while (test.contains(addOne)) {
                addOne = r.nextInt();
            }
            test.add(addOne);
            yValue.add(test.AverageDepth());
            xValue.add(j + 1);
        }
        XYChart chart = new XYChartBuilder().width(100000).height(30).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("randomAverageDepth", xValue, yValue);

        new SwingWrapper(chart).displayChart();

    }

    public static void main(String[] args) {
        Experiments experiments = new Experiments();
        //experiments.experiment1();
        experiments.experiment2();
       // experiments.experiment3();
    }
}
