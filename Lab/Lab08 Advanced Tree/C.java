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
            int T = in.nextInt();
            for (int i = 0; i < T; i++) {
                
                PlayerHeap players = new PlayerHeap();
                int n = in.nextInt();

                //每个球员的power
                PowerNode[] playerNode = new PowerNode[n];
                for (int j = 0; j < n; j++) {
                    playerNode[j] = new PowerNode();
                    playerNode[j].power = in.nextInt();
                }
                //每个球员购买的有效时间
                for (int j = 0; j < n; j++) {
                    playerNode[j].valid = in.nextInt();
                }


                Arrays.sort(playerNode, new Comparator<PowerNode>() {
                    @Override
                    public int compare(PowerNode o1, PowerNode o2) {
                        return Integer.compare(o1.valid, o2.valid);
                    }

                });

                int pos = playerNode.length - 1;
                int currentDay = n;
                PriorityQueue<Integer> players1 =new PriorityQueue<>((x,y) -> (y-x));
                long ans = 0;
                long ans2 = 0;

                while (currentDay >= 0) {
                    while (playerNode[pos].valid >= currentDay) {
                        players1.add(playerNode[pos].power);
                        players.add(playerNode[pos]);
                        pos--;
                        if (pos < 0) {
                            break;
                        }
                    }
                    if(players1.size() != 0){
                        ans2 += players1.poll();

                    }
                    if (players.size != 0) {
                        ans += players.pop();
                    }
                    if (pos < 0) {
                        break;
                    }
                    currentDay--;
                }


                out.println(ans2) ;

//                int validTime = playerNode[playerNode.length - 1].index;
//                long sum = 0;
//
//
//                for (int j = playerNode.length - 1; j >= 0; j--) {
//
//                    if (validTime != playerNode[j].index) {
//                        int popTime = validTime - playerNode[j].index;
//                        for (int k = 0; k < popTime; k++) {
//                            sum += players.pop();
//                        }
//                        validTime = playerNode[j].index;
//                    }
//
//
//
//                    players.add(playerNode[j]);
//
//                }
//
//                int popTime = playerNode[0].index;
//                for (int k = 0; k < popTime; k++) {
//                    sum += players.pop();
//                }
//
//                out.println(sum);


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


    /**
     * @param arr        主要排序顺序
     * @param arr2       从排序列表
     * @param leftIndex  待排序列起始位置
     * @param rightIndex 待排序列结束位置
     */
    private static void quickSort(int[] arr, int[] arr2, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            return;
        }

        int left = leftIndex;
        int right = rightIndex;
        //待排序的第一个元素作为基准值
        int key = arr[left];
        int key2 = arr2[left];

        //从左右两边交替扫描，直到left = right
        while (left < right) {
            while (right > left && arr[right] >= key) {
                //从右往左扫描，找到第一个比基准值小的元素
                right--;
            }

            //找到这种元素将arr[right]放入arr[left]中
            arr[left] = arr[right];
            arr2[left] = arr2[right];

            while (left < right && arr[left] <= key) {
                //从左往右扫描，找到第一个比基准值大的元素
                left++;
            }

            //找到这种元素将arr[left]放入arr[right]中
            arr[right] = arr[left];
            arr2[left] = arr2[right];
        }
        //基准值归位
        arr[left] = key;
        arr2[left] = key2;

        //对基准值左边的元素进行递归排序
        quickSort(arr, arr2, leftIndex, left - 1);
        //对基准值右边的元素进行递归排序。
        quickSort(arr, arr2, right + 1, rightIndex);
    }
}


class PowerNode {
    PowerNode father = null;
    PowerNode leftChild = null;
    PowerNode rightChild = null;
    int power;
    int valid;

    public PowerNode(int index, int value) {
        this.valid = index;
        this.power = value;
    }
    public PowerNode() {
    }
}


class PlayerHeap {

    PowerNode root = null;
    int size = 0;

    public void add(PowerNode newNode) {
        int binaryNum = size + 1;
        String binary = binaryToDecimal(binaryNum);
        //第一个Node
        if (binaryNum == 1) {
            size++;
            root = newNode;
            //不是第一个Node的情况
        } else {
            size++;
            PowerNode currentNode = root;
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

            PowerNode u = newNode;
            while (true) {
                if (u.power == root.power && u.valid == root.valid) {
                    return;
                }
                if (u.power <= u.father.power) {
                    return;
                } else {
                    int tempVal = u.power;
                    int tempInd = u.valid;
                    u.power = u.father.power;
                    u.father.power = tempVal;
                    u.valid = u.father.valid;
                    u.father.valid = tempInd;

                    u = u.father;

                }
            }

        }
    }


    public int peek() {
        return root.power;
    }

    public int pop() {


        //堆内是空的
        if (size == 0) {
            return 0;

            //堆内剩下一个
        } else if (size == 1) {
            int returnValue = root.power;
            root = null;
            size--;
            return returnValue;

            //堆内剩下两个及以上
        } else {
            int returnValue = root.power;

            //找到要删除的那个Node
            PowerNode deleteNode = root;
            int binaryNum = size;
            String binary = binaryToDecimal(binaryNum);
            for (int i = 1; i < binary.length(); i++) {
                switch (binary.charAt(i)) {
                    case '0':
                        deleteNode = deleteNode.leftChild;
                        break;
                    case '1':
                        deleteNode = deleteNode.rightChild;
                        break;
                }
            }

            //临时保存值
            int index = deleteNode.valid;
            int value = deleteNode.power;

            PowerNode father = deleteNode.father;
            if (father.leftChild == deleteNode) {
                father.leftChild = null;
            } else if (father.rightChild == deleteNode) {
                father.rightChild = null;
            }
            deleteNode.father = null;
            root.power = value;
            root.valid = index;
            size--;


            PowerNode u = root;
            //删完了还剩一个
            if (size == 1) {
                return returnValue;
                //删完了剩下不止一个
            } else {

                while (true) {

                    if (u.rightChild != null && u.leftChild != null) {
                        if (u.power >= u.rightChild.power && u.power >= u.leftChild.power) {
                            return returnValue;
                        } else {
                            int leftVal = u.leftChild.power;
                            int rightVal = u.rightChild.power;

                            int min = Math.min(leftVal, rightVal);
                            if (min == leftVal) {
                                u = swapLeft(u);
                            } else {
                                u = swapRight(u);
                            }
                        }

                    } else if (u.leftChild == null && u.rightChild != null) {
                        if (u.power >= u.rightChild.power) {
                            return returnValue;
                        } else {
                            u = swapRight(u);
                        }

                    } else if (u.leftChild != null && u.rightChild == null) {
                        if (u.power >= u.leftChild.power) {
                            return returnValue;
                        } else {
                            u = swapLeft(u);

                        }

                        //叶节点
                    } else {
                        return returnValue;
                    }

                }


            }


        }
    }

    private PowerNode swapRight(PowerNode u) {
        int value1 = u.power;
        int index1 = u.valid;
        u.power = u.rightChild.power;
        u.valid = u.rightChild.valid;
        u.rightChild.power = value1;
        u.rightChild.valid = index1;
        u = u.rightChild;
        return u;
    }

    private PowerNode swapLeft(PowerNode u) {
        int value1 = u.power;
        int index1 = u.valid;
        u.power = u.leftChild.power;
        u.valid = u.leftChild.valid;
        u.leftChild.power = value1;
        u.leftChild.valid = index1;
        u = u.leftChild;
        return u;
    }


    public static String binaryToDecimal(int n) {
        String str = "";
        while (n != 0) {
            str = n % 2 + str;
            n = n / 2;
        }
        return str;
    }
}

