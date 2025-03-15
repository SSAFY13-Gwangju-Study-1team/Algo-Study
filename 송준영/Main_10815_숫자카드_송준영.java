import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Main_10815_숫자카드
 * 난이도 2/10
 * 이분탐색
 * 1124ms 108MB
 * 
 * 이분탐색을 사용해서 풀 수 있는 문제
 * Arrays.sort()를 사용해서 정렬을 해주고 Arrays.binarySearch()를 사용해서 찾아주면 된다
 * 사실 Set를 써서 풀어도 되지만 이분탐색을 사용해서 풀어보았다
 */
public class Main_10815_숫자카드_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;    // 카드 수, 체크할 수
    static int[] card;  // 카드 배열
    static int[] check; // 체크할 배열

    public static void main(String[] args) throws Exception {
        // 입력 처리
        N = parseInt(br.readLine());
        card = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            card[i] = parseInt(st.nextToken());
        }
        Arrays.sort(card);  // 이분탐색을 위한 정렬

        // 체크할 배열 입력
        M = parseInt(br.readLine());
        check = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            check[i] = parseInt(st.nextToken());
        }

        // Arrays.BinarySearch()를 사용
        for (int i = 0; i < M; i++) {
            int result = Arrays.binarySearch(card, check[i]);   // 이분탐색
            if (result < 0) {   // 음수면 없는 것
                sb.append(0).append(" ");   // 없으면 0
            } else {
                sb.append(1).append(" ");   // 있으면 1
            }
        }

        // 출력
        System.out.println(sb);
    }
}
