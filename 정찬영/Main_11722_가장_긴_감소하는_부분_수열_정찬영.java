/* 메모리 14424kb, 시간 128ms
 * 문제 풀이 아이디어: dp를 이용한다. 수 마다 수열 길이를 저장하고 그것보다 작은 수가 나오면 +1한다.
 * 체감 난이도: 5/10
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11722_가장_긴_감소하는_부분_수열_정찬영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    /* ----- 입력 ----- */
    static int N;
    static int[] A;
    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());    // 수열의 크기 N

        st = new StringTokenizer(br.readLine());
        A = new int[N]; // 수열 A
        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }

    /* ----- 구현 ----- */
    static int[] dp;
    static void solve() {
        int maxLen = 1;
        dp = new int[N];
        Arrays.fill(dp, 1); // 모든 부분의 최소 길이는 1

        for(int i=1; i<N; i++) {
            for(int j=i-1; j>=0; j--) {
                if(A[i] < A[j]) {   // 현재 수가 이전에 있던 수보다 작으면
                    dp[i] = Math.max(dp[i], dp[j]+1);   // 길이 갱신
                }
            }
            maxLen = Math.max(maxLen, dp[i]); // 가장 긴 부분수열 길이 갱신
        }
        sb.append(maxLen);
    }

    /* ----- 출력 ----- */
    static void output() throws IOException {
        System.out.println(sb);
        br.close();
    }
}