import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {
    PriorityQueue<Flight> minStartPQ;
    PriorityQueue<Flight> minEndPQ;
    Comparator<Flight> StartComparator = Comparator.comparingInt(i -> i.startTime);
    Comparator<Flight> EndComparator = Comparator.comparingInt(i -> i.endTime);

    public FlightSolver(ArrayList<Flight> flights) {
        minStartPQ = new PriorityQueue<>(StartComparator);
        minEndPQ = new PriorityQueue<>(EndComparator);
        for (Flight i : flights) {
            minStartPQ.add(i);
            minEndPQ.add(i);
        }
    }

    public int solve() {
        int max = 0;
        int currentMax = 0;
        while (!minStartPQ.isEmpty() ) {
            Flight EarliestStart = minStartPQ.peek();
            Flight EarliestEnd = minEndPQ.peek();
            if (EarliestStart.startTime <= EarliestEnd.endTime) {
                currentMax+= EarliestStart.passengers;
                minStartPQ.poll();
            } else {
                minEndPQ.poll();
                currentMax -= EarliestEnd.passengers;
            }
            if (currentMax > max) {
                max = currentMax;
            }
        }
        return max;
    }
    /*
        int tally = 0;
        int maxVal = 0;
        while (minStartPQ.size() > 0 && minEndPQ.size() > 0) {
            int startTime = minStartPQ.peek().startTime(); // just peek
            int endTime = minEndPQ.peek().endTime();

            if (startTime <= endTime) { // Start-End Overlap: Option 2
                tally += minStartPQ.poll().passengers;
            } else { // including endTime <= startTime
                tally -= minEndPQ.poll().passengers();
            }

            maxVal = (tally > maxVal) ? tally : maxVal;
        }
        return maxVal;*/





    public static void main(String[] args) {
        ArrayList<Flight> test = new ArrayList<>();
        test.add(new Flight(1, 3, 30));
        test.add(new Flight(2, 5, 30));
        test.add(new Flight(4, 9, 30));
        FlightSolver Test = new FlightSolver(test);
        for (Flight i : Test.minStartPQ) {
            System.out.println(i.startTime);
        }
    }


}
