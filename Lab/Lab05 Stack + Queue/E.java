import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
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

            ArrayList<MyCandyStack> stackFrequencyList = new ArrayList<>();
            stackFrequencyList.add(new MyCandyStack());//0位置凑数

            //每一种糖果的出现次数
            int[] candyFrequency = new int[100001];

            //最大出现频率
            int maxFrequencyNum = 0;



            flag: while(true){

                switch (in.next()){


                    case "put-in":


                        int num = in.nextInt();//第几种糖果
                        CandyNode newNode = new CandyNode(num);//创建一个糖果
                        candyFrequency[num]++;

                        //FrequencyList里面有一样更大的Frequency
                        if(maxFrequencyNum >= candyFrequency[num]){
                            stackFrequencyList.get(candyFrequency[num]).push(newNode);
                        }
                        //FrequencyList里面还没有更大的Frequency
                        else if(maxFrequencyNum < candyFrequency[num]){
                            stackFrequencyList.add(new MyCandyStack());
                            stackFrequencyList.get(candyFrequency[num]).push(newNode);
                            maxFrequencyNum = candyFrequency[num];
                        }

                        continue;

                    case "eat":

                        //就没有
                        if(stackFrequencyList.size() == 1){
                            out.println("pa");
                            continue;
                        }else{
                            //该弹出的那个
                            int outPutPos = stackFrequencyList.size() - 1;
                            //该频率栈只有一个了
                            if(stackFrequencyList.get(outPutPos).size == 1){
                                CandyNode outputNode = stackFrequencyList.get(outPutPos).pop();
                                out.println(outputNode.num);
                                stackFrequencyList.remove(outPutPos);
                                candyFrequency[outputNode.num]--;
                                maxFrequencyNum--;
                            }else{
                                CandyNode outputNode = stackFrequencyList.get(outPutPos).pop();
                                candyFrequency[outputNode.num]--;
                                out.println(outputNode.num);
                            }
                        }

                        continue;



                    case "nsdd":
                        break flag;
                    default:
                        break;
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


class CandyNode{

    CandyNode prev;
    CandyNode next;
    int num;//放入candy的编号


    public CandyNode( int num){
        this.num = num;
    }

}
class MyCandyStack{

    CandyNode top = null;
    int size = 0;


    public MyCandyStack(){

    }

    public boolean push(CandyNode pushNode){
        if (size != 0) {
            top.next = pushNode;
            pushNode.prev = top;
        }
        top = pushNode;
        size++;
        return true;
    }

    public CandyNode pop(){
        if(size == 0){
            return null;
        }else if(size == 1){
            CandyNode popNode = top;
            top = null;
            size--;
            return popNode;
        }else{
            CandyNode popNode = top;
            top = top.prev;
            top.next = null;
            popNode.prev = null;
            size--;
            return popNode;
        }
    }

    public int getSize() {
        return size;
    }
}
