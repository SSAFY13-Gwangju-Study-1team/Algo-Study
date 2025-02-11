import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 이미 벽에 닿은 프로세서는 포함 안하기
 * 처음 프로세서 부터 백트래킹
 * 각 백트래킹 프로세스는 4방향 탐색 적용 -> visited 배열 이용
 * 4방향 다 안되는 상황 발생하면 뒤로 가기 == return
 * 여러 방법 경우 최소의 경우니까 min 스태틱 설정해서 될때마다 갱신
 */
public class Solution_1767_프로세서연결하기 {
    

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    // static int result;
    static int[] resultList;
    static int[][] map;
    static boolean[][] visited;
    static List<int[]> processors;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    public static void main(String[] args) throws Exception {
        int T = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int result = 0;
            solve();
            for (int i = resultList.length - 1; i > -1; i--) {
                if (resultList[i] != Integer.MAX_VALUE) {
                    result = resultList[i];
                    break;
                }
            }
            for (int e : resultList) {
                System.out.print(e + " ");
            }
            System.out.println();
            sb.append(String.format("#%d %d\n", t, result));
        }
        System.out.println(sb);
    }
    
    public static void solve() throws Exception {
        N = parseInt(br.readLine());
        // result = Integer.MAX_VALUE;
        
        map = new int[N][N];
        visited = new boolean[N][N];
        processors = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    if (!isSide(N, i, j))
                        processors.add(new int[] { i, j }); // 가장자리에 있으면 방문만, 없으면 프로세서 리스트에 추가
                    visited[i][j] = true;
                }
            }
        }

        resultList = new int[processors.size() + 1];
        Arrays.fill(resultList, Integer.MAX_VALUE);
        
        backtrack(processors.size(), 0, 0);
    }

    public static void backtrack(int processorNum, int depth, int cable) {
        resultList[depth] = Math.min(resultList[depth], cable);
        if (processorNum == depth) {
            // result = Math.min(result, cable);
            return;
        }

        // 현재 프로세서 좌표
        int x = processors.get(depth)[0];
        int y = processors.get(depth)[1];

        Loop1: for (int i = 0; i < 4; i++) {
            int nx, ny;
            int tempCable = 1;
            while (true) {
                nx = x + dx[i] * tempCable;
                ny = y + dy[i] * tempCable;

                if (!isIn(N, nx, ny)) {
                    backtrack(processorNum, depth + 1, cable + tempCable - 1);
                    break;
                } else if (visited[nx][ny]) {
                    break;
                }
                visited[nx][ny] = true;
                tempCable++;
            }
            
            for (int j = 1; j < tempCable; j++) {
                nx = x + dx[i] * j;
                ny = y + dy[i] * j;
                visited[nx][ny] = false;
            }
        }
    }
    
    public static boolean isIn(int N, int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    /**
     * 가장자리에 있는지 확인
     * @param N
     * @param x
     * @param y
     * @return  있으면 true
     */
    public static boolean isSide(int N, int x, int y) {
        int nx, ny;

        for (int i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];
            if (!isIn(N, nx, ny))
                return true;
        }
        return false;
    }
}
