/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
import es.datastructur.synthesizer.GuitarString;
import es.datastructur.synthesizer.HarpString;

public class GuitarHero {
    public static void main(String[] args) {
        /* create 37 guitar strings, for String keyboard*/
        GuitarString[] string = new GuitarString[37];
        for (int i = 0; i < 37; i++) {
            double powMe = (double) (i - 24) / 12.0;
            double frequency = 440 * Math.pow(2, powMe);
            string[i] = new GuitarString(frequency);
        }
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

        while (true) {
            int index = 0;
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                index = keyboard.indexOf(key);
                if (index < 0 || index > 37) {
                    index = 0;
                }
                string[index].pluck();
            }

            /* compute the superposition of samples */
            double sample = 0;
            for (int i = 0; i < 37; i++) {
                sample += string[i].sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < 37; i++) {
                string[i].tic();
            }
        }
    }
}
