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
            MyLinkedListQueue q1 = new MyLinkedListQueue(2000000);
            for (int i = 0; i < n; i++) {
                switch (in.next().charAt(0)) {
                    case 'E':
                        q1.enqueue(in.nextInt());
                        break;
                    case 'D':
                        q1.dequeue();
                        break;
                    case 'A':
                        out.println(q1.peakHead());
                        break;
                }
            }
            Node p = q1.head;
            while (true) {
                if (q1.getSize() == 0) {
                    break;
                } else if (p == q1.tail) {
                    out.println(p.value);
                    break;
                }
                out.print(p.value + " ");
                p = p.prev;
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
    Node prev;
    Node next;
    Object value;
}

class MyLinkedListQueue {

    private int MAX_SIZE;
    private int size;
    Node head;//头指针
    Node tail;//尾指针


    public MyLinkedListQueue(int MAX_SIZE) {
        this.size = 0;
        this.MAX_SIZE = MAX_SIZE;
        Node head = new Node();
        this.head = head;
        this.tail = head;
        Node p = head;
        for (int i = 1; i < MAX_SIZE; i++) {
            Node i1 = new Node();
            p.next = i1;
            i1.prev = p;
            if (i == MAX_SIZE - 1) {
                i1.next = head;
                head.prev = i1;
                break;
            }
            p = p.next;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean ifFull() {
        return size == MAX_SIZE;
    }

    public boolean enqueue(Object i) {
        if (ifFull()) {
            return false;
        } else if (isEmpty()) {
            tail.value = i;
            size++;
            return true;
        } else {
            tail = tail.prev;
            tail.value = i;
            size++;
            return true;
        }
    }

    public Object dequeue() {
        if (isEmpty()) {
            return null;
        } else if (size == 1) {
            Object returnValue = head.value;
            head.value = null;
            size--;
            return returnValue;
        } else {
            Object returnValue = head.value;
            head.value = null;
            head = head.prev;
            size--;
            return returnValue;
        }
    }

    public Object peakHead() {
        return head.value;
    }

    public Object peakTail() {
        return tail.value;
    }


    public void printQueue() {
        System.out.println("————————————————————");
        System.out.println("Queue");
        Node p = head;
        while (true) {
            if (p == tail) {
                System.out.println(p.value);
                break;
            }
            System.out.print(p.value + "→");
            p = p.prev;
        }
        System.out.println("————————————————————");
    }


    public int getMAX_SIZE() {
        return MAX_SIZE;
    }

    public int getSize() {
        return size;
    }


}

