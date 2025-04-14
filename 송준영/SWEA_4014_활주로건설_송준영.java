import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * SWEA_4014_활주로건설
 * 난이도 4/10
 * 구현?
 * 100ms 27mb
 * 
 * 활주로를 건설할 수 있는지 체크하는 문제
 * 조건은 다음과 같다
 * - 높이차가 2 이상 나면 안됨
 * - 경사로가 설치된 곳에는 경사로 설치 못함 -> 경사로 설치해야 하는데 이미 있으면 fail
 * - 높이가 1 차이 나는 경우는 경사로 설치 위해 낮은 곳이 X만큼 연속으로 있어야 함
 * 
 * 인덱스 접근을 빠르게 하기 위해 map1, map2를 만들어서 세로와 가로를 모두 체크할 수 있도록 했다
 */
public class SWEA_4014_활주로건설_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T;               // 테케 수
    static int N, X;            // 맵 크기, 경사로 길이
    static int[][] map1, map2;  // 맵1(가로), 맵2(세로) -> 높이차 체크를 위해서 세로와 가로를 모두 체크할 수 있도록 함

    public static void main(String[] args) throws Exception {
        // 테케 수 입력 및 반복
        T = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append(String.format("#%d %d\n", t, solve()));
        }
        // 출력
        System.out.println(sb);
    }

    /**
     * 메인 메서드
     * @return
     * @throws Exception
     */
    public static int solve() throws Exception {
        // 입력 처리 및 초기화
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        X = parseInt(st.nextToken());

        map1 = new int[N][N];
        map2 = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int v = parseInt(st.nextToken());
                map1[i][j] = v;
                map2[j][i] = v;
            }
        }

        int result = 0; // 결과값
        for (int i = 0; i < N; i++) {
            if (check(map1[i])) result++;   // 가로 체크
            if (check(map2[i])) result++;   // 세로 체크
        }

        return result;  // 결과값 리턴
    }

    /**
     * 체크 메서드
     * @param arr   체크할 배열
     * @return      체크 결과
     */
    public static boolean check(int[] arr) {
        boolean[] visited = new boolean[N]; // 방문 배열

        for (int i = 0; i < N-1; i++) {
            if (Math.abs(arr[i] - arr[i+1]) > 1) return false;  // 높이차가 2 이상이면 false
            if (arr[i] - arr[i+1] == -1) {  // 높이가 1 차이 나면(왼쪽이 낮음)
                for (int x = 0; x < X; x++) {
                    int j = i - x;
                    if (!isIn(j) || visited[j] || arr[j] != arr[i]) return false;   // 높이가 1 차이 나고, 낮은 곳이 X만큼 연속으로 있어야 함
                    visited[j] = true;  // 방문 처리
                }
            }
            if (arr[i] - arr[i+1] == 1) {   // 높이가 1 차이 나면(오른쪽이 낮음)
                for (int x = 1; x <= X; x++) {
                    int j = i + x;
                    if (!isIn(j) || visited[j] || arr[j] != arr[i+1]) return false; // 높이가 1 차이 나고, 낮은 곳이 X만큼 연속으로 있어야 함
                    visited[j] = true;  // 방문 처리
                }
            }
        }

        return true;    // 체크가 끝났으니 true 리턴
    }

    /**
     * 인덱스 체크 메서드
     * @param x 인덱스
     * @return  인덱스가 유효한지 체크
     */
    public static boolean isIn(int x) {
        return x >= 0 && x < N;
    }
}
