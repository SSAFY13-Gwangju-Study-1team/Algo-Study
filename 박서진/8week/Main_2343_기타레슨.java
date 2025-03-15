import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_2343_기타레슨 {
    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());
        arr=new int[n];
        int total = 0;
        int max = Integer.MIN_VALUE;
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i] = parseInt(st.nextToken());
            total += arr[i];
            max = Math.max(max, arr[i]);
        }
        // 범위 지정
        int left = max;
        int right = total;
        int mid;
        // 나누어 지는 강의 개수에 따라 left, right 조정
        while(left<=right){
            mid = (left+right)/2;
            int sum=0;
            int cnt=1; // 몇개로 나뉘어 지는지 체크
            for(int i=0;i<n;i++){
                sum += arr[i];
                if(sum>mid){
                    cnt++;
                    i--;
                    sum=0;
                }
            }
            if(cnt<=m){
                right = mid-1;
            }else{
                left = mid + 1;
            }
        }
        System.out.println(left);

    }
}
