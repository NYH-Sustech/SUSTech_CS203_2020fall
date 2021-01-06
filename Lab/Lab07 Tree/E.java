import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;


public class Main {

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    static class Task {

        public void solve(InputReader in, PrintWriter out) {
            int T = in.nextInt();
            for (int i = 0; i < T; i++) {
                int N = in.nextInt();
                long ans = 0;
                ArrayList<Long> sticks = new ArrayList<>();
                for (int j = 0; j < N; j++) {
                    sticks.add((long) in.nextInt());
                }
                Collections.sort(sticks);
                long a1, a2;
                while (true) {
                    if(sticks.size() == 0 || sticks.size() == 1){
                        break;
                    }
                    a1 = sticks.get(0);
                    a2 = sticks.get(1);
                    long sum = a1 + a2;
                    ans += sum;
                    sticks.remove(0);
                    sticks.remove(0);
                    if (sticks.size() == 0) {
                        break;
                    } else {

                        if (sticks.get(sticks.size() - 1) <= sum) {
                            sticks.add(sum);
                        }else if(sticks.get(0) > sum){
                            sticks.add(0,sum);
                        } else {
                            int k = searchInsert(sticks, sum);
                            sticks.add(k, sum);
                        }

                    }
                }
                out.println(ans);
            }


        }
    }


    public static int searchInsert(ArrayList<Long> arr, long target) {
        int left = 0;
        int right = arr.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr.get(mid) == target) {
                return mid;
            } else if (arr.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
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

