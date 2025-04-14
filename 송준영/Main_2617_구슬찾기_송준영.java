import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2617_구슬찾기_송준영 {
    static final int INF = 10000000;

    static int N, M;
    static int[][] dist1, dist2;
    
    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader와 StringTokenizer
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 구슬의 개수
        M = Integer.parseInt(st.nextToken()); // 비교 결과의 개수
        
        dist1 = new int[N + 1][N + 1];
        dist2 = new int[N + 1][N + 1];
        
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist1[i], INF);
            Arrays.fill(dist2[i], INF);
            dist1[i][i] = 0;
            dist2[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dist1[a][b] = 1; // a가 b보다 무겁다
            dist2[b][a] = 1; // b는 a보다 가볍다 (즉, a는 b보다 무겁다)
        }
        
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (dist1[i][k] != INF && dist1[k][j] != INF) {
                        dist1[i][j] = Math.min(dist1[i][j], dist1[i][k] + dist1[k][j]);
                    }
                    if (dist2[i][k] != INF && dist2[k][j] != INF) {
                        dist2[i][j] = Math.min(dist2[i][j], dist2[i][k] + dist2[k][j]);
                    }
                }
            }
        }
        
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            int countLighter = 0; // i보다 가벼운 구슬의 개수
            int countHeavier = 0; // i보다 무거운 구슬의 개수
            
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;

                if (dist1[i][j] != INF) countLighter++;
                if (dist2[i][j] != INF) countHeavier++;
            }
            
            if (countLighter > N / 2 || countHeavier > N / 2) {
                answer++;
            }
        }
        
        System.out.println(answer);
    }
}
