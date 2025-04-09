import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;

public class Main_1300_K번째수_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static long N, K;

    public static void main(String[] args) throws Exception {
        N = parseInt(br.readLine());
        K = parseInt(br.readLine());

        long l = 0;
        long r = N * N;

        while (l <= r) {
            long mid = (l + r) / 2;
            long cal = calcul(mid);

            if (K == cal) {
                System.out.println(mid);
                return;
            } else if (K < cal) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        System.out.println(l);
    }

    public static long calcul(long mid) {
        long order = 0;
        for (long i = 1; i <= N; i++) {
            order += Math.min(N, mid/i);
        }

        return order;
    }
}
