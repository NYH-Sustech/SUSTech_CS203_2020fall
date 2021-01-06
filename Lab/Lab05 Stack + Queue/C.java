import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) {
        InputStream inputStream = System.in;// new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    static class Task {

        public void solve(InputReader in, PrintWriter out) {


            while (in.hasNext()) {
                int n = in.nextInt();
                int q = in.nextInt();
                //多一个从1开始
                MyLinkedListQueue1[] queueList = new MyLinkedListQueue1[n + 1];
                for (int i = 0; i < queueList.length; i++) {
                    queueList[i] = new MyLinkedListQueue1();
                }
                for (int i = 0; i < q; i++) {
                    int num = in.nextInt();
                    if (num == 1) {
                        int u = in.nextInt();
                        int w = in.nextInt();
                        int val = in.nextInt();
                        queueList[u].enqueue(w, val);

//                        System.out.println("第" + i + "条命令，插入数据到第" + u + "个queue");
//                        queueList[u].printQueue();


                    } else if (num == 2) {
                        int u = in.nextInt();
                        int w = in.nextInt();
                        Object pop = queueList[u].pop(w);
                        out.println(pop);

//                        System.out.println("第" + i + "条命令，弹出第" + u + "个queue的数据" + pop);
//                        queueList[u].printQueue();


                    } else if (num == 3) {
                        int u = in.nextInt();
                        int v = in.nextInt();
                        int w = in.nextInt();

//                        System.out.println("第" + i + "条命令，把第" + u + "个queue和第" + v  + "个queue结合起来并且更新");
//                        System.out.println("结合前——————");
//                        queueList[u].printQueue();
//                        queueList[v].printQueue();

                        MyLinkedListQueue1.connection(queueList, u, v, w);

//                        System.out.println("结合后——————");
//                        queueList[u].printQueue();
//                        queueList[v].printQueue();



                    }

                }

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
    Node prev;
    Node next;
    Object value;
}

class MyLinkedListQueue1 {

    int size;
    Node head;//头指针
    Node tail;//尾指针


    public MyLinkedListQueue1() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public void enqueue(int w, Object val) {

        Node newNode = new Node();
        newNode.value = val;


        if (isEmpty()) {//注意我的LinkedListQueue是每个Node都有意义的，所以需要额外的判定
            //第一个入队的时候指针不动
            head = newNode;
            tail = newNode;
            size++;
            return;
        }
        //前插入
        if (w == 0) {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
            size++;
            //后插入
        } else if (w == 1) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            size++;
        }

    }

    //弹出
    public Object pop(int w) {
        if (isEmpty()) {
            return -1;
        } else if (size == 1) {
            Object pop = head.value;
            head = null;
            tail = null;
            size--;
            return pop;
        } else {
            //前pop
            if (w == 0) {
                //head往后移一格
                head = head.next;
                //把原先的head找出来
                Object pop = head.prev.value;
                //断开连接
                head.prev.next = null;
                head.prev = null;
                //size-1
                size--;

                return pop;
                //后pop
            } else if (w == 1) {

                //tail往前移动一个
                tail = tail.prev;
                //把原先的tail找出来
                Object pop = tail.next.value;
                //断开连接
                tail.next.prev = null;
                tail.next = null;
                //size-1
                size--;

                return pop;
            }

        }
        return -1;
    }

    //将一个deque反过来
    public static MyLinkedListQueue1 reverse(MyLinkedListQueue1 i1) {

        if (i1.isEmpty()) {
            return i1;
        } else if (i1.size == 1) {
            return i1;
        } else if (i1.size == 2) {
            Node newTail = i1.head;
            Node newHead = i1.tail;
            newHead.prev = null;
            newHead.next = newTail;
            newTail.next = null;
            newTail.prev = newHead;
            i1.head = newHead;
            i1.tail = newTail;
            return i1;
        } else {

            MyLinkedListQueue1 reverse = new MyLinkedListQueue1();
            Node p = i1.tail;
            while (p != null) {
                reverse.enqueue(1, p.value);
                p = p.prev;
            }
            i1 = reverse;
            return i1;
        }

    }


    public void setSize(int size) {
        this.size = size;
    }

    public static MyLinkedListQueue1[] connection(MyLinkedListQueue1[] myLinkedListQueue1,int u, int v, int w) {
        if (myLinkedListQueue1[u].isEmpty() && myLinkedListQueue1[v].isEmpty()) {
            //什么都不做
        } else if (myLinkedListQueue1[u].isEmpty() && !myLinkedListQueue1[v].isEmpty()) {

            if (w == 0) {
                myLinkedListQueue1[u] = myLinkedListQueue1[v];
                /**
                 * 避免覆盖
                 */
                MyLinkedListQueue1 v1 = new MyLinkedListQueue1();
                myLinkedListQueue1[v] = v1;

            } else if (w == 1) {
                myLinkedListQueue1[v] = reverse(myLinkedListQueue1[v]);
                myLinkedListQueue1[u] = myLinkedListQueue1[v];

                MyLinkedListQueue1 v1 = new MyLinkedListQueue1();
                myLinkedListQueue1[v] = v1;
            }

        } else if (!myLinkedListQueue1[u].isEmpty() && myLinkedListQueue1[v].isEmpty()) {
            //什么都不做
        } else {

            //直接连接
            if (w == 0) {

                myLinkedListQueue1[u].tail.next = myLinkedListQueue1[v].head;
                myLinkedListQueue1[v].head.prev = myLinkedListQueue1[u].tail;
                myLinkedListQueue1[u].tail = myLinkedListQueue1[v].tail;
                myLinkedListQueue1[u].size = myLinkedListQueue1[u].size + myLinkedListQueue1[v].size;

                MyLinkedListQueue1 v1 = new MyLinkedListQueue1();
                myLinkedListQueue1[v] = v1;


            } else if (w == 1) {

                myLinkedListQueue1[v] = reverse(myLinkedListQueue1[v]);
                myLinkedListQueue1[u].tail.next = myLinkedListQueue1[v].head;
                myLinkedListQueue1[v].head.prev = myLinkedListQueue1[u].tail;
                myLinkedListQueue1[u].tail = myLinkedListQueue1[v].tail;
                myLinkedListQueue1[u].size = myLinkedListQueue1[u].size + myLinkedListQueue1[v].size;

                MyLinkedListQueue1 v1 = new MyLinkedListQueue1();
                myLinkedListQueue1[v] = v1;


            }

        }

        return myLinkedListQueue1;

    }


    public void printQueue() {
        System.out.println("————————————————————");
        System.out.println("Queue");
        if (isEmpty()) {
            System.out.println("本queue目前为空");
            System.out.println("————————————————————");
            return;
        }
        System.out.print("head→");
        Node p = head;
        System.out.print(p.value + "→");
        while (p.next != null) {
            p = p.next;
            System.out.print(p.value + "→");
        }
        System.out.println("tail");
        System.out.println("————————————————————");
    }


    public int getSize() {
        return size;
    }


}


