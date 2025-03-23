import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 348ms
 * 이분탐색 중 파라메트릭서치입니다
 * 자료형을 long으로 사용해야해요 그래서 계속 틀렸습니다
 * 이분탐색은 그냥 long!!
 */
public class Main_2110_공유기설치 {
    static int n, k;
    static long maxPosition;
    static long[] wifi;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n=parseInt(st.nextToken());
        k=parseInt(st.nextToken());
        wifi = new long[n];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            wifi[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(wifi);
        maxPosition = wifi[n-1] - wifi[0]; // 최대 거리
        long ans = parametricSearch();
        System.out.println(ans);
    }

    private static long parametricSearch() {
        long left = 1; //공유기 개수가 겹칠 수 없고 공유기가 2개 이상이므로
        long right = maxPosition;
        long mid;
        long result = 0;
        while(left<=right){
            mid = (left+right)/2;
            int cnt=1; // 1포함
            long rel = wifi[0];
            for (int i = 1; i < n; i++) {
                if(wifi[i] -  rel >= mid){
                    cnt++;
                    rel = wifi[i];
                }
            }
            if(cnt>=k){
                result = mid;
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return result;
    }
}
