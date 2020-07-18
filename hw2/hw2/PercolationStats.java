package hw2;
import edu.princeton.cs.introcs.StdRandom;
import static edu.princeton.cs.introcs.StdRandom.uniform;

public class PercolationStats {
    int N;
    int T;
    double[] FractionOfOpenSites;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        this.N = N;
        this.T = T;
        FractionOfOpenSites = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation OneIndependentExperiment = pf.make(N);
            int cnt = 0;
            while (!OneIndependentExperiment.percolates()) {
                int openX = uniform(N);
                int openY = uniform(N);
                if (!OneIndependentExperiment.isOpen(openX, openY)) {
                    OneIndependentExperiment.open(openX, openY);
                    cnt ++;
                }
            }
            FractionOfOpenSites[i] = (double) 1.0 * cnt / (N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return edu.princeton.cs.algs4.StdStats.mean(FractionOfOpenSites);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return edu.princeton.cs.algs4.StdStats.stddev(FractionOfOpenSites);
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats test = new PercolationStats(20, 1000, pf);
        System.out.println(test.mean());
        System.out.println("[" + test.confidenceLow() + ", " + test.confidenceHigh() + "]");
    }


}
