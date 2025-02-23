import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n,m,k;
    static int[][] map;
    static boolean[][] visited;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];
        int sum= 0;

        for(int i = 0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 1;
        }
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(map[i][j] == 1 && !visited[i][j]){
                    visited[i][j] = true;
                    cnt = 0;
                    dfs(i,j);
                    sum = Math.max(cnt, sum);
                }
            }
        }

        System.out.println(sum);

    }
    public static void dfs(int x, int y){
        int[] dx = {-1,0,1,0};
        int[] dy = {0,1,0,-1};
        cnt++;

        for(int i = 0; i<4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];

            if(nx>=0&&nx<n&&ny>=0&&ny<m){
                if(!visited[nx][ny]&&map[nx][ny] == 1){
                    visited[nx][ny] = true;
                    dfs(nx,ny);
                }
            }
        }


    }



}