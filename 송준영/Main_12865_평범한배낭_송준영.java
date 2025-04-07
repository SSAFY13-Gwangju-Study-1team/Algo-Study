import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * Main_12865_평범한배낭
 * 난이도 3/10
 * DP
 * 172ms 53mb
 * 
 * DP를 이용한 배낭 문제
 * 01knapsack 문제로, 물건을 담을 수 있는 배낭의 용량과 물건의 무게와 가치를 고려하여 최대 가치를 구하는 문제
 * DP 테이블을 만들어서 물건을 하나씩 넣어가며 최대 가치를 구하는 방식
 * DP 테이블을 만들 때, 물건을 넣는 경우와 넣지 않는 경우를 비교하여 최대 가치를 구한다
 * 물건을 넣는 경우는 현재 물건의 가치 + 남은 용량에서 넣을 수 있는 최대 가치를 구하고, 물건을 넣지 않는 경우는 이전 물건의 최대 가치를 그대로 가져온다
 * 이 두 가지 경우 중 최대 값을 DP 테이블에 저장한다
 */
public class Main_12865_평범한배낭_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, K;
    static int[][] things, dp;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        K = parseInt(st.nextToken());

        things = new int[N+1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = parseInt(st.nextToken()), v = parseInt(st.nextToken());
            things[i][0] = w;
            things[i][1] = v;
        }

        dp = new int[N+1][K+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                if (j - things[i][0] >= 0) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - things[i][0]] + things[i][1]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
