package argoStudy;

import java.io.*;
import java.util.*;

public class Test {
    static int n;
    static String[] num;
    static boolean[] visited;
    static boolean result;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        num = new String[n];
        for(int i = 0; i<n; i++) {
            num[i] = Integer.toString(i+1);
        }

        visited = new boolean[n+1];


        // backtrack의 반환 결과 소수라면 false를 리턴함
        // 그래서 !false를 통해서 "NO"를 출력
        if(!backtrack("", 0, 0)){
            System.out.println("NO");
        }
    }

    public static boolean backtrack(String s, int cnt, int sum) {
        boolean result = false;

        // 소수이면 중단
        if(sosu(sum)){
            return false;
        }

        // cnt가 n이 되면 종료
        if(cnt == n) {
            System.out.println("YES");
            System.out.println(s.trim());
            return true;
        }
        for(int i = 0; i<n; i++){
            if(!visited[i]){
                visited[i] = true;

                // 여기서 답을 찾아도 다른 답들을 계속 탐색하기 떄문에
                // 바로 true로 리턴해줘야함
                if(backtrack(s+num[i]+" ",cnt+1, sum+Integer.parseInt(num[i]))){
                    return true;
                }
                visited[i] = false;
            }
        }
        return result;
    }

    public static boolean sosu(int t){


        if(t <= 1) return false; // 0일 경우 true가 반환될 수 있으니까 t의 범위가 1보다 작거나 같아야함
        if(t == 3) return true;  // 3일 경우 소수 이므로 true 반환 해줘야함
        for(int i = 2; i<=t; i++){
            if(t%i == 0){
                return false;
            }
        }
        return true;


    }
}
