import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SWEA_1952_수영장 (DP 버전)
 * 난이도 7/10
 * DP
 * 86ms 25mb
 * 
 * DP를 사용하여 1일, 1달, 3달, 1년 이용권을 조합하여 최소 비용을 계산하는 문제
 * DP 배열을 사용하여 각 월까지의 최소 비용을 저장하고, 이를 바탕으로 다음 월의 최소 비용을 계산함
 */
public class SWEA_1952_수영장DP_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[] ticket;    // 1일, 1달, 3달, 1년
    static int[] months;    // 12개월 이용일
    static int[] dp;        // DP 배열: dp[i] = i월까지의 최소 이용료

    public static void main(String[] args) throws Exception {
        // 테스트 케이스 수 입력
        int T = parseInt(br.readLine());

        // 테스트 케이스만큼 반복
        for (int t = 1; t <= T; t++) {
            solve();
            sb.append(String.format("#%d %d\n", t, dp[12]));
        }

        // 결과 출력
        System.out.println(sb);
    }
    
    /**
     * 메인 메서드
     * @throws Exception
     */
    public static void solve() throws Exception {
        // 배열 초기화 및 할당
        ticket = new int[4];                // 1일, 1달, 3달, 1년 이용권 가격
        months = new int[13];               // 1~12월 인덱스 사용 (0번 인덱스는 사용 X)
        dp = new int[13];                   // dp[i] = i월까지의 최소 이용료
        Arrays.fill(dp, Integer.MAX_VALUE); // DP 배열 초기화

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            ticket[i] = parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 12; i++) {
            months[i] = parseInt(st.nextToken());
        }

        // (0월의 이용료는 0)
        dp[0] = 0;
        
        // DP 로직 실행 (상향식)
        for (int i = 1; i <= 12; i++) {
            // 1일 이용권 사용
            int day = dp[i-1] + months[i] * ticket[0];
            
            // 1달 이용권 사용
            int month = dp[i-1] + ticket[1];
            
            // 3달 이용권 사용 (i가 3 이상인 경우만) 아니면 최대
            int quarter = (i >= 3) ? dp[i-3] + ticket[2] : Integer.MAX_VALUE;
            if (i == 1 || i == 2) {
                // 1월이나 2월에 3달 이용권을 사용하는 경우
                quarter = ticket[2];
            }
            
            // 1년 이용권 사용 (1월부터 시작할 때만) 아니면 최대
            int year = (i == 12) ? Math.min(dp[0] + ticket[3], dp[i]) : Integer.MAX_VALUE;
            if (i < 12) {
                year = Integer.MAX_VALUE;
            }
            
            // if (i == 12) {
            //     System.out.println("1년 이용권: " + year);
            //     System.out.println("3달 이용권: " + quarter);
            //     System.out.println("1달 이용권: " + month);
            //     System.out.println("1일 이용권: " + day);
            // }

            // 최소 비용 선택
            dp[i] = Math.min(Math.min(day, month), Math.min(quarter, year));
        }

        // for (int i = 0; i < 13; i++) {
        //     System.out.print(dp[i] + " ");
        // }
        // System.out.println();
    }
}