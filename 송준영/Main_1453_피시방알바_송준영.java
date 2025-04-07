import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.*;

public class Main_1453_피시방알바_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, cnt = 0;
    static Set<Integer> set;

    public static void main(String[] args) throws Exception {
        N = parseInt(br.readLine());
        set = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = parseInt(st.nextToken());
            if (set.contains(num)) {
                cnt++;
            } else {
                set.add(num);
            }
        }

        System.out.println(cnt);
    }
}
