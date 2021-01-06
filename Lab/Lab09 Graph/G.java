
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentLinkedQueue;


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

    static class Node {


        long b;
        long maxCount;
        int inDegree;//入度
        ArrayList<Integer> outNode = new ArrayList<>();

        public Node(int a, int b) {
            this.b = b;
            this.maxCount = a;
        }
    }

    static class Task {

        public void solve(InputReader in, PrintWriter out) {

            int T = in.nextInt();
            int MOD = 1000000007;
            long ans;
            ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();//存每一个Node的位置的queue
            int n;
            int m;
            Node[] nodes;


            for (int i = 0; i < T; i++) {

                n = in.nextInt();//节点数量
                m = in.nextInt();//边数量

                //存各类节点的链表
                nodes = new Node[n];

                //Node和a和b的配置
                for (int j = 0; j < n; j++) {
//                    int a = in.nextInt();
//                    int b = in.nextInt();
//                    nodes[j] = new Node(a, b);
                    nodes[j] = new Node(in.nextInt(),in.nextInt());
                }


                //路的连接，基本配置
                for (int j = 0; j < m; j++) {
                    //从Node u 指向 Node v
                    int u = in.nextInt();
                    int v = in.nextInt();

                    nodes[u - 1].outNode.add(v - 1);
                    nodes[v - 1].inDegree++;
                }



                //入度为0的都加入队列
                for(int j = 0; j < nodes.length; j++){
                    if(nodes[j].inDegree == 0){
                        queue.add(j);
                    }
                }


                ArrayList<Integer> outNodeList;//每一个u的邻接链表
                Node u;
                Node v;
                ans = 0;
                while (!queue.isEmpty()) {
                    int uPos = queue.poll();
                    u = nodes[uPos];//弹出一个节点
                    outNodeList = u.outNode;
                    for (Integer vPos : outNodeList) {
                        v = nodes[vPos];
                        v.inDegree--;
                        v.maxCount = (v.maxCount + u.maxCount) % MOD;
                        if (v.inDegree == 0) {
                            queue.add(vPos);
                        }
                        ans = (ans + u.maxCount * v.b) % MOD;
                    }
                }
                out.println(ans % MOD);
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




