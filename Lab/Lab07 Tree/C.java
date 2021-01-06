
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
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
            int T = in.nextInt();
            for (int i = 0; i < T; i++) {

                int N = in.nextInt();
                Node[] list = new Node[N + 1];
                StringBuilder outPut = new StringBuilder();
                for (int j = 0; j < list.length; j++) {
                    list[j] = new Node();
                }
                list[1].index = 1;
                for (int j = 2; j <= N; j++) {
                    int father = in.nextInt();
                    Node newNode = list[j];
                    newNode.index = j;
                    newNode.value = father;
                    list[father].subNotes.add(newNode);
                }
                MyLinkedListQueue queue = new MyLinkedListQueue();
                queue.enqueue(list[1]);
                while (true) {
                    if(queue.isEmpty()){
                        break;
                    }else {
                        Node dequeueNode = queue.dequeue();
                        if(dequeueNode.subNotes.size() == 0){
                            outPut.append(dequeueNode.index).append(" ");
                            continue;
                        }
                        for (int j = 0; j < dequeueNode.subNotes.size(); j++) {
                            queue.enqueue(dequeueNode.subNotes.get(j));
                        }
                        outPut.append(dequeueNode.index).append(" ");
                    }
                }
                String ans = outPut.substring(0,outPut.length() - 1);
                out.println(ans);
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
    Object index;
    ArrayList<Node> subNotes = new ArrayList<>();

    public Node(int index, int value) {
        this.index = index;
        this.value = value;
    }

    public Node() {

    }
}

class MyLinkedListQueue {

    int size;
    Node head;//头指针
    Node tail;//尾指针


    public MyLinkedListQueue() {
        this.size = 0;
        this.head = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public void enqueue(Node newNode) {
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public Node dequeue() {
        if (isEmpty()) {
            return null;
        } else if (size == 1) {
            Node returnNode = head;
            head = null;
            tail = null;
            size--;
            return returnNode;
        } else {
            Node returnNode = head;
            head = head.next;
            size--;
            return returnNode;

        }
    }


    public void setSize(int size) {
        this.size = size;
    }


    public void printQueue() {
        System.out.println("————————————————————");
        System.out.println("Queue");
        if (isEmpty()) {
            System.out.println("本queue目前为空");
            System.out.println("————————————————————");
            return;
        }
        System.out.print("head→");
        Node p = head;
        System.out.print(p.value + "→");
        while (p.next != null) {
            p = p.next;
            System.out.print(p.value + "→");
        }
        System.out.println("tail");
        System.out.println("————————————————————");
    }


    public int getSize() {
        return size;
    }


}
