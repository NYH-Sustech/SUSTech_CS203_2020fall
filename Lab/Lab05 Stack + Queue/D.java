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

            int T = in.nextInt();
            for (int i = 0; i < T; i++) {

                int n = in.nextInt();
                long[] upcomingDays = new long[n];
                for (int j = 0; j < upcomingDays.length; j++) {
                    upcomingDays[j] = in.nextLong();
                }
                MyLinkedListStack stack = new MyLinkedListStack();
                long[] queryDays = new long[n];
                for (int j = upcomingDays.length - 1; j >= 0; j--) {

                    //栈是空的
                    if (stack.size == 0) {
                        stack.enqueue(upcomingDays[j], j);
                        queryDays[j] = -1;

                    } else {
                        while (true) {
                            if (stack.top == null) {
                                stack.enqueue(upcomingDays[j], j);
                                queryDays[j] = -1;
                                break;
                            }
                            if (stack.top.value > upcomingDays[j]) {
                                queryDays[j] = stack.top.index - j;
                                stack.enqueue(upcomingDays[j], j);
                                break;
                            } else if (stack.top.value <= upcomingDays[j]) {
                                stack.dequeue();
                            }
                        }
                    }

                }

                int q = in.nextInt();
                for(int j = 0; j < q; j++){
                    int day = in.nextInt();
                    out.println(queryDays[day - 1]);
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

}

class Node {
    long value;//值
    long index;//第几天
    Node prev;
    Node next;

    public Node(long value, long index) {
        this.value = value;
        this.index = index;
    }
}

class MyLinkedListStack {

    long size;
    Node top;


    //入队
    public void enqueue(long value, long index) {

        Node newNode = new Node(value, index);
        if (!isEmpty()) {
            newNode.prev = top;
            top.next = newNode;
        }
        top = newNode;
        size++;
    }


    //离队
    public Node dequeue() {

        if (isEmpty()) {
            return null;
        } else if (size == 1) {
            Node dequeueNode = top;
            top = null;
            size--;
            return dequeueNode;
        } else {
            Node dequeueNode = top;
            top = top.prev;
            dequeueNode.prev = null;
            top.next = null;
            size--;
            return dequeueNode;
        }

    }

    public boolean isEmpty() {
        return size == 0;
    }


}


