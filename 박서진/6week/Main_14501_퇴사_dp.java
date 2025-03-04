import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main_14501_퇴사_dp {
    static int n;
    static int[] P;
    static int[] T;
    static int maxVal=0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = parseInt(br.readLine());
        T = new int[n+1];
        P = new int[n+1];
        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            T[i] = parseInt(st.nextToken());
            P[i] = parseInt(st.nextToken());
        }

        // 최대값을 구하기 위한 dp
        int[] dp = new int[n+2]; //i번째 일에 얼마만큼 벌수 있는가 퇴사날까지
        for(int i=n;i>=1;i--){
            if(i+T[i]>n+1){
                dp[i] = dp[i+1];
            }else {
                dp[i] = Math.max(dp[i+1], dp[i+T[i]]+P[i]); // 내거를 고르지 않거나, 나를 거쳐서 간 아이에서 p만큼 더해준 값
            }
        }
        
        System.out.println(dp[1]);
    }

}
