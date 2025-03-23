import java.io.*;
import java.util.*;

import static java.lang.Integer.min;
import static java.lang.Integer.parseInt;

/**
 * bfs 3개로 풀이하였습니다.
 * 1. bfs로 같이 붙어있는 육지를 한 섬으로 만들기
 * 2. 0으로 bfs를 돌리면서 섬들의 경계를 찾는다
 * 3. bfs로 섬끼리 연결하면서 최소의 길이를 찾는다
 *
 */
public class Main_2146_다리만들기 {
    static int n, minLength;
    static int[][] map;
    static int[][] islandMap;
    static int[][] contourMap;
    static boolean[][] isVisited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = parseInt(br.readLine());
        map = new int[n][n];
        islandMap = new int[n][n];
        contourMap = new int[n][n];
        isVisited = new boolean[n][n];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = parseInt(st.nextToken());
            }
        }
        // 1. 같이 붙어있는 육지를 bfs를 이용하여 한 섬으로 만들기
        // 섬마다 번호를 배정해서 새로운 맵을 만들기
        int label = 1;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(!isVisited[i][j] && map[i][j]==1){
                    bfs(i,j, label);
                    label++;
                }
            }
        }
        
        // 2. 0으로 bfs를 돌리면서 섬들의 경계를 찾는다
        isVisited = new boolean[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(!isVisited[i][j] && islandMap[i][j]==0){
                    contourBfs(i,j);
                }
            }
        }
        
        // 3. bfs로 섬끼리 연결하면서 최소의 길이를 찾는다
        // 섬하나로 bfs로 들어가면 0이면 q에 넣고 아니면 그 섬이 번호가 다른 섬인지를 찾는다
        // 만약 번호가 다른 섬이라면 최소의 길이를 갱신한다

        minLength = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                isVisited = new boolean[n][n];
                if(contourMap[i][j]!=0){
                    buildBridge(i,j);
                }
            }
        }

        System.out.println(minLength);

    }

    private static void buildBridge(int r, int c) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r, c, 0});
        isVisited[r][c] = true;
        int startIsland = contourMap[r][c]; // 처음시작한 섬의 번호
        while(!q.isEmpty()){
            int[] temp = q.poll();
            int dist = temp[2];
            for(int i=0;i<4;i++){
                int nr = temp[0] + dr[i];
                int nc = temp[1] + dc[i];
                if(nr >=0 && nc >=0 && nr<n && nc<n && !isVisited[nr][nc]){
                    if(contourMap[nr][nc]==0){ //바다로 연결될 때만 q에 넣기
                        q.add(new int[]{nr, nc, dist+1});
                        isVisited[nr][nc] = true;
                    }
                    else if(contourMap[nr][nc]!=startIsland){
                        minLength = Math.min(minLength, dist);
                    }
                }
            }
        }
    }

    private static void contourBfs(int r, int c) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r, c});
        isVisited[r][c] = true;
        while(!q.isEmpty()){
            int[] temp = q.poll();
            for(int i=0;i<4;i++){
                int nr = temp[0] + dr[i];
                int nc = temp[1] + dc[i];
                if(nr >=0 && nc >=0 && nr<n && nc<n && !isVisited[nr][nc]){
                    if(islandMap[nr][nc]==0){
                        isVisited[nr][nc] = true;
                        q.add(new int[]{nr, nc});
                    }else{
                        contourMap[nr][nc] = islandMap[nr][nc];
                    }
                }
            }
        }
    }

    private static void bfs(int r, int c, int label) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r, c});
        isVisited[r][c] = true;
        islandMap[r][c] =label; // 섬의 라벨을 적은 맵에 섬을 연결하기
        while(!q.isEmpty()){
            int[] temp = q.poll();
            for(int i=0;i<4;i++){
                int nr = temp[0] + dr[i];
                int nc = temp[1] + dc[i];
                if(nr >=0 && nc >=0 && nr<n && nc<n && !isVisited[nr][nc] && map[nr][nc]==1){
                    q.add(new int[]{nr, nc});
                    isVisited[nr][nc] = true;
                    islandMap[nr][nc] = label;
                }
            }
        }

    }
}
