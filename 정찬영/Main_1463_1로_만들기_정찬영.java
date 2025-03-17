/* 메모리 18004kb, 시간 120ms
 * 문제 풀이 아이디어: 이전에 풀었던 문제지만 dp연습을 위해 다시 풀이함
 * 우선 이전 수를 참고하여 -1한 값에 +1을 한다.
 * 다음으로 2와 3으로 나누어지는 경우를 확인하고 나누어진 수+1이 더 연산 수가 작은지 확인하여 갱신한다.
 * 체감 난이도: 6/10
 */

import java.io.*;
import java.util.*;

public class Main_1463_1로_만들기_정찬영 {
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
    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
    }

    /* ----- 구현 ----- */
    static int[] dp;
    static void solve() {
        dp = new int[N+1];

        for(int i=2; i<=N; i++) {
            dp[i] = dp[i-1] + 1;
            if(i%2 == 0) {
                dp[i] = Math.min(dp[i], dp[i/2]+1);
            }

            if(i%3 == 0) {
                dp[i] = Math.min(dp[i], dp[i/3]+1);
            }
        }

        sb.append(dp[N]);
    }

    /* ----- 출력 ----- */
    static void output() throws IOException {
        System.out.println(sb);
        br.close();
    }
}