package 스터디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * GPT 도움 받았어요 (논리 설명)
 * --------------------------
 * 메모리 : 15,620 KB
 * 시간 : 96 ms
 * --------------------------
 * Bottom Up
 * 1. 초기 조건
 *  - 1은 이미 1이니까 dp[1] = 0
 * 2. 반복문으로 채우기
 *  - 아래 3가지 방법 중 가장 작은 값 저장
 *  1) N이 3으로 나누어 떨어지면 dp[N] = dp[N/3] + 1
 *  2) N이 2로 나누어 떨어지면 dp[N] = dp[N/2] + 1
 *  3) N에서 1을 빼면 dp[N] = dp[N - 1] + 1
 */
public class Main_1463_1로_만들기_풀이1_임규리 {

    static int N;       // 목표 정수
    static int[] arr;   // dp 저장 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];

        dp();
        System.out.println(arr[N]);
    }

    private static void dp() {
        // 초기 조건
        arr[1] = 0;

        for (int i = 2; i < N + 1; i++) {
            int min = Integer.MAX_VALUE;

            if (i % 3 == 0) {   // 3으로 나누어 떨어질 때
                min = Math.min(min, arr[i / 3] + 1);
            }

            if (i % 2 == 0) {    // 2로 나누어 떨어질 때
                min = Math.min(min, arr[i / 2] + 1);
            }

            // 1 빼기
            min = Math.min(min, arr[i - 1] + 1);

            arr[i] = min;
        }
    }
}
