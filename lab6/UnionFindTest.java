import org.junit.Test;
import static org.junit.Assert.*;
public class UnionFindTest {

    @Test
    public void UnionFindTest() {
        UnionFind actual = new UnionFind(8);
        actual.union(1, 2);
        actual.union(2,5);
        assertEquals(actual.sizeOf(1), 3);
        assertTrue(actual.connected(2, 5));
        actual.union(7,1);
        assertTrue(actual.connected(5,7));
        assertFalse(actual.connected(7, 3));
    }
}
