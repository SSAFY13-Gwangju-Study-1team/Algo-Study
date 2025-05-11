import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * 메모리 : 14,840 kb
 * 시간 : 244 ms
 */
public class Main_11049_행렬_곱셈_순서_임규리 {

    static int N;   // 행렬의 개수
    static int[][] matrix;  // 행렬 배열
    static long[][] dp;     // dp[i][j] = i번 행렬부터 j번 행렬까지 곱하는데 드는 최소 연산 횟수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = parseInt(br.readLine());
        matrix = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            matrix[i][0] = parseInt(st.nextToken());
            matrix[i][1] = parseInt(st.nextToken());
        }

        dp = new long[N][N];

        // 구간 길이 2 ~ N
        for (int len = 2; len <= N; len++) {
            // i : 구간 시작점
            for (int i = 0; i <= N - len; i++) {
                // j : 구간 끝점
                int j = i + len - 1;

                // 최소 비용 저장 변수 초기화
                long minCost = Long.MAX_VALUE;

                // i ~ j 구간을 k를 기중느로 분할하여 최소 비용 계산
                for (int k = i; k < j; k++) {
                    // (i~k) 부분 곱셈 비용 + (k+1~j) 부분 곱셈 비용 + 두 결과를 곱하는 비용
                    long cost = dp[i][k] + dp[k + 1][j]
                            + (long) matrix[i][0] * matrix[k][1] * matrix[j][1];

                    // 현재 최소 비용 갱신
                    minCost = Math.min(minCost, cost);
                }

                // 구간 i~j까지의 최소 비용을 dp에 저장
                dp[i][j] = minCost;
            }
        }

        // 전체 행렬을 곱하는 최소 비용 출력
        System.out.println(dp[0][N - 1]);
    }
}
