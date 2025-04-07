import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * SWEA_3124_최소스패닝트리
 * Prim 알고리즘 사용
 * 난이도 5/10
 * 2447ms 162mb
 * 
 */
public class SWEA_3124_최소스패닝트리_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T, V, E;             // 테스트 케이스 수, 정점 수, 간선 수
    static List<List<Node>> list;   // 인접 리스트
    static boolean[] visited;       // 방문 여부

    // Node 클래스 정의
    // Comparable 인터페이스를 구현하여 가중치 기준으로 정렬
    static class Node implements Comparable<Node> {
        int v, w;
        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }

    public static void main(String[] args) throws Exception {
        // 테스트 케이스 입력 및 반복
        T = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append(String.format("#%d %d\n", t, solve()));
        }
        System.out.println(sb);
    }

    /**
     * 메인 메서드
     * @return  최소 스패닝 트리의 가중치 합
     * @throws Exception
     */
    public static long solve() throws Exception {
        // 입력 처리 및 초기화
        st = new StringTokenizer(br.readLine());
        V = parseInt(st.nextToken());
        E = parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = parseInt(st.nextToken());
            int to = parseInt(st.nextToken());
            int weight = parseInt(st.nextToken());

            list.get(from).add(new Node(to, weight));
            list.get(to).add(new Node(from, weight)); // 무향 그래프
        }

        return prim();  // Prim 알고리즘 호출
    }

    /**
     * Prim 알고리즘
     * @return  최소 스패닝 트리의 가중치 합
     */
    public static long prim() {
        PriorityQueue<Node> pq = new PriorityQueue<>(); // 가중치 기준 오름차순 정렬
        visited = new boolean[V + 1];                   // 방문 여부
        long total = 0;                                 // 가중치 합    
        int cnt = 0;                                    // 정점 개수

        pq.offer(new Node(1, 0)); // 임의의 정점(1번)에서 시작

        // Prim 알고리즘 수행
        // 방문하지 않은 정점 중에서 가중치가 가장 작은 간선을 선택하여 방문 처리
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (visited[current.v]) continue;   // 이미 방문한 정점이면 skip

            visited[current.v] = true;        // 방문 처리
            total += current.w;           // 가중치 합에 추가
            cnt++;                      // 정점 개수 증가

            if (cnt == V) break;        // 모든 정점을 방문했으면 종료

            for (Node next : list.get(current.v)) {
                if (!visited[next.v]) {
                    pq.offer(next);
                }
            }
        }

        return total;           // 최소 스패닝 트리의 가중치 합 반환
    }
}
