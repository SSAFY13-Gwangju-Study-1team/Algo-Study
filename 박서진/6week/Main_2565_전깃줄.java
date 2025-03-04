import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 100ms 
 * dp문제 중 LIS 문제 
 * 증가하는 부분 수열이랑 비슷한 문제
 */
public class Main_2565_전깃줄 {
    static int n;
    static int[][] A;
    static int[] dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = parseInt(br.readLine());
        A = new int[n+1][2];
        dp = new int[n+1]; // 겹치지 않는 전깃줄의 개수 저장

        for(int i=1;i<=n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i][0] = parseInt(st.nextToken());
            A[i][1] = parseInt(st.nextToken());
        }
        Arrays.sort(A, new Comparator<int[]>() { // 0번째 인덱스로 정렬
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0]; // 첫번재 값 기준 오름차순
            }
        }); // A 오름 차순 정렬

        int res = 0; // 최종 max 값은 마지막에 들어가는 것이 아님
        dp[1] = 1;
        for(int i=2;i<=n;i++){
            dp[i] = 1; // 그냥 나만 되는 경우
            for(int j=1;j<i;j++) {
                if (A[j][1] < A[i][1] && dp[j] >= dp[i]) { // LIS 사용하기 - B에 든 수는 더 크고 dp는 작을 때
                    dp[i] = dp[j]+1;
                }
            }
            res = Math.max(res, dp[i]); // 전깃줄에 사용 가능 최대수 구하기
        }
        System.out.println(n-res); // 제거해야하는 최소수로 바꾸기

    }
}
