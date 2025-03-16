import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_2775_부녀회장이될테야 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc =parseInt(br.readLine());
        for(int t=0;t<tc;t++){
            int k = parseInt(br.readLine()); // 층수
            int n = parseInt(br.readLine()); // 호수
            int[][] dp = new int[k+1][n+1];
            for(int i=1;i<=n;i++){
                dp[0][i] = i;
            }
            for(int i=1;i<=k;i++){
                for(int j=1;j<=n;j++) {
                    dp[i][j] = dp[i][j-1]+dp[i-1][j];
                }
            }
            sb.append(dp[k][n]).append("\n");
        }
        System.out.println(sb);
    }
}
