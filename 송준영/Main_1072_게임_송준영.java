import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_1072_게임
 * 난이도 4/10
 * 이분탐색
 * 100ms 14mb
 * 
 * 수 범위가 매우 커서 이분탐색을 사용해야 한다
 * 수가 매우 크기 때문에 long을 사용하였다
 * 또한 99퍼에서는 절대 100퍼가 될 수 없으므로 -1을 출력해 주면 된다.
 */
public class Main_1072_게임_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static long x, y;   // 게임 횟수, 이긴 게임 수

    public static void main(String[] args) throws Exception {
        // 입력
        st = new StringTokenizer(br.readLine());
        x = Long.parseLong(st.nextToken());
        y = Long.parseLong(st.nextToken());

        long z = (y * 100) / x; // 승률

        // 99퍼에서 100퍼가 될 수 없으므로 -1 출력
        if (z >= 99) {
            System.out.println(-1);
            return;
        }

        // 이분탐색을 위한 왼쪽, 오른쪽 변수 선언
        long l = 0;
        long r = 1000000000;

        // 이분탐색
        while (l <= r) {
            long mid = (l + r) / 2;

            long temp = ((y + mid) * 100) / (x + mid);

            if (temp > z) { // 승률이 높아지면, 오른쪽을 줄여준다
                r = mid - 1;
            } else {        // 승률이 높아지지 않으면, 왼쪽을 늘려준다
                l = mid + 1;
            }
        }

        // 출력
        System.out.println(l);
    }
}
