import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
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
        static long tempCity = 0;

        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();

            //处理一下，保证 a < b
            int a = in.nextInt();
            int b = in.nextInt();

            Node[] nodes = new Node[n];
            for (int i = 0; i < n; i++) {
                nodes[i] = new Node(i + 1);
            }
            Node A = nodes[a - 1];
            Node B = nodes[b - 1];
            A.visited = true;
            B.visited = true;

            boolean ab_connect = false;//判断ab是不是可以连接

            for (int i = 0; i < m; i++) {
                int u = in.nextInt();
                int v = in.nextInt();
                if ((nodes[u - 1] == A && nodes[v - 1] == B) || (nodes[u - 1] == B && nodes[v - 1] == A)) {
                    ab_connect = true;
                    continue;
                }
                nodes[u - 1].outEdge.add(nodes[v - 1]);
                nodes[v - 1].outEdge.add(nodes[u - 1]);

            }

            int cityBelongToA = 0;//只属于A城市的
            int cityBelongToB = 0;//只属于B城市的
            int cityBelongToA_B = 0;//同时属于A和B的城市

            for (int i = 0; i < n; i++) {
                tempCity = 0;
                if (!nodes[i].visited) {
                    ConnCountry currentCity = DFS_Search(nodes[i], A, B);
                    if (currentCity == ConnCountry.A) {
                        cityBelongToA += tempCity;
                    } else if (currentCity == ConnCountry.B) {
                        cityBelongToB += tempCity;
                    } else if (currentCity == ConnCountry.AB) {
                        cityBelongToA_B += tempCity;
                    }
                }
            }

            if (ab_connect || cityBelongToA_B != 0) {
                long ans = cityBelongToA * cityBelongToB;
                out.println(ans);
            } else {
                out.println(0);
            }


        }


        public static ConnCountry DFS_Search(Node root, Node A, Node B) {
            ConnCountry conn = null;
            root.visited = true;
            tempCity += 1;

            for (int i = 0; i < root.outEdge.size(); i++) {
                
                Node edgeNode = root.outEdge.get(i);
                if (edgeNode == A && (conn == ConnCountry.B || conn == ConnCountry.AB)) {
                    conn = ConnCountry.AB;
                } else if (edgeNode == A) {
                    conn = ConnCountry.A;
                } else if (edgeNode == B && (conn == ConnCountry.A || conn == ConnCountry.AB)) {
                    conn = ConnCountry.AB;
                }else if (edgeNode == B){
                    conn = ConnCountry.B;
                }

                if (!edgeNode.visited) {
                    ConnCountry tempConn = DFS_Search(edgeNode, A, B);
                    if ((conn == ConnCountry.A && tempConn == ConnCountry.B) || (conn == ConnCountry.B && tempConn == ConnCountry.A)) {
                        conn = ConnCountry.AB;
                    } else if (tempConn == ConnCountry.AB) {
                        conn = ConnCountry.AB;
                    } else if (conn == null) {
                        conn = tempConn;
                    }

                }

            }
            return conn;
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
    int value;
    ArrayList<Node> outEdge = new ArrayList<>();
    boolean visited = false;

    //BFS tree 中Node的孩子节点
    ArrayList<Node> childNodes = new ArrayList<>();

    public Node(int value) {
        this.value = value;
    }
}

enum ConnCountry {
    A, B, AB;
}
