import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * 아이디어
 * - 사방탐색으로 줄어드는 빙산 개수 계산
 * - 계산 후 BFS로 빙산 덩어리 개수 계산
 * - 전부 다 녹을 때까지 두 덩어리 이상으로 나눠지지 않으면 0 출력
 * -------------------------------------------------
 * 1트 ~ 6트 => 시간초과
 * - 남은 빙산 개수를 업데이트 하는 과정에서 불필요한 반복문 호출 때문
 */
public class Main_2573_빙산_임규리 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int N;   // 행 개수
    static int M;   // 열 개수
    static int[][] map; // 지도
    static boolean[][] visited;     // 방문 관리 배열
    static List<int[]> icebergs;    // 빙산 좌표 리스트
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        icebergs = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = parseInt(st.nextToken());

                // 빙산인 곳들 저장
                if (map[i][j] != 0) {
                    icebergs.add(new int[]{i, j});
                }
            }
        }

        // 몇 번 호출했는지 카운팅
        int count = 0;

        while (true) {
            if (getCount() >= 2) {  // 빙산 덩어리가 2개 이상이 되면 끝내기
                result = count;
                break;
            }

            if (icebergs.isEmpty()) {   // 모든 빙산을 계산했는데도 안 끝나면 0
                result = 0;
                break;
            }

            meltIceberg();  // 빙산 녹이기
            count++;        // 호출 횟수 ++
        }

        System.out.println(result);
    }

    private static int getCount() {
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }

        int count = 0;

        for (int[] cur : icebergs) {
            if (!visited[cur[0]][cur[1]] && map[cur[0]][cur[1]] != 0) {
                findIceberg(cur[0], cur[1]);
                count++;    // 빙산 덩어리 찾은 횟수 계산
            }
        }

        return count;
    }

    // 빙산 덩어리 1개 구하기
    private static void findIceberg(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || nx > N - 1 || ny < 0 || ny > M -1) {
                    continue;
                }

                if (!visited[nx][ny] && map[nx][ny] != 0) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }

    private static void meltIceberg() {
        int[][] countOcean = new int[N][M];    // 빙산을 바로 map에서 녹여버리면 제대로 처리가 되지 않음
        List<int[]> remain = new ArrayList<>(); // 남은 빙산 좌표 저장 리스트

        for (int[] cur : icebergs) {
            int count = 0;

            for (int j = 0; j < 4; j++) {
                int nx = cur[0] + dx[j];
                int ny = cur[1] + dy[j];

                if (nx < 0 || nx > N - 1 || ny < 0 || ny > M - 1) {
                    continue;
                }

                // 사방 탐색하며 바다와 닿는 부분 계산
                if (map[nx][ny] == 0) {
                    count++;
                }
            }

            // 바다와 닿는 부분 개수 저장
            countOcean[cur[0]][cur[1]] = count;
        }

        for (int[] temp : icebergs) {
            // 바다에 닿는 부분을 뺀 값과 0 중 큰 수 저장
            map[temp[0]][temp[1]] = Math.max(map[temp[0]][temp[1]] - countOcean[temp[0]][temp[1]], 0);

            // 0보다 크다면 남은 빙산 리스트에 저장
            if (map[temp[0]][temp[1]] > 0) {
                remain.add(new int[]{temp[0], temp[1]});
            }
        }

        // 갈아끼우기
        icebergs = remain;
    }
}
