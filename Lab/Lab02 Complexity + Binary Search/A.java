import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCase = in.nextInt();
        long[] returnList = new long[testCase];//返回清单
        for(int i = 0; i < testCase; i++){
            long n = in.nextInt();
            long f1 = (n * (n + 1)) / 2 ;
            long f2 = f1 % (1000000000 + 7);
            long f3 = f2 * f2;
            long f4 = f3 % (1000000000 + 7);
            returnList[i] = f4;
        }
        for(int i = 0; i < returnList.length; i++){
            System.out.println(returnList[i]);
        }
    }
}
