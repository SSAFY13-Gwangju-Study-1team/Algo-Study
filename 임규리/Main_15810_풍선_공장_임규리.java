import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main_15810_풍선_공장_임규리 {

    static int N;   // 스태프 수
    static int M;   // 풍선 수
    static int[] A; // 각 스태프들이 풍선 하나를 만드는데 걸리는 시간
    static int max; // 시간 중 최대값
    static long result;  // 결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = parseInt(st.nextToken());
            max = Math.max(max, A[i]);
        }

        search();
        System.out.println(result);
    }

    private static void search() {
        long start = 0;
        long end = (long) M * max;

        while (start <= end) {
            long mid = (start + end) / 2;

            long count = 0;  // 만들 수 있는 풍선 수
            for (int i = 0; i < N; i++) {
                count += mid / A[i];
            }

            if (count < M) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        result = start;
    }
}
