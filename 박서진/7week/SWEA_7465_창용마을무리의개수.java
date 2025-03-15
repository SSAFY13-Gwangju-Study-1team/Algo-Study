import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;

/**
 * 106 ms
 * 간단한 dfs 문제
 * 실패 이유
 * --> 고립되는 경우를 생각하지 못했다..역시 여러 테케를 직접 생각해봐야 함
 */
public class SWEA_7465_창용마을무리의개수 {
    static int n, m;
    static int[][] matrix;
    static boolean[] isVisited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = parseInt(st.nextToken());
            m = parseInt(st.nextToken());
            matrix = new int[n+1][n+1];
            isVisited = new boolean[n+1];
            int cnt = 0;
            for(int i=0;i<m;i++){
                st = new StringTokenizer(br.readLine());
                int n1 = parseInt(st.nextToken());
                int n2 = parseInt(st.nextToken());
                matrix[n1][n2] = 1;
                matrix[n2][n1] = 1;
            }

            for(int i=1;i<=n;i++){
                boolean flag = false;
                for(int j=1;j<=n;j++){
                    if(matrix[i][j] == 1){
                        flag = true;
                        if(!isVisited[i]) {
                            dfs(i);
                            cnt++;
                        }
                    }
                }
                if(!flag){
                    // 고립
                    cnt++;
                }
            }
            sb.append("#"+t+" "+cnt+"\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int r) {
        if(isVisited[r]) return;
        isVisited[r] = true;
        for(int c=1;c<=n;c++){
            if(!isVisited[c] && matrix[r][c]==1){
                dfs(c);
            }
        }
    }
}
