import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class SeparableEnemySolverTests {

    @Test
    public void triangleEnemies() {
        Graph g = new Graph();
        g.connect("A", "B");
        g.connect("A", "C");
        g.connect("A", "D");
        g.connect("C", "D");
        SeparableEnemySolver solver = new SeparableEnemySolver(g);
        assertEquals(false, solver.isSeparable());
    }

    @Test
    public void disconnected() {
        Graph g = new Graph();
        g.connect("A", "B");
        g.connect("C", "D");
        SeparableEnemySolver solver = new SeparableEnemySolver(g);
        assertEquals(true, solver.isSeparable());
    }

    @Test
    public void disconnected2() {
        Graph g = new Graph();
        g.connect("A", "B");
        g.connect("C", "D");
        g.connect("E", "D");
        g.connect("E", "C");
        SeparableEnemySolver solver = new SeparableEnemySolver(g);
        assertEquals(false, solver.isSeparable());
    }

    @Test
    public void input1() throws FileNotFoundException {
        SeparableEnemySolver solver = new SeparableEnemySolver("input/party1");
        assertEquals(true, solver.isSeparable());
    }

    @Test
    public void input2() throws FileNotFoundException {
        SeparableEnemySolver solver = new SeparableEnemySolver("input/party2");
        assertEquals(true, solver.isSeparable());
    }

    @Test
    public void input3() throws FileNotFoundException {
        SeparableEnemySolver solver = new SeparableEnemySolver("input/party3");
        assertEquals(false, solver.isSeparable());
    }

    @Test
    public void input4() throws FileNotFoundException {
        SeparableEnemySolver solver = new SeparableEnemySolver("input/party4");
        assertEquals(false, solver.isSeparable());
    }

    @Test //basic test
    public void ExtraTest1() {
        Graph g = new Graph();
        g.connect("a", "b");
        g.connect("a", "c");
        g.connect("a", "d");
        g.connect("b", "d");
        g.connect("c", "d");
        SeparableEnemySolver solver = new SeparableEnemySolver(g);
        assertEquals(false, solver.isSeparable());
        assertEquals(true, solver.isSeparableExtra());
    }

    @Test  //complete K-3 test
    public void ExtraTest2() {
        Graph g = new Graph();
        g.connect("a", "c");
        g.connect("a", "d");
        g.connect("a", "f");
        g.connect("a", "e");
        g.connect("a", "g");
        g.connect("b", "f");
        g.connect("b", "g");
        g.connect("b", "e");
        g.connect("b", "d");
        g.connect("b", "c");
        g.connect("e", "c");
        g.connect("e", "d");
        g.connect("g", "d");
        g.connect("g", "c");
        g.connect("f", "d");
        g.connect("f", "c");
        SeparableEnemySolver solver = new SeparableEnemySolver(g);
        assertEquals(true, solver.isSeparableExtra());
    }

    @Test //false test
    public void ExtraTest3() {
        Graph g = new Graph();
        g.connect("a", "c");
        g.connect("a", "d");
        g.connect("a", "b");
        g.connect("a", "m");
        g.connect("b", "d");
        g.connect("b", "c");
        g.connect("b", "m");
        g.connect("c", "m");
        SeparableEnemySolver solver = new SeparableEnemySolver(g);
        assertEquals(false, solver.isSeparableExtra());
    }

}
