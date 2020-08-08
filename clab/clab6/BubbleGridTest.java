import org.junit.Test;
import static org.junit.Assert.*;

public class BubbleGridTest {

    @Test
    public void testBasic() {

       int[][] grid = {{1, 0, 0, 0},
                        {1, 1, 1, 0}};
        int[][] darts = {{1, 0}};
        int[] expected = {2};

        validate(grid, darts, expected);

        int[][] Grid = {{1}, {1}, {1}, {1}, {1}};
        int[][] dartss = {{3, 0}, {4, 0}, {1, 0}, {2, 0}, {0, 0}};
        int[] expectedd = {1, 0, 1, 0, 0};
        validate(Grid, dartss, expectedd);
    }

    private void validate(int[][] grid, int[][] darts, int[] expected) {
        BubbleGrid sol = new BubbleGrid(grid);
        assertArrayEquals(expected, sol.popBubbles(darts));
    }

    @Test
    public void GirdLastTest() {
        int[][] grid = {{1, 0, 0, 0},
                {1, 1, 1, 0}};
        int[][] darts = {{1, 0}};
        int[][] expected = {{1, 0, 0, 0},
                            {0, 1, 1, 0}};
        int [][] actual = BubbleGrid.GridAtLast(grid, darts);
        assertArrayEquals(expected, actual);

    }
}
