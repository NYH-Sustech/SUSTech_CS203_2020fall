import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
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

                int n = in.nextInt();
                Node[] nodes = new Node[n];
                for (int j = 0; j < n; j++) {
                    nodes[j] = new Node(in.nextInt());
                }

                int fatherIndex;
                int childIndex;
                for (int j = 0; j < n - 1; j++) {
                    fatherIndex = in.nextInt() - 1;
                    childIndex = in.nextInt() - 1;
                    nodes[fatherIndex].child.add(nodes[childIndex]);
                    nodes[childIndex].father.add(nodes[fatherIndex]);
                }

                //找到满足条件的rootNode
                ArrayList<Node> rootNodes = new ArrayList<>();
                Node rootNode = null;
                for(int j = 0; j < n; j++){
                    if(nodes[j].father.size() == 0){
                        rootNodes.add(nodes[j]);
                    }
                }
                if(rootNodes.size() == 1) {
                    rootNode = rootNodes.get(0);
                }
                //rootNode为空
                if(rootNodes.size() != 1){
                    out.println("Case #" + (i+1) + ": NO");
                }else{
                    boolean flag = (judgeMaxHeap(rootNode) || judgeMinHeap(rootNode)) && isCompleteTree(rootNode);
                    if(flag){
                        out.println("Case #" + (i+1) + ": YES");
                    }else{
                        out.println("Case #" + (i+1) + ": NO");
                    }
                }


            }


        }
    }
    
    public static boolean isCompleteTree(Node root){
        //空树也是完全二叉树
        if(null == root){
            return true;
        }
        //树非空
        //1.按照层序遍历方式，找第一个不饱和的节点
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        boolean isLeafOrLeft = false;
        while(!q.isEmpty()){
            Node cur = q.poll();

            //从第一个不饱和节点之后，所有的节点不能有孩子节点
            if(isLeafOrLeft){
                if( cur.child.size() != 0){
                    return false;
                }
            }
            //cur节点的左右子节点均存在
            if(cur.child.size() == 2){
                q.offer(cur.child.get(0));
                q.offer(cur.child.get(1));
            }
            else if(cur.child.size() == 1){
                //cur节点只有左孩子
                isLeafOrLeft = true;
            }
            else{
                //cur是叶子结点
                isLeafOrLeft = true;
            }
        }
        return true;
    }


    public static boolean judgeMaxHeap(Node rootNode){

        if(child_father_satisfy(rootNode) && childSmall(rootNode)){
            if(rootNode.child.size() == 0){
                return true;
            }else if(rootNode.child.size() == 1){
                return judgeMaxHeap(rootNode.child.get(0));
            }else if(rootNode.child.size() == 2){
                return judgeMaxHeap(rootNode.child.get(0)) && judgeMaxHeap(rootNode.child.get(1));
            }
            return false;
        }

        return false;
    }

    public static boolean judgeMinHeap(Node rootNode){

        if(child_father_satisfy(rootNode) && childBig(rootNode)){
            if(rootNode.child.size() == 0){
                return true;
            }else if(rootNode.child.size() == 1){
                return judgeMinHeap(rootNode.child.get(0));
            }else if(rootNode.child.size() == 2){
                return judgeMinHeap(rootNode.child.get(0)) && judgeMinHeap(rootNode.child.get(1));
            }
            return false;
        }
        return false;
    }


    //儿子和父亲的个数满足条件
    public static boolean child_father_satisfy(Node node){
        return node.father.size() <= 1 && node.child.size() <= 2;
    }

    //儿子节点的值更大，用于小顶堆
    public static boolean childBig(Node node){
        if(node.child.size() == 0){
            return true;
        }
        for(int i = 0; i < node.child.size(); i++){
            if(node.child.get(i).val < node.val){
                return false;
            }
        }
        return true;
    }


    //儿子节点的值更小，用于大顶堆
    public static boolean childSmall(Node node){
        if(node.child.size() == 0){
            return true;
        }
        for(int i = 0; i < node.child.size(); i++){
            if(node.child.get(i).val > node.val){
                return false;
            }
        }
        return true;
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
    int val;
    ArrayList<Node> father = new ArrayList<>();
    ArrayList<Node> child = new ArrayList<>();

    public Node() {

    }

    public Node(int val) {
        this.val = val;
    }
}
