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

        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();//房间
            int m = in.nextInt();//门


            //Step 1: obtain the reverse graph GR by reversing the directions of all the edges in G.
            Node[] Graph = new Node[n];
            Node[] Graph_reverse = new Node[n];//反向连接的所有Node
            for (int i = 0; i < n; i++) {
                Graph[i] = new Node(i + 1);
                Graph_reverse[i] = new Node(i + 1);
            }
            //道路的连接
            for (int i = 0; i < m; i++) {
                int u = in.nextInt();//u → v
                int v = in.nextInt();
                Graph[u - 1].outEdge.add(Graph[v - 1]);
                Graph_reverse[v - 1].outEdge.add(Graph_reverse[u - 1]);
            }

            // Step 2: Perform DFS on GR
            // and obtain the sequence LR that the vertices in GR turn red
            // (i.e., whenever a vertex is popped out of the stack, append it to LR )
            DFS_Tree tree1 = new DFS_Tree(Graph_reverse[0]);
            tree1.DFS_Search();
            tree1.ReverseList_L();

            //Step 3: Perform DFS on the original graph G by obeying the following rules:
            //Rule 1: start the DFS at the first vertex of L
            int firstIndex = tree1.L.get(0).value;
            Node tree2root = null;
            for (Node node : Graph) {
                if (node.value == firstIndex) {
                    tree2root = node;
                }
            }
            //Rule 2: whenever a restart is needed, start from the first vertex of L that is still white.
            //Output the vertices in each DFS-tree as an SCC
            DFS_Tree tree2 = new DFS_Tree(tree2root);
            tree2.DFS_Search();
            if (tree1.L.size() == tree2.L.size() && tree1.L.size() == n) {
                out.println("Bravo");
            } else {
                out.println("wawawa");
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
    int value;
    boolean visited = false;
    //Color color = Color.WHITE;
    ArrayList<Node> outEdge = new ArrayList<>();


    public Node(int value) {
        this.value = value;
    }
}


class DFS_Tree {
    Node root;
    Stack<Node> S = new Stack<>();//维护的栈
    ArrayList<Node> L = new ArrayList<>();//拓扑排序所用的列表


    public DFS_Tree(Node root) {
        this.root = root;
        S.push(root);
        root.visited = true;
        //root.color = Color.YELLOW;
    }


    //深度优先搜索的实现
    //因为构造器传入root，所以初始状态栈内必然有一个Node
    public void DFS_Search() {

        //Let v be the vertex that currently tops of the stack S
        //Do not remove v from S
        assert S.size() != 0;
        Node v = S.peek();

        //Repeat the following until S is empty
        while (S.size() != 0) {

            //Does v still have a white out-neighbour
            boolean hasWhiteOutEdge = false;
            Node u = null;
            for (int i = 0; i < v.outEdge.size(); i++) {
                if (!v.outEdge.get(i).visited) {
                    hasWhiteOutEdge = true;
                    u = v.outEdge.get(i);
                    break;
                }
            }

            if (hasWhiteOutEdge) {
                //If yes: let it be u
                //Push u into S, and color u YELLOW
                S.push(u);
                u.visited = true;
                //Make u a child of v in the DFS-tree T
                v = u;
                //If no, pop v from S, and color v red(meaning v is visited)
            } else {
                v = S.pop();
                L.add(v);
                if (S.size() != 0) {
                    v = S.peek();
                }
            }
        }

    }
    
    
    public void ReverseList_L(){
        Collections.reverse(L);
    }


}
