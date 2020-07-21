import org.junit.Test;
import static org.junit.Assert.*;
public class HelperTest {
    @Test
    public void optimalIPLtest() {
        assertEquals(1, ExperimentHelper.optimalIPL(2));
        assertEquals(ExperimentHelper.optimalIPL(3), 2);
        assertEquals(ExperimentHelper.optimalIPL(4), 4);
        assertEquals(ExperimentHelper.optimalIPL(5), 6);
        assertEquals(ExperimentHelper.optimalIPL(6), 8);
        assertEquals(ExperimentHelper.optimalIPL(7), 10);
        assertEquals(ExperimentHelper.optimalIPL(8), 13);
    }

    @Test
    public void OADtest() {
        /** Returns the average depth for nodes in an optimal BST of
         *  size N.
         *  Examples:
         *  N = 1, OAD: 0
         *  N = 5, OAD: 1.2
         *  N = 8, OAD: 1.625
         * @return
         */
        assertEquals(0, ExperimentHelper.optimalAverageDepth(1), 0.0000001);
        assertEquals(1.2, ExperimentHelper.optimalAverageDepth(5), 0.0000001);
        assertEquals(1.625, ExperimentHelper.optimalAverageDepth(8), 0.0000001);


    }
}
