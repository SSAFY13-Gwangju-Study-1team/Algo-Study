import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main_1197_최소_스패닝_트리_임규리 {

    static class Edge implements Comparable<Edge> {
        private int idx;
        private int weight;

        public Edge(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static int V;   // 정점 개수
    static int E;   // 간선 개수
    static List<List<Edge>> list;   // 인접 리스트
    static boolean[] visited;       // 방문 배열
    static long result; // 결과값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = parseInt(st.nextToken());
        E = parseInt(st.nextToken());
        list = new ArrayList<>();
        visited = new boolean[V + 1];   // 1부터 사용
        result = 0L;

        for (int i = 0; i <= V; i++) {  // 1부터 사용
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int from = parseInt(st.nextToken());
            int to = parseInt(st.nextToken());
            int weight = parseInt(st.nextToken());

            list.get(from).add(new Edge(to, weight));
            list.get(to).add(new Edge(from, weight));
        }

        prim();
        System.out.println(result);
    }

    private static void prim() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0));   // 1번 노드부터 시작, 거리 0

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (visited[cur.idx]) continue;  // 이미 방문했으면 패스

            visited[cur.idx] = true;    // 방문 처리
            result += cur.weight;   // 가중치 누적

            for (Edge next : list.get(cur.idx)) {
                if (!visited[next.idx]) {
                    pq.add(next);
                }
            }
        }
    }
}
