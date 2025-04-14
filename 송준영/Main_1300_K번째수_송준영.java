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

        long l = 1;
        long r = N * N;

        while (l <= r) {
            long mid = (l + r) / 2;
            long count = calcul(mid);

            if (count < K) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        System.out.println(l);
    }

    public static long calcul(long mid) {
        long order = 0;
        for (long i = 1; i <= N; i++) {
            order += Math.min(N, mid / i);
        }
        return order;
    }
}
