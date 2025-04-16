import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * SWEA_5653_줄기세포배양
 * 난이도 3/10
 * 시뮬레이션, 구현
 * 2700ms
 */
public class SWEA_5653_줄기세포배양_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T;       // 테케 수
    static int N, M, K; // 맵 크기, 배양 시간
    static int time;    / / 현재 시간
    static Map<String, int[]> cells;    // x, y, 생명력 수치, 들어간 시간

    // 방향 벡터
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        T = parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            solve();
            sb.append(String.format("#%d %d\n", t, count()));
        }

        System.out.println(sb);
    }

    public static void solve() throws Exception {
        st = new StringTokenizer(br.readLine());

        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        K = parseInt(st.nextToken());

        cells = new HashMap<>();    // 세포 정보 저장
        time = 0;                   // 시간 초기화

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int temp = parseInt(st.nextToken());
                if (temp != 0) {
                    cells.put(returnKey(i, j), new int[] { i, j, temp, time }); // x, y, 생명력 수치, 들어간 시간
                }
            }
        }

        for (time = 1; time <= K; time++) {
            multiply(); // 세포 분열
        }
        // System.out.println(time);
    }

    /**
     * 세포 분열 메서드
     * 1. 복사본을 만들어서 세포를 분열 시킨다.
     * 2. 복사본을 통해서 세포를 분열 시킨다.
     * 3. 세포가 분열할 때, 생명력 수치가 더 큰 세포로 대체한다.
     */
    public static void multiply() {
        Map<String, int[]> copy = new HashMap<>(cells); // 복사본
        for (int[] val : copy.values()) {
            if (val[2] + val[3] + 1 == time) {  // 세포가 분열할 수 있는 시간
                for (int i = 0; i < 4; i++) {
                    int nx = val[0] + dx[i], ny = val[1] + dy[i];
                    String key = returnKey(nx, ny);
    
                    if (cells.containsKey(key)) {   // 이미 세포가 있는 경우
                        if (cells.get(key)[3] == time && cells.get(key)[2] < val[2]) {  // 생명력 수치가 더 큰 세포로 대체
                            cells.put(key, new int[] { nx, ny, val[2], time }); 
                        }
                    } else {
                        cells.put(key, new int[] { nx, ny, val[2], time });
                    }
                }
            }
        }
    }
    

    /**
     * 세포 수 세는 메서드
     * @return
     */
    public static int count() {
        int cnt = 0;
        for (int[] val : cells.values()) {
            if (val[2] * 2 + val[3] > (time-1)) cnt++;  // 세포가 살아있는 경우
        }

        return cnt;
    }

    /**
     * 좌표를 문자열로 변환하는 메서드
     * @param x
     * @param y
     * @return
     */
    public static String returnKey(int x, int y) {
        return Arrays.toString(new int[] { x, y });
    }
}