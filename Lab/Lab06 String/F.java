
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
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
            Map<String,String> chipher = new HashMap<>();
            chipher.put(in.next(),"a");
            chipher.put(in.next(),"b");
            chipher.put(in.next(),"c");
            chipher.put(in.next(),"d");
            chipher.put(in.next(),"e");
            chipher.put(in.next(),"f");
            chipher.put(in.next(),"g");
            chipher.put(in.next(),"h");
            chipher.put(in.next(),"i");
            chipher.put(in.next(),"j");
            chipher.put(in.next(),"k");
            chipher.put(in.next(),"l");
            chipher.put(in.next(),"m");
            chipher.put(in.next(),"n");
            chipher.put(in.next(),"o");
            chipher.put(in.next(),"p");
            chipher.put(in.next(),"q");
            chipher.put(in.next(),"r");
            chipher.put(in.next(),"s");
            chipher.put(in.next(),"t");
            chipher.put(in.next(),"u");
            chipher.put(in.next(),"v");
            chipher.put(in.next(),"w");
            chipher.put(in.next(),"x");
            chipher.put(in.next(),"y");
            chipher.put(in.next(),"z");
            String string = in.next();
            StringBuilder stringNew = new StringBuilder();
            for(int i = 0; i < string.length(); i++){
                stringNew.append(chipher.get(String.valueOf(string.charAt(i))));
            }
            StringBuilder nextString = stringNew.append(string);
            int[] next = nextArray(nextString.toString());
            out.println(string.length() - next[next.length - 1]);


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

