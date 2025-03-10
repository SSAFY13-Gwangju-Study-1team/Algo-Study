package Week6;

import java.io.*;
import java.util.*;

/**
 * 메모리: 16160KB
 * 실행시간: 128ms
 *
 * 아이디어
 *  - 처음 피보나치에 관한 dp 문제를 풀어본후에
 *  - 한번 도전해봤는데 맞았다...
 *f
 */


public class Main_1003_피보나치함수_김태민 {
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t<=T; t++){
            int n = Integer.parseInt(br.readLine());
            dp = new int[n+1][2];


            int[] result = fibo(n,0,1);
            System.out.println(result[0]+" "+result[1]);
        }

    }

    // x: 0의 갯수, y: 1의 갯수
    static int[] fibo(int n,int x,int y){
        if(dp[n][x]!=0){
            return dp[n];
        }
        if(n==0){
            dp[n][x] = 1;
            dp[n][y] = 0;
            return dp[n];
        } else if (n==1) {
            dp[n][x] = 0;
            dp[n][y] = 1;
            return dp[n];

        }
        int[] n_1 = fibo(n-1,0,1);
        int[] n_2= fibo(n-2,0,1);

        dp[n][x] = n_1[x]+n_2[x];
        dp[n][y] = n_1[y]+n_2[y];
        return dp[n];

    }
}
