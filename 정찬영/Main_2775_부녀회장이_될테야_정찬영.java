/* 메모리 14232, 시간 100ms
 * 문제 풀이 아이디어: dp를 이용한다.
 * 체감 난이도: 2/10
 */

import java.io.*;
import java.util.*;

public class Main_2775_부녀회장이_될테야_정찬영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    /* ----- 입력 ----- */
    static int k, n;
    static void input() throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++) {
            k = Integer.parseInt(br.readLine());   // k층
            n = Integer.parseInt(br.readLine());   // n호
            solve();
        }
    }

    /* ----- 구현 ----- */
    static void solve() {
        int[][] dp = new int[k+1][n+1];
        for(int i=1; i<n+1; i++) {
            dp[0][i] = i;
        }

        for(int i=1; i<=k; i++) {       // a층의
            for(int j=1; j<=n; j++) {   // b호에 살려면
                for(int k=1; k<=j; k++) {
                    dp[i][j] += dp[i-1][k]; // 자신의 아래(a-1)층의 1호부터 b호까지 사람들의 수의 합만큼 사람들을 데려와 살아야 한다
                }
            }
        }
        sb.append(dp[k][n]).append("\n");
    }

    /* ----- 출력 ----- */
    static void output() throws IOException {
        System.out.println(sb);
        br.close();
    }
}