import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Main_2178_미로탐색_송준영
 * 난이도 2/10
 * BFS
 * 116ms 14mb
 * 
 * 이 문제는 최단 거리를 구하는 문제이지만 잘 보면 모든 칸 사이 거리가 1로 같다 -> BFS로도 풀 수 있다.
 * 마지막 지점 (N-1, M-1)에 도착하면 그 때의 visited 값을 출력해주면 된다
 * visited를 boolean으로 지정하지 않고 int로 지정해주면서 방문했을 때의 거리를 저장해주며 문제를 해결했다.
 * 이게 가능한 이유는 BFS의 특성 때문인데 BFS는 방문시 만나는 노드가 무조건 최단거리를 보장하기 때문에 가능하다 (각 간선의 가중치가 없거나 일정할때)
 */
public class Main_2178_미로탐색_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;        // 맵의 크기
    static char[][] map;    // 맵 배열 (char인 이유는 입력 받을때 연속적으로 받아서 array로 만들기 쉽게 하기 위해서 이다)
    static int[][] visited; // 방문 배열

    // 상하좌우 방향 벡터
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        // 입력 처리
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        // 초기화
        map = new char[N][M];
        visited = new int[N][M];

        // 맵 입력
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // BFS 시작
        bfs();
    }

    /**
     * BFS 메서드
     * 시작점은 (0, 0)이고 도착점은 (N-1, M-1)이다
     * 도착점에 도착하면 visited 값을 출력해주고 다른건 볼 필요도 없으니(?) 그 즉시 끝내주면 된다.
     */
    public static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();    // 큐 선언

        // 시작점 방문 처리
        q.offer(new int[] { 0, 0 });
        visited[0][0] = 1;

        // BFS 시작
        while(!q.isEmpty()) {
            int[] cur = q.poll();   // 현재 위치

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                // 범위 체크, 길이 맞는지 체크, 방문했는지 체크
                if (isIn(nx, ny) && map[nx][ny] == '1' && visited[nx][ny] == 0) {
                    q.offer(new int[] { nx, ny });
                    visited[nx][ny] = visited[cur[0]][cur[1]] + 1;  //  방문했을 때의 거리를 저장해준다, 각 칸 사이 거리가 1이므로 1씩 추가
                    if (nx == N - 1 && ny == M - 1) {   //  도착점에 도착하면 출력하고 끝
                        System.out.println(visited[nx][ny]);
                        return;
                    }
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
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}
