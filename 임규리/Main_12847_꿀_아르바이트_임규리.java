package 슬라이딩윈도우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이 1 -> 실패
 * - maxMoney를 int로 선언
 * 풀이 2 -> 성공
 * - maxMoney를 long으로 선언
 * - m은 최대 10만
 * - Ti는 최대 100만
 * - 10만 X 100만 = 1000억
 */
public class Main_12847_꿀_아르바이트_임규리 {

    static int n, m;        // 배열 크기, 윈도우 크기
    static int[] arr;       // 배열
    static long maxMoney;    // 최대 이익

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 초기값 세팅
        long sum = 0;
        for (int i = 0; i < m; i++) {
            sum += arr[i];
        }
        maxMoney = Math.max(maxMoney, sum);

        int sIdx = 0;
        int eIdx = m;
        while (eIdx < n) {
            sum -= arr[sIdx++];
            sum += arr[eIdx++];
            maxMoney = Math.max(maxMoney, sum);
        }

        System.out.println(maxMoney);
    }
}
