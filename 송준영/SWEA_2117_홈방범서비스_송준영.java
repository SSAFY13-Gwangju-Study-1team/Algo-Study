import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * SWEA_2117_홈방범서비스
 * 난이도 6/10
 * 
 * BFS
 * 
 * 각 점마다 퍼져나가는 모양을 BFS로 구현하여 K의 범위 내에 있는 집의 수를 구하고
 * 그 집의 수에 따른 비용을 계산하여
 * 비용이 K의 범위 내에 있는 집의 수 * M - K^2 - (K-1)^2 이 0보다 크거나 같으면
 * 그 집의 수를 result에 저장한다.
 * 
 * 테스트케이스 별로 처리하는 것과 맵을 다 돌면서 BFS를 하며 BFS 된 횟수를 구하면 그게 답이 된다
 * 근데 너무 완탐하긴 하는데 일단 통과는 해서....
 * 더 줄일 수 있는 방법을 찾아보아야 할 것 같다
 */
public class SWEA_2117_홈방범서비스_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T;               // 테케 수
    static int N, M, result;    // 맵 크기, 집 수, 결과
    static int[][] map;         // 맵
    

    // 방향 벡터
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        // 테케 수 입력 및 반복
        T = parseInt(br.readLine());
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
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        result = 0;
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = parseInt(st.nextToken());
            }
        }

        // 각 점마다 BFS를 돌리며 집의 수를 구하고 비용을 계산하여 결과를 갱신
        for (int K = 1; K <= N+1; K++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    calCost(K, i, j);
                }
            }
        }
    }

    /**
     * BFS를 돌리며 집의 수를 구하고 비용을 계산하는 메서드
     * @param K     K의 범위
     * @param sx    시작 x 좌표    
     * @param sy    시작 y 좌표
     * @return      계산된 비용
     */
    public static int calCost(int K, int sx, int sy) {
        Queue<int[]> q = new ArrayDeque<>();    // BFS를 위한 큐
        int homeCnt = 0;                        // 집의 수
        int cost = K * K + (K - 1) * (K - 1);   // 비용
        int[][] visited = new int[N][N];        // 방문 체크 배열

        // 시작 좌표를 큐에 넣고 방문 체크
        visited[sx][sy] = 1;
        if (map[sx][sy] == 1) homeCnt++;
        q.offer(new int[] {sx, sy});
        
        // BFS를 돌리며 집의 수를 구하고 비용을 계산
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int x = temp[0];
            int y = temp[1];

            if (visited[x][y] >= K) continue;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (isIn(nx, ny) && visited[nx][ny] == 0) {
                    visited[nx][ny] = visited[x][y] + 1;
                    if (map[nx][ny] == 1) homeCnt++;
                    q.offer(new int[] { nx, ny });
                }
            }
        }

        // 비용 계산
        int finalCost = (homeCnt * M) - cost;

        // 비용이 0보다 크거나 같으면 결과를 갱신
        if (finalCost >= 0) {
            result = Math.max(result, homeCnt);
        }

        return finalCost;   // 비용 반환
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
