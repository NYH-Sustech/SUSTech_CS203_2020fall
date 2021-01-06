import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.LinkedBlockingDeque;


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
            int m = in.nextInt();//有向路的数量

            //创建每一个城市
            Node[] cities = new Node[n];
            for(int i = 0; i < n; i++){
                cities[i] = new Node();
            }

            Node Pisces = cities[0];//Pisces所在的城市
            Node Princess = cities[cities.length - 1];//公主所在的城市

            //完成道路的连接
            for(int i = 0; i < m; i++){
                int u = in.nextInt();
                int v = in.nextInt();
                int w = in.nextInt();

                switch (w){
                    case 1:
                        cities[u - 1].outNode.add(cities[v - 1]);
                        break;
                    //当权重为2的时候，直接增加一个Node作为补充你
                    case 2:
                        Node midCity = new Node();
                        cities[u - 1].outNode.add(midCity);
                        midCity.outNode.add(cities[v - 1]);
                        break;
                }

            }

            //以Pisces的位置作为root进行BFS搜索
            BFS_Tree tree = new BFS_Tree(Pisces);
            tree.BFS_Search();
            //直接输出公主的深度
            out.println(Princess.depth);

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

class Node{

    int depth = -1;//在BFS_tree中的深度，-1代表无法访问
    Color color = Color.WHITE;//状态
    ArrayList<Node> outNode = new ArrayList<>();//出节点
    ArrayList<Node> childNode = new ArrayList<>();//BFS_Tree中的子节点

}

class BFS_Tree{
    Node root;
    Queue<Node> Q = new LinkedBlockingDeque<>();//维护的队列

    public BFS_Tree(Node root){
        this.root = root;
        root.depth = 0;
        Q.add(root);
        root.color = Color.YELLOW;
    }

    public void BFS_Search(){

        //Repeat the following until Q is empty
        while(Q.size() != 0){
            //Dequeue from Q the first vertex v
            Node v = Q.poll();
            for(int i = 0; i < v.outNode.size(); i++){
                Node u = v.outNode.get(i);
                //For every out-neighbour u of v that is still white
                if(u.color == Color.WHITE){
                    //Enqueue u into Q, and color u yellow
                    u.color = Color.YELLOW;
                    Q.add(u);
                    //Make u a child of v in the BFS tree
                    u.childNode.add(u);
                    u.depth = v.depth + 1;
                }
            }
            v.color = Color.RED;

        }
    }

    public static int returnDepth(Node node){
        return node.depth;
    }


}

//class DFS_Tree {
//    Node root;
//    Stack<Node> S = new Stack<>();//维护的栈
//    ArrayList<Node> L = new ArrayList<>();//拓扑排序所用的列表
//
//
//    public DFS_Tree(Node root) {
//        this.root = root;
//        S.push(root);
//        root.color = Color.YELLOW;
//    }
//
//
//    //深度优先搜索的实现
//    //因为构造器传入root，所以初始状态栈内必然有一个Node
//    public void DFS_Search() {
//
//        //Let v be the vertex that currently tops of the stack S
//        //Do not remove v from S
//        assert S.size() != 0;
//        Node v = S.peek();
//
//        //Repeat the following until S is empty
//        while (S.size() != 0) {
//
//            //Does v still have a white out-neighbour
//            boolean hasWhiteOutEdge = false;
//            Node u = null;
//            for(int i = 0; i < v.outEdge.size(); i++){
//                if(v.outEdge.get(i).color == Color.WHITE){
//                    hasWhiteOutEdge = true;
//                    u = v.outEdge.get(i);
//                    break;
//                }
//            }
//
//            if (hasWhiteOutEdge) {
//                //If yes: let it be u
//                //Push u into S, and color u YELLOW
//                S.push(u);
//                u.color = Color.YELLOW;
//                //Make u a child of v in the DFS-tree T
//                v.childNodes.add(u);
//                v = u;
//                //If no, pop v from S, and color v red(meaning v is visited)
//            } else {
//                v = S.pop();
//                L.add(v);
//                v.color = Color.RED;
//                if(S.size() != 0) {
//                    v = S.peek();
//                }
//            }
//        }
//
//    }
//
//    public ArrayList<Node> topologicalSort() {
//        Collections.reverse(L);
//        return L;
//    }
//
//
//}
//


/**
 * -WHITE：尚未访问
 * -YELLOW：已经访问且在栈内
 * -RED：已经访问且出栈
 */
//这是老师课上讲的访问
enum Color {
    RED, YELLOW, WHITE;
}
