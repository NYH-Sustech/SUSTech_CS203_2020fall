import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
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
                MyJuanHeap heap = new MyJuanHeap();
                int n = in.nextInt();
                JuanNode[] nodes = new JuanNode[n];
                int[] nodes2 = new int[n];
                for (int j = 0; j < nodes.length; j++) {
                    int val = in.nextInt();
                    nodes[j] = new JuanNode(j, val);
                    nodes2[j] = j;
                    heap.insert(nodes[j]);
                }
                int thatNode = nodes2[in.nextInt() - 1];
                JuanNode findNode = null;
                for (JuanNode node : nodes) {
                    if (thatNode == node.index) {
                        findNode = node;
                    }
                }
                int[] ans = heap.findAns(findNode);
                out.println(ans[0] + " " + ans[1]);
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

class JuanNode {
    JuanNode father = null;
    JuanNode leftChild = null;
    JuanNode rightChild = null;
    int value;
    int index;

    public JuanNode(int index, int value) {
        this.index = index;
        this.value = value;
    }
}


class MyJuanHeap {

    JuanNode root = null;
    int size = 0;

    public void insert(JuanNode newNode) {
        int binaryNum = size + 1;
        String binary = binaryToDecimal(binaryNum);
        //第一个Node
        if (binaryNum == 1) {
            size++;
            root = newNode;
            //不是第一个Node的情况
        } else {
            size++;
            JuanNode currentNode = root;
            for (int i = 1; i < binary.length() - 1; i++) {
                switch (binary.charAt(i)) {
                    case '0':
                        currentNode = currentNode.leftChild;
                        break;
                    case '1':
                        currentNode = currentNode.rightChild;
                        break;
                }
            }
            if (binary.charAt(binary.length() - 1) == '0') {
                currentNode.leftChild = newNode;
                newNode.father = currentNode;
            } else {
                currentNode.rightChild = newNode;
                newNode.father = currentNode;
            }

            JuanNode u = newNode;
            while (true) {
                if (u.value == root.value && u.index == root.index) {
                    return;
                }
                if (u.value <= u.father.value) {
                    return;
                } else {
                    int tempVal = u.value;
                    int tempInd = u.index;
                    u.value = u.father.value;
                    u.father.value = tempVal;
                    u.index = u.father.index;
                    u.father.index = tempInd;

                    u = u.father;

                }
            }

        }
    }

    public int[] findAns(JuanNode node) {
        int num = findBinary(node);
        return find(num);
    }

    public int findBinary(JuanNode node) {
        StringBuilder binary = new StringBuilder();
        if (node == root) {
            binary.append("1");
        } else {
            JuanNode p = node;
            while (true) {
                JuanNode father = p.father;
                if (father.leftChild == p) {
                    binary.insert(0, "0");
                } else {
                    binary.insert(0, "1");
                }
                if (father == root) {
                    binary.insert(0, "1");
                    break;
                }
                p = p.father;
            }
        }
        return binary2Decimal(binary.toString());
    }


    public int[] find(int num) {
        int[] returnArr = new int[2];
        if (num == 1) {
            returnArr[0] = 1;
            returnArr[1] = 1;
            return returnArr;
        } else {
            int h = 1;//层数
            int a = 1;//每层的元素个数
            int sum = 1;//到该层的总和
            int returnPos;//返回的该层的数
            while (true) {

                int k = a * 2;//
                sum += k;//
                if (sum >= num) {//
                    h++;
                    int lastLayer = sum - k;
                    returnPos = num - lastLayer;
                    break;
                } else {
                    a = a * 2;//
                    h++;//
                }
            }
            returnArr[0] = h;
            returnArr[1] = returnPos;
            return returnArr;
        }
    }


    public static String binaryToDecimal(int n) {
        String str = "";
        while (n != 0) {
            str = n % 2 + str;
            n = n / 2;
        }
        return str;
    }


    /**
     * 二进制转十进制
     *
     * @param number
     * @return
     */
    public static int binary2Decimal(String number) {
        return scale2Decimal(number, 2);
    }

    /**
     * 其他进制转十进制
     *
     * @param number
     * @return
     */
    public static int scale2Decimal(String number, int scale) {
        // 不同其他进制转十进制,修改这里即可
        int total = 0;
        String[] ch = number.split("");
        int chLength = ch.length;
        for (int i = 0; i < chLength; i++) {
            total += Integer.parseInt(ch[i]) * Math.pow(scale, chLength - 1 - i);
        }
        return total;

    }


}








