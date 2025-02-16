import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int total = 0;
        map = new int[n][n];
        visited = new boolean[n][n];

        for(int i = 0; i<n; i++){
            String num = br.readLine();
            for(int j = 0; j<n; j++){
                map[i][j] = num.charAt(j) - '0';
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(map[i][j] == 1 && !visited[i][j]){
                    visited[i][j] = true;
                    cnt = 0;
                    total++;
                    dfs(i,j);
                    list.add(cnt);
                }
            }
        }

        Collections.sort(list);
        System.out.println(total);
        for(int i:list){
            System.out.println(i);
        }

    }
    public static void dfs(int x, int y){
        int[] dx = {-1,0,1,0};
        int[] dy = {0,1,0,-1};
        cnt++;

        for(int i = 0; i<4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];

            if(nx>=0&&nx<n&&ny>=0&&ny<n){
                if(!visited[nx][ny]&&map[nx][ny] == 1){
                    visited[nx][ny] = true;
                    dfs(nx,ny);
                }
            }
        }


    }



}