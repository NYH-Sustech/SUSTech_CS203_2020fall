import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
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

        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[] an = new int[n];


            long sumout = 0;
            for (int i = 0; i < n; i++) {
                an[i] = in.nextInt();
                sumout += an[i];
            }

            long sum = 0;
            int index = 0;
            long[] suffix = new long[n - 1];
            for (int i = n - 1; i >= 1; i--) {
                sum += an[i];
                suffix[index] = sum;
                index++;
            }


            Arrays.sort(suffix);
            m--;
            for(int i = suffix.length - 1; i >= suffix.length - m; i--){
                sumout += suffix[i];
            }
            out.println(sumout);
        }
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

}


