import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCase = in.nextInt();
        String[] outPutList = new String[testCase];
        for(int i = 0; i < outPutList.length; i++){

            String t = in.next();
            String t_initial = t;//记录t的初始值
            int s = in.nextInt();

            boolean flag = true;
            int bit = 1;
            while(flag){
                if(findSum(t) > s){
                    t = carryBit(t,bit);
                    bit++;
                }
                if(findSum(t) <= s){
                    flag = false;
                }
            }
            outPutList[i] = Long.parseLong(t) - Long.parseLong(t_initial) + "";
        }

        for (String i : outPutList) {
            System.out.println(i);
        }
    }

    //一个数第n次进位
    //1-十位 2-百位 3-千位，以此类推
    public static String carryBit(String t, int bit){
        long number = Long.parseLong(t);
        number = number +  (long)Math.pow(10,bit) - (number % (long)Math.pow(10,bit));
        String  t1 = number + "";
        return t1;
    }

    //求一个数各个位数的和
    public static int findSum(String t){
        int sum = 0;
        for(int i = 0; i < t.length(); i++){
            sum += Integer.parseInt(String.valueOf(t.charAt(i)));
        }
        return sum;
    }
}
