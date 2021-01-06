/*
【拓扑排序】
用优先队列（由于这里要保证字典序，所以是用优先队列对val进行升序排序，否则用队列即可）
1.先把入度≤1的点放到队列里
2.弹出队列头
3.把队列头指向的点的入度都-1
4.如果此时指向的点入度≤1，那就扔进去
5.重复2，3，4直到队列清空
 */

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
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
            int n = in.nextInt();//段落的数量-点
            int m = in.nextInt();//限定的数量-边
            Node[] paragraphs = new Node[n];

            //新建点
            for (int i = 0; i < n; i++) {
                paragraphs[i] = new Node(i + 1);
            }
            //做边处理
            for (int i = 0; i < m; i++) {
                int a = in.nextInt();
                int b = in.nextInt();
                paragraphs[a - 1].outNode.add(paragraphs[b - 1]);
                paragraphs[b - 1].inDegree++;
            }

            PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o1.val - o2.val;
                }
            });

            for (int i = 0; i < n; i++) {
                if (paragraphs[i].inDegree == 0) {
                    queue.add(paragraphs[i]);
                    paragraphs[i].inQueue = true;
                }
            }

            int count = 0;
            Node popOut;
            while (queue.size() != 0) {
                popOut = queue.poll();
                for (int i = 0; i < popOut.outNode.size(); i++) {
                    popOut.outNode.get(i).inDegree--;
                    if ((!popOut.outNode.get(i).inQueue) && (popOut.outNode.get(i).inDegree == 0)) {
                        queue.add(popOut.outNode.get(i));
                        popOut.outNode.get(i).inQueue = true;
                    }

                }
                count++;
                if(count == n){
                    out.print(popOut.val);
                }else {
                    out.print(popOut.val + " ");
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
    int val;//文章段落
    boolean inQueue = false;//是否已经把Node放进队列里面
    int inDegree = 0;//入度
    ArrayList<Node> outNode = new ArrayList<>();//指向的边

    public Node(int val) {
        this.val = val;
    }

}
