
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
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
            String s1 = in.next();
            String s2 = in.next();


            //s1长的那个，s2短的那个
            if (s1.length() < s2.length()) {
                String temp = s1;
                s1 = s2;
                s2 = temp;
            }

            int min = 0;
            int max = s2.length();
            int mid;
            while (true) {
                mid = min + (max - min) / 2;
                if (RabinKarp(s1, s2, mid, 139)) {
                    min = mid;
                } else {
                    max = mid;
                }
                if (min + 1 == max) {
                    break;
                }
            }
            if(RabinKarp(s1, s2, max, 139)){
                out.println(max);
            }else {
                out.println(min);
            }


        }
    }

    /**
     *
     * @param T 长的字符串
     * @param P 短的字符串
     * @param m 窗口大小
     * @param d 进制
     * @return
     */
    public static boolean RabinKarp(String T, String P, int m, int d) {
        HashMap<Long, Long> hashMap = new HashMap<>();
        int n = T.length();
        long p = 0;
        long t = 0;
        long h = 1;

        //h的构建
        for (int i = 0; i < m - 1; i++)
            h = h * d;

        //计算初始的p和t0
        for (int i = 0; i < m; i++) {
            p = (d * p + P.charAt(i));
            t = (d * t + T.charAt(i));
        }

        int j;
        hashMap.put(t, (long) 0);
        for (int i = 0; i <= n - m; i++) {
            //相同，顺序比较
            if (p == t) {
                for (j = 0; j < m; j++)
                    if (T.charAt(i + j) != P.charAt(j))
                        break;
                //完全相同    
                if (j == m)
                    return true;
            }
            //不相同，将不同的t扔进HashMap里面
            if (i < n - m) {
                t = (d * (t - T.charAt(i) * h) + T.charAt(i + m));
                hashMap.put(t, (long) (i + 1));
            }
        }

        for (int i = 0; i < P.length() - m; i++) {
            p = (d * (p - P.charAt(i) * h) + P.charAt(i + m));
            //相同，进去比较
            if (hashMap.containsKey(p)) {
                for (j = 0; j < m; j++) {
                    int pos = Math.toIntExact(hashMap.get(p));
                    if (T.charAt(pos + j) != P.charAt(j + i + 1))
                        break;
                }
                if (j == m) {
                    return true;
                }
            }
        }
        return false;
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

