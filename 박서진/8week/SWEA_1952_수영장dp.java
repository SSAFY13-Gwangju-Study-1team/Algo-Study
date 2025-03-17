import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.min;
import static java.lang.Integer.parseInt;

public class SWEA_1952_수영장dp {
    static int minPrice = Integer.MAX_VALUE;
    static int[] price;
    static int[] plan;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int tc = parseInt(br.readLine());
        for(int t = 1;t<=tc;t++){
            price = new int[4]; //1일권 1달권 3달권 1년권
            plan = new int[13];
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<4;i++){
                price[i] = parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i=1;i<=12;i++){
                plan[i] = parseInt(st.nextToken());
            }
            minPrice = Integer.MAX_VALUE;
            dp();
            sb.append("#" + t + " "+minPrice).append("\n");
        }
        System.out.println(sb);
    }

    private static void dp() {
        int[] dp = new int[13]; //price를 넣는다
        for(int i=1;i<=12;i++){
            dp[i] = price[3]; // 초기에 1년 회원권으로 초기화한다
            // 0의 값을 갖는다면
            if(plan[i]==0){
                dp[i] = dp[i-1];
            }else{
                //1일권
                dp[i] = Math.min(dp[i-1]+plan[i]*price[0], dp[i]);
                //1달권
                dp[i] = Math.min(dp[i-1]+price[1], dp[i]);
                //3달권
                if(i>=3){
                    dp[i] = Math.min(dp[i-3]+price[2], dp[i]);
                }

            }
        }
        minPrice =dp[12];
    }
}
