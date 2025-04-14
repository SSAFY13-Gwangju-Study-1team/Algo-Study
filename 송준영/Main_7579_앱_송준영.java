import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * Main_7579_앱
 * 난이도 6/10
 * DP
 * 124ms 14mb
 * 
 * DP를 이용한 배낭 문제
 * 원래 2차원 DP를 이용한 배낭 문제였으나, 메모리와 비용을 1차원 DP로 변환하여 해결 -> 메모리초과 떠서...
 * 1차원으로 해도 바로 이전 값만 사용하므로 괜찮음
 * 그대신 뒤에서부터 접근해야함, 앞에서 접근하면 변경한 내용에 접근을 할 수도 있음
 */
public class Main_7579_앱_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M, costSum = 0;      // 앱 수, 필요한 메모리, 총 비용
    static int[] memory, cost, dp;     // 메모리, 비용, DP 테이블

    public static void main(String[] args) throws Exception {
        // 입력 처리 및 초기화
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        memory = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            memory[i] = parseInt(st.nextToken());
        }

        cost = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cost[i] = parseInt(st.nextToken());
            costSum += cost[i];
        }

        dp = new int[costSum+1];    // DP 테이블 초기화

        // DP 테이블 채우기
        for (int i = 1; i <= N; i++) {
            for (int j = costSum; j >= 0; j--) {
                if (j - cost[i] >= 0) {   // 현재 앱을 넣을 수 있는 경우
                    dp[j] = Math.max(dp[j], dp[j - cost[i]] + memory[i]);   // 앱을 넣는 경우와 넣지 않는 경우 비교
                }
                // 넣을수 없으면 그대로 지나감
            }
        }

        // 결과 출력
        for (int k = 0; k <= costSum; k++) {
            // System.out.print(dp[k] + " ");
            if (dp[k] >= M) {   // 메모리 확보 가능하면 출력하고 바로 끝내기
                System.out.println(k);
                break;
            } 
        }
    }
}
