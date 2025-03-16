import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * 수열안에 같은 수가 있을 때 같은 집합을 출력하지 않아야 함
 * 하지만 (9,9)처럼 같은 수의 집합을 출력해야함
 * set으로 풀려다가 다음과 같이 적용해봄
 * 백트랙에 들어가기 전에 같은 depth에서 전에 다녀온 dfs의 숫자가 뭔지 기록하고 다음에 들어갈 때 같으면 return을 하는것임
 * (물론 그 전에 정렬되어 있어야 같은 값 비교 가능!!)
 * 조건을 걸고 확인하는 부분이 어려웠던 문제ㅜㅜ
 */
public class Main_15663_N과M9 {
    static int n, m;
    static int[] nums;
    static boolean[] isVisited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        nums = new int[n];
        isVisited = new boolean[n];

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
            if (isVisited[i]) continue;
            if(prevNum==nums[i]) continue;
            isVisited[i] =true;
            selected[depth] = nums[i]; //백트래킹 호출 이전에 해줘야 함 반복문을 돌면서 숫자를 선택할 때 즉시 갱신해야 해!
            prevNum = nums[i];
            backtrack(depth+1, selected);
            isVisited[i] = false;
        }


    }
}
