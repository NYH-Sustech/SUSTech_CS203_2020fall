import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;


public class Main{

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

            int T = in.nextInt();
            for(int i = 0; i < T; i++){

                long n = in.nextInt();//多少盘菜
                long k = in.nextInt();//吃第k盘菜

                Node p = createLinkList(n);
                //7个node 删掉第4个

                Node deleteValue = null;
                boolean flag = false;
                while(true){
                    if(p.next.equals(p)){
                        out.println(p.value);
                        break;
                    }
                    if(k != 1){
                        if(!flag){
                            for (int j = 1; j < k - 1; j++) {
                                p = p.next;
                            }
                            deleteValue = p.next;
                            p.next = p.next.next;
                            out.print(deleteValue.value + " ");
                            flag = true;
                        }else {
                            for (int j = 1; j < k; j++) {
                                p = p.next;
                            }
                            deleteValue = p.next;
                            p.next = p.next.next;
                            out.print(deleteValue.value + " ");
                        }
                    }else{
                        if(p.value == n){
                            out.println(p.value);
                            break;
                        }
                        out.print(p.value + " ");
                        p = p.next;
                    }




                    //此时的p是了





                }





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

    static class Node {
        private long value;//值
        private Node next;//下一个Node的地址
    }


    private static Node createLinkList(long n){
        Node p = new Node();
        Node A = p;
        for(long i = 0; i < n; i++){
            p.value = i + 1;
            p.next = new Node();
            if(i == n - 1){
                p.next = A;
            }
            p = p.next;
        }
        return A;
    }



    private static Node deleteKthNodeValue(Node A, long k){
        //只剩A一个元素了
        if(A.next == null){
            return null;
        }

        Node p = A;
        long count = 0;
        while(true){
            if(count < k - 1){
                p = p.next;
                count++;
            }else if(count == k - 1){
                p.next = p.next.next;
                return A;
            }
            //到了尽头了
            if(p.next == null){
                return A;
            }
        }

    }



}
