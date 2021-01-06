
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

//        AVLTree t = new AVLTree();
//        System.out.println("插入顺序：");
//        for(int i=1;i<=10;i++){
//            int n = 1+new Random().nextInt(100);
//            System.out.print(n+" ");
//            t.add(n);
//        }
//        System.out.println();
//        System.out.println("中序遍历：");
//        t.showTree();
//        int a = new Scanner(System.in).nextInt();
//        System.out.println("删除结点"+a+":");
//        t.delete(a);
//        t.showTree();

    }

    static class Task {

        public void solve(InputReader in, PrintWriter out) {

            int m = in.nextInt();
            int k = in.nextInt();
            long[] A = new long[m];
            for (int i = 0; i < A.length; i++) {
                A[i] = in.nextLong();
            }
            int[] N = new int[m - k + 1];
            for (int i = 0; i < N.length; i++) {
                N[i] = in.nextInt();
            }

            AVL_Tree tree = new AVL_Tree();
            for (int i = 0; i < k; i++) {
                tree.add(A[i]);
            }

            System.out.println(tree.findKth(N[0], tree.head));
            for (int i = k; i < A.length; i++) {
                tree.delete(A[i - k]);
                tree.add(A[i]);
                out.println(tree.findKth(N[i - k + 1]));
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
    long element;
    Node leftChild;
    Node rightChild;
    int height;
    int size;

    public Node(long d) {
        this.element = d;
        this.height = 0;
        this.size = 1;
    }

}

class AVL_Tree {
    Node head = null;

    private int height(Node root) {
        return root == null ? -1 : root.height;
    }

    private int size(Node root) {
        return root == null ? 0 : root.size;
    }

    public AVL_Tree() {
    }


    private Node findMin(Node root) {
        if (root == null) {
            return null;
        }
        while (root.leftChild != null) {
            root = root.leftChild;
        }
        return root;
    }

    

    public Node add(long key, Node root) {
        if (root == null) {
            return new Node(key);
        }
        if (root.element > key) {
            root.leftChild = add(key, root.leftChild);
        } else if (root.element < key) {
            root.rightChild = add(key, root.rightChild);
        }
        return balance(root);
    }

    public void add(long key) {
        head = add(key, head);
    }


    public Node delete(long key, Node root) {
        if (root == null) {
            return null;
        }
        if (root.element > key) {
            root.leftChild = delete(key, root.leftChild);
        } else if (root.element < key) {
            root.rightChild = delete(key, root.rightChild);
        } else if (root.leftChild != null && root.rightChild != null) {
            root.element = findMin(root.rightChild).element;
            root.rightChild = delete(root.element, root.rightChild);
        } else {
            root = (root.leftChild != null) ? root.leftChild : root.rightChild;
        }
        return balance(root);
    }

    public void delete(long key) {
        head = delete(key, head);
    }


    public Long findKth(int k, Node root) {
        int s = size(root.leftChild);
        if (s == k - 1)
            return root.element;
        else if (k <= s)
            return findKth(k, root.leftChild);
        else {
            return findKth(k - s - 1, root.rightChild);
        }
    }

    public Long findKth(int k) {
        return findKth(k, head);
    }

    private Node balance(Node root) {
        if (root == null) {
            return null;
        }
        if (height(root.leftChild) - height(root.rightChild) > 1) {
            if (height(root.leftChild.leftChild) >= height(root.leftChild.rightChild))
                root = LL(root);
            else {
                root = LR(root);
            }
        } else if (height(root.rightChild) - height(root.leftChild) > 1) {
            if (height(root.rightChild.rightChild) >= height(root.rightChild.leftChild))
                root = RR(root);
            else {
                root = RL(root);
            }
        }
        root.height = Math.max(height(root.leftChild), height(root.rightChild)) + 1;
        root.size = size(root.leftChild) + size(root.rightChild) + 1;
        return root;
    }





    private Node RL(Node root) {
        root.rightChild = LL(root.rightChild);
        return RR(root);
    }
    private Node RR(Node root) {
        Node part = root.rightChild;
        root.rightChild = part.leftChild;
        part.leftChild = root;
        root.height = Math.max(height(root.rightChild), height(root.leftChild)) + 1;
        part.height = Math.max(height(part.rightChild), root.height) + 1;
        part.size = size(root);
        root.size = size(root.leftChild) + size(root.rightChild) + 1;
        return part;
    }
    private Node LR(Node root) {
        root.leftChild = RR(root.leftChild);
        return LL(root);
    }
    private Node LL(Node root) {
        Node part = root.leftChild;
        root.leftChild = part.rightChild;
        part.rightChild = root;
        root.height = Math.max(height(root.leftChild), height(root.rightChild)) + 1;
        part.height = Math.max(height(part.leftChild), root.height) + 1;
        part.size = size(root);
        root.size = size(root.leftChild) + size(root.rightChild) + 1;
        return part;
    }


}


