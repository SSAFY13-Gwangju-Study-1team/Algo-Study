import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main_10815_숫자_카드_임규리 {
    
    static int N;   // 숫자 카드 수
    static int[] arr;   // 숫자 카드 배열
    static int M;   // 찾을 카드 수
    static int[] find;  // 찾을 카드 배열

    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(st.nextToken());
        }

        M = parseInt(br.readLine());
        find = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            find[i] = parseInt(st.nextToken());
        }

        // 숫자 카드 배열 정렬 (오름차순)
        Arrays.sort(arr);

        for (int i = 0; i < M; i++) {
            if (contains(find[i])) {  // 이분탐색 결과 존재한다면
                sb.append(1);
                sb.append(" ");
            } else {        // 존재하지 않는다면
                sb.append(0);
                sb.append(" ");
            }
        }

        System.out.println(sb);
    }

    /**
     * 이분탐색
     * @param target    찾고자 하는 숫자
     * @return  존재한다면 true, 존재하지 않는다면 false
     */
    private static boolean contains(int target) {
        int start = 0;
        int end = N - 1;

        while (start <= end) {  // 한 개만 남아도 탐색해야 하니까 < 이 아니라 <=
            int mid = (start + end) / 2;

            if (target == arr[mid]) {
                return true;
            }

            if (target > arr[mid]) {
                start = mid + 1;    // start 이후부터 보기
            } else {
                end = mid - 1;      // end 이전까지만 보기
            }
        }

        return false;
    }
}