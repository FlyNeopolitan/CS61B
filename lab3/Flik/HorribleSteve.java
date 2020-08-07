public class HorribleSteve {
    public static void main(String [] args) throws Exception {
        int i = 0;
        for (int j = 0; i < 500; ++i, ++j) {
            if (!Flik.isSameNumber(i, j)) {
                System.out.println(i + " not same as " + j + " ??");
                break;
            }
        }
        System.out.println("i is " + i);
    }

}
