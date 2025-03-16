import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 시간: 432ms
 * bfs를 4방으로 갈때랑 knight가 가는 방향 두가지로 다루어야 한다는 것은 알고 있었다
 * 백준 문제 중 벽부수고 이동하기랑 비슷하지만 그 문제처럼 방문처리를 (1, 0)으로만 하면 안된다 -> 테케는 맞지만 답이 틀림
 * why? 말처럼 이동할 수 있는 수가 몇개 남아있는지에 따라서 상태가 계속 바뀌기 때문에 kCnt 수 별로 방문처리를 해줘야한다
 * bfs라 백트랙처럼 접근하는게 아니라 일단 처음 도착한게 가장 빠른 거리라는 것이 성립하는 것이기 때문에 많이 헷갈렸다
 */
public class Main_1600_말이되고픈원숭이 {
    static int k, w, h;
    static int[][] map;
    static boolean[][][] isVisited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = parseInt(st.nextToken());
        w = parseInt(st.nextToken());
        map = new int[w][h];
        isVisited = new boolean[w][h][k+1]; // mode 0: 4방 1: 나이트 방향
        for(int i=0;i<w;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<h;j++){
                map[i][j] = parseInt(st.nextToken());
            }
        }

        int dis = bfs(0, 0); 
        System.out.println(dis);
    }

    private static int bfs(int r, int c) {
        // 4방으로 가는 경우
        int[] dr = {-1, 1, 0 ,0};
        int[] dc = {0, 0, -1 ,1};
        // 나이트 이동 방향 (8가지)
        int[] kdr = {-2, -2, +2, +2, +1, +1, -1, -1};
        int[] kdc = {+1, -1, +1, -1, +2, -2, +2, -2};
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0,0,0,k}); // {행, 열, 이동 거리, mode (0: 4방, 1: 나이트)}
        isVisited[r][c][0] = true;
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int currentR = temp[0];
            int currentC = temp[1];
            int distance = temp[2];
            if(currentR==w-1 && currentC==h-1){
                return distance; // 처음으로 도착하는 것이 가장 빠른 거리이기 때문에 바로 리턴해주면 된다.
                // 백트래킹처럼 방문했다 취소했다 이런식으로 가는것이 아님!!
            }
            int kCnt = temp[3];
            // normal 방향
            for (int i = 0; i < 4; i++) {
                int nr = currentR+dr[i];
                int nc = currentC+dc[i];
                if(nr>=0 && nc>=0 && nr<w && nc<h && !isVisited[nr][nc][kCnt]){ // 방문처리를 kCnt별로 가지고 있어야한다
                    if(map[nr][nc] == 0) {
                        isVisited[nr][nc][kCnt] = true;
                        q.add(new int[]{nr, nc, distance + 1, kCnt});
                    }
                }

            }
            // 나이트 방향
            if (kCnt > 0) {
                for (int i = 0; i < 8; i++) {
                    int nr = currentR+kdr[i];
                    int nc = currentC+kdc[i];
                    if(nr>=0 && nc>=0 && nr<w && nc<h && !isVisited[nr][nc][kCnt-1]){
                        if(map[nr][nc] == 0) {
                            isVisited[nr][nc][kCnt-1] = true;
                            q.add(new int[]{nr, nc, distance + 1, kCnt - 1});
                        }
                    }
                }
            }
        }
        return -1;
    }
}
