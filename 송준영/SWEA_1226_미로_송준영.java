import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.*;

public class SWEA_1226_미로_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static char[][] map;
    static int test;
    static int[] start, end;

    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        for (int t = 1; t <= 10; t++) {
            test= parseInt(br.readLine());
            sb.append(String.format("#%d %d\n", test, solve()));
        }
        System.out.println(sb);
    }

    public static int solve() throws Exception {
        map = new char[16][16];
        start = new int[2];
        end = new int[2];

        for (int i = 0; i < 16; i++) {
            String str = br.readLine();
            for (int j = 0; j < 16; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == '2') {
                    start[0] = i;
                    start[1] = j;
                } else if (map[i][j] == '3') {
                    end[0] = i;
                    end[1] = j;
                }
            }
        }

        return bfs(start[0], start[1]);
    }

    public static int bfs(int sx, int sy) {
        boolean[][] visited = new boolean[16][16];
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[] { sx, sy });
        visited[sx][sy] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            if (x == end[0] && y == end[1]) {
                return 1;
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (isIn(nx, ny) && !visited[nx][ny] && map[nx][ny] != '1') {
                    visited[nx][ny] = true;
                    queue.offer(new int[] { nx, ny });
                }
            }
        }

        return 0;
    }

    public static boolean isIn(int x, int y) {
        return x >= 0 && x < 16 && y >= 0 && y < 16;
    }
}
