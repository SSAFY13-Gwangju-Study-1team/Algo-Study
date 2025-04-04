
public class 연습문제1_아파트색칠하기_송준영 {
    public static void main(String[] args) {
        int[][] dp = new int[9][2]; // dp 배열 선언
        
        dp[1][0] = 1; // 초기값 설정
        dp[1][1] = 1; // 초기값 설정

        for (int i = 2; i <= 8; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1]; // i층의 1번째 색상은 i-1층의 1번째 색상과 i-1층의 2번째 색상을 더한 것
            dp[i][1] = dp[i - 1][0];                // i층의 2번째 색상은 i-1층의 1번째 색상과 같음
        }
        
        System.out.println(dp[8][0] + dp[8][1]); // 8층의 1번째 색상과 2번째 색상을 더한 것
    }
}
