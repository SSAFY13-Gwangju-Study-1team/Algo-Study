import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_12865_평범한배낭 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = parseInt(st.nextToken()); // 가방 개수 
        int k = parseInt(st.nextToken()); // 최대 무게
        int[] weight = new int[n+1];
        int[] value = new int[n+1];

        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            weight[i] = parseInt(st.nextToken());
            value[i] = parseInt(st.nextToken());
        }
        
        int[][] dp = new int[n+1][1+k]; // 가로는 가방의 최대 무게, 세로는 weight 무게 종류
        
        // 현재 가방 무게가 같을 때까지 이전열과 같음
        for(int i=1;i<=n;i++){ // 가방의 종류
            for(int w=0;w<=k;w++){
                if(weight[i]>w){
                    dp[i][w] = dp[i-1][w];
                }else{
                    // 내 거를 안넣거나 내 전 거에서 내 무게만큼 뺀 것에서 value더하기
                    dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-weight[i]]+value[i]);
                }
            }
        }
        System.out.println(dp[n][k]);

    }
}
