
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
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
            int n = in.nextInt();//n个节点
            int m = in.nextInt();//m个边
            //建立城市
            ArrayList<City> cities = new ArrayList<>();
            for(int i = 0; i < n; i++){
                cities.add(new City());
            }
            //建立无向图道路
            for(int i = 0; i < m; i++){
                int u = in.nextInt();
                int v = in.nextInt();
                cities.get(u - 1).degree++;
                cities.get(v - 1).degree++;
                cities.get(u - 1).edge.add(cities.get(v - 1));
                cities.get(v - 1).edge.add(cities.get(u - 1));
            }
            boolean flag = true;
            while(flag){

                for (City currentCity : cities) {
                    //当只有一个相邻城市的时候
                    if (currentCity.degree == 1) {
                        City edgeCity = currentCity.edge.get(0);
                        currentCity.degree--;
                        edgeCity.degree--;
                        currentCity.edge.remove(edgeCity);
                        edgeCity.edge.remove(currentCity);

                    }
                }
                flag = false;
                for (City city : cities) {
                    if (city.degree == 1) {
                        flag = true;
                        break;
                    }
                }
            }
            boolean bad = false;
            for (City city : cities) {
                if (city.degree != 0) {
                    bad = true;
                    break;
                }
            }
            if(bad){
                out.println("Bad");
            }else{
                out.println("Good");
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


class City{

    public int degree = 0;
    public ArrayList<City> edge = new ArrayList<>();


}

