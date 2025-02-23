import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t<T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int cnt = 0;
            map = new int[m][n];
            visited = new boolean[m][n];
            for(int i = 0; i<k; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[x][y] = 1;
            }

            for(int i = 0; i<m; i++){
                for(int j = 0; j<n; j++){
                    if(map[i][j] == 1&&!visited[i][j]){
                        visited[i][j] = true;
                        dfs(i, j, m, n);
                        cnt++;

                    }
                }
            }
            System.out.println(cnt);


        }
    }
    public static void dfs(int x, int y, int m, int n){
        int[] dx = {-1,0,1,0};
        int[] dy = {0,1,0,-1};

        for(int i = 0; i<4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(0<=nx&&nx<m&&0<=ny&&ny<n){
                if(map[nx][ny] == 1&&!visited[nx][ny]){
                    visited[nx][ny] = true;
                    dfs(nx,ny,m,n);
                }
            }
        }

    }


}
