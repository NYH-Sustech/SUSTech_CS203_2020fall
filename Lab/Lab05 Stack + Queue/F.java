/**
 * 二分算法_疯狂超时...
 */

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;


public class Main{

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
            int k = in.nextInt();
            int n = in.nextInt();
            int[] N = new int[n];
            for (int i = 0; i < N.length; i++) {
                N[i] = in.nextInt();
            }
            //n = 1的时候，只跟k去比较
            if (n == 1) {
                if (N[0] <= k) {
                    out.println(1);
                }
            } else if (n == 2) {
                if (Math.abs(N[0] - N[1]) <= k) {
                    out.println(2);
                } else {
                    if (N[0] <= k || N[1] <= k) {
                        out.println(1);
                    }
                }
            } else {
                int L = 1;
                int R = n;
                int mid;
                do {

                    mid = L + (R - L) / 2;

                    if (absDiff(N, mid, k)) {
                        L = mid;
                    } else {
                        R = mid;
                    }

                } while (L + 1 != R);
                if (absDiff(N, R, k)) {
                    out.println(R);
                } else
                    out.println(L);
            }


        }
    }


    public static boolean absDiff(int[] N, int subArraySize, int k) {
        if (subArraySize == 1) {
            for (int value : N) {
                if (value <= k) {
                    return true;
                }
            }
            return false;
        }


        SpecialStack maxStack = new SpecialStack();
        SpecialStack minStack = new SpecialStack();

        int judge = 0;
       // System.out.println("\nwindow:  " + subArraySize);
        for (int j = 0; j < N.length; j++) {
         /*   System.out.println("j:   " + j);
            printStack(maxStack, minStack);
            System.out.println();*/

            pushIntoMinAndMaxStack(maxStack, minStack, j, N[j]);

            if (j >= subArraySize) {


                while (maxStack.peakBottom().index <= j - subArraySize) {
                    maxStack.dequeueBottom();
                }
                while (minStack.peakBottom().index <= j - subArraySize) {
                    minStack.dequeueBottom();
                }
                judge = maxStack.peakBottom().value - minStack.peakBottom().value;


                if (judge <= k) {
                    return true;
                }
            }
            if (j == N.length - 1) {
                judge = maxStack.peakBottom().value - minStack.peakBottom().value;


                if (judge <= k) {
                    return true;
                }
            }

        }

//        ArrayList<Integer> tempArr = new ArrayList<>();
//        List<Integer> sortArr = new ArrayList<Integer>();
//        Collections.addAll(sortArr, new Integer[subArraySize]);
//
//
//        int absDiff;
//        for (int i = 0; i < N.length - subArraySize + 1; i++) {
//
//            if (i == 0) {
//                for (int j = 0; j < subArraySize; j++) {
//                    tempArr.add(N[i + j]);
//                }
//            } else {
//                tempArr.remove(0);
//                tempArr.add(N[i + subArraySize - 1]);
//            }
//
//            Collections.copy(sortArr, tempArr);
//            Collections.sort(sortArr);
//
//            absDiff = sortArr.get(sortArr.size() - 1) - sortArr.get(0);
//            if (absDiff <= k) {
//                return true;
//            }
//
//
//        }


        return false;
    }

    static void printStack(SpecialStack maxStack, SpecialStack minStack) {
        System.out.print("max:  ");
        printStack(maxStack);
        System.out.print("min:  ");
        printStack(minStack);
    }

    static void printStack(SpecialStack specialStack) {
        printNode(specialStack.bottom);
    }

    private static void printNode(node n) {
        if (n != null) {
            System.out.print(n.value + "(" + n.index + ")-->");
            if (n.next != null) {
                printNode(n.next);
            } else
                System.out.println();
        }
    }

    static void pushIntoMinAndMaxStack(SpecialStack maxStack, SpecialStack minStack, int index, int value) {

        if (maxStack.isEmpty()) {
            maxStack.push(new node(index, value));
        } else {
            while (!maxStack.isEmpty() && maxStack.peakTop().value < value) {
                maxStack.pop();
            }
            maxStack.push(new node(index, value));
        }

        if (minStack.isEmpty()) {
            minStack.push(new node(index, value));
        } else {
            while (!minStack.isEmpty() && minStack.peakTop().value > value) {
                minStack.pop();
            }
            minStack.push(new node(index, value));
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

class node {
    node prev;
    node next;
    int index;
    int value;

    public node() {
    }

    public node(int index, int value) {
        this.index = index;
        this.value = value;
    }

}

class SpecialStack {
    node bottom;//栈底元素
    node top;//栈顶元素
    int size;


    public SpecialStack() {
        size = 0;
    }


    public void push(node i) {
        if (isEmpty()) {
            bottom = i;
            top = i;
        } else {
            top.next = i;
            i.prev = top;
            top = i;
        }
        size++;
    }

    public node pop() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            node returnNode = top;
            top = null;
            bottom = null;
            size--;
            return returnNode;
        } else {
            node returnNode = top;
            top = top.prev;

            top.next = null;
            returnNode.prev = null;

            size--;
            return returnNode;
        }

    }

    public node dequeueBottom() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            node returnNode = top;
            top = null;
            bottom = null;
            size--;
            return returnNode;
        } else {

            node returnNode = bottom;
            bottom = bottom.next;
            bottom.prev = null;
            size--;
            return returnNode;
        }
    }

    public node peakTop() {
        return top;
    }

    public node peakBottom() {
        return bottom;
    }


    public boolean isEmpty() {
        return size == 0;
    }

}


