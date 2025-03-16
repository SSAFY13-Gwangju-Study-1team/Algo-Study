package PACKAGE_NAME;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_1743_음식물피하기 {
    static int[][] map; // 음식물 쓰레기 있으면 -> 1 없으면 -> 0
    static boolean[][] isVisied;
    static int[] dr ={-1, 1, 0, 0};
    static int[] dc ={0, 0, -1, 1};
    static int n, m, k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        n = parseInt(st.nextToken()); // 세로
        m = parseInt(st.nextToken());  // 가로
        k = parseInt(st.nextToken()); // 음식물 개수

        map = new int[n+1][m+1];
        isVisied = new boolean[n+1][m+1];
        int res=0; //가장 큰 음식물의 크기

        for(int i=0;i<k;i++){
            st= new StringTokenizer(br.readLine());
            int r = parseInt(st.nextToken());
            int c = parseInt(st.nextToken());
            map[r][c] = 1;
        }

        for(int r=1;r<=n;r++){
            for(int c=1;c<=m;c++){
                if(map[r][c]==1){
                    if(!isVisied[r][c]){
                        res = Math.max(BFS(r, c), res);
                    }
                }
            }
        }
        System.out.println(res);
    }
    public static int BFS(int r, int c){
        // BFS 요소를 담을 q 선언
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r, c});
        isVisied[r][c] =true;
        int cnt = 1;
        while(!q.isEmpty()){
            int[] temp = q.poll();

            int x = temp[0];
            int y = temp[1];

            for(int i=0;i<4;i++){
                int nx = x+ dr[i];
                int ny = y+ dc[i];
                if(nx>0 && nx<=n && ny>0 && ny<=m){
                    if(map[nx][ny]==1){
                        if(!isVisied[nx][ny]){
                            q.offer(new int[]{nx, ny});
                            isVisied[nx][ny] = true;
                            cnt++;
                        }
                    }
                }
            }
        }
        return cnt;
    }
}
