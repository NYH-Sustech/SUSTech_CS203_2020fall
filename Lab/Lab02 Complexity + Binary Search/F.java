import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //机器人初始坐标
        long Xr = in.nextLong();
        long Yr = in.nextLong();
        //CC初始坐标
        long Xc = in.nextLong();
        long Yc = in.nextLong();
        //指示的数量
        long N = in.nextLong();
        //命令
        String instructions = in.next();
        System.out.println(findMinTime(Xr,Yr,Xc,Yc,instructions));
    }

    public static long findMinTime(long Xr, long Yr, long Xc, long Yc, String instruction){
        long tMin = 0;
        long tMax = Long.MAX_VALUE / 1000;
        boolean flag = true;
        while(flag){
            long mid = (tMax + tMin) / 2;//二分法时间
            //mid时间下的位置
            long[] currPos = findPosition(Xr, Yr, instruction, mid);
            //距离 < 时间 - 缩短时间
            if(Math.abs(currPos[0] - Xc) + Math.abs(currPos[1] - Yc) <= mid){
                tMax = mid;
            //距离 > 时间 - 增大时间
            }else if(Math.abs(currPos[0] - Xc) + Math.abs(currPos[1] - Yc) > mid){
                tMin = mid;
            }
            if(tMin + 1 == tMax){
                flag = false;
            }
        }
        if(tMax  == Long.MAX_VALUE / 1000){
            return -1;
        }

        return tMax;
    }

    //返回t时间下机器人的位置
    public static long[] findPosition(long Xr, long Yr, String instruction, long t){

        //返回pos数组，pos[0]是机器人t时刻下Xr的位置，pos[1]是机器人t时刻下Yr的位置
        long[]pos = new long[2];
        pos[0] = Xr;
        pos[1] = Yr;

        long[] cycMove = new long[2];//周期循环下的变化
        long cycle = instruction.length();//8
        for(int i = 0; i < instruction.length(); i++){
            if(instruction.charAt(i) == 'U'){
                cycMove[1]++;
            }else if(instruction.charAt(i) == 'D'){
                cycMove[1]--;
            }else if(instruction.charAt(i) == 'L'){
                cycMove[0]--;
            }else if(instruction.charAt(i) == 'R'){
                cycMove[0]++;
            }
        }
        //t = 23
        long cycleTime = t / cycle;//循环了几个周期
        long cycleLeft = t % cycle;//余数  7
        pos[0] = pos[0] + cycleTime * cycMove[0];
        pos[1] = pos[1] + cycleTime * cycMove[1];
        for(int i = 0; i < cycleLeft; i++){
            if(instruction.charAt(i) == 'U'){
                pos[1]++;
            }else if(instruction.charAt(i) == 'D'){
                pos[1]--;
            }else if(instruction.charAt(i) == 'L'){
                pos[0]--;
            }else if(instruction.charAt(i) == 'R'){
                pos[0]++;
            }
        }
        return pos;
    }
}
