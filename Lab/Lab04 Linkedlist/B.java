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
                int n = in.nextInt();

                String vinus = in.next();

                Node start = new Node();
                Node EOR = new Node();
                start.value = -2;
                start.next = EOR;
                EOR.value = -1;
                EOR.prev = start;
                Node p = EOR;
                for(int j = 0; j < n; j++){
                    if(Character.isDigit(vinus.charAt(j))){
                        p = addElement(p , Integer.parseInt(String.valueOf(vinus.charAt(j))));
                    }else {
                        switch (vinus.charAt(j)) {
                            case 'I':
                                p = start.next;
                                break;
                            case 'H':
                                p = H(p);
                                break;
                            case 'L':
                                p = L(p);
                                break;
                            case 'x':
                                p = x(p);
                                break;
                            case 'r':
                                if (j==n-1){
                                    break;
                                }
                                p = r(p, Integer.parseInt(String.valueOf(vinus.charAt(j + 1))));
                                j++;
                                break;
                        }
                    }
                }


                while (start.next.value != -1) {
                    start = start.next;
                    out.print(start.value);
                }


                out.println();
            }
        }
    }


    static class Node {
        private long value;//值
        private Node next = null;//下一个Node的地址
        private Node prev = null;//上一个Node的地址
    }


    //      |
    //START 1 2 3 4 5 6 EOR      add 8
    //        |
    //START 8 1 2 3 4 5 6 EOR

    //          |
    //START 1 2 3 4 5 6 EOR
    //      |
    //      1
    //指针返回第一个Node的位置，下一个添加的元素将在第一个元素之前
    private static Node I(Node p){
        while(true){
            if(p.prev.value == -2){
                return p;
            }
            p = p.prev;
        }
    }

    //       |
    //头 1 2 3 4 5 6 尾   r6
    //                       7
    //         |
    //头 1 2 7 3 4 5 6 尾
    //       |
    //头 1 2 6 4 5 6 尾

    //头 2 2 3 4 5 6 尾

    //头 尾
    //   |
    //头 1 尾巴
    //      |
    //头 1 尾巴
    //      |
    //头 1  1  尾巴
    //头 尾巴

    //todo 考虑第一个为r的情况
    private static Node r(Node p, int changeValue){
        //todo 指针在EOR的情况
        if(p.value == -1){
            Node Start = p.prev;
            Node newNode = new Node();
            Start.next = newNode;
            newNode.prev = Start;
            newNode.value = changeValue;
            newNode.next = p;
            p.prev = newNode;
            return newNode;
        }
        p.value = changeValue;
        return p;
    }

    private static Node H(Node p){
        if (p.prev.value != -2) {
            p = p.prev;
        }
        return p;
    }

    private static Node L(Node p){
        if (p.value != -1) {
            p = p.next;
        }
        return p;

    }



    //           |   |
    // 1 2 3 4 5 6 尾巴
    //          |
    // 1 2 3 4 5 尾巴
    //     |
    // 1 2 3 4 5 尾巴
    //     |
    // 1 2 4 5 尾巴
    private static Node x(Node p){
        //尾巴
        if(p.value == -1){
            return p;
        }else{
            Node last = p.next;
            p = p.prev;
            p.next = last;
            last.prev = p;
            return last;
        }
    }

    //  |
    //1 2 3 4 5

    //  |
    //1 EOR
    //    |
    //1 1 2 3 4 5


    //Start EOR
    private static Node addElement(Node p, int addValue){
        Node returnNode = p;
        p = p.prev;
        Node newNode = new Node();
        newNode.prev = p;
        newNode.value = addValue;
        newNode.next = returnNode;
        returnNode.prev = newNode;
        p.next= newNode;
        return returnNode;
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

