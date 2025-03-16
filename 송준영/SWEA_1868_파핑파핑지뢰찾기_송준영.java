import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * SWEA 1868 파핑파핑 지뢰찾기
 * 난이도 4/10
 * BFS, 그리디?
 * 169ms 45mb
 * 
 * 지뢰찾기 문제인데 은근 함정이 있다 -> 예시 사진에는 숫자로 표기를 하고 주위 지뢰수를 체크했는데
 * => 안 해도 됨 함정임;;
 * 문제를 잘 읽고 목표하는 것을 보면 그냥 최소 몇 번 클릭하냐 여서 주위 지뢰수를 체크할 필요는 없음
 * 
 * 다만 여기서 최소 클릭 수를 어떻게 푸냐가 문제인데
 * -> 주위에 지뢰가 없는 칸을 먼저 클릭한 후 주위에 지뢰있는 칸을 클릭하면 그게 최소가 됨
 * -> 주위 지뢰 없는 칸 클릭시 주위 칸들도 자동으로 퍼지며 클릭되서 이것을 먼저 클릭해야 최소값에 갈 수 있음
 * 
 * 그러면 주위 지뢰도 클릭하게 하려면 어떻게 해야하나? -> BFS 이용
 * 주위 지뢰없는 칸 클릭시 8방 탐색으로 BFS이용 만약 현재노드 주위에 지뢰가 없으면 계속 BFS , 있다면 그 노드는 더이상 번지지 않게 구현
 * 방문 처리는 따로 구현하는 것 보다 map안에서 구현하는게 나중에 남은 빈칸 셀 때 편할 것 같아서 map을 visited 배열로 사용 했다 -> 방문시 'v'로 변경
 * 
 * map을 다 돌면 주위에 지뢰가 없고 방문하지 않은 칸들을 클릭하면서 bfs 카운트를 세면 됨
 * 그후 다시 map을 전부 돌면서 남은 빈칸들을 세면 됨
 * => bfs 카운트 + 남은 빈칸들의 수 = 결과(최소값)
 * 
 * 로직만 잘 생각나면 생각포다 구현은 어렵지 않은 문제였다
 * -> 하지만 접근방법을 생각하는게 어려워보이는 문제임
 */
public class SWEA_1868_파핑파핑지뢰찾기_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N, cnt;      // N: 맵 크기, cnt: 클릭 수
    static char[][] map;    // 맵
    
    // 8방향 탐색을 위한 배열
    static int[] dx = { -1, 0, 1, 0, -1, 1, 1, -1 };
    static int[] dy = { 0, 1, 0, -1, 1, 1, -1, -1 };

    public static void main(String[] args) throws Exception {
        // 테스트 케이스 수 입력 및 테케만큼 돌리기
        int T = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            solve();
            sb.append(String.format("#%d %d\n", t, cnt));
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
        map = new char[N][N];
        cnt = 0;

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // map을 전부 순회 하며 bfs조건에 맞으면 bfs 실행 및 cnt++
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!isAroundMine(i, j) && map[i][j] == '.') { // 주위에 지뢰가 없고 빈칸이면
                    cnt++;
                    bfs(i, j);
                }
            }
        }

        // bfs 전부 순회 후(주위에 지뢰가 없는 칸 모두 클릭 후) 남은 빈칸(주위에 지뢰가 있어 클릭하면 1개만 클릭되는)들을 세기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '.') {
                    cnt++;
                }
            }
        }

        // System.out.println();
        // for (int i = 0; i < N; i++) {
        //     for (int j = 0; j < N; j++) {
        //         System.out.print(map[i][j]);
        //     }
        //     System.out.println();
        // }
        // System.out.println();
    }
    
    /**
     * bfs 메서드
     * @param x 시작점 행
     * @param y 시작점 열
     */
    public static void bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>(); // bfs를 위한 큐

        // 시작점 방문 처리 및 큐에 삽입
        map[x][y] = 'v';
        q.offer(new int[] { x, y });

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (isAroundMine(cur[0], cur[1])) // 주위에 지뢰가 있으면 더이상 번지지 않게
                continue;

            // 8방향 탐색
            for (int i = 0; i < 8; i++) {
                int nx = cur[0] + dx[i], ny = cur[1] + dy[i];

                if (isIn(nx, ny) && map[nx][ny] == '.') { // 주위에 지뢰가 없고 빈칸이면 처리
                    map[nx][ny] = 'v';
                    q.offer(new int[] { nx, ny });
                }
            }
        }
    }
    
    /**
     * 범위 체크 메서드
     * @param x 행
     * @param y 열
     * @return  범위 안이면 true, 아니면 false
     */
    public static boolean isIn(int x, int y) {
        return x >= 0 & x < N && y >= 0 && y < N;
    }

    /**
     * 주위에 지뢰가 있는지 체크하는 메서드
     * @param x 행
     * @param y 열
     * @return  주위에 지뢰가 있으면 true, 아니면 false
     */
    public static boolean isAroundMine(int x, int y) {
        for (int i = 0; i < 8; i++) {   // 8방향 탐색
            int nx = x + dx[i], ny = y + dy[i];
            if (isIn(nx, ny) && map[nx][ny] == '*') // 주위에 지뢰가 있으면
                return true;
        }
        return false;
    }
}
