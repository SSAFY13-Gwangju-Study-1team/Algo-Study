import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * 메모리 15832 시간 120ms
 * 조합 문제(중복 X)
 * 차례대로 한 부분에 방문 하는 경우와 그렇지 않은 경우를 나누어서 해주면 된다
 */
public class Main_15655_N과M_6 {
    static int n, m;
    static int[] nums;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());
        nums = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            nums[i] = parseInt(st.nextToken());
        }
        Arrays.sort(nums); // 오름차순 정렬을 위한 sort
        backtracking(0, 0, new int[m]);
        System.out.println(sb);
    }
    public static void backtracking(int depth, int target, int[] arr){
        if(depth==m){
            for(int i:arr){
                sb.append(i+ " ");
            }
            sb.append("\n");
            return;
        }
        if (target > n-1) return;

        arr[depth] = nums[target];

        // 조합에 target 넣는 경우
        backtracking(depth+1, target+1, arr);
        // 조합에 target 넣지 않는 경우
        backtracking(depth, target+1, arr);
    }
}
