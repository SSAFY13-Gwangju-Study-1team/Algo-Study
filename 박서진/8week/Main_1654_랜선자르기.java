import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_1654_랜선자르기 {
    static int k, n;
    static int[] lans;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        k = parseInt(st.nextToken());
        n = parseInt(st.nextToken());
        lans = new int[n];
        int maxLan = -1;
        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine());
            lans[i] = parseInt(st.nextToken());
            maxLan = Math.max(maxLan, lans[i]);
        }
        long res = parametricSearch(maxLan);
        System.out.println(res);
    }
    public static long parametricSearch(int max){
        long left = 1;
        long right = max;
        long mid;
        while(left<=right){
            mid = (left+right)/2;
            // 총 몇개가 잘렸는지 확인
            long  cut=0;
            for(int i=0;i<k;i++){
                cut+=lans[i]/mid;
            }
            // upper bound -> 같은 n개의 개수라면 가장 큰 수를 보장해야 함 계속 오른쪽으로 이동
            if(cut>=n){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return right;
    }

}
