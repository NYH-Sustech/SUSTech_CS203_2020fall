import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

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
            int n = in.nextInt();
            for(int p = 0; p < n; p++){
                int len = in.nextInt();
                int[] arrSSA = new int[len];
                int[] arrISA = new int[len];
                for(int j = 0; j < arrSSA.length; j++){
                    arrSSA[j] = in.nextInt();
                    arrISA[j] = arrSSA[j];
                }


                long SSAtime = 0;
                for(int i = 0; i < arrSSA.length - 1; i++){
                    int k = i;

                    for(int j = i + 1; j < arrSSA.length; j++){
                        SSAtime++;//if判断复杂度
                        if(arrSSA[k] > arrSSA[j]){
                            k = j;
                        }

                    }
                    SSAtime++;//swap复杂度
                    int temp = arrSSA[i];
                    arrSSA[i] = arrSSA[k];
                    arrSSA[k] = temp;


                }


                long ISAtime = 0;
                for(int i = 1; i < arrISA.length; i++){
                    for(int j = i; j >= 1; j--){
                        ISAtime++;//if判断复杂度
                        if(arrISA[j - 1] > arrISA[j]) {
                            ISAtime++;//swap复杂度
                            int temp = arrISA[j];
                            arrISA[j] = arrISA[j - 1];
                            arrISA[j - 1] = temp;
                        }else {
                            break;
                        }
                    }
                }

                for(int j = 0; j < arrISA.length; j++){
                    if(j == arrISA.length - 1){
                        out.println(arrISA[j]);
                    }else {
                        out.print(arrISA[j] + " ");
                    }
                }

                if(SSAtime < ISAtime){
                    out.println("Selection Sort wins!");
                }else if (SSAtime > ISAtime){
                    out.println("Insertion Sort wins!");
                }else {
                    break;
                }

            }

        }
    }


    //selection sort algorithm
    public static int[] SSA(int[] arr,int count){
        for(int i = 0; i < arr.length - 1; i++){
            int k = i;
            for(int j = i + 1; j < arr.length; j++){
                if(arr[k] > arr[j]){
                    k = j;
                }
            }
            int temp;
            temp = arr[i];
            arr[i] = arr[k];
            arr[k] = temp;
        }
        return arr;
    }

    //insertion sort algorithm
    public static int[] ISA(int[] arr,int count){
        for(int i = 1; i < arr.length; i++){
            for(int j = i; j >= 1; j--){
                if(arr[j - 1] > arr[j]){
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
        return arr;
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

