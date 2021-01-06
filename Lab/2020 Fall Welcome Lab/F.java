import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCase = in.nextInt();
        int[] returnList = new int[testCase];//返回清单

        for(int i = 0; i < testCase; i++){
            int pileNum = in.nextInt();
            int[] stoneNum = new int[pileNum];
            //把石子全部填入
            for(int j = 0; j < pileNum; j++){
                stoneNum[j] = in.nextInt();
            }
            int balance = stoneNum[0];
            for(int j = 1; j < stoneNum.length; j++){
                balance = balance ^ stoneNum[j];
            }
            //平衡态必输
            if(balance == 0){
                returnList[i] = 0;
            } else{
                //以下是非平衡态的部分
                String[] binaryStoneNum = new String[pileNum];
                int maxBit = 0;
                for(int j = 0; j < pileNum; j++){
                    binaryStoneNum[j] = Integer.toBinaryString(stoneNum[j]);
                    if(binaryStoneNum[j].length() > maxBit){
                        maxBit = binaryStoneNum[j].length();
                    }
                }
                //0101
                //1001
                //1102
                for(int j = 0; j < binaryStoneNum.length; j++){
                    if(binaryStoneNum[j].length() < maxBit){
                        int len = binaryStoneNum[j].length();
                        binaryStoneNum[j] = creatZero(maxBit - len) + binaryStoneNum[j];
                    }
                }
                int[] eachBitNum = new int[maxBit];
                for(int j = 0; j < pileNum; j++){
                    for(int k = 0; k < maxBit; k++){
                        eachBitNum[k] += Integer.parseInt(String.valueOf(binaryStoneNum[j].charAt(k)));
                    }
                }
                int way = 0;//输出有几种方法
                for(int j = 0; j < eachBitNum.length; j++){
                    if(eachBitNum[j] % 2 != 0){
                        way = eachBitNum[j];
                        break;
                    }
                }
                returnList[i] = way;
            }
        }

        for(int i = 0; i < returnList.length; i++){
            System.out.println(returnList[i]);
        }
    }
    public static String creatZero(int num){
        String zeroString = "0";
        for(int i = 0; i < num - 1; i++){
            zeroString = zeroString + "0";
        }
        return zeroString.toString();
    }
}
