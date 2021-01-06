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

        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            String[] strings = new String[n + 1];
            for (int k = 1; k < strings.length; k++) {
                strings[k] = in.next();
            }
            int q = in.nextInt();
            int trueAns = 0;//有多少个回答正确的题
            //O(q)
            for (int k = 0; k < q; k++) {
                int i = in.nextInt();
                int j = in.nextInt();
                String himJudge = in.next();
                if (maximumI(strings[i], strings[j]) > maximumI(strings[j], strings[i]) && himJudge.equals(">")) {
                    trueAns++;
                } else if (maximumI(strings[i], strings[j]) == maximumI(strings[j], strings[i]) && himJudge.equals("=")) {
                    trueAns++;
                } else if (maximumI(strings[i], strings[j]) < maximumI(strings[j], strings[i]) && himJudge.equals("<")) {
                    trueAns++;
                }
            }
            out.println(trueAns);


        }
    }


    
    //O(n)
    public static int maximumI(String s, String t) {
        int max = Math.min(s.length(), t.length());//答案不可能超过这个值
        while (true) {
            if (s.substring(0, max).equals(t.substring(t.length() - max))) {
                return max;
            }
            max--;
        }
    }
    
    public static int[] nextArray(String P) {
        int m = P.length();
        int[] next = new int[m];
        next[0] = 0;
        int k = 0;
        int j = 1;
        while (j < m) {
            if (P.charAt(j) == P.charAt(k)) {
                k++;
                next[j] = k;
                j++;
            } else if (k == 0) {
                next[j] = 0;
                j++;
            } else {
                k = next[k - 1];
            }
        }
        return next;
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

