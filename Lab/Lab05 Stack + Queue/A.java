
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
            for(int i = 0; i < T; i++){
                MyStack stack = new MyStack(30000);
                int n = in.nextInt();
                String input = in.next();
                boolean flag = true;
                out: for(int j = 0; j < input.length(); j++){

                    switch (input.charAt(j)){
                        case '(':
                            stack.push('(');
                            break;
                        case '[':
                            stack.push('[');
                            break;
                        case '{':
                            stack.push('{');
                            break;
                        case ')':
                            if(stack.peak() == (Object) '('){
                                stack.pop();
                                break;
                            }else{
                                flag = false;
                                break out;
                            }
                        case ']':
                            if(stack.peak() == (Object) '['){
                                stack.pop();
                                break;
                            }else{
                                flag = false;
                                break out;
                            }
                        case'}':
                            if(stack.peak() == (Object) '{'){
                                stack.pop();
                                break;
                            }else{
                                flag = false;
                                break out;
                            }
                    }



                }

                //判断最后栈是不是空了，没空还是错了！！！！
                //—————————————————————————————————————
                if(!stack.isNull()){
                    flag = false;
                }
                //——————————————————————————————————————

                if(flag){
                    out.println("YES");
                }else{
                    out.println("NO");
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


class MyStack {

    private int MAX_SIZE;
    private Object[] list;
    private int top;//顶层指针
    private int size;//当前栈里面的元素有几个


    public MyStack(int MAX_SIZE) {
        this.MAX_SIZE = MAX_SIZE;
        list = new Object[MAX_SIZE];
        this.top = -1;
        this.size = 0;
    }

    public Object pop() {

        if (isNull()) {
            return null;
        } else {
            size--;
            return list[top--];
        }

    }


    public boolean isNull() {
        return top == -1;
    }


    public boolean push(Object i) {
        if (isFull()) {
            return false;
        } else {
            size++;
            top++;
            list[top] = i;
            return true;
        }
    }

    private boolean isFull() {
        return top + 1 == MAX_SIZE;
    }


    public Object peak() {
        if (isNull()) {
            return null;
        } else {
            return list[top];
        }
    }


    public void printCurrentStack() {
        if (size == 0) {
            System.out.println("Stack is null");
        }
        System.out.println("----------------------");
        System.out.println("Stack");
        for (int i = size - 1; i >= 0; i--) {
            System.out.println(list[i]);
        }
        System.out.println("----------------------");
    }

    public int getMAX_SIZE() {
        return MAX_SIZE;
    }

    public Object[] getList() {
        return list;
    }

    public int getTop() {
        return top;
    }

    public int getSize() {
        return size;
    }


}

