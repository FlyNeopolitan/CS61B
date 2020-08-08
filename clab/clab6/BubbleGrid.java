public class BubbleGrid {
    private int[][] grid;

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    /* public int[] popBubbles(int[][] darts) {
        int[] sizeOfFalls = new int[darts.length];
        int[][] Grid = GridAtLast(grid, darts);
        for (int i = 0; i < darts.length; i++) {
            int preSize = popBubble(Grid);
            updateGrid(Grid, grid, darts, darts.length - 1 - i);
            int updateSize = popBubble(Grid);
            sizeOfFalls[darts.length - 1 - i] = updateSize - preSize + 1;
        }
        return sizeOfFalls;
    } */

    public int[] popBubbles(int[][] darts) {
        int[] sizeOfFalls = new int[darts.length];  //record size of bubbles falling
        int[][] Grid = GridAtLast(grid, darts);  // record grid after all darts-operation
        int above = grid.length * grid[0].length;  // to calculate stuck bubbles in grid
        UnionFind UF = UnionFindOfOneGrid(Grid);  //get Unionfind class of Grid
        for (int i = 0; i < darts.length; i++) {
            int preSize = UF.sizeOf(above);
            int updatePosX = darts[darts.length - 1 - i][0];    //position of darts-operation
            int updatePosY = darts[darts.length - 1 - i][1];
            updateUF(UF, Grid, updatePosX, updatePosY);
            int updateSize = UF.sizeOf(above);
            if (updateSize == preSize) {
                sizeOfFalls[darts.length - 1 - i] = 0;
            } else {
                sizeOfFalls[darts.length - 1 - i] = updateSize - preSize - 1;
            }
        }
        return sizeOfFalls;
    }

    //update UnionFind class for one operation at PosX, PosY
    public void updateUF(UnionFind UF, int[][] Grid, int PosX, int PosY) {
        if (grid[PosX][PosY] == 1) {
            connectSurround(Grid, PosX, PosY, UF);
            if (PosX == 0) {
                int above = Grid.length * Grid[0].length;
                UF.union(IndexOfPosition(Grid, PosX, PosY), above);
            }
        }
    }

    //show the gird at last
    public static int[][] GridAtLast(int[][] Grid, int[][] darts) {
        int[][] gridAtLast = new int[Grid.length][Grid[0].length];
        for (int i = 0; i < Grid.length; i++) {
            for (int j = 0; j < Grid[0].length; j++) {
                gridAtLast[i][j] = Grid[i][j];
            }
        }
        for (int i = 0; i < darts.length; i++) {
            gridAtLast[darts[i][0]][darts[i][1]] = 0;
        }
        return gridAtLast;
    }

    //calculate existed bubbles in current grid
    public UnionFind UnionFindOfOneGrid(int [][] Grid) {
        UnionFind UF = new UnionFind(Grid.length * Grid[0].length + 1);
        int AboveAll = Grid.length * Grid[0].length;
        for (int i = 0; i < Grid.length; i++) {
            for (int j = 0; j < Grid[0].length; j++) {
                if (Grid[i][j] == 1) {
                    connectSurround(Grid, i, j, UF);
                }
            }
        }
        for (int i = 0; i < Grid[0].length; i++) {
            if (Grid[0][i] == 1) {
                UF.union(i, AboveAll);
            }
        }
        return UF;
    }


    //connect Item in Position(raw, colomn) with Surrounded one
    public void connectSurround(int [][] Grid, int raw, int colomn, UnionFind UF) {
        int[][] dxy = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        Grid[raw][colomn] = 1;
        for (int  i = 0; i < 4; i++) {
            int RawPos = raw + dxy[i][0];
            int ColPos = colomn + dxy[i][1];
            if (inGrid(Grid, RawPos, ColPos) && Grid[RawPos][ColPos] == 1) {
                UF.union(IndexOfPosition(Grid, raw, colomn), IndexOfPosition(Grid, RawPos, ColPos));
            }
        }
    }

    // show the Index of Position (raw, colomn) in grid
    public int IndexOfPosition(int[][] grid, int raw, int column) {
        return raw * grid[0].length + column;
    }

    // test if given Position (raw, colomn) is in Grid
    public boolean inGrid(int [][] grid, int raw, int colomn) {
        if (raw >= 0 && raw < grid.length) {
            if (colomn >= 0 && colomn < grid[0].length) {
                return  true;
            }
        }
        return false;
    }
}

