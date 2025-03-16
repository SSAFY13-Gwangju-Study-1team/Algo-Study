/* 메모리 14220kb, 시간 104ms
 * 문제 풀이 아이디어: DP의 기초문제로, 탑다운 방식으로 풀이해본다.
 * 체감 난이도: 4/10
 */

import java.io.*;
import java.util.*;

public class Main_2748_피보나치_수_2_탑다운방식_정찬영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
    static long[] dp;   // 오버플로우 방지를 위해 데이터 타입은 long
    static void solve() {
        dp = new long[n+1];
        Arrays.fill(dp, -1);    // 배열 모든 초기 값 -1로 초기화
        sb.append(fibo(n));
    }

    static long fibo(int n) {
        if (n <= 1)
            return n;

        if (dp[n] != -1)
            return dp[n];   // 이미 계산된 값이면 반환

        dp[n] = fibo(n-1) + fibo(n-2);
        return dp[n];
    }

    /* ----- 출력 ----- */
    static void output() throws IOException {
        System.out.println(sb);
        br.close();
    }
}