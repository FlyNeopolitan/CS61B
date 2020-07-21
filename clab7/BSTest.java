import org.junit.Test;
import static org.junit.Assert.*;
public class BSTest {
    @Test
    public void IPLtest() {
        BST<Integer> actual = new BST<Integer>();
        actual.add(5);
        actual.add(8);
        actual.add(10);
        assertEquals(3, actual.IPL());
        actual.add(7);
        actual.add(2);
        actual.add(1);
        assertEquals(8, actual.IPL());
        actual.add(13);
        assertEquals(11, actual.IPL());
    }

    @Test
    public void AverageDepthTest() {
        BST<Integer> actual = new BST<Integer>();
        actual.add(5);
        actual.add(8);
        actual.add(10);
        assertEquals(1, actual.AverageDepth(), 0.000001);
        actual.add(7);
        actual.add(2);
        actual.add(1);
        assertEquals(8.0 / 6, actual.AverageDepth(), 0.000001);
        actual.add(13);
        assertEquals(11.0 / 7, actual.AverageDepth(), 0.0000001);

    }
}
