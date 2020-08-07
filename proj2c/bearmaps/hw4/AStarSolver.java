package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.DoubleMapPQ;
import bearmaps.proj2ab.ExtrinsicMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    Vertex start;
    Vertex end;
    SolverOutcome result;
    int numStateExplored = 0;
    double exploreTime;
    Map<Vertex, Vertex> edgeTo = new HashMap<>();
    Map<Vertex, Double> disTo = new HashMap<>();

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        this.start = start;
        this.end = end;
        Stopwatch sw = new Stopwatch();
        ExtrinsicMinPQ<Vertex> PQ = new ArrayHeapMinPQ<>();
        PQ.add(start, input.estimatedDistanceToGoal(start, end));
        disTo.put(start, (double) 0);
        while (PQ.size() != 0) {
            Vertex p = PQ.removeSmallest();
            numStateExplored++;
            if (p.equals(end)) {
                result = SolverOutcome.SOLVED;
                exploreTime = sw.elapsedTime();
                return;
            }
            if (sw.elapsedTime() > timeout) {
                result = SolverOutcome.TIMEOUT;
                exploreTime = sw.elapsedTime();
                return;
            }
            for (WeightedEdge<Vertex> i : input.neighbors(p)) {
                Vertex q = i.to();
                double weight = i.weight();
                double potentialDis = disTo.get(p) + weight;
                if (!disTo.containsKey(q) || disTo.get(p) + weight < disTo.get(q)) {
                    if (!PQ.contains(q)) {
                        disTo.put(q, potentialDis);
                        edgeTo.put(q, p);
                        PQ.add(q, potentialDis + input.estimatedDistanceToGoal(q, end));
                    } else {
                        PQ.changePriority(q, potentialDis + input.estimatedDistanceToGoal(q, end));
                        disTo.put(q, potentialDis);
                        edgeTo.put(q, p);
                    }
                }
            }
        }
        result = SolverOutcome.UNSOLVABLE;
        exploreTime = sw.elapsedTime();
    }
    public SolverOutcome outcome() {
        return result;
    }
    public List<Vertex> solution() {
        List<Vertex> solution = new LinkedList<>();
        if (result.equals(SolverOutcome.SOLVED)) {
            solutionHelper(solution, end, start);
        }
        return solution;
    }
    public double solutionWeight() {
        if (result.equals(SolverOutcome.SOLVED)) {
            return disTo.get(end);
        }
        return 0;
    }
    public int numStatesExplored() {
        return numStateExplored - 1;
    }
    public double explorationTime() {
        return exploreTime;
    }

    private void solutionHelper(List<Vertex> solution, Vertex p, Vertex start) {
        if (p.equals(start)) {
            solution.add(start);
            return;
        }
        solutionHelper(solution, edgeTo.get(p), start);
        solution.add(p);
    }
}
