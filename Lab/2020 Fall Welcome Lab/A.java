import java.util.Scanner;


public class Main {
            /*
        0 a 110
        1 b 011
        2   101
        3   110
        4   011
        5=a
        6=b
        7=a^b

        0 a 11011
        1 b 01001
        2   10010
        3   11011
        4   01001

        0
        1
        2

        3%3 = 0
        4%3 = 1
        5%3 = 2

         */
    
    
    
    public static void main(String[] args) { 
        Scanner in = new Scanner(System.in);
        int testCase = in.nextInt();
        int[] returnList = new int[testCase];//返回清单

        for(int i = 0; i < testCase; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            int n = in.nextInt();
            returnList[i] = fillInNumber(a,b,n);
        }
        for(int i = 0; i < returnList.length; i++){
            System.out.println(returnList[i]);
        }
    }
    

    public static int fillInNumber(int a, int b, int n){
        if(n == 0){
            return a;
        }
        if(n == 1){
            return b;
        }
        if(n % 3 == 0){
            return a;
        }
        if(n % 3 == 1){
            return b;
        }
        if(n % 3 == 2){
            return a^b;
        }
        return 0;
    }
}
