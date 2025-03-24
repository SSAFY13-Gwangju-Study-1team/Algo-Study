package 스터디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 풀이 1 => 실패
 * - N X M 배열에 음식물의 위치 표시
 * - bfs로 시작 위치로부터 끝까지의 이동 칸 수 카운팅
 * - 예제의 경우 (1, 1)에서부터 이동 칸 수를 세다 보니 최대가 3으로 나옴
 * 풀이 2 with G쌤
 * - bfs()에서 음식물의 크기를 계산하고 이를 반환하여 max를 갱신
 * - count 배열 대신 visit 배열로 변경
 */
public class Main_1743_음식물_피하기_임규리 {

    // 상하좌우
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static int N, M, K;
    static boolean[][] map;
    static boolean[][] visit;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];
        visit = new boolean[N][M];
        max = Integer.MIN_VALUE;

        // 지도 만들기
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map[x - 1][y - 1] = true;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] && !visit[i][j]) {
                    max = Math.max(max, bfs(i, j));
                }
            }
        }

        System.out.println(max);
    }

    private static int bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visit[x][y] = true;
        int count = 1 ;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < dx.length; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (!isIn(nx, ny)) continue;    // 범위 밖
                if (visit[nx][ny]) continue;    // 이미 방문 완료
                if (!map[nx][ny]) continue;     // 음식물이 떨어진 곳이 아님

                q.add(new int[]{nx, ny});
                visit[nx][ny] = true;
                count++;
            }
        }

        return count;
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
