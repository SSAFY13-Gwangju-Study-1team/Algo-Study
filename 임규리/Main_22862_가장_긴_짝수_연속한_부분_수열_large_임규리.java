package 스터디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * ChatGPT랑 같이 풀이...^_^
 * - 투포인터
 */
public class Main_22862_가장_긴_짝수_연속한_부분_수열_large_임규리 {

    static int N, K;
    static int[] S;
    static int oddCount;
    static int maxLength;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }

        int sIdx = 0;
        int eIdx = 0;

        while (eIdx < N) {
            if (S[eIdx] % 2 != 0) { // eIdx의 수가 홀수라면
                oddCount++;
            }

            while (oddCount > K) {  // oddCount가 K를 초과할 때 -> oddCount를 줄여줘야 함
                if (S[sIdx] % 2 != 0) { // sIdx의 값이 홀수일 때
                    oddCount--;         // oddCount - 1
                }
                sIdx++; // sIdx를 하나씩 늘려가며 oddCount가 K가 될 때까지 순회
            }

            maxLength = Math.max(maxLength, eIdx - sIdx + 1 - oddCount);    // 최대 길이 찾기
            eIdx++; // eIdx + 1
        }

        System.out.println(maxLength);
    }
}
