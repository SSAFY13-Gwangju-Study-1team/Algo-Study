import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * SWEA_24309_당구천태주현이를이겨라
 * 난이도 3/10
 * 수학
 * 86ms 25mb
 * 
 * 문제 개미랑 같은 문제
 */
public class SWEA_24309_당구천태주현이를이겨라_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T;       // 테케 수
    static int w, h, p, q, k;   // w: 가로, h: 세로, p: 출발 x좌표, q: 출발 y좌표, k: 시간
    static int nx, ny;  // nx: 목표 x좌표, ny: 목표 y좌표

    public static void main(String[] args) throws Exception {
        T = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            solve();
            sb.append(String.format("#%d %d %d\n", t, nx, ny));
        }
        System.out.println(sb);
    }

    public static void solve() throws Exception {
        st = new StringTokenizer(br.readLine());
        w = parseInt(st.nextToken());
        h = parseInt(st.nextToken());
        p = parseInt(st.nextToken());
        q = parseInt(st.nextToken());
        k = parseInt(st.nextToken());

        // 수학 계산 (좌표 계산)
        int mockX = Math.abs(p - k) / w, restX = Math.abs(p - k) % w;
        int mockY = Math.abs(q - k) / h, restY = Math.abs(q - k) % h;
        nx = mockX % 2 == 0 ? restX : w - restX;
        ny = mockY % 2 == 0 ? restY : h - restY;
    }
}
