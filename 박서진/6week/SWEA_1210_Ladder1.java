import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * dfs 문제 Ladder 다시 풀이
 * 만약 0에 도달했을 때 flag를 들어 계속 return 할 수 있게 해준다
 */
public class SWEA_1210_Ladder1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        for(int t=1;t<=10;t++){
            br.readLine(); // 첫 번째 줄 버리기 (테스트 케이스 번호)
            sb.append("#"+t+" ");
            solve();
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int[][] map;
    static boolean[][] isVisited;
    static int res=-1;
    static boolean flag;
    public static void solve() throws Exception {
        map = new int[100][100];
        isVisited = new boolean[100][100];
        res=-1;
        flag= false;
        for(int i=0;i<100;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<100;j++) {
                map[i][j] = parseInt(st.nextToken());
            }
        }
        // 가장 마지막 줄에서 2를 찾음 -> dfs
        for(int i=0;i<100;i++){
            if(map[99][i]==2){
                dfs(99, i);
                break;
            }
        }
        sb.append(res);
    }
    static int[] dr = {0, 0, -1}; // 왼쪽 오른쪽 위
    static int[] dc = {-1, 1, 0};

    public static void dfs(int r, int c){
        isVisited[r][c] = true;
        if(r==0){
            res = c;
            flag = true;
            return;
        }

        // 방문 처리를 할 필요가 없음 그냥 쭉쭉 올라가면 됨
        for(int i=0;i<3;i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr>=0 && nc>=0&& nr<100 && nc<100 && !isVisited[nr][nc] && map[nr][nc]==1){
                dfs(nr, nc);
                if(flag) return;
            }
        }

    }
}
