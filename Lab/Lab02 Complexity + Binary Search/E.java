import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCase = in.nextInt();
        int[] returnList = new int[testCase];//返回清单
        int[] list = new int[1000001];
        list[0] = 0;
        list[1] = 1;
        list[2] = 1;
        list[3] = 1;
        for(int i = 4; i < list.length; i++){
            list[i] = list[(int)(Math.floor(i/2) - 1)] + list[(int)(Math.floor(i/2))] + list[(int)(Math.floor(i/2) + 1)];
        }
        for(int i = 0; i < testCase; i++){
            int num = in.nextInt();
            returnList[i] = list[num];
        }
        for(int i = 0; i < returnList.length; i++){
            System.out.println(returnList[i]);
        }
    }
}
