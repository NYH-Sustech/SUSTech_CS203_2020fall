import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.io.*;
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

                int N = in.nextInt();//城市的个数
                int K = in.nextInt();//朋友的个数
                City[] cities = new City[N];

                //创建新的城市
                for (int j = 0; j < N; j++) {
                    City newCity = new City();
                    cities[j] = newCity;
                }
                //连接道路
                for (int j = 0; j < N - 1; j++) {
                    int A = in.nextInt();
                    int B = in.nextInt();
                    cities[A - 1].subCities.add(cities[B - 1]);
                    cities[B - 1].subCities.add(cities[A - 1]);
                }
                //标记有人呆着的城市
                for (int j = 0; j < K; j++) {
                    int pi = in.nextInt();
                    cities[pi - 1].setHasPeople(true);
                }
                //删除没人呆着且只有一条路的城市边缘
                deleteCities(cities);
                

                //随便找一个符合的城市
                int randomCity = 0;
                for (int j = 0; j < N; j++) {
                    if (cities[j].hasPeople && cities[j].subCities.size() == 1) {
                        randomCity = j;
                        break;
                    }
                }
                travel(cities[randomCity]);
                
                
                City maxLenCity = findMaxLenCity(cities);
                //hasVisited重新刷新
                for (int j = 0; j < N; j++) {
                    cities[j].hasVisited = false;
                }
                maxLenCity.treeHeight = 0;
                travel(maxLenCity);

                
                double maxDistance = findMaxLenCity(cities).treeHeight;
                int minimalTime = (int) Math.ceil(maxDistance / 2);
                out.println(minimalTime);
            }
        }


    }


    public static City findMaxLenCity(City[] cities){
        int pos = 0;
        double maxDistance = 0;

        //找到第⼀次遍历的最⻓路径的城市
        for (int j = 1; j < cities.length; j++) {
            if (cities[j].treeHeight > maxDistance) {
                maxDistance = cities[j].treeHeight;
                pos = j;
            }
        }
        return cities[pos];

    }

    public static void travel(City tree) {
        tree.hasVisited = true;
        for (int i = 0; i < tree.subCities.size(); i++) {
            if (!tree.subCities.get(i).hasVisited) {
                tree.subCities.get(i).treeHeight = tree.treeHeight + 1;
                travel(tree.subCities.get(i));
            }
        }
    }


    //删除所有没⼈的城市的道路连接
    public static void deleteCities(City[] cities) {
        while (true) {
            int count = 0;
            for (City city : cities) {
                if (!city.hasPeople && city.subCities.size() == 1) {
                    //双方互相删除路
                    city.subCities.get(0).subCities.remove(city);
                    city.subCities.remove(0);
                    count++;
                }
            }
            if(count == 0){
                break;
            }
        }
    }




}


class City {

    boolean hasPeople;//是否有人在这个节点
    boolean hasVisited;//是否被遍历到过

    int treeHeight;//城市连接广度
    ArrayList<City> subCities = new ArrayList<>();//儿子节点们



    public City() {
        hasPeople = false;
        hasVisited = false;
        treeHeight = 0;
    }



    public void setHasPeople(boolean a) {
        hasPeople = a;
    }

}


class InputReader {
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

    public BigDecimal nextBigDecimal() {
        return new BigDecimal(next());
    }
}



