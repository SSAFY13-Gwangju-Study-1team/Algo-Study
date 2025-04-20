import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main_2435_기상청_인턴_신현수_임규리 {

    static int N;   // 날짜 수
    static int K;   // 연속된 날짜 수
    static int[] arr;   // 수열
    static int[] prefix;    // 누적합 배열
    static int max; // 결과값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = parseInt(st.nextToken());
        K = parseInt(st.nextToken());
        arr = new int[N];
        prefix = new int[N + 1];
        max = Integer.MIN_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            prefix[i] = prefix[i - 1] + arr[i - 1];
        }

        for (int i = K; i <= N; i++) {
            max = Math.max(max, prefix[i] - prefix[i - K]);
        }

        System.out.println(max);
    }
}
