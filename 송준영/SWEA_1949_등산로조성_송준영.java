import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * SWEA_1949_등산로조성
 * 난이도 7/10
 * DFS
 * 100ms 28mb
 * 
 * 오랜 만의 DFS문제라 힘들었다
 * DFS를 하면서 절벽을 깎는 경우와 안 깎는 경우를 나누어 처리해 주었음
 * 깎는 경우는 K만큼 깎을 수 있으므로 K번 반복문을 돌면서 깎았을때 갈 수 있으면 멈추고 절벽을 깎아주고 DFS를 진행해 주었음
 * depth를 result와 계속 비교하며 가장 긴 길이를 구해 주었음
 */
public class SWEA_1949_등산로조성_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T;                   // 테케 수
    static int N, K, maxPeek;       // 맵 크기, 절벽 높이
    static int[][] map;             // 맵
    static boolean[][] isVisited;   // 방문 배열
    static List<int[]> peeks;       // 절벽 좌표 리스트
    static int result;              // 결과값

    // 방향 벡터
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        // 테케 수 입력 및 반복
        T = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            solve();
            sb.append(String.format("#%d %d\n", t, result));
        }

        // 출력
        System.out.println(sb);
    }

    /**
     * 메인 메서드
     * @throws Exception
     */
    public static void solve() throws Exception {
        // 입력 처리 및 초기화
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        K = parseInt(st.nextToken());
        maxPeek = 0;
        result = 0;

        peeks = new ArrayList<>();
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = parseInt(st.nextToken());
                if (maxPeek < map[i][j]) {
                    peeks.clear();
                    maxPeek = map[i][j];
                    peeks.add(new int[] { i, j });
                } else if (maxPeek == map[i][j]) {
                    peeks.add(new int[] { i, j }); 
                }
            }
        }

        // 정상에서만 시작 -> DFS
        for (int[] p : peeks) {
            isVisited = new boolean[N][N];  // 방문 배열 초기화
            advDfs(p[0], p[1], map[p[0]][p[1]], 0, 1);  
        }
    }

    /**
     * DFS 메서드
     * @param x 시작점 x좌표
     * @param y 시작점 y좌표
     * @param val   절벽 높이
     * @param flag  절벽을 깎았는지 여부 (0: 안 깎음, 1: 깎음)
     * @param depth 현재 깊이
     */
    public static void advDfs(int x, int y, int val, int flag, int depth) {
        result = Math.max(result, depth);   // 최대 깊이 갱신
        isVisited[x][y] = true;             // 방문 처리


        // 상하좌우 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
    
            if (!isIn(nx, ny)) continue;        // 범위 체크
            if (isVisited[nx][ny]) continue;    // 방문 체크
    
            // 절벽을 아직 깎지 않은 경우
            if (flag == 0) {
                for (int k = 0; k <= K; k++) {
                    int newHeight = map[nx][ny] - k;    // 절벽을 깎은 높이
                    if (val > newHeight) {
                        advDfs(nx, ny, newHeight, k > 0 ? 1 : 0, depth + 1);
                        break; // 한 번만 깎고 더 시도하지 않음
                    }
                }
            } else {    // 절벽을 이미 한 번 깎은 경우
                if (val > map[nx][ny]) {
                    advDfs(nx, ny, map[nx][ny], 1, depth + 1);
                }
            }
        }

        // 방문 체크 해제
        isVisited[x][y] = false;
    }

    /**
     * 범위 체크 함수
     * @param x 행
     * @param y 열
     * @return  범위 안이면 true, 아니면 false
     */
    public static boolean isIn(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
