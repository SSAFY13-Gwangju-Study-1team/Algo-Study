import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Main_2776_암기왕
 * 난이도 2/10
 * 이분탐색
 * 1624ms 231mb
 * 
 * 이분탐색을 사용해서 풀 수 있는 문제
 * Arrays.sort()를 사용해서 정렬을 해주고 Arrays.binarySearch()를 사용해서 찾아주면 된다
 * 이분탐색을 사용하면 시간복잡도가 O(logN)이 되기 때문에 빠르게 찾을 수 있다
 */
public class Main_2776_암기왕_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T, N, M;    // 테케 수, 첫번째 배열 크기, 두번째 배열 크기
    static int[] note1, note2; // 첫번째 배열, 두번째 배열

    public static void main(String[] args) throws Exception {
        T = parseInt(br.readLine());    // 테케 수 입력

        for (int t = 0; t < T; t++) {
            solve();
        }

        System.out.println(sb); // 출력
    }

    public static void solve() throws Exception {
        N = parseInt(br.readLine());    // 첫번째 배열 크기 입력
        note1 = new int[N]; // 첫번째 배열 초기화
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            note1[i] = parseInt(st.nextToken());
        }

        M = parseInt(br.readLine());    // 두번째 배열 크기 입력
        note2 = new int[M]; // 두번째 배열 초기화
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            note2[i] = parseInt(st.nextToken());
        }

        // 이분탐색을 위한 정렬
        Arrays.sort(note1);

        // Arrays.BinarySearch()를 사용
        for (int i = 0; i < M; i++) {
            int result = Arrays.binarySearch(note1, note2[i]);   // 이분탐색
            if (result < 0) {   // 음수면 없는 것
                sb.append(0).append("\n");   // 없으면 0
            } else {
                sb.append(1).append("\n");   // 있으면 1
            }
        }
    }
}
