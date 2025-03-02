import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 88 ms
 * backtrack 문제 + dfs
 * 어려웠다 어떤 dfs를 사용해야 하는건 알았는데 backtrack을 사용해서 다시 되돌려놔야 한다는 것을 늦게 깨달아서
 * 코드 고치는 데 오래 걸렸다.
 */
public class SWEA_1949_등산로조성 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        int tc = parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            sb.append("#" + t + " ");
            solve();
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int n, k;
    static int map[][];
    static boolean isVisited[][];
    static int maxVal = Integer.MIN_VALUE;
    static int res = 0;

    private static void solve() throws Exception {
        st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        k = parseInt(st.nextToken());
        map = new int[n][n];
        maxVal = Integer.MIN_VALUE;
        res = 0;
        int index = 0; //가장 높은 봉우리 최대 5개

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = parseInt(st.nextToken());
                maxVal = Math.max(maxVal, map[i][j]);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != maxVal) continue;
                if (index > 5) break; // 최대 봉우리 5개 끝나면 종료
                index++;
                // 가장 큰 값을 만나면 시작
                // dfs 들어가기 전에 방문 처리 초기화
                isVisited = new boolean[n][n];
                dfs(i, j, 1, false);
            }
        }
        sb.append(res);

    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static void dfs(int r, int c, int cnt, boolean flag) {
        isVisited[r][c] = true;
        res = Math.max(res, cnt);

        // 내 주위 4방을 돌면서 갈 수 있는 등산로 가기
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr >= 0 && nc >= 0 && nr < n && nc < n && !isVisited[nr][nc]) {
                if (map[nr][nc] < map[r][c]) { // 다음 노드가 현재보다 작을 경우
                    dfs(nr, nc, cnt + 1, flag);
                } else if (!flag && map[nr][nc] - k < map[r][c]) {
                    int origin = map[nr][nc];
                    map[nr][nc] = map[r][c] - 1; // 바꾼 값을 저장해 두고 다시 돌아왔을 때 복구해야 한다
                    dfs(nr, nc, cnt + 1, true);
                    map[nr][nc] = origin;
                }
            }
        }
        // 마지막에 방문 처리를 다시 false로 바꿔야 다음 dfs에 차질이 없음
        isVisited[r][c] = false;
    }
}
