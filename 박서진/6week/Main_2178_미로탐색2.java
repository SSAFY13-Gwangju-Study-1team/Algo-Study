import java.io.*;
import java.util.*;

import static java.lang.Integer.min;
import static java.lang.Integer.parseInt;
public class Main_2178_미로탐색2 {
    static int n, m;
    static int[][] map;
    static boolean[][] isVisited;
    static int minVal=Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());
        map = new int[n+1][m+1];
        isVisited = new boolean[n+1][m+1];
        for(int i=1;i<=n;i++){
            char[] temp = br.readLine().toCharArray();
            for(int j=1;j<=m;j++){
                map[i][j] = parseInt(String.valueOf(temp[j-1]));
            }
        }
        bfs(1,1);
        System.out.println(map[n][m]);
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static void bfs(int r, int c){
        ArrayDeque<int[]> q =new ArrayDeque<>();
        isVisited[r][c] =true;
        q.add(new int[]{r, c});
        int cnt=1;
        while(!q.isEmpty()){
            int[] temp = q.poll();
            for(int i=0;i<4;i++){
                int nr = temp[0]+dr[i];
                int nc = temp[1]+dc[i];
                if(nr>0 && nc>0 && nr<=n && nc<=m && !isVisited[nr][nc] && map[nr][nc]==1){
                    isVisited[nr][nc] = true;
                    map[nr][nc] = map[temp[0]][temp[1]] + 1;
                    q.add(new int[]{nr, nc});
                }
            }
        }
    }
}
