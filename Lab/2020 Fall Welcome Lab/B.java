import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCase = in.nextInt();
        long[] outPutList = new long[testCase];
        for (int i = 0; i < testCase; i++) {
            int numbers = in.nextInt();
            outPutList[i] = moveSteps(numbers);
        }
        for(int i = 0; i < outPutList.length; i++){
            System.out.println(outPutList[i]);
        }
    }
    public static long moveSteps(int n){
        long fn = 2;
        for(int i = 0; i < n - 1; i++){
            fn = fn * 3 + 2;
            if(fn >= 1000000007){
                fn %= 1000000007;
            }
        }
        return fn;
    }
}
