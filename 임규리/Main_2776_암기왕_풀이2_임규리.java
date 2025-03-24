import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * 메모리 : 330,840 KB
 * 시간 : 1352 ms
 * --------------------------------------------
 * Arrays.binarySearch() 사용
 * - 아주 조금 빠르고 메모리가 아주 조금 줄어듦
 * - 직접 이분탐색을 구현하지 않아도 된다는 점은 편함!
 */
public class Main_2776_암기왕_풀이2_임규리 {

    static int T;   // 테스트 케이스 개수
    static int N;   // 수첩1 개수
    static int[] arr1;  // 수첩1
    static int M;   // 수첩2 개수
    static int[] arr2;  // 수첩2
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            sb = new StringBuilder();

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
                if (Arrays.binarySearch(arr1, arr2[j]) >= 0) {
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
}
