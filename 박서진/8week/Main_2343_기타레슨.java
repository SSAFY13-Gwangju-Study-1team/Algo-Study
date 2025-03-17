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
        // 가장 긴 동영상 길이
        int max = Integer.MIN_VALUE;
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i] = parseInt(st.nextToken());
            total += arr[i];
            max = Math.max(max, arr[i]);
        }
        // 이분탐색 시작
        // 범위 지정
        // 강의의 개수만큼 나눌 때 블루레이의 최소값
        int left = max;
        // 모든 동영상길이의 합(강의를 하나로 나눌때)
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
                    // sum을 넘기는 순간을 구한다
                    cnt++;
                    i--;
                    sum=0;
                }
            }
            // 같은 cnt라도 가장 왼쪽에 있는 값을 구한다
            if(cnt<=m){
                right = mid-1;
            }else{
                left = mid + 1;
            }
        }
        System.out.println(left);

    }
}
