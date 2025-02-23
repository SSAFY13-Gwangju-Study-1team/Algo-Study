import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_15657_N과M8 {
    static int n, m;
    static int[] nums;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        nums = new int[n];
        for(int i=0;i<n;i++){
            nums[i] = parseInt(st.nextToken());
        }
        Arrays.sort(nums);
        backtrack(0, 0, new int[m]);
        System.out.println(sb);
    }
    public static void backtrack(int depth, int index, int[] selected){
        if(depth>=m){
            for(int i:selected){
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }
        if(index>=n) return;

        selected[depth] = nums[index];
        // cnt 숫자를 골랐을 때
        backtrack(depth+1, index, selected);
        // cnt 숫자를 고르지 않았을 때
        backtrack(depth, index+1, selected);

    }
}
