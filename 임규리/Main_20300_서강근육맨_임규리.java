import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

/**
 * 메모리 : 16,984 KB
 * 시간 : 144 ms
 * ---------------------------------------------------
 * 풀이 1 => 실패
 * - 이유 : 근손실 정도가 최대 10^18인걸 제대로 안 보고 int로 받음
 * ---------------------------------------------------
 * 풀이 2 => 성공
 * <아이디어>
 * 1. 배열 오름차순 정렬
 * 2. 배열의 개수가 홀수개면 맨 뒤를 temp 값으로 두고 앞 + 뒤 더하며 비교
 * 3. 배열의 개수가 짝수개면 앞 + 뒤 더하며 비교
 * 4. 최대값을 찾아 출력
 */
public class Main_20300_서강근육맨_임규리 {

    static int N;   // 운동기구 개수
    static long[] t; // 근손실 정도
    static long max; // 결과 (최대값)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = parseInt(br.readLine());
        t = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            t[i] = parseLong(st.nextToken());
        }

        Arrays.sort(t);     // 오름차순 정렬

        if (N % 2 == 1) {   // 홀수개
            long temp = t[N - 1];    // 맨 마지막 수

            for (int i = 0; i < (N - 1) / 2; i++) {
                long cur = t[i] + t[(N - 2) - i];
                temp = Math.max(temp, cur);
            }

            max = Math.max(temp, max);
        } else {            // 짝수개
            long temp = 0;

            for (int i = 0; i < N / 2; i++) {
                long cur = t[i] + t[N - 1 - i];
                temp = Math.max(temp, cur);
            }

            max = Math.max(temp, max);
        }

        System.out.println(max);
    }
}
