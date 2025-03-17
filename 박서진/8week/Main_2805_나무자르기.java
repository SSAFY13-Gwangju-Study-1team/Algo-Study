import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_2805_나무자르기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        int n =parseInt(st.nextToken());
        long m = parseInt(st.nextToken());
        long[] trees = new long[n];
        st =new StringTokenizer(br.readLine());
        long maxLength = Long.MIN_VALUE;
        for(int i=0;i<n;i++){
            trees[i] = Long.parseLong(st.nextToken());
            maxLength = Math.max(maxLength, trees[i]);
        }
        // 이분 탐색
        long left = 0;
        long right = maxLength;
        long ans=0;
        while(left<=right){
            long mid = (left+right)/2;
            long totalLen= 0;
            for(int i=0;i<n;i++){
                if(trees[i]>mid){
                    totalLen+=trees[i]-mid;
                }
            }
            if(totalLen>=m) {
                ans = mid;
                left = mid+1;
            } else{
                right = mid-1;
            }
        }
        System.out.println(ans);
    }
}
