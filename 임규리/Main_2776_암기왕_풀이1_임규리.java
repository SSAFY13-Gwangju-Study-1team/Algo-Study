import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * 메모리 : 332,348 KB
 * 시간 : 1384 ms
 * --------------------------------------------
 * 풀이 1 => 실패
 * - 이유 : sout() 바로바로 해서 시간 초과
 * --------------------------------------------
 * 풀이 2 => 실패
 * - 이유 : 각 테스트 출력 사이에 한 줄 공백 추가
 * --------------------------------------------
 * 풀이 3 => 성공
 */
public class Main_2776_암기왕_풀이1_임규리 {

    static int T;   // 테스트 케이스 개수
    static int N;   // 수첩1 개수
    static int[] arr1;  // 수첩1
    static int M;   // 수첩2 개수
    static int[] arr2;  // 수첩2

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringBuilder sb = new StringBuilder();

            N = parseInt(br.readLine());
            arr1 = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr1[j] = parseInt(st.nextToken());
            }

            M = parseInt(br.readLine());
            arr2 = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr2[j] = parseInt(st.nextToken());
            }

            Arrays.sort(arr1);  // 수첩1 정렬

            for (int j = 0; j < M; j++) {
                if (exists(arr2[j])) {
                    sb.append(1);
                    sb.append(System.lineSeparator());
                } else {
                    sb.append(0);
                    sb.append(System.lineSeparator());
                }
            }
            sb.delete(sb.length() - 1, sb.length());

            System.out.println(sb);
        }
    }

    private static boolean exists(int n) {
        // 수첩1에 n이라는 숫자가 존재하는지 확인

        int start = 0;
        int end = N - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (n == arr1[mid]) return true;

            if (n < arr1[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return false;
    }
}
