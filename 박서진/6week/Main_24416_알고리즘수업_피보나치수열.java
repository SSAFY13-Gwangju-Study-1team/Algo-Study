import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;
public class Main_24416_알고리즘수업_피보나치수열 {
    static int cnt1=0;
    static int cnt2=0;
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n+1];
        fib(n);
        fibonacci(dp, n);
        System.out.println(cnt1+ " "+cnt2);
    }
    private static int fib(int n){
        if(n==1 || n==2){
            cnt1++;
            return 1;
        }
        return fib(n-1) + fib(n-2);
    }
    private static int fibonacci(int[] dp, int n){
        if (n == 1 || n == 2) return 1;

        dp[1] = 1;
        dp[2] = 1;
        for(int i=3;i<=n;i++){
            dp[i] = dp[i-1]+dp[i-2];
            cnt2++;
        }
        return dp[n];
    }
}
