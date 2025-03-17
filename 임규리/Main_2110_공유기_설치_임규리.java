import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * 메모리 : 27,792 KB
 * 시간 : 236 ms
 * -------------------------------------
 * 풀이 1 => 실패
 * - 이유 : 0번 인덱스에 공유기 설치 후 카운팅 누락
 *         오히려 마지막에 diff가 1보다 클 때 카운팅
 *         -> 1보다 큰 게 아니라 mid보다 클 때 세야 하는데
 *            잘못된 조건 설정
 * -------------------------------------
 * 풀이 2 => 성공
 */
public class Main_2110_공유기_설치_임규리 {

    static int N;   // 집 개수
    static int C;   // 공유기 개수
    static int[] x; // 집 좌표
    static int minDiff; // 좌표간 최소 차이
    static int maxDiff; // 좌표간 최대 차이
    static int result;  // 가장 인접한 두 공유기 사이의 거리 최대값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        C = parseInt(st.nextToken());
        x = new int[N];
        minDiff = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            x[i] = parseInt(br.readLine());
        }

        Arrays.sort(x);
        int cur = x[0];
        for (int i = 1; i < N; i++) {
            minDiff = Math.min(minDiff, x[i] - cur);
            cur = x[i];
        }
        maxDiff = x[N - 1] - x[0];

        search();
        System.out.println(result);
    }

    private static void search() {
        int start = minDiff;
        int end = maxDiff;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (canDivide(mid)) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
    }

    private static boolean canDivide(int mid) {
        int count = 1;  // 0번 위치에 공유기 설치하고 시작

        int cur = x[0];
        for (int i = 1; i < N; i++) {
            // 이전에 공유기를 설치한 위치와 mid 이상으로 차이나는 위치라면
            if (x[i] - cur >= mid) {
                count++;    // 하나 더 설치
                cur = x[i]; // 마지막 설치 위치를 현 위치로 업데이트
            }
        }

        return count >= C;
    }
}
