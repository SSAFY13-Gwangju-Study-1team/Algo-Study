import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_11722_가장긴감소하는부분수열 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = parseInt(br.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n];
        int res = Integer.MIN_VALUE;
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i] = parseInt(st.nextToken());
        }
        for(int i=0;i<n;i++){
            dp[i] = 1;
            for(int j=0;j<i;j++){
                if(arr[i]<arr[j]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            res = Math.max(dp[i], res);
        }

        System.out.println(res);
    }
}
