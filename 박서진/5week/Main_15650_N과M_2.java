import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 메모리 15880 시간 128ms
 * 오름차순으로 순열을 구하는 문제는 -> 조합으로 구할 수 있음(자리가 정해져 있기 때문)
 * 차례대로 한 부분에 방문 하는 경우와 그렇지 않은 경우를 나누어서 해주면 된다
 */
public class Main_15650_N과M_2 {
    static int n, m;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());

        backtracking(0, 1, new int[m]);
    }
    public static void backtracking(int depth, int target, int[] arr){
        if(depth==m){
            for(int i:arr){
                System.out.print(i+ " ");
            }
            System.out.println();
            return;
        }
        if (target > n) return;

        arr[depth] = target;

        // 조합에 target 넣는 경우
        backtracking(depth+1, target+1, arr);
        // 조합에 target 넣지 않는 경우
        backtracking(depth, target+1, arr);
    }
}
