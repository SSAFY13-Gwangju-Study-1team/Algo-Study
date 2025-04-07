
public class 연습문제2_막대색칠하기_송준영 {
    public static void main(String[] args) {
        int[] dp = new int[7]; // dp 배열 선언

        dp[1] = 2; // 초기값 설정
        dp[2] = 5; // 초기값 설정

        for (int i = 3; i <= 6; i++) {
            dp[i] = dp[i - 1] * 2 + dp[i - 2]; // i번째 막대는 i-1번째 막대의 2배(1cm 더하기) + i-2번째 막대(2cm 더하기)
        }

        System.out.println(dp[6]); // 6번째 막대
    }
}
