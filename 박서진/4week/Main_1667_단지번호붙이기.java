package PACKAGE_NAME;

import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;

public class Main_1667_단지번호붙이기 {
    static int[][] map;
    static boolean[][] isVisited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(br.readLine()); // 맵의 크기
        map = new int[n][n];
        isVisited = new boolean[n][n]; // 방문 배열
        List<Integer> houses = new ArrayList<>(); // 단지별 집 수

        for(int i=0;i<n;i++){
            char[] line = br.readLine().toCharArray();
            for(int j=0; j<n;j++){
                map[i][j] = parseInt(String.valueOf(line[j]));
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(map[i][j]==1){
                    if(!isVisited[i][j]){
                        int cnt = BFS(n, i, j);
                        houses.add(cnt);
                    }
                }
            }
        }

        Collections.sort(houses);
        StringBuilder sb = new StringBuilder();
        sb.append(houses.size()).append("\n");
        for(int i:houses) sb.append(i).append("\n");
        System.out.println(sb);
    }

    private static int BFS(int n, int r, int c){
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        ArrayDeque<int[]> q =  new ArrayDeque<>();
        q.offer(new int[]{r,c});
        isVisited[r][c] = true;
        int cnt=1;
        while (!q.isEmpty()){
            int[] temp = q.poll();
            int x = temp[0];
            int y = temp[1];
            for(int i=0;i<4;i++){
                int nx = x+dr[i];
                int ny = y+dc[i];
                if(nx>=0 && nx<n && ny>=0 && ny<n){
                    if(map[nx][ny]==1){
                        if(!isVisited[nx][ny]){
                            cnt++;
                            q.offer(new int[]{nx, ny});
                            isVisited[nx][ny] = true;
                        }
                    }
                }
            }
        }
        return cnt;
    }
}
