import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class SWEA_2105_디저트카페2 {
    static int n;
    static int[][] map;
    static boolean[][] isVisited;
    static int dessertCnt = -1;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int tc = parseInt(br.readLine());
        for(int t=1;t<=tc;t++){
            n = parseInt(br.readLine());
            map = new int[n][n];
            dessertCnt = -1;
            for(int i=0;i<n;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<n;j++){
                    map[i][j] = parseInt(st.nextToken());
                }
            }

            // <-- 구현 --> //
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    isVisited = new boolean[n][n];
                    isVisited[i][j] = true;
                    ArrayList<Integer> desserts = new ArrayList<>();
                    desserts.add(map[i][j]);
                    dfs(i, j, i, j, 1, 0, desserts, isVisited);
                }
            }
            sb.append("#" + t+ " "+ dessertCnt+"\n");
        }
        System.out.println(sb);
    }

    // 만약 arrayList 대신 set을 쓰면 마지막 값이 아닌 해당 값을 뺄 수 있어서 편함
    private static void dfs(int originR, int originC, int r, int c, int dCnt, int dir, ArrayList<Integer> desserts, boolean[][] isVisited) {
        int[] dr = {1, 1, -1, -1};
        int[] dc = {1, -1, -1, 1};
        // 디저트 값을 리스트에다가 넣는다
        for(int i=dir;i<4;i++){
            int nr = r+dr[i];
            int nc = c+dc[i];
            if(dCnt>2 && nr==originR && nc==originC){
                dessertCnt = Math.max(dessertCnt, dCnt);
                return;
            }
            // 범위 안에 들어와 있어야 하며, 방문하지 않았어야 하고, 디저트가 겹치면 안된다
            if(nr>=0 && nc>=0 && nr<n && nc<n && !isVisited[nr][nc] && !desserts.contains(map[nr][nc])){
                isVisited[nr][nc] = true;
                desserts.add(map[nr][nc]);
                dfs(originR, originC, nr, nc, dCnt+1, i, desserts , isVisited);
                desserts.remove(desserts.size()-1);
                isVisited[nr][nc] = false;
            }
        }
    }
}
