import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {
    List<Bear> bears;
    List<Bed> beds;

    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        this.bears = bears;
        this.beds = beds;
    }

    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        return solveBedsHelper(beds, bears).second();
    }

    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        return solveBedsHelper(beds, bears).first();
    }

    private Pair<List<Bed>, List<Bear>> solveBedsHelper(List<Bed> beds, List<Bear> bears) {
        //basic case
        if (beds.size() <= 1 && bears.size() <= 1) {
            return new Pair<>(beds, bears);
        }
        List<Bed> smallBeds = new LinkedList<>();
        List<Bed> equalBeds = new LinkedList<>();
        List<Bed> greaterBeds = new LinkedList<>();
        Bear bearPivot = bears.get(0);
        partitionBeds(beds, bearPivot, smallBeds, equalBeds, greaterBeds);
        Bed bedPivot = equalBeds.get(0);
        List<Bear> smallBears = new LinkedList<>();
        List<Bear> equalBears = new LinkedList<>();
        List<Bear> greaterBears = new LinkedList<>();
        partitionBears(bears, bedPivot, smallBears, equalBears, greaterBears);
        Pair<List<Bed>, List<Bear>> smallPair = solveBedsHelper(smallBeds, smallBears);
        Pair<List<Bed>, List<Bear>> greatPair = solveBedsHelper(greaterBeds, greaterBears);
        return new Pair<>(BedMerge(smallPair.first(), equalBeds, greatPair.first()),
                BearMerge(smallPair.second(), equalBears, greatPair.second()));
    }

    private void partitionBeds(List<Bed> items, Bear pivot, List<Bed> small, List<Bed> equal, List<Bed> greater) {
       for (Bed i : items) {
           if (i.compareTo(pivot) < 0) {
               small.add(i);
           }
           if (i.compareTo(pivot) > 0) {
               greater.add(i);
           }
           if (i.compareTo(pivot) == 0) {
               equal.add(i);
           }
       }
    }

    private void partitionBears(List<Bear> items, Bed pivot, List<Bear> small, List<Bear> equal, List<Bear> greater) {
        for (Bear i : items) {
            if (i.compareTo(pivot) < 0) {
                small.add(i);
            }
            if (i.compareTo(pivot) > 0) {
                greater.add(i);
            }
            if (i.compareTo(pivot) == 0) {
                equal.add(i);
            }
        }
    }

    private List<Bed> BedMerge(List<Bed> p, List<Bed> q , List<Bed> r) {
        List<Bed> mergedBeds = new LinkedList<>();
        for (Bed i : p) {
            mergedBeds.add(i);
        }
        for (Bed i : q) {
            mergedBeds.add(i);
        }
        for (Bed i : r) {
            mergedBeds.add(i);
        }
        return mergedBeds;
    }

    private List<Bear> BearMerge(List<Bear> p, List<Bear> q , List<Bear> r) {
        List<Bear> mergedBeds = new LinkedList<>();
        for (Bear i : p) {
            mergedBeds.add(i);
        }
        for (Bear i : q) {
            mergedBeds.add(i);
        }
        for (Bear i : r) {
            mergedBeds.add(i);
        }
        return mergedBeds;
    }
}
