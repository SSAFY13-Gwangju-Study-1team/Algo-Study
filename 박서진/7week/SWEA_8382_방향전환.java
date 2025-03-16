import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * bfs로도 풀 수 있는데 시간이 더 오래걸림
 * 음수가 나올 수도 있기 때문에 최대치만큼 더해줘야 함
 * 방향을 상하 -> 좌우 or 좌우 -> 상하로만 이동해야해서 살짝 복잡함
 * 또한 최단 거리이기 때문에 bfs를 사용해야함
 */
public class SWEA_8382_방향전환 {
    static int result;
    static boolean isVisited[][];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = parseInt(br.readLine());
        for(int t=1;t<=tc;t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1= parseInt(st.nextToken())+100;
            int y1= parseInt(st.nextToken())+100;
            int x2= parseInt(st.nextToken())+100;
            int y2= parseInt(st.nextToken())+100;
            result = Integer.MAX_VALUE;
            isVisited = new boolean[201][201];
            bfs(x1,y1, x2, y2, 1);

            isVisited = new boolean[201][201];
            bfs(x1,y1, x2, y2, -1);

            sb.append("#"+t+" "+result).append("\n");
        }
        System.out.println(sb);
    }
    public static void bfs(int x1, int y1, int x2, int y2, int dir){
        int[][] dr = {{-1, 0}, {1,0}}; //상하
        int[][] dc = {{0, 1}, {0, -1}};
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x1, y1, 0, dir}); //x, y, cnt, dir
        isVisited[x1][y1] = true;
        while (!q.isEmpty()){
            int[] temp = q.poll();
            int x = temp[0];
            int y = temp[1];
            int cnt = temp[2];
            int d =  temp[3];
            if(x==x2 && y==y2) {
                result = Math.min(result, cnt);
                return;
            }
            if(temp[3]>0){ // 상하
                for(int[] direction:dc){
                    int nr = x + direction[0];
                    int nc = y + direction[1];
                    if(nr>=0 && nc>=0 && nr<201 && nc<201 && !isVisited[nr][nc]){
                        isVisited[nr][nc]=true;
                        q.add(new int[]{nr, nc, cnt+1, -d});
                    }
                }
            }else{ //좌우
                for(int[] direction:dr){
                    int nr = x + direction[0];
                    int nc = y + direction[1];
                    if(nr>=0 && nc>=0 && nr<201 && nc<201 && !isVisited[nr][nc]){
                        isVisited[nr][nc]=true;
                        q.add(new int[]{nr, nc, cnt+1, -d});
                    }
                }
            }

        }

    }
}
