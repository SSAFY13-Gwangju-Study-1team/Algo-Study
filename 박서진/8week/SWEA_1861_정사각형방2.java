import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class SWEA_1861_정사각형방2 {
    static int n;
    static int start, resCnt;
    static int[][] map;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int tc = parseInt(br.readLine());
        for(int t = 1;t<=tc;t++){
            st = new StringTokenizer(br.readLine());
            n = parseInt(st.nextToken());
            map = new int[n][n];
            start = 0;
            resCnt = 0;
            for(int i=0;i<n;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<n;j++){
                    map[i][j] = parseInt(st.nextToken());
                }
            }

            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    bfs(i, j);
                }
            }
            sb.append("#"+t+" "+start+" "+resCnt).append("\n");
        }
        System.out.println(sb);

    }

    private static void bfs(int r, int c) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        ArrayDeque<int[]> q= new ArrayDeque<>();
        q.add(new int[]{r, c});
        int cnt = 0;
        while(!q.isEmpty()) {
            int[] temp = q.poll();
            cnt++;
            for (int i = 0; i < 4; i++) {
                int nr = temp[0] + dr[i];
                int nc = temp[1] + dc[i];
                if (nr >= 0 && nc >= 0 && nr < n && nc < n) {
                    if (map[temp[0]][temp[1]]+1 == map[nr][nc]){
                        q.add(new int[]{nr, nc});
                    }
                }
            }
        }
        if(cnt>resCnt){
            start = map[r][c];
            resCnt = cnt;
        }else if (cnt==resCnt){
            start = Math.min(start, map[r][c]);
        }
    }
}
