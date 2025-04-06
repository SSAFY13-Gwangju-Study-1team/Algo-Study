import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * GPT 도움 받음... 넘 어려버요
 */
public class Main_11066_파일_합치기_임규리 {

    static int T;   // 테스트 케이스 수
    static int K;   // 소설 장 수
    static int[] chapters;  // 파일 크기 배열
    static int[] sum;   // 누적합 배열
    static int[][] dp;  // 계산 값 저장 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            K = parseInt(br.readLine());
            chapters = new int[K];
            sum = new int[K];
            dp = new int[K][K];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++) {
                chapters[j] = parseInt(st.nextToken());
                if (j >= 1) {
                    sum[j] += sum[j - 1] + chapters[j];
                } else {
                    sum[j] = chapters[j];
                }
            }

            for (int j = 2; j <= K; j++) {  // j = 구간 크기
                for (int k = 0; k <= K - j; k++) {  // k = 시작 인덱스
                    int l = k + j - 1;  // l = 끝 인덱스
                    dp[k][l] = Integer.MAX_VALUE;

                    // 구간 총 합 계산
                    int cost = sum[l] - (k > 0 ? sum[k - 1] : 0);

                    // 가능한 분할 지점 m (k <= m < l)에 대해 최소 비용 계산
                    for (int m = k; m < l; m++) {
                        // dp[k][m] + dp[m + 1][l] + cost = k부터 m까지 합치기, m + 1부터 l까지 합치기, 두 그룹을 합치기
                        dp[k][l] = Math.min(dp[k][l], dp[k][m] + dp[m + 1][l] + cost);
                    }
                }
            }

            System.out.println(dp[0][K - 1]);
        }
    }
}
