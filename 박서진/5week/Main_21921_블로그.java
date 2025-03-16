import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_21921_블로그 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = parseInt(st.nextToken());
        int m = parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i=0;i<n;i++){
            arr[i] = parseInt(st.nextToken());
        }

        int sum = 0;
        for(int i=0;i<m;i++){
            sum+=arr[i];
        }
        int max_sum=sum;
        int cnt=1;

        for(int i=0;i<n-m;i++){
            sum = sum-arr[i]+arr[i+m];
            if(sum>max_sum){
                max_sum = sum;
                cnt = 1;
            }else if(sum == max_sum) cnt++;

        }

        if(max_sum==0){
            System.out.println("SAD");
        }else{
            System.out.println(max_sum);
            System.out.println(cnt);
        }
    }
}
