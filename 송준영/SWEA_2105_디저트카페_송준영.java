import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * SWEA_2105_디저트카페
 * 난이도 6/10
 * DFS, 백트래킹?
 * 173ms 34mb
 * 
 * 사각형으로 돌아야하는 DFS 문제
 * 문제 풀이는 생각보다 어렵지 않지만 최적화하는 것을 잘 생각해야 한다고 생각한다
 * Set 사용해서 중복 처리 -> 시간 생각보다? 꽤 걸림 => 배열로 전환하니 반정도 줄어듦 wow
 * 그냥 사각형 방향으로 순회하는경우 시간이 꽤 많이 소요 -> 한 방향으로 돌때 3, 4 번째 선분이면 유망한 노드만 찾아서 떠나기(가지치기)
 * 
 * 머리아파서 주석 더 자세히 써야하는데 힘드네요 좀 쉬겠습니다.
 * 
 * => 근데 방금 생각난건데 최대 사각형 길이니까 맵 끝까지 간 돌고, 그다음 돌고 그러면 될 것 같은데 -> 이러면 시간 좀더 많이 줄어들 듯?
 */
public class SWEA_2105_디저트카페_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, result, startX, startY;   // 맵 크기, 결과, 시작점
    static int[][] arr;                     // 맵
    static boolean[][] visited;             // 방문 배열
    static boolean[] dupleInt;

    // 방향 벡터
    static int[] dx = { -1, 1, 1, -1 };
    static int[] dy = { 1, 1, -1, -1 };

    public static void main(String[] args) throws Exception {
        // 테케 수 입력 및 테케 수 만큼 반복
        int T = parseInt(br.readLine());
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
        N = parseInt(br.readLine());
        arr = new int[N][N];
        visited = new boolean[N][N];
        dupleInt = new boolean[101];
        result = -1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = parseInt(st.nextToken());
            }
        }

        // 모든 노드에서 시작 근데 사각형이 될 수 있는 노드만 (가지치기 1)
        for (int i = 0; i < N - 2; i++) {
            for (int j = 1; j < N - 1; j++) {
                // 시작점 처리 및 방문, 중복 처리
                startX = i;
                startY = j;
                visited[i][j] = true;
                dupleInt[arr[i][j]] = true;
                dfs(i, j, 0, 1);
                dupleInt[arr[i][j]] = false;
                visited[i][j] = false;
            }
        }
    }

    /**
     * DFS 메서드
     * @param x 행
     * @param y 열
     * @param depth 선분들의 길이
     * @param dir   현재 가고 있는 방향
     */
    public static void dfs(int x, int y, int depth, int dir) {
        for (int i = dir; i <= dir + 1; i++) {
            int d = i % 4; // 방향

            if (dir == 3) { // 3번째 선분이면
                if (startX - startY == x - y) // 같은 선상에 있으면 방향 전환 만
                    continue;
                if (startX + startY != x + y && d == 0) // 같은 선상에 없으면 직진만
                    break;
            }

            // 처음 시작이면 
            if (dir == 0 && d == 1) {
                break;
            }

            // 처음 노드는 한 방향만
            if (depth == 0 && i == dir + 1)
                break;

            // 다음 노드
            int nx = x + dx[d], ny = y + dy[d];

            if (!isIn(nx, ny)) // 범위 밖이면 무시
                continue;

            // 방문하지 않았고 중복되지 않은 노드면
            if (!visited[nx][ny] && !dupleInt[arr[nx][ny]]) {
                visited[nx][ny] = true;
                dupleInt[arr[nx][ny]] = true;
                dfs(nx, ny, depth + 1, d);
                dupleInt[arr[nx][ny]] = false;
                visited[nx][ny] = false;
            } else if (nx == startX && ny == startY && depth > 1 && (d == 1 || d == 0)) { // 주변에 시작점이 있는 경우 그리고 진행 방향인경우
                result = Math.max(result, depth + 1);
            }
        }
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