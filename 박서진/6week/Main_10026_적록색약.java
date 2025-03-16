import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * bfs 문제
 * 단지번호붙이기 문제랑 비슷한듯!!
 * 색약인의 경우 dfs 들어가기 전에 g->r 처리 해줘서 쉽게 풀 수 있었음
 */
public class Main_10026_적록색약 {
    static int n;
    static char[][] map;
    static boolean[][] isVisited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = parseInt(br.readLine());
        map = new char[n][n];
        isVisited = new boolean[n][n];
        for(int i=0;i<n;i++){
            char[] temp = br.readLine().toCharArray();
            for(int j=0;j<n;j++){
                map[i][j] = temp[j];
            }
        }
        int nCnt=0;
        // 일반인 
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(!isVisited[i][j]){
                    BFS(i, j);
                    nCnt++;
                }
            }
        }
        sb.append(nCnt+" ");

        isVisited = new boolean[n][n];
        int aCnt=0;
        // 색약인
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(map[i][j]=='G'){
                    map[i][j]='R';
                }
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(!isVisited[i][j]){
                    BFS(i, j);
                    aCnt++;
                }
            }
        }
        sb.append(aCnt);
        System.out.println(sb);

    }
    static void BFS(int r, int c){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        isVisited[r][c] = true;
        q.offer(new int[]{r, c});
        char color = map[r][c];
        while(!q.isEmpty()){
            int[] temp = q.poll();
            for(int i=0;i<4;i++) {
                int nr = temp[0] + dr[i];
                int nc = temp[1] + dc[i];
                if(nr>=0 && nc>=0 && nr<n && nc<n && !isVisited[nr][nc] && map[nr][nc]==color){
                    isVisited[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                }
            }
        }

    }
}
