import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * Main_15810_풍선공장
 * 난이도 3/10
 * 이분탐색 파라매트릭서치
 * 528ms 92mb
 * 
 * 풍선을 만드는데 걸리는 시간을 이분탐색으로 찾아내는 문제
 * 특정 시간에 만들 수 있는 풍선의 수를 계산하는 함수를 만들어서 이분탐색을 진행
 * Long.MAX_VALUE로 초기화하면 안되고 적절한 값으로 초기화해야 한다 -> 오버플로우 남
 */
public class Main_15810_풍선공장_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;        // 직원 수, 풍선 수
    static int[] staff;     // 직원들이 풍선을 만드는데 걸리는 시간

    public static void main(String[] args) throws Exception {
        // 입력 처리
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        
        // 직원들이 풍선을 만드는데 걸리는 시간 입력
        staff = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            staff[i] = parseInt(st.nextToken());
        }

        long l = 1, r = (long)1e12; // 최대 시간을 적절히 설정

        // 이분탐색
        while (l <= r) {
            long mid = (l + r) / 2;
            if (makeBalloon(mid) >= M) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        // 출력
        System.out.println(l);
    }

    /**
     * 특정 시간에 만들 수 있는 풍선의 수 계산
     * @param time  특정 시간
     * @return      특정 시간에 만들 수 있는 풍선의 수
     */
    public static long makeBalloon(long time) {
        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += time / staff[i];
            if (sum >= M) 
                return sum; // 이미 M개 이상이면 조기 종료 가능
        }
        return sum;
    }
}