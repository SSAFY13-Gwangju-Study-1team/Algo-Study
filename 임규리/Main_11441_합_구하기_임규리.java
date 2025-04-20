import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main_11441_합_구하기_임규리 {

    static int N;           // 수 개수
    static int[] arr;       // 수열
    static int M;           // 구간 개수
    static int[] prefix;    // 누적합 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(st.nextToken());
        }

        prefix = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            prefix[i] = prefix[i - 1] + arr[i - 1];
        }

        M = parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = parseInt(st.nextToken());
            int to = parseInt(st.nextToken());

            sb.append(prefix[to] - prefix[from - 1]).append(System.lineSeparator());
        }

        System.out.println(sb);
    }
}
