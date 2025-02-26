package Week6;

import java.io.*;
import java.util.*;

public class Main_2775_부회장이될테야_김태민 {
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t<T; t++){
            int k = Integer.parseInt(br.readLine());  // k층
            int n = Integer.parseInt(br.readLine());  // n호
            dp = new int[k+1][n+1];
            for(int i = 1; i<=n; i++){
                dp[0][i] = i;
            }


        }

    }
    static int back(int k, int n){
        // k층의 n호에는 k-1층의 1부터 n호까지의 사람이 산다.
        if(dp[k][n]!=0){
            return dp[k][n];
        }

        for(int i = 1; i<=n; i++){
            dp[k][n] += dp[k-1][i];
        }
        return 1;
    }
}
