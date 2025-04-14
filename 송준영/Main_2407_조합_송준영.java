import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main_2407_조합_송준영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        // 입력 처리
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        BigInteger first = BigInteger.ONE;
        BigInteger second = BigInteger.ONE;

        for (int i = 1; i <= M; i++) {
            first = first.multiply(BigInteger.valueOf(N - i + 1));
            second = second.multiply(BigInteger.valueOf(i));
        }

        BigInteger result = first.divide(second);

        // 출력
        System.out.println(result);
    }
}
