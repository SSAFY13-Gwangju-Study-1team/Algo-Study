import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * 메모리 19916 시간 164ms
 * 오름차순으로 자리가 정해지기 때문에 중복 조합으로 풀 수 있다.
 */
public class Main_15652_N과M_4 {
    static int n, m;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());

        backtracking(0, 1, new int[m]);
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
        if (target > n) return;

        // 조합에 target(현재숫자)를 다시 넣는 경우
        arr[depth] = target;
        backtracking(depth+1, target, arr);

        backtracking(depth, target + 1, arr);
    }
}
