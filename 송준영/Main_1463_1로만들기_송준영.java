import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;

/**
 * 1로 만들기
 * 난이도 2/10
 * DP, Bottom-Up
 * 160ms 18mb
 */
public class Main_1463_1로만들기_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;             // 정수 N
    static int[] dp;          // DP 배열
    public static void main(String[] args) throws Exception {
        // 입력 및 초기화
        N = parseInt(br.readLine());
        dp = new int[N+1];

        for (int i = 0; i < N+1; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        dp[1] = 0;  // 1을 1로 만들기 위해 필요한 연산 횟수는 0

        // DP Bottom-Up 방식으로 연산 횟수 계산
        for (int i = 1; i <= N; i++) {
            if (i * 3 <= N) {
                dp[i * 3] = Math.min(dp[i] + 1, dp[i * 3]);
            }
            if (i * 2 <= N) {
                dp[i * 2] = Math.min(dp[i] + 1, dp[i * 2]);
            }
            if (i + 1 <= N) {
                dp[i+1] = Math.min(dp[i] + 1, dp[i+1]);
            }
        }

        // 결과 출력
        System.out.println(dp[N]);
    }
}
