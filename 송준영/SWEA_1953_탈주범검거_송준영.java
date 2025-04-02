import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.*;

public class SWEA_1953_탈주범검거_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T;
    static int N, M, R, C, L;
    static int[][] map;

    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static Map<Integer, Set<Integer>> pipe = new HashMap<>();


    public static void main(String[] args) throws Exception {
        pipe.put(0, new HashSet<>());
        pipe.put(1, new HashSet<>(Arrays.asList(0, 1, 2, 3)));
        pipe.put(2, new HashSet<>(Arrays.asList(0, 2)));
        pipe.put(3, new HashSet<>(Arrays.asList(1, 3)));
        pipe.put(4, new HashSet<>(Arrays.asList(0, 1)));
        pipe.put(5, new HashSet<>(Arrays.asList(1, 2)));
        pipe.put(6, new HashSet<>(Arrays.asList(2, 3)));
        pipe.put(7, new HashSet<>(Arrays.asList(0, 3)));


        T = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append(String.format("#%d %d\n", t, solve()));
        }

        System.out.println(sb);
    }

    public static int solve() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        R = parseInt(st.nextToken());
        C = parseInt(st.nextToken());
        L = parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = parseInt(st.nextToken());
            }
        }

        return bfs();
    }

    public static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        int[][] visited = new int[N][M];
        int cnt = 0;

        q.offer(new int[] { R, C});
        visited[R][C] = 1;
        cnt++;

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int x = temp[0], y = temp[1];

            if (visited[x][y] >= L) continue; 

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                // System.out.println(map[x][y] + " 결과 : " + pipe.get(map[x][y]));

                // 일단 맵 밖에 안나가고 방문 안했으며 파이프가 연결되어 있고 그 통로로 갈 수 있는 경우
                if (isIn(nx, ny) && visited[nx][ny] == 0 && pipe.get(map[x][y]).contains(i) && pipe.get(map[nx][ny]).contains((i + 2) % 4)) {
                    visited[nx][ny] = visited[x][y] + 1;
                    // System.out.println(nx + " " + ny);
                    cnt++;
                    q.offer(new int[] { nx, ny });
                }
            }
        }

        return cnt;
    }

    public static boolean isIn(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
