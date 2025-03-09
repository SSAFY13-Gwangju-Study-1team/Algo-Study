import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_1182_부분수열의합 {
    static int n, s, res;
    static int[] nums;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        s = parseInt(st.nextToken());
        nums = new int[n];
        res = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            nums[i] = parseInt(st.nextToken());
        }
        backtrack(0, 0);
        if(s==0) res--; // 공집합을 빼줘야 한다
        System.out.println(res);
    }
    // sum이 s가 되면 res를 올린다
    public static void backtrack(int i, int sum){
        // 기저 조건
        // 조합을 다 구하면(들어가고 안들어가고를 다 구하면)
        if(i==n){
            if(sum==s){
                res++;
            }
            return;
        }
        
        // 선택하고
        backtrack(i + 1, sum + nums[i]);
        // 선택하지 않고
        backtrack(i+1, sum);
    }
}
