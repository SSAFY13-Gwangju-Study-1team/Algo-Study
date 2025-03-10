package argoStudy;

import java.util.*;
import java.io.*;

public class mero2178 {
    public static class Node{
        int x;
        int y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int n, m;
    static char[][] map;
    static ArrayList<Integer> result = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());  // 행 크기
        m = Integer.parseInt(st.nextToken());  // 열 크기

        // 입력 받은 값들을 담음
        map = new char[n][m];
        for(int i = 0; i<n; i++){
            String s = br.readLine();
            for(int j = 0; j<m; j++){
                map[i][j] = s.charAt(j);
            }
        }

        // 모든 지도에 표시된 육지 완전탐색
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(map[i][j] == 'L'){
                    bfs(i,j, new boolean[n][m]);
                }
            }
        }
        System.out.println(Collections.max(result));


    }

    public static void bfs(int x, int y, boolean[][] visited){
        int[] dx = {-1,0,1,0};
        int[] dy = {0,1,0,-1};

        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.addLast(new Node(x,y));
        int[][] dist = new int[n][m];
        visited[x][y] = true;
        ArrayList<Integer> num = new ArrayList<>();
        while (!queue.isEmpty()){
            Node now = queue.pollFirst();

            for(int i = 0; i<4; i++){
                int nx = now.x+dx[i];
                int ny = now.y+dy[i];
                if(0<=nx&&nx<n&&0<=ny&&ny<m){
                    if(!visited[nx][ny]&&map[nx][ny]=='L'){
                        visited[nx][ny] =true;
                        queue.addLast(new Node(nx,ny));
                        dist[nx][ny] = dist[now.x][now.y]+1;
                        num.add(dist[nx][ny]);
                    }
                }
            }

        }
        if(!num.isEmpty()){
            result.add(Collections.max(num));
        }
    }
}
