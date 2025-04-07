import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * SWEA_3282_01Knapsack
 * 난이도 3/10
 * DP
 * 111ms 32mb
 * 
 * DP를 이용한 0-1 Knapsack 문제
 * DP 테이블을 만들어서 물건을 하나씩 넣어가며 최대 가치를 구하는 방식
 * DP 테이블을 만들 때, 물건을 넣는 경우와 넣지 않는 경우를 비교하여 최대 가치를 구한다
 * 물건을 넣는 경우는 현재 물건의 가치 + 남은 용량에서 넣을 수 있는 최대 가치를 구하고, 물건을 넣지 않는 경우는 이전 물건의 최대 가치를 그대로 가져온다
 * 이 두 가지 경우 중 최대 값을 DP 테이블에 저장한다
 */
public class SWEA_3282_01Knapsack_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T;               // 테케 수
    static int N, K;            // 물건 수, 가방 용량
    static int[][] things, dp;  // 물건 정보(가치, 부피), DP 테이블

    public static void main(String[] args) throws Exception {
        // 테케 수 입력 및 반복
        T = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append(String.format("#%d %d\n", t, solve()));
        }
        // 결과 출력
        System.out.println(sb);
    }

    /**
     * 메인 메서드
     * @return
     * @throws Exception
     */
    public static int solve() throws Exception {
        // 입력 처리 및 초기화
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        K = parseInt(st.nextToken());

        things = new int[N+1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int vol = parseInt(st.nextToken()), val = parseInt(st.nextToken());
            things[i][0] = val;
            things[i][1] = vol;
        }

        dp = new int[N+1][K+1]; // DP 테이블 초기화
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                if (j-things[i][1] >= 0) {  // 현재 물건을 넣을 수 있는 경우
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-things[i][1]] + things[i][0]);    // 물건을 넣는 경우와 넣지 않는 경우 비교
                } else {                    // 현재 물건을 넣을 수 없는 경우
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        
        // for (int i = 1; i <= N; i++) {
        //     for (int j = 0; j <= K; j++) {
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        return dp[N][K];    // 최대 가치 반환
    }
}
