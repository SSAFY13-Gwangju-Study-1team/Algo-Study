import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.*;

public class Main_24391_귀찮은해강이_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static int[] parents, arr;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        arr = new int[N];
        parents = new int[N+1];
        for (int i = 0; i <= N; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = parseInt(st.nextToken()), y = parseInt(st.nextToken());

            union(x, y);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(st.nextToken());
        }


        int result = 0;
        for (int i = 0; i < N-1; i++) {
            int a = find(arr[i]);
            int b = find(arr[i+1]);

            if (a != b) result++;
        }

        System.out.println(result);
    }

    public static int find(int x) {
        if (x == parents[x]) return x;

        return parents[x] = find(parents[x]);
    }

    public static boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px == py) return false;

        if (px < py) parents[py] = px;
        else parents[px] = py;

        return true;
    }
}
