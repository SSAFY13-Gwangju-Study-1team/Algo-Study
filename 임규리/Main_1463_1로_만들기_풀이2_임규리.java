package 스터디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 메모리 : 44,520 KB
 * 시간 : 112 ms
 * ----------------------------------------------------
 * Top Down
 * - 재귀
 * - Memoization
 * ----------------------------------------------------
 * 풀이 1 => 실패
 * - 이유 : Memoization 없이 풀이해서 시간 초과로 실패
 * ----------------------------------------------------
 * 풀이 2 => 성공
 */
public class Main_1463_1로_만들기_풀이2_임규리 {

    static int N;       // 목표 정수
    static int[] arr;   // dp 저장 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];

        System.out.println(dp(N));
    }

    private static int dp(int depth) {
        if (depth == 1) {   // 초기 조건
            return 0;
        }

        int min = Integer.MAX_VALUE;

        if (depth % 3 == 0) {   // 3으로 나누어 떨어질 때
            if (arr[depth / 3] != 0) {
                min  = Math.min(min, arr[depth / 3] + 1);
            } else {
                min = Math.min(min, dp(depth / 3) + 1);
            }
        }

        if (depth % 2 == 0) {    // 2로 나누어 떨어질 때
            if (arr[depth / 2] != 0) {
                min  = Math.min(min, arr[depth / 2] + 1);
            } else {
                min = Math.min(min, dp(depth / 2) + 1);
            }
        }

        // 1 빼기
        if (arr[depth - 1] != 0) {
            min  = Math.min(min, arr[depth - 1] + 1);
        } else {
            min = Math.min(min, dp(depth - 1) + 1);
        }

        arr[depth] = min;
        return min;
    }
}