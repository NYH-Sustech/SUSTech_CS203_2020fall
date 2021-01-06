import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int testCase = in.nextInt();
        String[][][] outPutList = new String[testCase][][];
        for (int i = 0; i < testCase; i++) {
            int a = in.nextInt();//长
            int b = in.nextInt();//宽
            int c = in.nextInt();//高

            //创建数组
            outPutList[i] = new String[2 * c + 2 * b + 1][2 * a + 2 * b + 1];
            
            int m = 2 * c + 2 * b + 1;//输出矩阵侧边 13
            int n = 2 * a + 2 * b + 1;//输出矩阵上边 17
            
            String[] line1 = new String[2 * a + 1];//+-+-+-+-+-+边
            String[] line2 = new String[2 * a + 1];//|.|.|.|.|.|边
            String[] line3 = new String[2 * a + 1];///./././././边
            for (int j = 0; j < line1.length; j++) {
                if (j % 2 == 0) {
                    line1[j] = "+";
                    line2[j] = "|";
                    line3[j] = "/";
                } else {
                    line1[j] = "-";
                    line2[j] = ".";
                    line3[j] = ".";
                }
            }
            int dotNumberMax = 2 * b;
            int dotNumber = dotNumberMax;

            if(b <= c){
                for (int j = 0; j < outPutList[i].length; j++) {
                    if ((j <= (2 * b + 1) - 1) && (j % 2 == 0)) {
                        outPutList[i][j] = concat(produceElement(".",".",dotNumberMax - j),line1);
                        int res = n - outPutList[i][j].length;
                        if(res != 0){
                            outPutList[i][j] = concat(outPutList[i][j],reverse(produceElement("+",".",res)));
                        }
                    }else if ((j <= (2 * b + 1) - 1) && (j % 2 == 1)) {
                        outPutList[i][j] = concat(produceElement(".",".",dotNumberMax - j),line3);
                        int res = n - outPutList[i][j].length;
                        if(res != 0){
                            outPutList[i][j] = concat(outPutList[i][j],reverse(produceElement("|","/",res)));
                        }
                    }
                    //    行 8 9 10 11 12
                    //    点 4 3  2  1  0
                    //    中 0 1  2  3  4
                    else if(j >= outPutList[i].length - (2 * b + 1) && (j % 2 == 0)){
                        outPutList[i][j] = concat(line1,produceElement(".","+",m - 1 - j));
                        outPutList[i][j] = concat(outPutList[i][j],produceElement(".",".",n - outPutList[i][j].length));
                    }
                    else if(j >= outPutList[i].length - (2 * b + 1) && (j % 2 == 1)){
                        outPutList[i][j] = concat(line2,produceElement("/","|",m - 1 - j));
                        outPutList[i][j] = concat(outPutList[i][j],produceElement(".",".",n - outPutList[i][j].length));
                    }else{
                        if(j % 2 == 0){
                            outPutList[i][j] = line1;
                            outPutList[i][j] = concat(outPutList[i][j],produceElement(".","+",n - outPutList[i][j].length));
                        }else{
                            outPutList[i][j] = line2;
                            outPutList[i][j] = concat(outPutList[i][j],produceElement("/","|",n - outPutList[i][j].length));
                        }

                    }
                }
            }
            if (b > c){
                for (int j = 0; j < outPutList[i].length; j++) {
                    if ((j <= (2 * b + 1) - 1) && (j % 2 == 0)) {
                        outPutList[i][j] = concat(produceElement(".",".",dotNumberMax - j),line1);
                        int res = n - outPutList[i][j].length;
                        if(res != 0){
                            outPutList[i][j] = concat(outPutList[i][j],reverse(produceElement("+",".",res)));
                        }
                    }else if ((j <= (2 * b + 1) - 1) && (j % 2 == 1)) {
                        outPutList[i][j] = concat(produceElement(".",".",dotNumberMax - j),line3);
                        int res = n - outPutList[i][j].length;
                        if(res != 0){
                            outPutList[i][j] = concat(outPutList[i][j],reverse(produceElement("|","/",res)));
                        }
                    }
                    if(j >= outPutList[i].length - (2 * b + 1) && (j <= (2 * b + 1) - 1)){
                        int dotNum = n - (line1.length + m - 1 -j);
                        for(int k = outPutList[i][j].length - 1; k > outPutList[i][j].length - 1 - dotNum; k-- ){
                            outPutList[i][j][k] = ".";
                        }
                    }
                    else if(j >= outPutList[i].length - (2 * b + 1) && (j % 2 == 0)){
                        outPutList[i][j] = concat(line1,produceElement(".","+",m - 1 - j));
                        outPutList[i][j] = concat(outPutList[i][j],produceElement(".",".",n - outPutList[i][j].length));
                    }
                    else if(j >= outPutList[i].length - (2 * b + 1) && (j % 2 == 1)){
                        outPutList[i][j] = concat(line2,produceElement("/","|",m - 1 - j));
                        outPutList[i][j] = concat(outPutList[i][j],produceElement(".",".",n - outPutList[i][j].length));
                    }else{
                        if(j % 2 == 0){
                            if(outPutList[i][j].length == n){
                                continue;
                            }
                            outPutList[i][j] = line1;
                            outPutList[i][j] = concat(outPutList[i][j],produceElement(".","+",n - outPutList[i][j].length));
                        }else{
                            if(outPutList[i][j].length == n){
                                continue;
                            }
                            outPutList[i][j] = line2;
                            outPutList[i][j] = concat(outPutList[i][j],produceElement("/","|",n - outPutList[i][j].length));
                        }

                    }
                }
            }


        }
        
        for (int i = 0; i < outPutList.length; i++) {
            for(int j = 0; j < outPutList[i].length; j++){
                for(int k = 0; k < outPutList[i][j].length; k++){
                    System.out.print(outPutList[i][j][k]);
                }
                System.out.println();
            }
        }
    }


    public static String[] concat(String[] front, String[] behind) {

        String[] backList= new String[front.length + behind.length];

        for(int i = 0; i < front.length; i++){
            backList[i] = front[i];
        }
        for(int j = front.length; j < front.length + behind.length; j++){
            backList[j] = behind[j - front.length];
        }
        return backList;

    }

    public static String[] produceElement(String front, String behind, int length){
        String[] backList = new String[length];
        for(int i = 0; i < backList.length; i++){
            if(i % 2 == 0){
                backList[i] = front;
            }else{
                backList[i] = behind;
            }
        }
        return backList;
    }


    public static String[] reverse(String[] original){
        String[] reverseList = new String[original.length];
        for(int i = 0; i < original.length; i++){
            reverseList[original.length - 1 - i] = original[i];
        }
        return reverseList;
    }
}
