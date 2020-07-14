package creatures;
import huglife.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import static huglife.HugLifeUtils.random;
import static huglife.HugLifeUtils.randomEntry;

public class TestClorus {
    @Test public void  testBasic() {
        Clorus p = new Clorus(1.1);
        assertEquals(1.1, p.energy(), 0.001);
        p.move();
        assertEquals(1.07, p.energy(), 0.001);
        p.stay();
        assertEquals(1.06, p.energy(), 0.001);

        Plip sadOne = new Plip(1.1);
        p.attack(sadOne);
        assertEquals(2.16, p.energy(), 0.001);

        Clorus q = p.replicate();
        assertEquals(p.energy(), q.energy(), 0.001);
        assertEquals(p.energy(), 1.08, 0.001);

    }

    @Test
    public void testChooseClorus() {

        // No empty adjacent spaces; stay.
        Clorus p = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = p.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space.
        p = new Clorus(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = p.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(expected, actual);




        //test for attack
        p = new Clorus(.99);
        HashMap<Direction, Occupant> ExistPlip = new HashMap<Direction, Occupant>();
        ExistPlip.put(Direction.TOP, new Plip());
        ExistPlip.put(Direction.BOTTOM, new Empty());
        ExistPlip.put(Direction.LEFT, new Impassible());
        ExistPlip.put(Direction.RIGHT, new Impassible());

        actual = p.chooseAction(ExistPlip);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);

        assertEquals(expected, actual);


        // We don't have Cloruses yet, so we can't test behavior for when they are nearby right now.
    }
}
