import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * SWEA_3289_서로소집합_송준영
 * 난이도 3/10
 * union-find
 * 406ms 108mb
 * 
 * union-find를 사용하여 서로소 집합을 구현하는 문제
 * find와 union 메서드를 구현하여 사용하면 된다
 * union은 좀 특별하게 flag값을 넘겨 줘서 0이면 합치고 1이면 같은 집합인지 확인만 하는 방식으로 구현
 */
public class SWEA_3289_서로소집합_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T;       // 테케 수
    static int N, M;    // 집합 수, 연산 수
    static int[] parent;    // 부모 배열

    public static void main(String[] args) throws Exception {
        // 테케 수 입력 및 반복
        T = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append(String.format("#%d ", t));
            solve();
        }
        // 결과 출력
        System.out.println(sb);
    }

    /**
     * 메인메서드
     * @throws Exception
     */
    public static void solve() throws Exception {
        // 입력 처리 및 초기화
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        parent = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;  // 초기 부모는 자기 자신
        }

        // 연산 처리
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int com = parseInt(st.nextToken());
            int x = parseInt(st.nextToken());
            int y = parseInt(st.nextToken());

            if (com == 1) { // 같은 집합인지 확인
                sb.append(union(x, y, com));    // 같은 집합이면 1, 아니면 0
            } else {        // 합치기
                union(x, y, com);
            }
        }

        // 줄바꿈
        sb.append('\n');
    }

    /**
     * find 메서드
     * @param x 부모를 찾을 노드
     * @return  부모 노드
     */
    public static int find(int x) {
        if (parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }

    /**
     * union 메서드
     * @param x     합칠 노드 1
     * @param y     합칠 노드 2
     * @param flag  0이면 합치기, 1이면 같은 집합인지 확인
     * @return      같은 집합이면 1, 아니면 0
     */
    public static int union(int x, int y, int flag) {
        int px = find(x);
        int py = find(y);

        if (px == py) return 1; // 같은 집합이면 1

        if (flag == 0) {        // 합치기 (flag가 0일 때)
            if (px < py) {
                parent[py] = px;
            } else {
                parent[px] = py;
            } 
        }

        // 다른 집합이면 0
        return 0;
    }
}
