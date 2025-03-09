import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_10972_다음순열2 {
    static int n;
    static int[] nums;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = parseInt(br.readLine());
        nums = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            nums[i] = parseInt(st.nextToken());
        }

        if(np()){
            for(int i:nums){
                sb.append(i).append(" ");
            }
        }else{
            sb.append(-1);
        }
        System.out.println(sb);

    }
    public static boolean np(){
        int i = n-1;
        while(i>=1 && nums[i-1]>=nums[i]) i--;
        if(i==0) return false;

        int j = n-1;
        while(nums[i-1]>=nums[j]) j--;

        swap(i-1, j);
        int k=n-1;
        while(i<k) swap(i++, k--);

        return true;
    }

    private static void swap(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
