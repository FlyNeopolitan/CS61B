package es.datastructur.synthesizer;

import static edu.princeton.cs.introcs.StdRandom.uniform;

//Note: This file will not compile until you complete task 1 (BoundedQueue).
public class DrumString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = 1.00; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public DrumString(double frequency) {
        // TODO: Create a buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this division operation into an int. For
        //       better accuracy, use the Math.round() function before casting.
        //       Your buffer should be initially filled with zeros.
        buffer = new ArrayRingBuffer<>((int) Math.round(SR/frequency));
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.enqueue(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // TODO: Dequeue everything in buffer, and replace with random numbers
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        //
        //       Make sure that your random numbers are different from each
        //       other.
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.dequeue();
            double r = Math.random() - 0.5;
            buffer.enqueue(r);
        }
    }

   /* private boolean testOfR(double r, double[] numbersGet, int i) {
        for (int j = 0; j < i; j++) {
            if (numbersGet[j] == r) {
                return false;
            }
        }
        return true;
    }*/

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // TODO: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       Do not call StdAudio.play().
        double frontSample = buffer.dequeue();
        double SecondFrontSample = buffer.peek();
        double insertedSample = signal() * DECAY * (frontSample + SecondFrontSample) / 2;
        buffer.enqueue(insertedSample);
    }

    private int signal() {
        double x = Math.random();
        if(x > 0.5) {
            return 1;
        } else {
            return -1;
        }
    }
    /* Return the double at the front of the buffer. */
    public double sample() {
        // TODO: Return the correct thing.
        return buffer.peek();
    }
}