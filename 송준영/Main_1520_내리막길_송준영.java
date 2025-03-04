import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1520_내리막길_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static int[][] arr;
    static boolean[][] visited;
    static int[][] possible;

    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M];
        possible = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = parseInt(st.nextToken());
            }
            Arrays.fill(possible[i], -1);
        }

        dfs(0, 0, arr[0][0]);

        System.out.println(possible[0][0]);

        // for (int i = 0; i < N; i++) {
        //     for (int j = 0; j < M; j++) {
        //         System.out.print(possible[i][j] + " ");
        //     }
        //     System.out.println();
        // }
    }
    
    public static void dfs(int x, int y, int val) {
        if (x == N - 1 && y == M - 1) {
            possible[x][y] = 1;
            // System.out.println("나는 왔다 : " + x + " " + y);
            return;
        }
        
        // System.out.println("나는 왔다 : " + x + " " + y);

        int flag = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];

            if (isIn(nx, ny) && (arr[nx][ny] < val) && !visited[nx][ny]) {
                if (possible[nx][ny] == 0)
                    continue;
                if (possible[nx][ny] > 0) {
                    flag += possible[nx][ny];
                    continue;
                }

                visited[nx][ny] = true;
                dfs(nx, ny, arr[nx][ny]);
                flag += possible[nx][ny];
                visited[nx][ny] = false;
            }
        }

        possible[x][y] = flag;
    }
    
    public static boolean isIn(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
