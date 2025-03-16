import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

/**
 * 100 ms
 * 쉬운 조합 문제 -> 9개중 7개를 뽑아 조건을 만족시키는 (합이 100) 조합을 되는 문제
 * 이 풀이는 조합으로 풀었습니다*^^*
 */
public class Main_3040_백설공주와일곱난쟁이_ver2 {
    static int[] nums;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        nums = new int[9];
        for(int i=0;i<9;i++){
            nums[i] = parseInt(br.readLine());
        }
        arr = new int[7];
        backtrack(0, 0, 0);
        System.out.println(sb);
    }

    // 조합(난쟁이 9명 중에 난쟁이 7명을 고르는 경우)
    public static void backtrack(int depth, int index, int sum){
        if(sum>100) return;
        if(depth==7){
            if(sum==100){
                for(int i:arr){
                    sb.append(i).append("\n");
                }
            }
            return;
        }

        for(int i=index;i<9;i++){
            arr[depth] = nums[i];
            backtrack(depth+1, i+1, sum+nums[i]);
        }

    }
}
