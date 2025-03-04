package 스터디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 메모리 : 11,512 KB
 * 시간 : 64 ms
 * --------------------------
 * Top Down (제대로 구현 못해서 다시 공부...)
 * - Top Down은 재귀 호출 사용
 * - Memoization : 이미 계산한 값을 배열에 저장
 * - N은 최대 90이라 int[]가 아닌 long[]
 */
public class Main_2748_피보나치_수_2_풀이1_임규리 {

    static int N;       // n번째 피보나치 수 구하기
    static long[] arr;   // 피보나치 수 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new long[N + 1];

        System.out.println(fibo(N));
    }

    private static long fibo(int n) {
        // 기저 조건
        if (n == 0) return 0;
        if (n == 1) return 1;

        // 이미 계산한 값이면 반환
        if (arr[n] != 0) return arr[n];

        arr[n] = fibo(n - 1) + fibo(n - 2);
        return arr[n];
    }
}
