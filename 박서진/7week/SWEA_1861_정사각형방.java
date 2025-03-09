import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class SWEA_1861_정사각형방 {
    static int n, result, resCnt;
    static int[][] map;
    static boolean[][] isVisited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = parseInt(br.readLine());
        for(int t=1; t<=tc;t++){
            n = parseInt(br.readLine());
            map = new int[n][n];
            resCnt = 0;
            result = Integer.MAX_VALUE;
            for(int i=0;i<n;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0;j<n;j++){
                    map[i][j] = parseInt(st.nextToken());
                }
            }
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    isVisited = new boolean[n][n];
                    bfs(i, j);
                }
            }
            sb.append("#"+t+" "+result+" "+resCnt).append("\n");
        }
        System.out.println(sb);


    }
    public static void bfs(int r, int c){
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        ArrayDeque<int[]> q = new ArrayDeque<>();
        isVisited[r][c]=true;
        q.add(new int[]{r,c}); //마지막 cnt
        int res = 1;
        while(!q.isEmpty()){
            int[] temp = q.poll();
            for(int i=0;i<4;i++){
                int nr = temp[0]+dr[i];
                int nc = temp[1]+dc[i];
                if(nr>=0 && nc>=0 && nc<n && nr<n && !isVisited[nr][nc] && map[nr][nc]==map[temp[0]][temp[1]]+1){
                    isVisited[nr][nc] = true;
                    res++;
                    q.add(new int[]{nr, nc});
                }
            }
        }

        if(resCnt == res){
            if(result>map[r][c])
                result=map[r][c];
        }else if(resCnt<res){
            resCnt = res;
            result=map[r][c];
        }
    }
}
