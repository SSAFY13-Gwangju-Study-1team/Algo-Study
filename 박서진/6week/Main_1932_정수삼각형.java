import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * 248ms
 * 무난한 dp 문제였다
 * 한 단계 앞 부분만 신경쓰면 되서 이런건 쉽다..
 */
public class Main_1932_정수삼각형 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(br.readLine());
        int[][] dp= new int[n+1][n+1];
        int[][] map= new int[n+1][n+1];
        for(int i=1;i<=n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1;j<=i;j++) {
                map[i][j] = parseInt(st.nextToken());
            }
        }

        // dp 채우기
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++) {
                dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j])+map[i][j];
            }
        }

        // 마지막 줄에 있는 것은 최대 값들의 모임 - 그 중에서 가장 큰 것을 고르자
        int maxRes=0;
        for(int i=1;i<=n;i++){
            maxRes = Math.max(maxRes, dp[n][i]);
        }
        System.out.println(maxRes);
    }
}
