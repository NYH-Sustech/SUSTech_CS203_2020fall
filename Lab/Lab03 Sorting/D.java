import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) {
        InputStream inputStream = System.in;// new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    static class Task {
        private long count;

        public void solve(InputReader in, PrintWriter out) {
            int T = in.nextInt();
            for (int i = 0; i < T; i++) {
                count = 0;
                int n = in.nextInt();
                int[] arr = new int[n];
                for (int j = 0; j < arr.length; j++) {
                    arr[j] = in.nextInt();
                }
                getReverseCount(arr);
                out.println(count);


            }


        }

        public void mergeArray(int[] A, int[] leftA, int[] rightA) {
            int len = 0;
            int i = 0;
            int j = 0;
            int lenL = leftA.length;
            int lenR = rightA.length;
            while (i < lenL && j < lenR) {
                if (leftA[i] > rightA[j]) {
                    A[len++] = rightA[j++]; //将rightA[j]放在leftA[i]元素之前，那么leftA[i]之后lenL - i个元素均大于rightA[j]
                    count += (lenL - i);   //合并之前，leftA中元素是非降序排列，rightA中元素也是非降序排列。所以，此时就新增lenL -　i个逆序对
                } else {
                    A[len++] = leftA[i++];
                }
            }
            while (i < lenL)
                A[len++] = leftA[i++];
            while (j < lenR)
                A[len++] = rightA[j++];
        }


        //全局变量，使用合并排序，计算逆序对数
        //使用归并排序方法计算数组A中的逆序对数
        public void getReverseCount(int[] A) {
            if (A.length > 1) {
                int[] leftA = getHalfArray(A, 0);   //数组A的左半边元素
                int[] rightA = getHalfArray(A, 1);  //数组A的右半边元素
                getReverseCount(leftA);
                getReverseCount(rightA);
                mergeArray(A, leftA, rightA);
            }
        }

        //根据judge值判断，获取数组A的左半边元素或者右半边元素
        public int[] getHalfArray(int[] A, int judge) {
            int[] result;
            if (judge == 0) {   //返回数组A的左半边
                result = new int[A.length / 2];
                for (int i = 0; i < A.length / 2; i++)
                    result[i] = A[i];
            } else {    //返回数组的右半边
                result = new int[A.length - A.length / 2];
                for (int i = 0; i < A.length - A.length / 2; i++)
                    result[i] = A[A.length / 2 + i];
            }
            return result;
        }
        //合并数组A的左半边和右半边元素，并按照非降序序列排列

    }

    public static int BSA(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    count++;
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
        return count;
    }

    public static int BSA2(int[] arr) {
        int count = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j <= arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                    count++;
                }
            }
        }
        return count;
    }
//
//    public static int InverseNumber(int[] arr) {
//        int count = 0;
//        int ai, aj;
//        for (int i = 1; i < arr.length; i++) {
//            for (int j = 0; j < i; j++) {
//                ai = arr[i];
//                aj = arr[j];
//                if (ai < aj) {
//                    count++;
//                }
//            }
//        }
//        return count;
//    }


    //    public static void test(String[] args) {
//        ans = 0;
//        int[] a = {9,8,7,6,5,4};//输入的序列
//        int[] tmp = {0,0,0,0,0,0};
//        InverseNumber(a,0,a.length-1,tmp);
//        System.out.println(ans);
//
//    }
    //将不同规模的‘a’逐步分治排序，并归并值tmp中，之后反传递给a
    //s:start index,e:end index,m:mid index
    static int merge_lm(int[] a, int s, int m, int e, int[] tmp) {

        int count = 0;
        int pt = 0;
        int p1 = s;
        int p2 = m + 1;
        while (p1 <= m && p2 <= e) {
            if (a[p1] < a[p2]) {
                tmp[pt++] = a[p1++];
            } else {
                tmp[pt++] = a[p2++];
                count += (m - p1 + 1);//记录逆序数
            }
        }
        while (p1 <= m) {
            tmp[pt++] = a[p1++];
        }
        while (p2 <= m) {
            tmp[pt++] = a[p2++];
        }
        for (int i = 0; i < e - s + 1; i++) {
            a[s + i] = tmp[i];
        }

        return count;
    }

    //s:start index,e:end index,m:mid index
    static int InverseNumber(int[] a, int start, int end, int[] tmp) {

        int count = 0;
        if (start < end) {
            //divide
            int mid = start + (end - start) / 2;
            //conquer
            count += InverseNumber(a, start, mid, tmp);
            count += InverseNumber(a, mid + 1, start, tmp);
            //combine
            count += merge_lm(a, start, mid, end, tmp);
        }
        return count;
    }

    //------------------------------------------------------------


    //快读快写
    //-----------------------------------------------------------------

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public char[] nextCharArray() {
            return next().toCharArray();
        }

        public boolean hasNext() {
            try {
                String string = reader.readLine();
                if (string == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(string);
                return tokenizer.hasMoreTokens();
            } catch (IOException e) {
                return false;
            }
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecinal() {
            return new BigDecimal(next());
        }
    }

}

