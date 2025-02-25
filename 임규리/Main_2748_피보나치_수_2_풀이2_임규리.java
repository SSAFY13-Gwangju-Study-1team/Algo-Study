package 스터디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 메모리 : 11,496 KB
 * 시간 : 64 ms
 * --------------------------
 * Bottom Up
 * - for loop
 * - Memoization
 */
public class Main_2748_피보나치_수_2_풀이2_임규리 {

    static int N;       // n번째 피보나치 수 구하기
    static long[] arr;   // 피보나치 수 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new long[N + 1];

        System.out.println(fibo());
    }

    private static long fibo() {
        arr[0] = 0;
        arr[1] = 1;

        for (int i = 2; i < N + 1; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }

        return arr[N];
    }
}
