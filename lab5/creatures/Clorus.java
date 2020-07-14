package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.*;

import static huglife.HugLifeUtils.random;
import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature {
    private  int r, g, b;

    //constructor
    public Clorus(double e) {
        super("Clorus");
        energy = e;
        r = 0;
        g = 0;
        b = 0;
    }

    //return colors of Clorus (red = 34, green = 0, blue = 231)
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(34, 0 , 231);
    }


    //
    @Override
    public String name() {
        return "Clorus";
    }

    public  void move() {
        energy = energy - 0.03;
    }

    public Clorus replicate() {
        energy = energy/ 2.0;
        return new Clorus(energy);
    }

    public  void attack(Creature target) {
        energy = energy + target.energy();
    }
    public void stay() {
        energy = energy - 0.01;
    }
    /*If there are no empty squares, the Clorus will STAY (even if there are Plips nearby they could attack since plip squares do not count as empty squares).
    Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
            Otherwise, if the Clorus has energy greater than or equal to one, it will REPLICATE to a random empty square.
            Otherwise, the Clorus will MOVE to a random empty square. */
    public Action chooseAction(Map<Direction, Occupant> neighbours) {
        Deque<Direction> emptyNeighbours = new ArrayDeque<Direction>();
        boolean anyPlip = false;
        Direction attckDirection = Direction.TOP;
        for (Direction key : neighbours.keySet()) {
            if (neighbours.get(key).name().equals("empty")) {
                emptyNeighbours.add(key);
            } else {
                if (neighbours.get(key).name().equals("Plip")) {
                    anyPlip = true;
                    attckDirection = key;
                }
            }
        }

        if (emptyNeighbours.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }
        if (anyPlip) {
            return new Action(Action.ActionType.ATTACK, attckDirection);
        }
        if (energy >= 1.0) {
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbours));
        } else {
            return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbours));
        }

    }
}
