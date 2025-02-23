import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

// 질문할 것 이거 왜 안돼?
/**
 * N개의 자연수 중에서 M개를 고른 수열 -> 조합 -- 이 풀이는 집합으로 풀어보았습니다!
 * 수열 안에 같은 수가 있어서 조합을 고른 후에 숫자를 기록해서 다음 백트랙에서 숫자가 같다면 pass 하도록
 */
public class Main_15664_N과M10_ver2 {
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
        backtrack(0,  0, new int[m], -1);
        System.out.println(sb);
    }

    public static void backtrack(int depth, int target, int[] selected, int prevNum){
        if(depth==m){
            for(int i:selected){
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        if (target>=n) return;
        if(prevNum==nums[target]) return;
        selected[depth] = nums[target];
        // 현재 인덱스를 고를 때 
        backtrack(depth+1, target+1, selected, -1);
        // 현재 인덱스를 고르지 않을 때
        backtrack(depth, target+1, selected, nums[target]);
    }
}
