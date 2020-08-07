package bearmaps.hw4.integerhoppuzzle;

import bearmaps.hw4.AStarGraph;
import bearmaps.hw4.WeightedEdge;

import java.util.ArrayList;
import java.util.List;

/**
 * The Integer Hop puzzle implemented as a graph.
 * Created by hug.
 */
public class IntegerHopGraph implements AStarGraph<Integer> {

    @Override
    public List<WeightedEdge<Integer>> neighbors(Integer v) {
        ArrayList<WeightedEdge<Integer>> neighbors = new ArrayList<>();
        neighbors.add(new WeightedEdge<>(v, v * v, 10));
        neighbors.add(new WeightedEdge<>(v, v * 2, 5));
        neighbors.add(new WeightedEdge<>(v, v / 2, 5));
        neighbors.add(new WeightedEdge<>(v, v - 1, 1));
        neighbors.add(new WeightedEdge<>(v, v + 1, 1));
        return neighbors;
    }

    @Override
    public double estimatedDistanceToGoal(Integer s, Integer goal) {
        // possibly fun challenge: Try to find an admissible heuristic that
        // speeds up your search. This is tough!
        //return 0;
        return Integer.min(Integer.min(Math.abs(goal - s), estimatedMD(s, goal)), Integer.min(estimatedMD(s, goal), estimatedSquare(s, goal)));
    }

    private int estimatedMD(Integer s, Integer goal) {
        return estimatedMDhelper(s, goal, 0);
    }

    private int estimatedMDhelper(Integer s, Integer goal, int cnt) {
        if (goal >= s && goal < 2 * s) {
            return 5 * cnt;
        }
        if (goal > s / 2 && goal <= s) {
            return 5 * cnt;
        }
        if (goal > s) {
            return estimatedMDhelper(2 * s, goal, cnt + 1);
        }
        else {
            return estimatedMDhelper(s / 2, goal, cnt + 1);
        }
    }

    private int estimatedSquare(Integer s, Integer goal) {
        if (s > goal) {
            return Integer.MAX_VALUE;
        }
        return estimatedSquereHelper(s, goal, 0);
    }

    private int estimatedSquereHelper(Integer s, Integer goal, int cnt) {
        if (s < goal && goal < s * s) {
            return 10 * cnt;
        }
        return estimatedSquereHelper(s * s, goal, cnt + 1);
    }
}
