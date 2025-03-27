import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * SWEA_3124_최소스패닝트리
 * 난이도 4/10
 * MST
 * 1889ms 122mb
 * 
 * 크루스칼 알고리즘을 이용하여 최소 스패닝 트리를 구하는 문제
 */
public class SWEA_3124_최소스패닝트리_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T, V, E;         // 테케 수, 정점 수, 간선 수
    static List<int[]> list;    // 간선 리스트
    static int[] parent;        // 부모 배열

    public static void main(String[] args) throws Exception {
        // 테케 수 입력 및 반복
        T = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append(String.format("#%d %d\n", t, solve()));
        }
        // 결과 출력
        System.out.println(sb);
    }

    /**
     * 메인 메서드
     * @return
     * @throws Exception
     */
    public static long solve() throws Exception {
        // 입력 처리 및 초기화
        st = new StringTokenizer(br.readLine());
        V = parseInt(st.nextToken());
        E = parseInt(st.nextToken());
        list = new ArrayList<>();
        parent = new int[V+1];
        for (int i = 1; i < V+1; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = parseInt(st.nextToken());
            int B = parseInt(st.nextToken());
            int C = parseInt(st.nextToken());
            list.add(new int[] { C, A, B });
        }

        // 가중치를 기준으로 오름차순 정렬
        Collections.sort(list, (o1, o2) -> {
            return o1[0] - o2[0];
        });


        int cnt = 0;        // 현재 선택한 간선 수
        long result = 0;    // 결과값

        // 크루스칼 알고리즘
        for (int[] e : list) {
            if (union(e[1], e[2])) {    // 사이클이 발생하지 않으면 넣기
                result += e[0];
                // System.out.println("?");
                if (++cnt == (V - 1)) break;    // 간선 수가 정점 수 - 1이면 종료
            }
        }

        return result;  // 결과 반환
    }

    /**
     * 부모 찾기
     * @param x 부모 찾을 정점
     * @return  부모
     */
    public static int find(int x) {
        if (parent[x] == x) return x;

        return parent[x] = find(parent[x]);   // 경로 압축
    }

    /**
     * 합치기
     * @param x 
     * @param y
     * @return  합쳐졌는지 여부
     */
    public static boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px == py) return false; // 부모가 같으면 사이클 발생, false 반환

        if (px < py) parent[py] = px;   // 작은 쪽으로 합치기
        else parent[px] = py;
        return true;    // 합쳐졌다면 true 반환
    }
}
