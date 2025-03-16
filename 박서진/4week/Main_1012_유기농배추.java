package PACKAGE_NAME;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_1012_유기농배추 {
    static boolean[][] isVisited;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int m; //가로길이
    static int n; // 세로길이


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = parseInt(st.nextToken()); // test case

        for(int tt=1;tt<=t;tt++) {
            st = new StringTokenizer(br.readLine());
            m = parseInt(st.nextToken()); //가로길이
            n = parseInt(st.nextToken()); // 세로길이
            int k = parseInt(st.nextToken()); // 배추 위치 개수
            map = new int[m][n];
            isVisited = new boolean[m][n];
            int cnt=0; // 지렁이의 개수

            for(int i=0;i<k;i++) {
                st = new StringTokenizer(br.readLine());
                int x = parseInt(st.nextToken()); // x의 위치
                int y = parseInt(st.nextToken()); // y의 위치
                map[x][y] = 1;
            }
            for(int i=0;i<m;i++) {
                for(int j=0;j<n;j++) {
                    if(map[i][j] == 1 && isVisited[i][j]==false) {
                        cnt++;
                        DFS(i, j);
                    }
                }
            }
            System.out.println(cnt);
        }


    }
    public static void DFS(int r, int c) {
        if(isVisited[r][c] || map[r][c] == 0) return;
        isVisited[r][c] =true;
        for(int i=0;i<4;i++) {
            int nx = r+dx[i];
            int ny = c+dy[i];
            if(nx>=0 && nx<m && ny>=0 && ny<n) {
                if(!isVisited[nx][ny])
                    DFS(nx, ny);
            }
        }

    }

}
