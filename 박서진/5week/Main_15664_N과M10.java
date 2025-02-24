import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * N개의 자연수 중에서 M개를 고른 수열 -> 조합
 * 수열 안에 같은 수가 있어서 조합을 고른 후에 숫자를 기록해서 다음 백트랙에서 숫자가 같다면 pass 하도록
 */
public class Main_15664_N과M10 {
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
        backtrack(0,  0, new int[m]);
        System.out.println(sb);
    }
    public static void backtrack(int depth, int start, int[] selected){
        if(depth==m){
            for(int i:selected){
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        int prevNum=0;

        for(int i=start;i<n;i++){
            if(prevNum==nums[i]) continue;
            selected[depth] = nums[i]; //백트래킹 호출 이전에 해줘야 함 반복문을 돌면서 숫자를 선택할 때 즉시 갱신해야 해!
            prevNum = nums[i];
            backtrack(depth+1, i+1, selected);
        }



    }
}
