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
            int n = in.nextInt();
            long x1 = in.nextLong();
            long x2 = in.nextLong();

            int[] ki = new int[n];
            int[] bi = new int[n];
            long[] y1 = new long[n];
            long[] y2 = new long[n];

            for(int i = 0; i < ki.length; i++){
                ki[i] = in.nextInt();
                bi[i] = in.nextInt();
                y1[i] = ki[i] * x1 + bi[i];
                y2[i] = ki[i] * x2 + bi[i];
            }

            quickSort(y1,y2,0,y1.length - 1);
            getReverseCount(y2);
            if(count > 0){
                out.println("YES");
            }else{
                out.println("NO");
            }






        }

        public void mergeArray(long[] A, long[] leftA, long[] rightA) {
            int len = 0;
            int i = 0;
            int j = 0;
            int lenL = leftA.length;
            int lenR = rightA.length;
            while (i < lenL && j < lenR) {
                if (leftA[i] > rightA[j]) {
                    A[len++] = rightA[j++];
                    count += (lenL - i);
                    if(count > 0){
                        return;
                    }

                } else {
                    A[len++] = leftA[i++];
                }
            }
            while (i < lenL)
                A[len++] = leftA[i++];
            while (j < lenR)
                A[len++] = rightA[j++];
        }


        public void getReverseCount(long[] A) {
            if (A.length > 1) {
                long[] leftA = getHalfArray(A, 0);
                long[] rightA = getHalfArray(A, 1);
                getReverseCount(leftA);
                getReverseCount(rightA);
                mergeArray(A, leftA, rightA);
            }
        }


        public long[] getHalfArray(long[] A, int judge) {
            long[] result;
            if (judge == 0) {
                result = new long[A.length / 2];
                for (int i = 0; i < A.length / 2; i++)
                    result[i] = A[i];
            } else {
                result = new long[A.length - A.length / 2];
                for (int i = 0; i < A.length - A.length / 2; i++)
                    result[i] = A[A.length / 2 + i];
            }
            return result;
        }



    }

    

    private static void quickSort(long[] arr, long[] arr2,int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            return;
        }

        int left = leftIndex;
        int right = rightIndex;

        long key = arr[left];
        long key2 = arr2[left];


        while (left < right) {
            while (right > left && arr[right] > key) {
                right--;
            }

            while(right > left && arr[right] == key && arr2[right] >= key2){
                right--;
            }


            arr[left] = arr[right];
            arr2[left] = arr2[right];

            while (left < right && arr[left] <= key) {
                left++;
            }

            while(right > left && arr[left] == key && arr2[left] <= key2){
                left++;
            }


            arr[right] = arr[left];
            arr2[right] = arr2[left];
        }

        arr[left] = key;
        arr2[left] = key2;

        quickSort(arr, arr2, leftIndex, left - 1);
        quickSort(arr, arr2, right + 1, rightIndex);
    }

    private static void quickSort(long[] arr, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            return;
        }

        int left = leftIndex;
        int right = rightIndex;

        long key = arr[left];


        while (left < right) {
            while (right > left && arr[right] >= key) {
                right--;
            }


            arr[left] = arr[right];

            while (left < right && arr[left] <= key) {

                left++;
            }


            arr[right] = arr[left];
        }

        arr[left] = key;

        quickSort(arr, leftIndex, left - 1);

        quickSort(arr, right + 1, rightIndex);
    }






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

