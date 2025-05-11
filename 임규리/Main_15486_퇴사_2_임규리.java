import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * 메모리 : 320,236 kb
 * 시간 : 648 ms
 */
public class Main_15486_퇴사_2_임규리 {

    static int N;
    static int[] T;
    static int[] P;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = parseInt(br.readLine());
        T = new int[N];
        P = new int[N];
        dp = new long[N + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = parseInt(st.nextToken());
            P[i] = parseInt(st.nextToken());
        }

        for (int i = N - 1; i >= 0; i--) {
            int endDay = i + T[i];  // 상담 끝나는 날

            if (endDay <= N) {      // 상담 가능
                dp[i] = Math.max(dp[i + 1], P[i] + dp[endDay]);
            } else {                // 상담 불가능
                dp[i] = dp[i + 1];
            }
        }

        System.out.println(dp[0]);
    }
}
