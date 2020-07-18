package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int[][] grid; //if (a, b) is open, let gird[a][b] = 1;
    private WeightedQuickUnionUF UF;
    private WeightedQuickUnionUF uFBackWash;
    private int[][] dire = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};
    private int upperSite, lowerSite;
    private int numberOfOpenSites;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = 0;
            }
        }
        UF = new WeightedQuickUnionUF(N * N + 2);
        uFBackWash = new WeightedQuickUnionUF(N * N + 1);
        upperSite = N * N + 1;
        lowerSite = N * N;
        numberOfOpenSites = 0;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isInrange(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        if (isOpen(row, col)) {
            return;
        }
        grid[row][col] = 1;
        numberOfOpenSites++;
        for (int i = 0; i < 4; i++) {
            if (isInrange(row + dire[i][0], col + dire[i][1])) {
                if (isOpen(row + dire[i][0], col + dire[i][1])) {
                    UF.union(xyto1D(row, col), xyto1D(row + dire[i][0], col + dire[i][1]));
                    uFBackWash.union(xyto1D(row, col), xyto1D(row + dire[i][0], col + dire[i][1]));
                }
            }
            if (row + dire[i][0] == -1) {
                UF.union(xyto1D(row, col), upperSite);
                uFBackWash.union(xyto1D(row, col), upperSite - 1);
            }
            if (row + dire[i][0] == grid.length) {
                UF.union(xyto1D(row, col), lowerSite);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!isInrange(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isInrange(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        if (!percolates()) {
            return UF.connected(xyto1D(row, col), upperSite);
        } else {
            if (UF.connected(xyto1D(row, col), upperSite)) {
                if (uFBackWash.connected(xyto1D(row, col), upperSite - 1)) {
                    return true;
                }
            }
            return false;
        }
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return UF.connected(upperSite, lowerSite);
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {
        int a = 0;
    }


    private boolean isInrange(int raw, int col) {
        if (raw >= 0 && raw < grid.length) {
            return col >= 0 && col < grid.length;
        }
        return false;
    }

    private int xyto1D(int raw, int col) {
        return col + raw * grid.length;
    }

}
