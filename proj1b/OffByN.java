public class OffByN implements CharacterComparator {

    int n;
    OffByN(int N){
        n = N;
    }

    public boolean equalChars(char x, char y) {
        if (Math.abs(x - y) == n) {
            return true;
        } else {
            return false;
        }
    }
}
