import java.util.*;
import java.io.*;

public class Main_2589_보물섬 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int y, x;
    static String[][] map;
    static boolean[][] visited;
    static int maxDistance;
    static ArrayDeque<int[]> que;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        y = Integer.parseInt(st.nextToken());   // 세로
        x = Integer.parseInt(st.nextToken());   // 가로
        map = new String[y][x]; // 지도

        // 지도 채우기
        for(int i=0; i<y; i++) {
            map[i] = br.readLine().split("");
        }

        maxDistance = 0;
        for(int i=0; i<y; i++) {
            for(int j=0; j<x; j++) {
                if(map[i][j].equals("L")) {         // 모든 땅을 시작 지점으로
                    visited = new boolean[y][x];    // 방문체크
                    bfs(i,j,0);
                }
            }
        }

        System.out.print(maxDistance);
    }

    public static void bfs(int curY, int curX, int d) {   // 현재 y/x좌표, 누적 거리
        que = new ArrayDeque<>();
        que.addLast(new int[] {curY,curX,d});    // y, x, 누적거리(0)
        visited[curY][curX] = true;

        while(!que.isEmpty()) {
            int[] cur = que.removeFirst();
            for(int i=0; i<4; i++) {
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];

                if(ny >= 0 && ny < y && nx >= 0 && nx < x) {
                    if(map[ny][nx].equals("L") && !visited[ny][nx]) {
                        visited[ny][nx] = true; // 방문처리
                        que.addLast(new int[] {ny, nx, cur[2]+1}); // 다음 좌표, 누적거리+1
                        maxDistance = Math.max(maxDistance, cur[2]+1);  // 항상 최대 거리를 업데이트
                    }
                }
            }
        }
    }
}

