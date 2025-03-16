import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class SWEA_2001_파리퇴치 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = parseInt(br.readLine());
        for(int t=1;t<=tc;t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = parseInt(st.nextToken());
            int m = parseInt(st.nextToken());
            int[][] map = new int[n+1][n+1];
            int[][] dp = new int[n+1][n+1];
            int maxValue = Integer.MIN_VALUE;
            for(int i=1;i<=n;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=1;j<=n;j++){
                    map[i][j] = parseInt(st.nextToken());
                }
            }

            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    dp[i][j] = dp[i][j-1] + dp[i-1][j] - dp[i-1][j-1] + map[i][j];
                }
            }

            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    if(i-m<0 || j-m<0) continue;
                    int flies = dp[i][j] - dp[i][j-m]- dp[i-m][j] + dp[i-m][j-m];
                    maxValue = Math.max(maxValue, flies);
                }
            }

            sb.append("#"+t+" "+maxValue+"\n");

        }
        System.out.println(sb);

    }
}
