import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * SWEA_1953_탈주범검거
 * 난이도 5/10
 * BFS
 * 117ms 31mb
 * 
 * 탈주범이 n초 뒤에 있을수 있는 위치
 * 문제를 보니까 n초 뒤에 어디까지 bfs가 퍼질수 있냐 물어보는 문제임
 * visitesd 배열에 방문 체크를 몇 초에 방문했는지 체크해주고
 * 그 초에 방문한 곳에서 갈 수 있는 곳을 bfs로 탐색하며 카운트 해주면 된다
 * 파이프 모양에 따라 갈 수 있는 곳이 달라지므로 주의
 */
public class SWEA_1953_탈주범검거_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T;               // 테스트케이스 수
    static int N, M, R, C, L;   // 맵 크기, 시작 좌표, 시간
    static int[][] map;         // 맵

    // 방향 벡터
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static Map<Integer, Set<Integer>> pipe = new HashMap<>();   // 파이프 모양에 따라 갈 수 있는 방향을 저장하는 맵


    public static void main(String[] args) throws Exception {
        // 파이프 모양에 따라 갈 수 있는 방향을 저장하는 맵 초기화
        pipe.put(0, new HashSet<>());
        pipe.put(1, new HashSet<>(Arrays.asList(0, 1, 2, 3)));
        pipe.put(2, new HashSet<>(Arrays.asList(0, 2)));
        pipe.put(3, new HashSet<>(Arrays.asList(1, 3)));
        pipe.put(4, new HashSet<>(Arrays.asList(0, 1)));
        pipe.put(5, new HashSet<>(Arrays.asList(1, 2)));
        pipe.put(6, new HashSet<>(Arrays.asList(2, 3)));
        pipe.put(7, new HashSet<>(Arrays.asList(0, 3)));

        // 테스트케이스 수 입력 및 반복
        T = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append(String.format("#%d %d\n", t, solve()));
        }
        // 출력
        System.out.println(sb);
    }

    /**
     * 메인 메서드
     * @return  탈주범이 n초 뒤에 있을 수 있는 위치의 개수
     * @throws Exception
     */
    public static int solve() throws Exception {
        // 입력 처리 및 초기화
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

        return bfs();   // bfs 탐색 후 카운트 반환
    }

    /**
     * BFS 탐색 메서드
     * @return  탐색 후 카운트
     */
    public static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();    // 큐 선언
        int[][] visited = new int[N][M];        // 방문 배열 선언
        int cnt = 0;                            // 카운트 변수

        // 큐에 시작 좌표 넣고 방문 처리
        q.offer(new int[] { R, C});
        visited[R][C] = 1;
        cnt++;

        // BFS 탐색
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int x = temp[0], y = temp[1];

            if (visited[x][y] >= L) continue;   // L초가 지나면 종료

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

        return cnt;  // 탐색 후 카운트 반환
    }

    /**
     * 맵 범위 체크 메서드
     * @param x 행
     * @param y 열
     * @return  범위 안에 있으면 true, 아니면 false
     */
    public static boolean isIn(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
