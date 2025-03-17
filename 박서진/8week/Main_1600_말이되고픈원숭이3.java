import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_1600_말이되고픈원숭이3{
    static int k, w, h;
    static int[][] map;
    static boolean[][][] isVisited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = parseInt(br.readLine());
        StringTokenizer st =new StringTokenizer(br.readLine());
        w =parseInt(st.nextToken());
        h =parseInt(st.nextToken());
        map = new int[h][w];
        isVisited = new boolean[h][w][k+1];
        for(int i=0;i<h;i++){
            st =new StringTokenizer(br.readLine());
            for(int j=0;j<w;j++){
                map[i][j] = parseInt(st.nextToken());
            }
        }
        int res = bfs(0,0);
        System.out.println(res);

    }

    private static int bfs(int r, int c) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int[] hdr = {-1, -2, -2, -1, 1, 2, 2, 1};
        int[] hdc = {-2, -1, 1, 2, 2, 1, -1, -2};
        ArrayDeque<int[]> q = new ArrayDeque<>();
        isVisited[r][c][0] = true;
        q.add(new int[]{r, c, 0, 0}); // r, c, 거리, 상태
        while (!q.isEmpty()){
            int[] temp = q.poll();
            int dist = temp[2];
            int status = temp[3];
            if(temp[0]==h-1 && temp[1]==w-1){
                return dist;
            }
            for(int i=0;i<4;i++){
                int nr = temp[0]+dr[i];
                int nc = temp[1]+dc[i];
                if(nr>=0 && nc>=0 && nr<h && nc<w && !isVisited[nr][nc][status]&& map[nr][nc]==0){
                    isVisited[nr][nc][status] = true;
                    q.add(new int[]{nr, nc, dist+1, status});
                }
            }

            if(status+1<=k){
                for(int i=0;i<8;i++){
                    int nr = temp[0]+hdr[i];
                    int nc = temp[1]+hdc[i];
                    if(nr>=0 && nc>=0 && nr<h && nc<w && !isVisited[nr][nc][status+1] && map[nr][nc]==0){
                        isVisited[nr][nc][status+1] = true;
                        q.add(new int[]{nr, nc, dist+1, status+1});
                    }
                }
            }
        }
        return -1;
    }


}
