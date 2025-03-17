import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_2206_벽부수고이동하기2 {
    static int n, m;
    static int[][] map;
    static boolean[][][] isVisited;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());
        map = new int[n+1][m+1];
        isVisited = new boolean[n+1][m+1][2]; //벽을 부시지 않은 경우 0, 벽을 부신 경우 1
        for(int i=1;i<=n;i++){
            char[] temp = br.readLine().toCharArray();
            for(int j=1;j<=m;j++){
                map[i][j] = parseInt(String.valueOf(temp[j-1]));
            }
        }
        int res = bfs(1,1);
        System.out.println(res);
    }

    private static int bfs(int r, int c) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        ArrayDeque<int[]> q = new ArrayDeque<>();
        isVisited[r][c][0] = true;
        q.add(new int[]{r, c, 1,0}); // r, c, 거리, 상태
        while (!q.isEmpty()){
            int[] temp = q.poll();
            int dist = temp[2];
            int status = temp[3];
            if(temp[0]==n && temp[1]==m){
                return dist;
            }
            for(int i=0;i<4;i++){
                int nr = temp[0] + dr[i];
                int nc = temp[1] + dc[i];
                if(nr>0 && nc>0 && nr<=n && nc<=m){
                    if(map[nr][nc] == 0  && !isVisited[nr][nc][status]){
                        isVisited[nr][nc][status] =true;
                        q.add(new int[]{nr, nc, dist+1, status});
                    }else if (map[nr][nc] == 1  && !isVisited[nr][nc][status]){
                        // 이미 상태가 1인 경우
                        if(status==1){
                            continue;
                        }
                        // 아직 0인 경우
                        else{
                            isVisited[nr][nc][1] =true;
                            q.add(new int[]{nr, nc, dist+1, 1});
                        }
                    }
                }
            }
        }
        return -1;
    }
}
