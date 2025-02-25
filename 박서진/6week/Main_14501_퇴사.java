import java.io.*;
import java.util.*;

import static java.lang.Integer.max;
import static java.lang.Integer.parseInt;
public class Main_14501_퇴사 {
    static int n;
    static int[] P;
    static int[] T;
    static int maxVal=0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = parseInt(br.readLine());
        T = new int[n+1];
        P = new int[n+1];
        for(int i=1;i<=n;i++){
            st = new StringTokenizer(br.readLine());
            T[i] = parseInt(st.nextToken());
            P[i] = parseInt(st.nextToken());
        }

        // 최대값을 구하기 위한 dfs
        dfs(1, 0);
        System.out.println(maxVal);
    }
    static void dfs(int day, int value){
        if(day>=n+1) {
            maxVal = Math.max(maxVal, value);
            return;
        }
        
        // 그 다음 일수를 고르는 부분
        if(day+T[day]<=n+1) { // 퇴사일을 넘지 않도록 체크
            dfs(day+T[day], value+P[day]);
        }
        // 현 일수를 고르지 않는 경우(완전탐색을 위한 코드)
        dfs(day + 1, value);
    }
}
