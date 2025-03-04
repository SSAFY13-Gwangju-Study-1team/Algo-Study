import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
class Solution_타겟넘버 {
    static int answer;
    static int n;
    public int solution(int[] numbers, int target) {
        answer = 0;
        n = numbers.length;
        backtrack(0, 0, numbers, target);
        return answer;
    }
    public static void backtrack(int depth, int sum, int[] numbers, int target){
        if(depth==n){
            if(target==sum)
                answer++;
            return;
        }
        // +를 붙이는 경우
        backtrack(depth+1, sum+numbers[depth], numbers,target);
        // -를 붙이는 경우
        backtrack(depth+1, sum-numbers[depth], numbers,target);
    }
    public static void main(String[] args) throws Exception{
        Solution_타겟넘버 sol = new Solution_타겟넘버();
        int res = sol.solution(new int[]{4, 1, 2, 1}, 4);
        System.out.println(res);
    }
}
