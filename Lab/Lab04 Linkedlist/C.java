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
            int T = in.nextInt();
            for (int i = 0; i < T; i++) {
                int n = in.nextInt();
                long[][] coeff_expoonent1 = null;
                if (n > 0) {
                    coeff_expoonent1 = new long[n][2];
                    for (int j = 0; j < coeff_expoonent1.length; j++) {
                        coeff_expoonent1[j][0] = in.nextLong();
                        coeff_expoonent1[j][1] = in.nextLong();
                    }
                }
                long[][] coeff_expoonent2 = null;
                int m = in.nextInt();
                if (m > 0) {
                    coeff_expoonent2 = new long[m][2];
                    for (int j = 0; j < coeff_expoonent2.length; j++) {
                        coeff_expoonent2[j][0] = in.nextLong();
                        coeff_expoonent2[j][1] = in.nextLong();
                    }
                }


                //两个数组都不是0，0的情况！！
                //指数是有序的

                int pos1 = 0;//指针1
                int pos2 = 0;//指针2
                ListNode Start = new ListNode(-1);
                ListNode p = Start;

                if (coeff_expoonent1 != null && coeff_expoonent2 != null) {
                    while (true) {
                        if (pos1 >= n && pos2 >= m) {
                            break;
                        }

                        if (pos1 <= n - 1 && (pos2 >= m || coeff_expoonent1[pos1][1] < coeff_expoonent2[pos2][1])) {
                            if (coeff_expoonent1[pos1][1] == p.exponent) {
                                p.coeff += coeff_expoonent1[pos1][0];
                            } else if (coeff_expoonent1[pos1][1] > p.exponent) {
                                ListNode newNode = new ListNode(coeff_expoonent1[pos1][1]);
                                newNode.coeff = coeff_expoonent1[pos1][0];
                                p.next = newNode;
                                p = p.next;
                            }
                            pos1++;


                        } else if (pos2 <= m - 1 && (pos1 >= n || coeff_expoonent2[pos2][1] <= coeff_expoonent1[pos1][1])) {
                            if (coeff_expoonent2[pos2][1] == p.exponent) {
                                p.coeff += coeff_expoonent2[pos2][0];
                            } else if (coeff_expoonent2[pos2][1] > p.exponent) {
                                ListNode newNode = new ListNode(coeff_expoonent2[pos2][1]);
                                newNode.coeff = coeff_expoonent2[pos2][0];
                                p.next = newNode;
                                p = p.next;
                            }
                            pos2++;

                        }


                    }
                } else if (coeff_expoonent1 == null && coeff_expoonent2 == null) {
                    out.println(0);
                    continue;
                } else if (coeff_expoonent1 == null && coeff_expoonent2 != null) {
                    while (true) {
                        if (pos2 >= m) {
                            break;
                        }
                        if (coeff_expoonent2[pos2][1] == p.exponent) {
                            p.coeff += coeff_expoonent2[pos2][0];
                        } else if (coeff_expoonent2[pos2][1] > p.exponent) {
                            ListNode newNode = new ListNode(coeff_expoonent2[pos2][1]);
                            newNode.coeff = coeff_expoonent2[pos2][0];
                            p.next = newNode;
                            p = p.next;
                        }
                        pos2++;
                    }
                } else if (coeff_expoonent2 == null && coeff_expoonent1 != null) {

                    while (true) {
                        if (pos1 >= n) {
                            break;
                        }

                        if (coeff_expoonent1[pos1][1] == p.exponent) {
                            p.coeff += coeff_expoonent1[pos1][0];
                        } else if (coeff_expoonent1[pos1][1] > p.exponent) {
                            ListNode newNode = new ListNode(coeff_expoonent1[pos1][1]);
                            newNode.coeff = coeff_expoonent1[pos1][0];
                            p.next = newNode;
                            p = p.next;
                        }
                        pos1++;

                    }

                }


                StringBuilder output = new StringBuilder();
                int count = 0;//0表示第一个数，后面都不是

                while (true) {

                    Start = Start.next;


                    //指数是0，第一位
                    if (Start.exponent == 0) {

                        if (Start.coeff == 0) {
                            //什么都不做
                        } else {
                            //输出这个数
                            output.append(Start.coeff);
                        }

                        //指数是1，不一定是最后一位      1 + x  或者  x  或 1 - x 或 -x
                    } else if (Start.exponent == 1) {

                        //2x或-2x的类型
                        if (count == 0) {

                            //第一位是-x或x的类型
                            if (Start.coeff == 0) {
                            } else if (Start.coeff == 1) {
                                output.append("x");
                            } else if (Start.coeff == -1) {
                                output.append("-x");
                            } else {
                                output.append(Start.coeff + "x");
                            }
                            //1+2x或者1-2x的类型
                        } else {
                            //1 +0
                            if (Start.coeff == 0) {
                                //1 +x
                            } else if (Start.coeff == 1) {
                                output.append("+x");
                                //1 +2x
                            } else if (Start.coeff == -1) {
                                output.append("-x");
                            } else if (Start.coeff > 1) {
                                output.append("+" + Start.coeff + "x");
                                //1 -2x
                            } else {
                                output.append(Start.coeff + "x");
                            }
                        }
                        //指数是大于1的，不一定是最后一位
                        //
                    } else {
                        //2x^2或-2x^2的类型
                        if (count == 0) {

                            if (Start.coeff == 0) {

                            } else if (Start.coeff == 1) {
                                output.append("x^" + Start.exponent);
                            } else if (Start.coeff == -1) {
                                output.append("-x^" + Start.exponent);
                            } else {
                                output.append(Start.coeff + "x^" + Start.exponent);
                            }
                            //1 +2x^2或1 -2x^2的类型
                        } else {
                            if (Start.coeff == 0) {
                            } else if (Start.coeff == 1) {
                                output.append("+x^" + Start.exponent);
                            }else if(Start.coeff == -1){
                                output.append("-x^" + Start.exponent);
                            }
                            else if (Start.coeff > 1) {
                                output.append("+" + Start.coeff + "x^" + Start.exponent);
                            } else {
                                output.append(Start.coeff + "x^" + Start.exponent);
                            }
                        }
                    }

                    if (Start.next == null) {
                        break;
                    }
                    count++;
                }

                if (output.length() == 0) {
                    out.println(0);
                } else if (output.charAt(0) == '+') {
                    out.println(output.delete(0, 1));
                } else {
                    out.println(output);
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

class ListNode {

    long coeff;
    long exponent;
    ListNode next;

    public ListNode(long exponent) {
        this.exponent = exponent;
    }
}

