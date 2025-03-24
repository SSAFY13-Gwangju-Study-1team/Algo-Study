import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * Main_22864_피로도
 * 난이도 2/10
 * 그리디
 * 108ms 14mb
 * 
 * 처음에 봤을 때 어려워 보였는데 그냥 피로도가 임계 넘을 것 같으면 쉬어 주고 안 넘으면 일하는 간단한 그리디 문제이다
 */
public class Main_22864_피로도_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int A, B, C, M;  // 피로 증가량, 일 증가량, 휴식시 피로 감소량, 임계치
    static int work, tired; // 일한 시간, 현재 피로도

    public static void main(String[] args) throws Exception {
        // 입력 처리
        st = new StringTokenizer(br.readLine());
        A = parseInt(st.nextToken());
        B = parseInt(st.nextToken());
        C = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        work = tired = 0;   // 초기화

        for (int i = 0; i < 24; i++) {
            if (tired + A <= M) {   // 일을 했을 때 피로도가 임계치를 넘지 않으면 일을 한다
                work += B;          // 총 일에 일 추가
                tired += A;         // 피로도 증가
            } else {                // 일을 했을 때 피로도가 임계치를 넘으면 휴식을 취한다
                tired -= C;         // 피로도 감소
                tired = tired < 0 ? 0 : tired;  // 피로도가 음수가 되면 0으로 초기화
            }
        }

        // 결과 출력
        System.out.println(work);
    }
}
