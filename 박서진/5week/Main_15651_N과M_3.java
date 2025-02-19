import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * 메모리 211516 시간 660ms
 * 중복 순열!!
 */
public class Main_15651_N과M_3 {
    static int n, m;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());

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

        for(int i=1;i<=n;i++) {
            arr[depth] = i;
            backtracking(depth + 1, arr);
        }

    }
}
