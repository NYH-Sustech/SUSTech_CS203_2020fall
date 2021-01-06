
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
            int n = in.nextInt();//网格的行数
            int m = in.nextInt();//网格的列数

            //建立网格
            Node[][] Grid = new Node[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    double height = in.nextDouble();
                    Grid[i][j] = new Node(height, i, j);
                }
            }

            //建立边
            Edge.EdgeBuild(Grid);
            //建立Dijkstra算法
            Dijkstra dijkstra = new Dijkstra(Grid,Grid[0][0],Grid[n-1][m-1]);
            dijkstra.Algorithm();
            out.println(String.format("%.2f", dijkstra.findMinTimeToDst()));
            
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
    double height = 0;//每座山的高度
    ArrayList<Edge> edges = new ArrayList<>();//出边
    boolean visited = false;

    int x;//网格中的横坐标
    int y;//网格中的纵坐标

    double velocity;//到当前山的速度


    public Node(double height, int x, int y) {
        this.height = height;
        this.x = x;
        this.y = y;
    }
}


//有向边，带边权
class Edge {

    //u -> v
    Node u;
    Node v;
    double weight;
    boolean visited = false;


    public Edge(Node u, Node v) {
        this.u = u;
        this.v = v;
        this.weight = u.height - v.height;
    }


    public static void EdgeBuild(Node[][] Grid) {


        int n = Grid.length;
        int m = Grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                //左
                try {
                    Edge edge1 = new Edge(Grid[i][j], Grid[i][j - 1]);
                    Grid[i][j].edges.add(edge1);
                } catch (Exception ignored) {
                }
                //上
                try {
                    Edge edge2 = new Edge(Grid[i][j], Grid[i - 1][j]);
                    Grid[i][j].edges.add(edge2);
                } catch (Exception ignored) {
                }
                //下
                try {
                    Edge edge3 = new Edge(Grid[i][j], Grid[i + 1][j]);
                    Grid[i][j].edges.add(edge3);
                } catch (Exception ignored) {
                }
                //右
                try {
                    Edge edge4 = new Edge(Grid[i][j], Grid[i][j + 1]);
                    Grid[i][j].edges.add(edge4);
                } catch (Exception ignored) {
                }


            }
        }


    }

}


//寻找有向带权的最短路径
class Dijkstra {

    Node src;
    Node dst;


    //因为传进来的是网格，所以用二维数组表示dist(v)和parent(v)
    ArrayList<Node> S = new ArrayList<>();
    Node[][] Vertex;
    double[][] dist;
    Node[][] parent;


    //初始化
    public Dijkstra(Node[][] Graph, Node src, Node dst) {
        //基本初始化
        this.Vertex = Graph;
        this.src = src;
        this.dst = dst;


        //把所有的Node加入到Set里面
        for (Node[] nodes : Graph) {
            S.addAll(Arrays.asList(nodes).subList(0, Graph[0].length));
        }

        //建立dist(v)，初始化全部设置成无穷
        dist = new double[Graph.length][Graph[0].length];
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist[0].length; j++) {
                dist[i][j] = Double.MAX_VALUE;
            }
        }

        //建立parent(v)，初始化全部设置成null
        parent = new Node[Graph.length][Graph[0].length];
    }


    public void Algorithm() {
        // Repeat the following until S is empty
        dist[src.x][src.y] = 0;
        src.velocity = 1;



        while (!S.isEmpty()) {
            // Remove from S the vertex u with the smallest dist(u).
            Node u = S.get(0);

            for (Node node : S) {
                if (dist[node.x][node.y] < dist[u.x][u.y]) {
                    u = node;
                }
            }
            S.remove(u);

            // For every outgoing edge (u,v) of u
            // If dist(v) > dist(u) + w(u,v) then
            // Set dist(v) = dist(u) + w(u,v), and parent (v)=u
            for (int i = 0; i < u.edges.size(); i++) {

                Edge uv = u.edges.get(i);
                //找到u的出边的对面那个元素v
                Node v;
                if (u != u.edges.get(i).v) {
                    v = u.edges.get(i).v;
                } else {
                    v = u.edges.get(i).u;
                }


                //这里权重是花的时间
                //需要按照题意对时间进行处理
                v.velocity = u.velocity * Math.pow(2,uv.weight);
                double uv_time = 1 / u.velocity;
                double uv_weight = uv_time;


                if (dist[v.x][v.y] > dist[u.x][u.y] + uv_weight) {
                    dist[v.x][v.y] = dist[u.x][u.y] + uv_weight;
                    parent[v.x][v.y] = u;
                }


            }


        }


    }


    public double findMinTimeToDst() {
        return dist[dst.x][dst.y];
    }


}
