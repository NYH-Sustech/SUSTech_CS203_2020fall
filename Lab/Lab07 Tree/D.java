
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
            int T = in.nextInt();
            for (int i = 0; i < T; i++) {
                int N = in.nextInt();
                int[] pre = new int[N];
                for (int j = 0; j < pre.length; j++) {
                    pre[j] = in.nextInt();
                }
                int[] mid = new int[N];
                for (int j = 0; j < N; j++) {
                    mid[j] = in.nextInt();
                }
                Node head = buildTree(pre, 0, pre.length - 1, mid, 0, mid.length - 1);
                String outPut = postOrder(head);
                out.println(outPut.substring(0,outPut.length() - 1));

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


    public static Node buildTree(int[] pre, int begin1, int end1, int[] mid, int begin2, int end2) {
        if (begin1 > end1 || begin2 > end2) {
            return null;
        }
        int rootData = pre[begin1];
        Node head = new Node(rootData);
        int rootIndex = findIndexInArray(mid, rootData, begin2, end2);

        Node left = buildTree(pre, begin1 + 1, begin1 + rootIndex - begin2, mid, begin2, rootIndex - 1);
        Node right = buildTree(pre, begin1 + rootIndex - begin2 + 1, end1, mid, rootIndex + 1, end2);
        head.prev = left;
        head.next = right;

        return head;
    }



    public static int findIndexInArray(int[] a, int x, int begin, int end) {
        for (int i = begin; i <= end; i++) {
            if (a[i] == x) {
                return i;
            }
        }
        return -1;
    }


    //后序遍历方法递归实现
    public static String postOrder(Node localRoot) {
        String outPut = "";
        if (localRoot != null) {
            outPut += postOrder(localRoot.prev);
            outPut += postOrder(localRoot.next);
            outPut += localRoot.value + " ";
            //System.out.print(localRoot.value + " ");
        }
        return outPut;
    }


}

class Node {
    Node prev;
    Node next;
    int value;

    public Node(int value) {
        this.value = value;
    }


}




