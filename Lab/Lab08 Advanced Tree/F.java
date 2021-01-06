import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;


public class Main{

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
            int n = in.nextInt();
            AVL_Tree pets = new AVL_Tree();
            AVL_Tree adopters = new AVL_Tree();
            long ans = 0;
            for (int i = 0; i < n; i++) {

                int a = in.nextInt();
                long b = in.nextLong();

                switch (a) {
                    case 1:
                        if (pets.size == 0) {
                            adopters.add(b);
                        }else{
                            ans += AVL_Tree.Buy(b,pets);
                        }
                        break;
                    case 0:
                        if (adopters.size == 0) {
                            pets.add(b);
                        }else{
                            ans += AVL_Tree.Buy(b,adopters);
                        }
                        break;
                }
            }
            out.print(ans);
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
    long size;

    public Node(long d) {
        this.element = d;
        this.height = 0;
        this.size = 1;
    }

}








class AVL_Tree {
    Node head = null;
    long size = 0;

    private int height(Node root) {
        return root == null ? -1 : root.height;
    }

    private long size(Node root) {
        return root == null ? 0 : root.size;
    }

    public AVL_Tree() {
    }


    public long predecessorQuery(Node root, long q) {

        while (true) {
            if (root == null) {
                return -1;
            } else if (root.element == q) {
                return root.element;
            } else if (root.element > q) {
                return predecessorQuery(root.leftChild, q);
            } else if (root.element < q) {
                long temp =  predecessorQuery(root.rightChild, q);
                if(temp == -1){
                    return root.element;
                }else{
                    return temp;
                }
            }
        }
    }

    public long predecessorQuery(long q) {
        return predecessorQuery(head, q);
    }

    public long successorQuery(Node root, long q) {
        while (true) {
            if (root == null) {
                return -1;
            } else if (root.element == q) {
                return root.element;
            } else if (root.element > q) {
                long temp = successorQuery(root.leftChild,q);
                if(temp == -1){
                    return root.element;
                }else{
                    return temp;
                }
            } else if (root.element < q) {
                return successorQuery(root.rightChild,q);
            }
        }
    }



    public long successorQuery(long q) {
        return successorQuery(head, q);
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
        size++;
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
        size--;
        head = delete(key, head);
    }


//    public Long findKth(long k, Node root) {
//        long s = size(root.leftChild);
//        if (s == k - 1)
//            return root.element;
//        else if (k <= s)
//            return findKth(k, root.leftChild);
//        else {
//            return findKth(k - s - 1, root.rightChild);
//        }
//    }
//
//    public Long findKth(int k) {
//        return findKth(k, head);
//    }

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

    public static long Buy(long key, AVL_Tree tree) {
        return tree.remove(tree.head, key);
    }

    public long remove(Node root, long q){
        long p = predecessorQuery(root, q);
        long s = successorQuery(root, q);

        if (p != -1 && s != -1) {
            if ((q - p) <= (s - q)) {
                delete(p);
                return q - p;
            } else {
                delete(s);
                return s - q;
            }
        } else if (p != -1) {
            delete(p);
            return q - p;
        } else {
            delete(s);
            return s - q;
        }

    }


}




