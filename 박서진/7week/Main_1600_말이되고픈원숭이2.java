import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main_1600_말이되고픈원숭이2 {
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
        isVisited = new boolean[w][h][2]; // mode 0: 4방 1: 나이트 방향
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
                return distance;
            }
            int kCnt = temp[3];
            // normal 방향
            for (int i = 0; i < 4; i++) {
                int nr = currentR+dr[i];
                int nc = currentC+dc[i];
                if(nr>=0 && nc>=0 && nr<w && nc<h && !isVisited[nr][nc][0]){
                    if(map[nr][nc] == 0) {
                        isVisited[nr][nc][0] = true;
                        q.add(new int[]{nr, nc, distance + 1, kCnt});
                    }
                }

            }
            // 나이트 방향
            if (kCnt > 0) {
                for (int i = 0; i < 8; i++) {
                    int nr = currentR+kdr[i];
                    int nc = currentC+kdc[i];
                    if(nr>=0 && nc>=0 && nr<w && nc<h && !isVisited[nr][nc][1]){
                        if(map[nr][nc] == 0) {
                            isVisited[nr][nc][1] = true;
                            q.add(new int[]{nr, nc, distance + 1, kCnt - 1});
                        }
                    }
                }
            }
        }
        return -1;
    }
}
