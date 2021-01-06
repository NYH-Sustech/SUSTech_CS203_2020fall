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

            int testCase = in.nextInt();

            for(int i = 0; i < testCase; i++){
                int Alen = in.nextInt();
                int Blen = in.nextInt();
                long[] A = new long[Alen];
                long[] B = new long[Blen];
                for(int j = 0; j < A.length; j++){
                    A[j] = in.nextLong();
                }
                for(int j = 0; j < B.length; j++){
                    B[j] = in.nextLong();
                }

                long[] C = Merge2(A,B);
                for(int j = 0; j < C.length; j++){
                    if(j == C.length - 1){
                        out.print(C[j]);
                    }else {
                        out.print(C[j] + " ");
                    }
                }
                out.println();


            }


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

    public static long[] Merge2(long[] A, long[]B){
        int nL = A.length;
        int nR = B.length;
        long[] returnList = new long[A.length + B.length];
        int countA = 0;
        int countB = 0;
        for(int i = 0; i < returnList.length; i++){
            if((countA <= nL - 1) && (countB > nR - 1 || A[countA] <= B[countB])){
                returnList[i] = A[countA];
                countA++;
            }else{
                returnList[i] = B[countB];
                countB++;
            }
        }
        return returnList;
    }

}
