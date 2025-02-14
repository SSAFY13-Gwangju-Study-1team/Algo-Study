package 스터디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2589_보물섬_임규리 {

    // 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int N, M;        // 행 열
    static char[][] map;    // 지도
    static int[][] dist;    // 거리
    static int max;         // 결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'L') { // 육지라면
                    dist = new int[N][M];   // 거리 초기화
                    bfs(i, j);
                    getMax();   // 최대 거리 업데이트
                }
            }
        }

        System.out.println(max - 1);
    }

    private static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        dist[x][y] = 1;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < dx.length; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (!isIn(nx, ny)) continue;        // 범위 밖
                if (dist[nx][ny] != 0) continue;    // 이미 방문

                if (map[nx][ny] == 'L') {   // 육지라면
                    dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }

    private static boolean isIn(int x, int y) {
        return (x >= 0 && x < N) && (y >= 0 && y < M);
    }

    // 최대 거리 찾기
    private static void getMax() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                max = Math.max(max, dist[i][j]);
            }
        }
    }
}
