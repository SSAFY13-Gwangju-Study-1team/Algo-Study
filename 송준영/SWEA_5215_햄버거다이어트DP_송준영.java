import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * SWEA 5215 햄버거 다이어트
 * 난이도 4/10
 * DP
 * 146ms 26,600kb
 * 
 * DP 문제중 쉬운 편
 * 넣을지 말지만 고려해서 하면 된다
 * 0-1 knapsack 문제와 비슷한 느낌
 * 2차원 DP 배열을 사용하여 각 재료를 포함하는 경우와 포함하지 않는 경우를 고려하여 최대 가치를 계산함
 */
public class SWEA_5215_햄버거다이어트DP_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, L;        // 재료 수, 제한 칼로리
    static int[][] info;    // 재료 정보
    static int[][] dp;      // DP 배열
    static int result;      // 결과 값

    public static void main(String[] args) throws Exception {
        int T = parseInt(br.readLine()); // 테스트 케이스 수 입력

        // 테스트 케이스 수만큼 반복
        for (int t = 1; t <= T; t++) {
            solve();
            sb.append(String.format("#%d %d\n", t, result));
        }

        // 출력
        System.out.println(sb);
    }
    
    /**
     * 메인 로직 메서드
     * @throws Exception
     */
    public static void solve() throws Exception {
        // 입력 처리 및 초기화
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        L = parseInt(st.nextToken());

        info = new int[N][2]; // 재료 정보 배열
        result = 0; // 결과 값 초기화

        // 재료 정보 입력
        for (int i = 0; i < N; i++) {
            int val, cal;
            st = new StringTokenizer(br.readLine());
            val = parseInt(st.nextToken());
            cal = parseInt(st.nextToken());

            info[i][0] = val;
            info[i][1] = cal;
        }

        // DP 배열 초기화
        dp = new int[N + 1][L + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= L; j++) {
                // 현재 재료의 칼로리가 제한 칼로리보다 작거나 같으면
                if (info[i - 1][1] <= j) {
                    // 현재 재료를 포함하는 경우와 포함하지 않는 경우 중 최대 값을 선택
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - info[i - 1][1]] + info[i - 1][0]);
                } else {
                    // 현재 재료를 포함할 수 없는 경우
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // 결과 값은 DP 배열의 마지막 행에서 최대 값
        for (int i = 0; i <= L; i++) {
            result = Math.max(result, dp[N][i]);
        }
    }
}
