package 스터디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1012_유기농_배추_임규리 {

    // 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int T;               // 테스트 케이스 개수
    static int M, N, K;         // 가로, 세로, 배추 개수
    static boolean[][] map;     // 배추 위치 지도
    static boolean[][] visited; // 방문 체크

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            int count = 0;

            // 배추 개수가 1개면 1 출력 후 끝
            if (K == 1) {
                System.out.println("1");
                st = new StringTokenizer(br.readLine());    // 입력 한 줄 스킵
                continue;
            }

            map = new boolean[M][N];
            visited = new boolean[M][N];

            // 배추 위치 지도에 위치 표시
            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[x][y] = true;
            }

            for (int j = 0; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    if (map[j][k] && !visited[j][k]) {  // 배추 위치고, 아직 방문 전이라면
                        bfs(j, k);
                        count++;
                    }
                }
            }

            System.out.println(count);
        }
    }

    private static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < dx.length; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                
                if (!isIn(nx, ny)) continue;    // 범위 밖이라면 무시
                if (visited[nx][ny]) continue;  // 이미 방문했다면 무시

                if (map[nx][ny]) {              // 배추 위치라면
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }

    private static boolean isIn(int x, int y) {
        return (x >= 0 && x < M) && (y >= 0 && y < N);
    }
}
