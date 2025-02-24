import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 난이도: 상
 * 어려웠다.. dp를 더 공부해야겠다..
 * 0.15초 안에 풀어야하기 때문에 완전 탐색을 할 수 없었고 dp만을 활용해야 하는 문제 였음
 */
public class Main_1463_1로만들기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n =parseInt(br.readLine());
        int[] dp = new int[n+1];
        dp[1] = 0;

        for(int i=2;i<=n;i++){
            dp[i] = 1 + dp[i-1];

            if(i%2==0){
                dp[i] = Math.min(dp[i], dp[i/2] + 1);
            }

            if(i%3==0){
                dp[i] = Math.min(dp[i], dp[i/3] + 1);
            }

        }
        System.out.println(dp[n]);
    }
}
