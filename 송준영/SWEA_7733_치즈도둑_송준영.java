import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SWEA_7733_치즈도둑
 * 난이도 4/10
 * BFS
 * 299ms 98mb
 * 
 * 이 문제는 저번 월말평가 2번 문제랑 같은 로직의 문제이다, 다만 테스트케이스를 곁들인
 * 그래서 2번 문제를 풀었던 사람은 같은 방법으로 풀면 풀린다
 * 여기서 주의해야할 점은 치즈의 맛이 1부터 100까지라는 것이다
 * 그래서 치즈의 맛을 1부터 100까지 돌면서 BFS를 돌려서 BFS 횟수를 세어주면 된다 -> 아니면 map에 있는 숫자만 돌리든 min,max를 구해서 돌리든 자유다
 * -> 다만 다른 방법으로 하려면 처음 덩어리 개수 값을 잘 정해주어야 한다
 * 
 * 각각의 먹는 맛으로 map 전체를 돌며 bfs를 실행 하는데, bfs를 실행할 때마다 먹는 맛보다 큰 치즈를 만나면 bfs를 실행한다
 * bfs는 먹는 맛보다 큰 구역만 돌게 한다
 * bfs가 실행된 횟수가 곧 치즈 덩어리 개수이다!
 */
public class SWEA_7733_치즈도둑_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, result;           // 맵 크기, 결과
    static int[][] map;             // 맵
    static boolean[][] visited;     // 방문 배열
    static int minTaste, maxTaste;  // 최소, 최대 맛

    // 방향 벡터
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        // 테케 수 입력 및 테케 수 만큼 반복
        int T = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            solve();
            sb.append(String.format("#%d %d\n", t, result));
        }
        // 결과 출력
        System.out.println(sb);
    }

    /**
     * 메인 메서드
     * @throws Exception
     */
    public static void solve() throws Exception {
        // 입력 처리 및 초기화
        N = parseInt(br.readLine());
        map = new int[N][N];
        result = 1; // 덩어리는 무조건 1개 이상이니까 1로 초기화

        minTaste = Integer.MAX_VALUE;
        maxTaste = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = parseInt(st.nextToken());
                minTaste = Math.min(minTaste, map[i][j]);
                maxTaste = Math.max(maxTaste, map[i][j]);
            }
        }

        // 최소 맛부터 최대 맛까지 돌면서 bfs 실행 -> 좀 더 시간 줄이고 싶으면 map에 있는 수로만 돌리게 만들어도 될 듯
        for (int k = minTaste; k < maxTaste; k++) {
            visited = new boolean[N][N];    // 방문 배열 초기화
            int cnt = 0;                    // 덩어리 개수

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && map[i][j] > k) { // 방문하지 않았고, 먹는 맛보다 큰 치즈라면 bfs 실행
                        bfs(i, j, k);
                        cnt++; // bfs 실행 횟수 == 덩어리 개수
                    }
                }
            }

            // 최대값 갱신
            result = Math.max(result, cnt);
        }
    }

    /**
     * BFS 메서드
     * @param x 시작 행
     * @param y 시작 열
     * @param taste 지금 먹는 맛
     */
    public static void bfs(int x, int y, int taste) {
        Queue<int[]> q = new ArrayDeque<>();

        // 시작값 처리
        visited[x][y] = true;
        q.offer(new int[] { x, y });

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i], ny = cur[1] + dy[i];

                // 범위 안에 있고, 방문하지 않았고, 먹는 맛보다 큰 치즈라면 -> 방문처리 후 큐에 넣기
                if (isIn(nx, ny) && !visited[nx][ny] && map[nx][ny] > taste) {
                    visited[nx][ny] = true;
                    q.offer(new int[] { nx, ny });
                }
            }
        }
    }

    /**
     * 범위 체크 메서드
     * @param x 행
     * @param y 열
     * @return  범위 안에 있으면 true, 아니면 false
     */
    public static boolean isIn(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
