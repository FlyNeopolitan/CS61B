public class RabinKarpAlgorithm {


    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        /*if (pattern.length() > input.length()) {
            return -1;
        }
        RollingString ObjectString = new RollingString(input, pattern.length());
        RollingString patternString = new RollingString(pattern, pattern.length());
        int i = 0;
        while (pattern.length() + i < input.length()) {
            if (ObjectString.hashCode() == patternString.hashCode()) {
                if (ObjectString.equals(patternString)) {
                    return i;
                }
            }
            ObjectString.addChar(input.charAt(pattern.length() + i));
            i++;
        }
        return -1;
    }*/
        if (pattern.length() > input.length()) return -1;
        int n = input.length();
        int m = pattern.length();

        RollingString patternRoll = new RollingString(pattern, m);
        RollingString inputRoll = new RollingString(input.substring(0, m), m);
        int patternHash = patternRoll.hashCode();
        for (int i = 0; i < n - m + 1; i += 1) {
            int inputHash = inputRoll.hashCode();   // constant
            if (inputHash == patternHash) {
                // match
                if (inputRoll.equals(patternRoll)) {
                    return i;
                }
            }
            if (i + m < n)  inputRoll.addChar(input.charAt(i + m));   // constant
        }
        return -1;
    }

}
