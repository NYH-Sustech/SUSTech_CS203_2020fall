import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
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
//            int t = in.nextInt();
//            int k = in.nextInt();//第k大
//            int s = in.nextInt();//初始last_ans
//            //初始状态
//            long last_ans = s;
//            int i;
//            ArrayList<Long> arr = new ArrayList<>();
//            ArrayList<Long> ans = new ArrayList<>();
//            //第3大
//            //1 2
//            for (i = 1; i < k; i++) {
//                last_ans = a(i + last_ans);
//                arr.add(last_ans);
//                Collections.sort(arr);
//                if (i % 100 == 0) {
//                    ans.add(arr.get(0));
//                }
//            }
//            Object[] arrLong = arr.toArray();
//            KthLargest kthHeap = new KthLargest(k, arrLong);
//            for (i = k; i <= t; i++) {
//                last_ans = a(last_ans + i);
//                long currKth = kthHeap.add(last_ans);
//                if (i % 100 == 0) {
//                    ans.add(currKth);
//                }
//            }
//
//            StringBuilder outputAns = new StringBuilder();
//            for (int j = 0; j < ans.size() - 1; j++) {
//                outputAns.append(ans.get(j)).append(" ");
//            }
//            outputAns.append(ans.get(ans.size() - 1));
//            out.println(outputAns);


            int t = in.nextInt();
            int k = in.nextInt();//第k大
            int s = in.nextInt();//初始last_ans
            //初始状态
            long last_ans = s;
            int i;
            ArrayList<Long> arr = new ArrayList<>();
            ArrayList<Long> ans = new ArrayList<>();
            //第3大
            //1 2
            for (i = 1; i < k; i++) {
                long answer = a(i + last_ans);
                arr.add(answer);
                Collections.sort(arr);
                if (i % 100 == 0) {
                    last_ans = arr.get(0);
                    ans.add(arr.get(0));
                }
            }
            Object[] arrLong = arr.toArray();
            KthLargeHeap kthHeap = new KthLargeHeap(k, arrLong);
            for (i = k; i <= t; i++) {
                long answer = a(last_ans + i);
                long currKth = kthHeap.add(answer);
                if (i % 100 == 0) {
                    last_ans = currKth;
                    ans.add(currKth);
                }
            }

            StringBuilder outputAns = new StringBuilder();
            for (int j = 0; j < ans.size() - 1; j++) {
                outputAns.append(ans.get(j)).append(" ");
            }
            outputAns.append(ans.get(ans.size() - 1));
            out.println(outputAns);

        }
    }



    public static long a(long n) {
        String n2String = n + "";
        long returnValue = n;
        for (int i = 0; i < n2String.length(); i++) {
            returnValue += Integer.parseInt(String.valueOf(n2String.charAt(i)));
        }
        return returnValue;

    }

    static class KthLargeHeap {

        public int k;
        public PriorityQueue<Long> queue;


        public long add(long val) {
            if (queue.size() < k) {
                queue.add(val);
            } else if (val > queue.peek()) {
                queue.poll();
                queue.add(val);
            }
            return queue.peek();
        }

        public KthLargeHeap(int k, Object[] nums) {
            queue = new PriorityQueue<>(k);
            this.k = k;
            Long[] nums2Long = new Long[nums.length];
            for (int i = 0; i < nums.length; i++) {
                nums2Long[i] = Long.valueOf(String.valueOf(nums[i]));
            }
            for (long num : nums2Long) {
                if (queue.size() < k) {
                    queue.add(num);
                } else {
                    if (num > queue.peek()) {
                        queue.poll();
                        queue.add(num);
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



