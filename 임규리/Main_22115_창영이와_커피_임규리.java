import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * 0-1 Knapsack
 * 메모리 : 12,104 kb
 * 시간 : 92 ms
 */
public class Main_22115_창영이와_커피_임규리 {

    static int N;
    static int K;
    static int[] C;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        K = parseInt(st.nextToken());
        C = new int[N];
        dp = new int[K + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            C[i] = parseInt(st.nextToken());
        }

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        // i번째 캡슐 사용 여부 결정
        for (int i = 0; i < N; i++) {
            // 역방향 순회 : 중복 사용 방지
            for (int j = K; j >= C[i]; j--) {
                if (dp[j - C[i]] != Integer.MAX_VALUE) {
                    // C[i]를 포함한 경우 최소 캡슐 수 갱신
                    dp[j] = Math.min(dp[j], dp[j - C[i]] + 1);
                }
            }
        }

        System.out.println(dp[K] == Integer.MAX_VALUE ? -1 : dp[K]);
    }
}
