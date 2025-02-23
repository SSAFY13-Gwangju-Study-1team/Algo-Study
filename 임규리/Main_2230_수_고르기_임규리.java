package barkingdog.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2230_수_고르기_임규리 {

    static int N, M;    // 배열 크기, 두 수의 최소 차이
    static int[] arr;   // 배열
    static int min = Integer.MAX_VALUE; // 결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);   // 오름차순 정렬 -> 탐색 용이

        int sIdx = 0;   // 시작 포인터
        int eIdx = 0;   // 끝 포인터

        while (eIdx < N) {  // eIdx는 N - 1까지 (N이면 배열 범위를 벗어남)
            if (arr[eIdx] - arr[sIdx] >= M) {   // 두 수의 차가 M 이상일 때
                min = Math.min(min, arr[eIdx] - arr[sIdx]); // 최소값 업데이트
                sIdx++; // 시작 포인터 한 칸 이동
            } else {    // 두 수의 차가 M 미만일 때
                eIdx++; // 차이가 부족 -> 더 큰 수(그 다음 수)로 이동
            }

            if (min == M) { // 결과값의 최소는 M이니 M과 일치한다면 탐색 중지
                break;
            }
        }

        System.out.println(min);
    }
}
