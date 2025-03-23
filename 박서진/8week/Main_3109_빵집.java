import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_3109_빵집 {
    static int r, c;
    static char[][] map;
    static int cnt;
    static boolean flag;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        r = parseInt(st.nextToken());
        c = parseInt(st.nextToken());
        map = new char[r][c];
        for(int i=0;i<r;i++){
            map[i] = br.readLine().toCharArray();
        }
        cnt = 0;
        for(int i=0;i<r;i++){
            flag = false;
            dfs(i, 0);
        }
        System.out.println(cnt);

    }

    private static void dfs(int row, int col) {
        int[] dr = {-1, 0, 1};
        if(col==c){
            cnt++;
            flag = true;
            return;
        }
        for(int i=0;i<3;i++){
            int nr = row+dr[i];
            if(nr>=0 && nr<r && col<c && map[nr][col]=='.'){
                map[nr][col]='x';
                dfs(nr, col+1);
                if(flag) return;
            }
        }

    }
}
