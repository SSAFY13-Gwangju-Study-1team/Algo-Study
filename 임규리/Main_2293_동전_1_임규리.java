import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * 메모리 : 11,684 kb
 * 시간 : 72 ms
 */
public class Main_2293_동전_1_임규리 {

    static int n;           // 동전 종류 수
    static int k;           // 만들고자 하는 금액
    static int[] coins;     // 동전 금액 배열
    static long[] dp;       // dp[i] = i원을 만들 수 있는 경우의 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        k = parseInt(st.nextToken());
        coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = parseInt(br.readLine());
        }

        dp = new long[k + 1];
        dp[0] = 1L; // 0원을 만드는 방법은 "아무 동전도 사용하지 않는 경우" 1가지가 존재

        // 조합 방식 : 동전을 하나씩 고정하고, 그 동전으로 가능한 모든 금액 i 갱신
        for (int coin : coins) {
            for (int i = coin; i <= k; i++) {
                // i원을 만들 수 있는 경우의 수에, i - coin원을 만들 수 있는 경우의 수를 더함
                dp[i] += dp[i - coin];
            }
        }

        System.out.println(dp[k]);
    }
}
