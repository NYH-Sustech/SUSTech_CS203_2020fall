import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int testCase = in.nextInt();
        String[] outPutList = new String[testCase];
        for (int i = 0; i < testCase; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            if (n == 1 && m == 1) {
                outPutList[i] = "Bob";
            } else if (n == 1 || m == 1 || n == m) {
                outPutList[i] = "Alice";
            } else if (Math.abs(n - m) == 2) {
                outPutList[i] = "Bob";
            } else {
                outPutList[i] = "Alice";
            }
        }
        for (String i : outPutList) {
            System.out.println(i);
        }
    }
}
