package 스터디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1940_주몽_임규리 {

    static int N, M;    // 재료 개수, 필요한 두 재료의 합
    static int[] arr;   // 재료 배열
    static int count;   // 결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);   // 오름차순 정렬

        int sIdx = 0;       // 시작 인덱스 (처음 -> 끝)
        int eIdx = N - 1;   // 끝 인덱스 (끝 -> 처음)
        int sum;            // 합 계산을 위해 필요

        while (sIdx < eIdx) {
            sum = arr[sIdx] + arr[eIdx];    // 합 계산

            if (sum == M) {         // 합이 M과 같다면
                count++;            // 결과 + 1
                sIdx++;             // 시작 인덱스 한 칸 뒤로 이동
                eIdx--;             // 끝 인덱스 한 칸 앞으로 이동
            } else if (sum < M) {   // 합이 M보다 작다면 더 크게 만들어야 함
                sIdx++;             // 시작 인덱스 한 칸 뒤로 이동 (조금 더 큰 수 찾음)
            } else {                // 합이 M보다 크다면 더 작게 만들어야 함
                eIdx--;             // 끝 인덱스 한 칸 앞으로 이동 (조금 더 작은 수 찾음)
            }
        }

        System.out.println(count);
    }
}
