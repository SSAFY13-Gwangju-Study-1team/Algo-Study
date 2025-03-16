import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * 메모리 43720 시간 376ms
 * 중복 순열!!
 */
public class Main_15654_N과M_5 {
    static int n, m;
    static int[] nums;
    static boolean[] isVisited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());
        nums = new int[n];
        isVisited = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            nums[i] = parseInt(st.nextToken());
        }
        Arrays.sort(nums); // 오름차순 정렬을 위한 sort
        backtracking(0,new int[m]);
        System.out.println(sb);
    }
    public static void backtracking(int depth, int[] arr){
        if(depth==m){
            for(int i:arr){
                sb.append(i+ " ");
            }
            sb.append("\n");
            return;
        }

        for(int i=0;i<n;i++) {
            if(isVisited[i]) continue;
            isVisited[i] = true;
            arr[depth] = nums[i];
            backtracking(depth + 1, arr);
            isVisited[i] = false;
        }

    }
}
