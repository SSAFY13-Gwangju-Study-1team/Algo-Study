import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * 가지치기 X
 * 메모리 : 11,564 kb
 * 시간 : 60 ms
 * -------------------
 * 가지치기 O
 * 메모리 : 11,556 kb
 * 시간 : 68 ms
 */
public class Main_5557_1학년_임규리 {

    static int N;
    static int[] num;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = parseInt(br.readLine());
        num = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = parseInt(st.nextToken());
        }

        // dp[i][sum] = i번째 수까지 사용해서 sum을 만든 경우의 수
        dp = new long[N - 1][21];   // 연산 결과는 항상 0~20 사이만 허용됨
        dp[0][num[0]] = 1;          // 첫번째 수는 그대로 초기값으로 사용 (경우의 수 1개)

        for (int i = 1; i < N - 1; i++) {   // 마지막 수는 결과값이라 연산에 포함 X
            for (int sum = 0; sum <= 20; sum++) {
                // sum이라는 결과가 이전에 만들어진 적이 없다면 무시 (가지치기)
                if (dp[i - 1][sum] == 0) continue;

                int add = sum + num[i]; // 현재 숫자를 더한 결과
                int sub = sum - num[i]; // 현재 숫자를 뺀 결과

                // 가능한 범위 내에서만 갱신
                if (add <= 20) dp[i][add] += dp[i - 1][sum];
                if (sub >= 0) dp[i][sub] += dp[i - 1][sum];
            }
        }

        // 마지막 숫자를 목표값으로 했을 때의 경우의 수 출력
        System.out.println(dp[N - 2][num[N - 1]]);
    }
}
