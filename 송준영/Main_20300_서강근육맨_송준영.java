import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * Main_20300_서강근육맨
 * 난이도 3/10
 * 그리디
 * 208ms 18mb
 * 
 * 정렬을 해서 작은 값이랑 큰값의 합이 최소가 되게끔
 * 홀수면 정렬시 마지막 값이 최대값이 되므로 따로 처리
 * 양 끝점 씩 더하면 골고루 평균치? 처럼 나오는데 그게 최소일때가 된다
 * 그래서 그리디 문제
 * 
 * 그런데 이것을 확실하게 알거나 증명할 수 있어야하는데
 * 나는 그게 약해서 그냥 감으로 했다 (이렇게하면 되지 않을까?)
 */
public class Main_20300_서강근육맨_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;               // 기구 수
    static long[] muscleDamage; // 근손실 배열 (값이 10^18 이므로 long으로 처리)
    static long result = 0;     // 결과값

    public static void main(String[] args) throws Exception {
        // 입력 처리 및 초기화
        N = parseInt(br.readLine());
        muscleDamage = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            muscleDamage[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(muscleDamage); // 정렬

        // 홀수일때 처리
        int lastidx = N;
        if (N % 2 != 0) {
            lastidx = N - 1;
            result = Math.max(result, muscleDamage[N - 1]);
        }

        // 양 끝값을 더해가면서 최대값을 찾는다 -> 최대가 최소가 되게끔
        for (int i = 0; i < lastidx / 2; i++) {
            result = Math.max(result, muscleDamage[i] + muscleDamage[(lastidx - 1) - i]);
        }

        // 결과 출력
        System.out.println(result);
    }
}
