import static org.junit.Assert.*;
import org.junit.Test;



public class TestArrayDequeGold {


    @Test
    public void randomTest() {
        StudentArrayDeque<Integer> studentD = new StudentArrayDeque<Integer>();
        ArrayDequeSolution<Integer> solutionD = new ArrayDequeSolution<Integer>();
        int cntOfAdd = 0;
        int cntOfRemove = 0;
        String operation = "";
        for (int i = 0; i < 500; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.25) {
                 operation = operation + "\naddFirst(" + i + ")";
                 studentD.addFirst(i);
                 solutionD.addFirst(i);
                 cntOfAdd = cntOfAdd + 1;

            } else if (numberBetweenZeroAndOne < 0.5) {
                operation = operation + "\naddLast(" + i + ")";
                studentD.addLast(i);
                solutionD.addLast(i);
                cntOfAdd = cntOfAdd + 1;

            } else if (numberBetweenZeroAndOne < 0.75) {
                if (cntOfAdd > cntOfRemove) {
                    operation = operation + "\nremoveLast()";
                    assertEquals(operation, solutionD.removeLast(), studentD.removeLast());
                    cntOfRemove += 1;

                }
            } else {
                if (cntOfAdd > cntOfRemove) {
                    operation = operation + "\nremoveFirst()";
                    assertEquals(operation, solutionD.removeFirst(), studentD.removeFirst());
                    cntOfRemove += 1;

                }
            }

        }

    }
}
