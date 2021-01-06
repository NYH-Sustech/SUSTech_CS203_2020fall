import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int problemNum = in.nextInt();
        int labNum = in.nextInt();
        int[] problemDiff = new int[problemNum];//问题难度
        int[] labEner = new int[labNum];//每个实验室有的能力
        for(int i = 0; i < problemDiff.length; i++){
            problemDiff[i] = in.nextInt();
        }
        for(int i = 0; i < labEner.length; i++){
            labEner[i] = in.nextInt();
        }
        for(int i = 0; i < labEner.length; i++){
            if(BSA(problemDiff,labEner[i])){
                System.out.println("Accept");
            }else{
                System.out.println(BSA2(problemDiff,labEner[i]));
            }
        }
    }

    public static boolean BSA(int[] arr, int num){
        boolean exist = false;
        //original location
        int left = 0;
        int right = arr.length - 1;
        boolean flag = true;
        while(flag){
            int mid = left + (right - left) / 2;
            if(arr[mid] == num){
                exist = true;
                break;//remind letting the loop break!!
            }else if(num < arr[mid]){
                right = mid - 1;
            }else if(num > arr[mid]){
                left = mid + 1;
            }
            //break situation
            if(left > right){
                flag = false;
            }
        }
        return exist;
    }

    public static int BSA2(int[] arr, int num){
        //original location
        int left = 0;
        int right = arr.length - 1;
        int diff = Integer.MAX_VALUE;//差值
        boolean flag = true;
        while(flag){
            int mid = left + (right - left) / 2;
            if(arr[mid] == num){
                break;//remind letting the loop break!!
            }else if(num < arr[mid]){
                right = mid - 1;
            }else if(num > arr[mid]){
                diff = Math.min(diff,num - arr[mid]);
                left = mid + 1;
            }
            //break situation
            if(left > right){
                flag = false;
            }
        }
        return diff;
    }

}
