import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_12865_평범한배낭2번째풀이 {
    public static void main(String[] args) throws Exception{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = parseInt(st.nextToken());
        int k = parseInt(st.nextToken());
        int[] weight = new int[n+1];
        int[] value = new int[n+1];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            weight[i] = parseInt(st.nextToken());
            value[i] = parseInt(st.nextToken());
        }

        //정렬이 필요 없는것 같아..

        int[][] dp = new int[n+1][k+1]; // 가방의 가치를 초기화 할 dp map 만들기
        for(int i=1;i<=n;i++){
            for(int w=0;w<=k;w++){
                if(w<weight[i]){ // 가방의 무게보다 무거우면 넣을 수가 없음
                    dp[i][w] = dp[i-1][w];
                }else{
                    dp[i][w] = Math.max(dp[i-1][w-weight[i]]+value[i], dp[i-1][w]);
                }
            }
        }

        // 가장 끝 값이 가장 가치의 최댓값이 되는 구조
        System.out.println(dp[n][k]);
    }
}
