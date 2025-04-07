import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * Main_1753_최단경로_송준영
 * 난이도 4/10
 * 다익스트라
 * 876ms 132mb
 * 
 * 다익스트라 알고리즘을 이용하여 최단 경로를 구하는 문제이다.
 * 다익스트라 알고리즘은 우선순위 큐를 이용하여 구현하였다.
 */
public class Main_1753_최단경로_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int V, E, start; // 정점 수, 간선 수, 시작 정점
    static List<Map<Integer, Integer>> graph = new ArrayList<>();   // 그래프, 최소 갱신을 좀 더 편하게 하기 위해 map으로 선언
    static int[] dist;      // 거리 배열
    static PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {  // 우선순위 큐를 이용하여 거리 기준으로 정렬
        return o1[1] - o2[1];
    });

    public static void main(String[] args) throws Exception {
        // 입력 처리 및 초기화
        st = new StringTokenizer(br.readLine());
        V = parseInt(st.nextToken());
        E = parseInt(st.nextToken());
        start = parseInt(br.readLine());
        
        for (int i = 0; i <= V; i++) {
            graph.add(new HashMap<>());
        }
        
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = parseInt(st.nextToken());
            int v = parseInt(st.nextToken());
            int w = parseInt(st.nextToken());
            if ( graph.get(u).containsKey(v) ) {
                graph.get(u).put(v, Math.min(graph.get(u).get(v), w));  // 이미 간선이 존재하는 경우, 최소값으로 갱신
            } else {
                graph.get(u).put(v, w);                                 // 간선이 존재하지 않는 경우, 추가
            }
        }
        dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);               // 거리 배열 초기화
        dist[start] = 0;                                    // 시작 정점의 거리는 0으로 초기화

        pq.offer(new int[] {start, 0});                     // 시작 정점을 우선순위 큐에 추가

        // 다익스트라 돌기
        while(!pq.isEmpty()) {
            int[] temp = pq.poll();
            int cur = temp[0];
            int val = temp[1];

            if (dist[cur] != val) continue; // 이미 처리된 정점인 경우 continue

            for (int next : graph.get(cur).keySet()) {
                if (dist[next] > val + graph.get(cur).get(next)) {  // 다음 정점의 거리가 현재 정점의 거리 + 간선 가중치보다 큰 경우
                    dist[next] = val + graph.get(cur).get(next);    // 거리 갱신
                    pq.offer(new int[] { next, dist[next] });       // 우선순위 큐에 추가
                }
            }
        }

        // 출력 처리
        for (int i = 1; i <= V; i++) {
            sb.append(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]).append('\n'); // 방문 안한 정점은 INF로 표시
        }

        // 출력
        System.out.println(sb);
    }
}
