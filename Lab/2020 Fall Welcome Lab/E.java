import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int testCase = in.nextInt();
        int[] outPutList = new int[testCase];
        for (int i = 0; i < testCase; i++) {

            int length = in.nextInt();
            //数组串
            int[] numberList = new int[length];
            for (int j = 0; j < numberList.length; j++) {
                numberList[j] = in.nextInt();
            }

            /*
            1 2 3 4 5


            //左边最大 右边最小
            1 2 3      5 4 6
                         5
            左边最大的 后面的右边最小
             */

            int temDiff = numberList[0];
            int max = numberList[0] - numberList[1];
            for (int j = 1; j < numberList.length; j++) {
                if (max < temDiff - numberList[j]) {
                    max = temDiff - numberList[j];
                }
                temDiff = Math.max(temDiff, numberList[j]);
            }
            outPutList[i] = max;

        }


        for (int i : outPutList) {
            System.out.println(i);
        }
    }

}
