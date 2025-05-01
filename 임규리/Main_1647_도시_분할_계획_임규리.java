import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * MST 구하고 가장 높은 가중치의 간선 하나 지우기
 */
public class Main_1647_도시_분할_계획_임규리 {

    static class Edge implements Comparable<Edge> {
        int idx;
        int weight;

        public Edge(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static int N;   // 집의 개수
    static int M;   // 길의 개수
    static List<List<Edge>> list;   // 인접 리스트
    static boolean[] visited;   // 방문 처리
    static int maxWeight;   // 가장 큰 가중치
    static long result;     // 결과값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        list = new ArrayList<>();

        for (int i = 0; i <= N; i++) {  // 1번부터 사용
            list.add(new ArrayList<>());
        }

        visited = new boolean[N + 1];
        maxWeight = 0;
        result = 0L;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = parseInt(st.nextToken());
            int to = parseInt(st.nextToken());
            int weight = parseInt(st.nextToken());

            list.get(from).add(new Edge(to, weight));
            list.get(to).add(new Edge(from, weight));
        }

        prim();
        result -= maxWeight;
        System.out.println(result);
    }

    private static void prim() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (visited[cur.idx]) continue;

            visited[cur.idx] = true;
            maxWeight = Math.max(maxWeight, cur.weight);
            result += cur.weight;

            for (Edge next : list.get(cur.idx)) {
                if (!visited[next.idx]) {
                    pq.add(next);
                }
            }
        }
    }
}
