import java.util.Scanner;

/**
 * 漆狗屋/最大值最小问题
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //节点个数
        int treeNum = in.nextInt();
        //人的个数
        long peopleNum = in.nextLong();
        //终点位置
        long finishLine = in.nextLong();
        //树的位置
        long[] treePos = new long[treeNum];
        for (int i = 0; i < treePos.length; i++) {
            treePos[i] = in.nextLong();
        }
        //各个距离数组
        long[] distance = new long[treeNum];
        for (int i = 0; i < treePos.length - 1; i++) {
            distance[i] = treePos[i + 1] - treePos[i];
        }
        distance[distance.length - 1] = finishLine - treePos[treePos.length - 1];
        System.out.println(findMaxMin(distance, peopleNum));
    }


    /**
     * @param arr 传入每个点之间的距离数组arr
     * @param sub 数组分成几段
     * @return
     */
    public static long findMaxMin(long[] arr, long sub) {
        long Min = arr[0];
        long Max = 0;
        for (int i = 0; i < arr.length; i++) {
            Min = Math.min(arr[i], Min);//数组中的最小的那个元素
            Max += arr[i];//数组加和值
        }
        boolean flag = true;
        while (flag) {
            long mid = (Max + Min) / 2;
            //如果需要的人数比指定人数多，则增大最小
            if (peopleCount(arr, mid) > sub) {
                Min = mid;
                //如果需要的人数比指定人数多，则减小最大
            } else if (peopleCount(arr, mid) <= sub) {
                Max = mid;
            }
            //找到值了
            if (Min + 1 == Max) {
                flag = false;
            }
        }
        return Max;
    }

    /**
     * 装桶原理
     *
     * @param arr     传入数组
     * @param totDist 能够装多少桶
     * @return
     */
    public static long peopleCount(long[] arr, long totDist) {
        long peopleNum = 1;
        long tempdist = totDist;
        for (long l : arr) {
            if (tempdist - l < 0) {
                tempdist = totDist - l;
                peopleNum++;
            } else if (tempdist - l >= 0) {
                tempdist -= l;
            }
        }
        return peopleNum;
    }

}
