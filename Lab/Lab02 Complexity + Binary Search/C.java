import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        long[] arr = new long[length];
        long S = in.nextLong();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.nextLong();
        }
        long number = findComb(arr,S);//可能的排列组合情况
        System.out.println(number);
    }

    public static long findComb(long[] arr, long S) {
        long num = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            int j = i + 1; int k = arr.length - 1;
            while (k > j) {
                if (arr[i] + arr[j] + arr[k] == S) {
                    num++;
                    for(int a = j + 1; a < k; a++){
                        if(arr[a] == arr[j]){
                            num++;
                        }
                    }
                    for(int b = k - 1; b > j; b--){
                        if(arr[b] == arr[k]){
                            num++;
                        }
                    }
                    k--;j++;
                } else if (arr[i] + arr[j] + arr[k] < S) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return num;
    }
}
