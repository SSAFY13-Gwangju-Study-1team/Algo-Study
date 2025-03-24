package barkingdog.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 1 -> 실패
 * - 정렬하면 안 되는데 정렬함...
 * - 매번 sum을 새로 계산해서 투포인터를 사용한 이유가 없음
 * 풀이 2 -> 실패
 * - min을 못찾으면 0을 출력하라는 조건을 빼먹음
 * 풀이 3 -> 성공
 */
public class Main_1806_부분합_임규리 {

    static int N, S;
    static int[] arr;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sIdx = 0;
        int eIdx = 0;
        int sum = 0;

        while (eIdx < N) {
            sum += arr[eIdx++]; // sum이 S 이상이 될 때까지 eIdx 증가

            while (sum >= S) { // sum이 S 이상이면 최소 길이를 업데이트하고 sIdx 증가
                min = Math.min(min, eIdx - sIdx);
                sum -= arr[sIdx++];
            }
        }

        System.out.println(min == Integer.MAX_VALUE ? 0 : min);
    }
}
