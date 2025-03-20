import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * Main_1173_운동
 * 난이도 3/10
 * 시뮬레이션 그리디
 * 104ms 14mb
 * 
 * 운동을 할 때 최소 맥박과 최대 맥박이 주어지고 운동 시간, 휴식 시간, 운동량 증가량이 주어진다
 * 최소 맥박에서 운동량 증가량만큼 더해주고 최대 맥박을 넘지 않는다면 운동을 한다
 * 만약 최대 맥박을 넘는다면 최소 맥박으로 돌아가고 휴식을 한다
 * 이를 N번 반복하면서 걸리는 시간을 구하는 문제
 */
public class Main_1173_운동_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, m, M, T, R, X; // 운동 시간, 최소 맥박, 최대 맥박, 운동 시간, 휴식 시간, 운동량 증가량
    static int time; // 현재 시간

    public static void main(String[] args) throws Exception {
        // 입력 처리
        st = new StringTokenizer(br.readLine());

        N = parseInt(st.nextToken());
        m = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        T = parseInt(st.nextToken());
        R = parseInt(st.nextToken());

        X = m;      // 현재 맥박
        time = 0;   // 현재 시간

        // 만약 운동량 증가량이 최대 맥박보다 크다면 운동을 할 수 없다
        if (m + T > M) {
            System.out.println(-1);
            return;
        }

        // N번 반복
        while (N > 0) {
            if (X + T <= M) {   // 운동량 증가량을 더해도 최대 맥박을 넘지 않는다면 운동
                X += T;
                N--;
            } else {            // 최대 맥박을 넘는다면 휴식    
                X = Math.max(m, X - R); // 최소 맥박 미만으로 못 내려감
            }
            time++;             // 시간 증가
            if (X == m && X + T > M) {  // 최소 맥박이고 운동량 증가량을 더해도 최대 맥박을 넘는다면 운동을 할 수 없다
                System.out.println(-1);
                return;
            }
        }

        // 출력
        System.out.println(time);
    }
}
