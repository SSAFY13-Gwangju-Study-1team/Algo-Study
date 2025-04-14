import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.*;

public class Main_1726_로봇_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int M, N;
    static int[][] map;
    static int[][][] visited;
    static int[] start, end;

    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    public static void main(String[] args) throws Exception{
        st = new StringTokenizer(br.readLine());
        M = parseInt(st.nextToken());
        N = parseInt(st.nextToken());

        map = new int[M][N];
        visited = new int[M][N][4];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = parseInt(st.nextToken());
            }
        }

        start = new int[3];
        end = new int[3];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            if (i == 2) {
                start[i] = badDirectionConverter(parseInt(st.nextToken()) - 1);
                break;
            }
            start[i] = parseInt(st.nextToken()) - 1;
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            if (i == 2) {
                end[i] = badDirectionConverter(parseInt(st.nextToken()) - 1);
                break;
            }
            end[i] = parseInt(st.nextToken()) - 1;
        }

        if (start[0] == end[0] && start[1] == end[1] && start[2] == end[2]) {
            System.out.println(0);
            return;
        }

        bfs(start[0], start[1], start[2]);


        System.out.println(visited[end[0]][end[1]][end[2]]);
    }

    public static void bfs(int sx, int sy, int dir) {
        Queue<int[]> q = new ArrayDeque<>();
        visited[sx][sy][dir] = 0;
        q.offer(new int[]{sx, sy, dir});
    
        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0], y = now[1], d = now[2];
    
            // 회전 명령
            for (int i = -1; i <= 1; i += 2) { // i = -1(left), 1(right)
                int nd = (d + i + 4) % 4; // 회전 방향
                if (visited[x][y][nd] == 0) {
                    visited[x][y][nd] = visited[x][y][d] + 1;
                    q.offer(new int[]{x, y, nd});
                }
            }
    
            // 1~3칸 전진
            for (int k = 1; k <= 3; k++) {
                int nx = x + dx[d] * k;
                int ny = y + dy[d] * k;
    
                if (!isIn(nx, ny) || map[nx][ny] == 1) break;
                if (visited[nx][ny][d] == 0) {
                    visited[nx][ny][d] = visited[x][y][d] + 1;
                    q.offer(new int[]{nx, ny, d});
                }
            }
        }
    }
    

    // public static void print() {
    //     for (int i = 0; i < M; i++) {
    //         for (int j = 0; j < N; j++) {
    //             int m = 100000;
    //             for (int k = 0; k < 4; k++) {
    //                 if (visited[i][j][k] != 0) {
    //                     m = Math.min(m, visited[i][j][k]);
    //                 }
    //             }
    //             System.out.print((m != 100000 ? m : 0) + " ");
    //         }
    //         System.out.println();
    //     }
    //     System.out.println();
    // } 

    public static boolean isIn(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }

    public static int badDirectionConverter(int dir) {
        switch (dir) {
            case 0: return 1; // 동 -> 0 → 1 (내부)
            case 1: return 3; // 서 -> 1 → 3
            case 2: return 2; // 남 -> 2 → 2
            case 3: return 0; // 북 -> 3 → 0
            default: return -1;
        }
    }
}