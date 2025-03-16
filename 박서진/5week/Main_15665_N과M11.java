import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * N개의 자연수 중에서 M개를 골라서 줄세우는 수열, 중복 가능 -  순열
 * 이전에 방문한 노드를 체크하고 다음에 같은 수면 pass
 */
public class Main_15665_N과M11 {
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
        backtrack(0,  new int[m]);
        System.out.println(sb);
    }
    public static void backtrack(int depth, int[] selected){
        if(depth==m){
            for(int i:selected){
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        int prevNum=0;

        for(int i=0;i<n;i++){
            if(prevNum==nums[i]) continue; // 만약 전에 방문한 숫자와 같으면
            selected[depth] = nums[i]; //백트래킹 호출 이전에 해줘야 함 반복문을 돌면서 숫자를 선택할 때 즉시 갱신해야 해!
            prevNum = nums[i]; // 이전에 방문한 노드의 수를 기록하기
            backtrack(depth+1, selected);
        }


    }
}
