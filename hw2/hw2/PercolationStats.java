package hw2;
import static edu.princeton.cs.introcs.StdRandom.uniform;

public class PercolationStats {
    private int N;
    private int T;
    private double[] threshold;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        this.T = T;
        threshold = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation oneExperiment = pf.make(N);
            while (!oneExperiment.percolates()) {
                int openX = uniform(N);
                int openY = uniform(N);
                if (!oneExperiment.isOpen(openX, openY)) {
                    oneExperiment.open(openX, openY);
                }
            }
            threshold[i] = (double) 1.0 * oneExperiment.numberOfOpenSites() / (N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return edu.princeton.cs.algs4.StdStats.mean(threshold);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return edu.princeton.cs.algs4.StdStats.stddev(threshold);
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

}

