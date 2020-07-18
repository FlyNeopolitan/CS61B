package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    int[][] grid; //if (a, b) is open, let gird[a][b] = 1;
    WeightedQuickUnionUF UF;
    int[][] direction = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};
    int upperSite, lowerSite;
    int numberOfOpenSites;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = 0;
            }
        }
        UF = new WeightedQuickUnionUF(N*N + 2);
        upperSite = N*N + 1;
        lowerSite = N*N;
        numberOfOpenSites = 0;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isInrange(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        grid[row][col] = 1;
        for(int i = 0; i < 4; i++) {
            if (isInrange(row + direction[i][0], col + direction[i][1])) {
                if (isOpen(row + direction[i][0], col + direction[i][1])) {
                    UF.union(XYto1D(row, col), XYto1D(row + direction[i][0], col + direction[i][1]));
                }
            }
            if (row + direction[i][0] == -1) {
                UF.union(XYto1D(row, col), upperSite);
            }
            if (row + direction[i][0] == grid.length) {
                UF.union(XYto1D(row, col), lowerSite);
            }
        }
        numberOfOpenSites ++;
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
        return UF.connected(XYto1D(row, col), upperSite);
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

    public int XYto1D(int raw, int col) {
        return col + raw * grid.length;
    }

}
