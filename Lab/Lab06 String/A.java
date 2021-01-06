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

            //所有名字里面最小的那一个的长度
            int min = Integer.MAX_VALUE;
            String[] name = new String[n];
            for (int i = 0; i < n; i++) {
                name[i] = in.next();
                min = Math.min(name[i].length(), min);
            }
            int maxPrefix = 0;
            int maxSuffix = 0;
            flag:
            while (true) {
                if (maxPrefix == min) {
                    break;
                }
                for (int j = 0; j < name.length - 1; j++) {
                    if (name[j].charAt(maxPrefix) != name[j + 1].charAt(maxPrefix)) {
                        break flag;
                    }
                }
                maxPrefix++;
            }
            flag:
            while (true) {
                if (maxSuffix == min) {
                    break;
                }
                for (int j = name.length - 1; j >= 1; j--) {

                    if (name[j].charAt(name[j].length() - 1 - maxSuffix) != name[j - 1].charAt(name[j - 1].length() - 1 - maxSuffix)) {
                        break flag;
                    }
                }
                maxSuffix++;
            }
            out.println(maxPrefix * maxSuffix);
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

}

