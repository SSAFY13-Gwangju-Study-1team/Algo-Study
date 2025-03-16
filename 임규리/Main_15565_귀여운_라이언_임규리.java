package 스터디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 1 -> 실패
 * - sIdx 업데이트 로직을 잘못 구현함
 * 풀이 2 -> 성공
 * - sIdx 위치의 인형이 라이언이어서 count가 K 미만이 될 때까지 while로 반복
 */
public class Main_15565_귀여운_라이언_임규리 {

    static int N, K;    // 인형 개수, 필요한 라이언 인형 개수
    static int[] arr;   // 인형 배열
    static int count;   // 현재 라이언 인형 개수
    static int minLength = Integer.MAX_VALUE;   // 결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sIdx = 0;
        int eIdx = 0;

        while (eIdx < N) {
            if (arr[eIdx] == 1) {   // eIdx의 인형이 라이언이면
                count++;
            }

            while (count >= K) {    // count가 K 이상일 때
                minLength = Math.min(minLength, eIdx - sIdx + 1);

                if (arr[sIdx] == 1) {   // sIdx의 인형이 라이언이면
                    count--;
                }

                sIdx++; // sIdx 한 칸 이동
            }   // sIdx가 라이언이어서 count가 K 미만이 될 때까지 반복

            eIdx++;
        }

        System.out.println((minLength == Integer.MAX_VALUE) ? -1 : minLength);
    }
}
