import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * 메모리 : 26,880 KB
 * 시간 : 364 ms
 * ------------------------------------------
 * 초기 아이디어
 * - mid를 M - 1개만큼 만들어서 M개의 구역으로 나누기
 * - 잘못 접근함 ^_^...
 * ------------------------------------------
 * GPT 힌트
 * - 블루레이 크기의 최소값 = 가장 긴 강의 길이
 * - 블루레이 크기의 최대값 = 모든 강의 길이의 합
 * -> 이를 start, end로 삼아 이분 탐색
 *
 * - 필요한 블루레이 개수가 M개 이하라면 end를 줄여 재탐색
 * - 필요한 블루레이 개수가 M개 초과라면 start를 늘려서 재탐색
 */
public class Main_2343_기타_레슨_임규리 {

    static int N;   // 강의 수
    static int M;   // 블루레이 수
    static int[] arr;   // 강의 길이 배열
    static int min; // 최소 크기

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        min = Integer.MAX_VALUE;

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(st.nextToken());
        }

        cal();
        System.out.println(min);
    }

    private static void cal() {
        // start : 가장 긴 강의 (블루레이 최소 개수가 1이라서)
        int start = Arrays.stream(arr)
                .max()
                .getAsInt();
        // end : 모든 강의 길이 합 (블루레이 최소 개수라 N이라서)
        int end = Arrays.stream(arr)
                .sum();

        // 이분탐색
        while (start <= end) {
            int mid = (start + end) / 2;

            int count = 0;  // 필요한 블루레이 개수
            int temp = 0;   // 현재까지 강의 길이 합
            int curMax = 0; // 강의 길이 중 최대값
            for (int i = 0; i < N; i++) {
                if (temp + arr[i] <= mid) { // 이전까지 강의 길이 합에 현재 값을 더해도 mid보다 작거나 같다면 더해도 됨
                    temp += arr[i];
                } else {                    // mid를 넘어선다면 더할 수 없음
                    curMax = Math.max(curMax, temp);    // 강의 길이 최대값 업데이트
                    temp = arr[i];                      // 현재 강의 길이 = 현재까지 강의 길이 합
                    count++;                            // 이전 강의까지를 하나의 블루레이에 저장하는 것이니 필요한 블루레이 개수 카운팅
                }
            }

            // 마지막 블루레이에 대한 내용 업데이트 필요!
            curMax = Math.max(curMax, temp);
            count++;

            if (count <= M) {   // 필요한 블루레이 개수가 M개 이하라면
                end = mid - 1;  // 블루레이 최대 크기를 줄여서 다시 탐색 (더 줄여도 되겠다고 판단)
                min = Math.min(min, curMax);    // min 업데이트
            } else {            // 필요한 블루레이 개수가 M개 초과라면
                start = mid + 1;    // 블루레이 최대 크기를 늘려서 다시 탐색 (더 늘려야겠다고 판단)
            }
        }
    }
}
