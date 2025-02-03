
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main_13251_조약돌꺼내기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[] stones = new int[M];

        int sumStone = 0;
        double total = 0;
        for (int i = 0; i < M; i++) {
            stones[i] = Integer.parseInt(st.nextToken());
            sumStone += stones[i];
        }

        for (int i : stones) {
            total += calPercent(i, sumStone);
        }
        
        System.out.println(total);
    }

    // N : 뽑고자하는 색깔 수, K : 뽑으려는 돌 수, sumStone : 총 돌 수
    public static double calPercent(int N, int K, int sumStone) {
        if (N < K) {
            return 0.0;
        }

        double temp = 1.0;

        for (int i = 0; i < K; i++) {
            temp *= (N-- / sumStone--);
        }

        return temp;
    }
}
