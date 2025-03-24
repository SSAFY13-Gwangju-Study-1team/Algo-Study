/* 메모리 14188kb, 시간 104ms
 * 문제 풀이 아이디어: DP의 기초문제로, 바텀업 방식으로 풀이해본다.
 * 체감 난이도: 4/10
 */

import java.io.*;
import java.util.*;

public class Main_2748_피보나치_수_2_바텀업방식_정찬영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    /* ----- 입력 ----- */
    static int n;
    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());    // 구할 피보나치 수
    }

    /* ----- 구현 ----- */
    static void solve() {
        sb.append(fibo(n));
    }

    static long fibo(int n) {
        long[] dp = new long[n+1];  // 오버플로우 방지를 위해 데이터 타입은 long
        dp[0] = 0;  // 초기값 0
        dp[1] = 1;  // 초기값 1

        for(int i=2; i<=n; i++) {
            dp[i] = dp[i-1] + dp[i-2];  // 피보나치 수 계산
        }

        return dp[n];
    }

    /* ----- 출력 ----- */
    static void output() throws IOException {
        System.out.println(sb);
        br.close();
    }
}