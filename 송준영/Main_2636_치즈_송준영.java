import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Main_2636_치즈
 * 난이도 6/10
 * BFS
 * 156ms 16mb
 * 
 * 치즈의 가장자리만 녹고 그 녹은 개수를 세야한다
 * 다행인거는 여기에 힌트가 있다, map의 가장자리에는 치즈가 놓이지 않는다 -> 가장자리 빈칸에서 BFS 돌려서 체크해봐라?
 * 그래서 가장자리에서 BFS를 돌리면 치즈가 바깥 공기와 만나는 부분만 접촉할 수 있다!
 * 
 * 그래서 BFS를 돌리면서 치즈를 만나면 녹이고, 공기를 만나면 큐에 넣어주면 된다
 * -> 치즈를 녹인후 visited 체크해줘서 다시 체크 안하게 막아야 한다
 * -> 치즈 녹일 때 마다 cnt++ 해주면서 마지막에 리턴해주면 된다
 * -> 그리고 치즈녹인 수가 0이면 치즈가 없는거니까 종료
 */
public class Main_2636_치즈_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static int R, C, time, result;  // 행, 열, 시간, 결과
    static int[][] map;             // 맵
    static boolean[][] visited;     // 방문 배열

    // 방향 벡터
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        // 입력 처리 및 초기화
        st = new StringTokenizer(br.readLine());
        R = parseInt(st.nextToken());
        C = parseInt(st.nextToken());

        map = new int[R][C];
        visited = new boolean[R][C];
        time = 0;
        result = 0;

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = parseInt(st.nextToken());
            }
        }

        int cnt; // 치즈 녹인 개수를 저장할 임시 변수
        while ((cnt = bfs()) != 0) { // 녹은 치즈가 0 (치즈가 없을때까지) 반복 -> 이 방법은 치즈가 없어도 bfs를 한번 더 돌아야 한다 -> 더 좋은 방법있으면 추천좀
            time++; // 시간 증가
            result = cnt; // 치즈 녹인 개수 저장

            // for (int i = 0; i < R; i++) {
            //     for (int j = 0; j < C; j++) {
            //         System.out.print(map[i][j] + " ");
            //     }
            //     System.out.println();
            // }
            visited = new boolean[R][C]; // 방문 배열 초기화
        }

        // 결과 출력
        sb.append(time + "\n").append(result);
        System.out.println(sb);

    }

    /**
     * BFS 메서드
     * @param x 행
     * @param y 열
     * @return  녹인 치즈 개수
     */
    public static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();    // 큐
        int cnt = 0;                            // 녹인 치즈 개수

        // 초기값 처리
        q.offer(new int[] { 0, 0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i], ny = cur[1] + dy[i];

                if (!isIn(nx, ny) || visited[nx][ny])   // 범위를 벗어나거나 방문한 곳이면 continue
                    continue;

                if (map[nx][ny] == 1) { // 치즈를 만나면 녹이고 카운트 증가
                    visited[nx][ny] = true; // 방문처리 해줘야 중복 체크 안함
                    map[nx][ny] = 0;
                    cnt++;
                } else {                // 공기를 만나면 큐에 넣어준다
                    visited[nx][ny] = true;
                    q.offer(new int[] { nx, ny });
                }
            }
        }

        return cnt; // 녹인 치즈 개수 리턴
    }

    /**
     * 범위 체크 메서드
     * @param x 행
     * @param y 열
     * @return  범위 안이면 true, 아니면 false
     */
    public static boolean isIn(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
