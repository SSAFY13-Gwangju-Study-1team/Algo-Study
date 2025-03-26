import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * SWEA_7465_창용마을무리의개수
 * 난이도 2/10
 * 유니온 파인드
 * 110ms 28mb
 * 
 * 유니온 파인드를 이용하여 무리의 개수를 구하는 문제
 * 유니온 파인드를 한 번 싸악 다 돌고
 * parent 배열을 확인하면서 parent[i] == i 인 경우만 세어주면 집합의 개수가 세어진다.
 */
public class SWEA_7465_창용마을무리의개수_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T, N, M;     // 테케 수, 사람 수, 관계 수
    static int[] parent;    // 부모 배열

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
     * 메인메서드
     * @return  무리의 개수
     * @throws Exception
     */
    public static int solve() throws Exception {
        // 입력 처리
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        // 부모 배열 초기화
        parent = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        // 관계의 수만큼 입력 및 유니온 해주기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = parseInt(st.nextToken());
            int y = parseInt(st.nextToken());
            union(x, y);
        }
        
        int cnt = 0;    // 무리의 개수
        for (int i = 1; i <= N; i++) {
            if (parent[i] == i) cnt++;  // 부모가 자기 자신인 경우만 무리의 개수로 카운트
        }

        return cnt; // 무리의 개수 반환
    }

    /**
     * 유니온 파인드의 find 메서드
     * @param x 부모를 찾을 노드
     * @return  찾은 부모
     */
    public static int find(int x) {
        if (parent[x] == x) return x;   // 부모가 자기 자신인 경우

        return parent[x] = find(parent[x]); // 부모를 찾아서 저장하고 반환, path compression
    }

    /**
     * 유니온 파인드의 union 메서드
     * @param x 그룹 1
     * @param y 그룹 2
     * @return  합쳐진 경우 1, 이미 같은 그룹인 경우 0
     */ 
    public static int union(int x, int y) {
        int px = find(x);   // x의 부모
        int py = find(y);   // y의 부모

        if (px == py) return 0; // 이미 같은 그룹인 경우 안 합치고 0 반환

        // 더 작은 부모를 부모로 설정
        if (px < py) {
            parent[py] = px;
        } else {
            parent[px] = py;
        }
        
        return 1;   // 합쳐진 경우 1 반환
    }
}
