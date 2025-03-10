import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main_22864_피로도_임규리 {

    static int A;   // 피로도
    static int B;   // 처리량
    static int C;   // 휴식
    static int M;   // 최대 피로도
    static int cur;     // 현재까지의 피로도
    static int count;   // 하루 최대 처리량

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = parseInt(st.nextToken());
        B = parseInt(st.nextToken());
        C = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        for (int i = 0; i < 24; i++) {
            if (cur + A <= M) {
                cur += A;
                count += B;
            } else {
                if (cur - C < 0) {
                    cur = 0;
                } else {
                    cur -= C;
                }
            }
        }

        System.out.println(count);
    }
}
