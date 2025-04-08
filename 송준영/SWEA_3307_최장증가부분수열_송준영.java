import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SWEA_3307_최장증가부분수열
 * 난이도 3/10
 * 이분탐색, dp
 * 103ms 29mb
 * 
 * 이분탐색을 이용한 dp를 활용하여 최장 증가 부분 수열을 구하는 문제
 * 오늘 배운 LIS 방식을 사용 하면 된다
 */
public class SWEA_3307_최장증가부분수열_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T;           // 테스트 케이스 수
    static int N;           // 수열의 길이
    static int[] arr, dp;   // 수열, dp 배열

    public static void main(String[] args) throws Exception{
        // 테스트 케이스 수 입력 및 반복
        T  = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append(String.format("#%d %d\n", t, solve()));
        }
        // 결과 출력
        System.out.println(sb);
    }

    /**
     * 메인 메서드
     * @return
     * @throws Exception
     */
    public static int solve() throws Exception {
        // 입력 및 초기화
        N = parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(st.nextToken());
        }

        int size = 0;   // 처음 사이즈는 0, (새로 추가되는 요소가 들어갈 인덱스)
        for (int i = 0; i < N; i++) {
            int pos = Arrays.binarySearch(dp, 0, size, arr[i]); // 이분탐색 (이분 탐색할 배열, 시작 인덱스, 끝 인덱스(dp에 현재 채워진 사이즈), 찾을 값)
            int temp = Math.abs(pos) - 1;   // 이분 탐색 결과가 음수면 그 값의 절대값에 1을 빼면 들어갈 인덱스가 된다

            dp[temp] = arr[i];  // dp에 들어갈 값은 arr[i]로 넣어준다
            // System.out.println(Arrays.toString(dp) + " " + size);

            if (size == temp) size++;   // temp가 size와 같으면 size를 증가시켜준다 (새로 요소가 추가된 것임)
        }

        return size;    // size가 최장 증가 부분 수열의 길이

    }
}
