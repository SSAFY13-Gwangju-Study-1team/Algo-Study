import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_2961_도영이가만든맛있는음식 {
    static int[][] ingr;
    static int minRes = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = parseInt(br.readLine());
        ingr = new int[n][2];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            ingr[i][0] = parseInt(st.nextToken());
            ingr[i][1] = parseInt(st.nextToken());
        }

        backtrack(n, 0,0,1, 0);
        System.out.println(minRes);
    }

    /**
     * 음식 고르는 것 -> 중복이 안되는 조합
     */
    private static void backtrack(int n, int depth, int selectedCnt, int sour, int bitter) {
        if(depth==n) {
            if(selectedCnt>=1) { // 음식을 하나 이상 무조건 골라야 함! 이걸 안하면 아무것도 고르지 않는 공집합이 들어가서 틀릴 수 있음
                int total = Math.abs(sour - bitter);
                minRes = Math.min(minRes, total);
            }
            return;
        }
        
        // index의 음식을 골랐을 때
        backtrack(n, depth+1, selectedCnt+1, sour*ingr[depth][0], bitter+ingr[depth][1]);
        // index 음식을 고르지 않았을 때
        backtrack(n, depth+1, selectedCnt, sour, bitter);
    }
}
