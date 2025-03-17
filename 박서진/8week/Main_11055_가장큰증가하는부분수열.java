import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_11055_가장큰증가하는부분수열 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = parseInt(br.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n]; // 수열의 합 저장
        int resSum = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i] = parseInt(st.nextToken());
        }
        for(int i=0;i<n;i++){
            dp[i]= arr[i];
            for(int j=0;j<i;j++){
                if(arr[i]>arr[j]){
                    dp[i] = Math.max(dp[i], dp[j]+arr[i]);
                }
            }
            resSum = Math.max(resSum, dp[i]);
        }
        System.out.println(resSum);
    }
}
