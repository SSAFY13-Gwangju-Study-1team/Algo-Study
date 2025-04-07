import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * Main_11066_파일합치기
 * 난이도 9/10
 * DP
 * 
 * 테스트케이스 수 만큼 반복하면서 파일 합치기 문제를 해결하는 메서드
 * 조합을 쓰면 시간초과가 난다
 * PriorityQueue를 써보려 했으나 예외의 경우가 있다
 * 결국 다 찾아서 제일 최소를 찾아야 하는데
 * 그걸 저장을 어떻게 해야하나?
 * dp[i][j] = i~j까지의 최소 합병 비용 이런식으로 저장한단다 -> 처음 보는 패턴
 * i가 왼쪽 j가 오른쪽
 * dp[i][j] = dp[i][k] + dp[k+1][j] + (prefixSum[j] - prefixSum[i-1]) 이런식으로 계산
 * ====> chat gpt 도움
 */
public class Main_11066_파일합치기_송준영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T;   // 테스트 케이스 수

    public static void main(String[] args) throws Exception {
        // 테스트 케이스 수 입력 및 반복
        T = parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            solve();
        }
        System.out.println(sb);
    }

    public static void solve() throws Exception {
        int K = parseInt(br.readLine());
        int[] files = new int[K + 1];         // 1-based indexing
        int[] prefixSum = new int[K + 1];     // 누적합 배열
        int[][] dp = new int[K + 1][K + 1];   // dp[i][j]: i~j 최소 합병 비용

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= K; i++) {
            files[i] = parseInt(st.nextToken());
            prefixSum[i] = prefixSum[i - 1] + files[i];
        }

        for (int len = 2; len <= K; len++) {    // len: 합병할 파일의 개수
            for (int i = 1; i <= K - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;   // 초기화

                for (int k = i; k < j; k++) {   // 반으로 나눴을때 합병 비용 중 제일 최소 찾기
                    int cost = dp[i][k] + dp[k + 1][j] + (prefixSum[j] - prefixSum[i - 1]); // i~j까지의 합병 비용, dp 부분에 추가로 더하는 값도 추가하니까 그럼 ex) 30 + 40 -> 70 => 140
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        // 결과 출력
        sb.append(dp[1][K]).append("\n");
    }
}
