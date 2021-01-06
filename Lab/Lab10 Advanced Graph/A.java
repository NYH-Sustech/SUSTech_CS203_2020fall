
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;


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
            int n = in.nextInt();//城市的数量
            int m = in.nextInt();//道路的数量

            ArrayList<Node> nodes = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                nodes.add(new Node(i + 1));
            }
            Edge smallestWeightEdge = null;
            ArrayList<Edge> edges = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                int u = in.nextInt();
                int v = in.nextInt();
                int w = in.nextInt();
                Edge edge = new Edge(nodes.get(u - 1), nodes.get(v - 1), w);
                nodes.get(u - 1).edges.add(edge);
                nodes.get(v - 1).edges.add(edge);
                edges.add(edge);

                //维护最小边权的一条边
                if (smallestWeightEdge == null){
                    smallestWeightEdge = edge;
                }else{
                    if(smallestWeightEdge.weight > edge.weight){
                        smallestWeightEdge = edge;
                    }
                }
            }

            SpanningTree tree = new SpanningTree(edges,nodes,smallestWeightEdge);
            tree.Span();
            out.println(tree.totalCost);



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


//最小生成树
class SpanningTree {

    ArrayList<Edge> edges;//所有的边的集合
    ArrayList<Node> nodes;//所有的点的集合
    int totalCost = 0;//最小生成树的总cost


    Set<Node> S = new HashSet<>();//是否已经在最小生成树里面的节点
    Edge lightExtensionEdge;//当前最短的出边


    //最小可拓展边 —> 用最小堆实现
    PriorityQueue<Edge> best_Ext = new PriorityQueue<>(new Comparator<Edge>() {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    });

    /**
     * @param edges              边哈希集合
     * @param nodes              节点哈希集合
     * @param smallestWeightEdge 最小的边权 —> 用于初始化
     */
    public SpanningTree(ArrayList<Edge> edges, ArrayList<Node> nodes, Edge smallestWeightEdge) {
        this.edges = edges;
        this.nodes = nodes;

        lightExtensionEdge = smallestWeightEdge;
        lightExtensionEdge.color = Color.RED;       //已经加入
        lightExtensionEdge.u.color = Color.RED;     //已经加入
        lightExtensionEdge.v.color = Color.RED;     //已经加入
        S.add(lightExtensionEdge.u);
        S.add(lightExtensionEdge.v);
        totalCost += lightExtensionEdge.weight;
    }


    //最小生成树的代价
    public int treeCost() {
        return totalCost;
    }


    public void Span() {


        while (S.size() != nodes.size()) {
            Node u = lightExtensionEdge.u;
            Node v = lightExtensionEdge.v;

            //加入当前u和v没有访问的所有边
            for (int i = 0; i < u.edges.size(); i++) {
                if( (u.edges.get(i).color != Color.RED) ){
                    best_Ext.add(u.edges.get(i));
                }
            }
            for (int i = 0; i < v.edges.size(); i++) {
                if( (v.edges.get(i).color != Color.RED)){
                    best_Ext.add(v.edges.get(i));
                }
            }

            while(true){
                Edge edge = best_Ext.poll();
                if(!S.contains(edge.v) || !S.contains(edge.u)){
                    lightExtensionEdge = edge;
                    break;
                }
            }
            totalCost += lightExtensionEdge.weight;
            lightExtensionEdge.color = Color.RED;
            u = lightExtensionEdge.u;
            v = lightExtensionEdge.v;
            S.add(u);
            S.add(v);
            u.color = Color.RED;
            v.color = Color.RED;


        }


    }


}

class Node {
    ArrayList<Edge> edges = new ArrayList<>();//邻接矩阵，无向图的边
    int index;//编号
    Color color = Color.WHITE;//初始尚未访问

    public Node(int index) {
        this.index = index;
    }


}

//无向图的边
class Edge {
    Node u;
    Node v;
    int weight;
    Color color = Color.WHITE;//边是否已经加入Spanning Tree

    public Edge(Node u, Node v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

}


enum Color {
    RED, WHITE;
}
