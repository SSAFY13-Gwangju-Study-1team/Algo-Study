import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1504_특정한_최단_경로_임규리 {

    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Node implements Comparable<Node> {
        int position;
        int totalWeight;

        public Node(int position, int totalWeight) {
            this.position = position;
            this.totalWeight = totalWeight;
        }

        @Override
        public int compareTo(Node o) {
            return this.totalWeight - o.totalWeight;
        }
    }

    static int N;   // 정점 개수
    static int E;   // 간선 개수
    static List<Edge>[] edges;  // 간선 배열
    static int[] must;  // 꼭 방문해야 하는 정점
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        edges = new List[N + 1];
        must = new int[2];
        dist = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[from].add(new Edge(to, weight));
            edges[to].add(new Edge(from, weight));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2; i++) {
            must[i] = Integer.parseInt(st.nextToken());
        }

        int d1 = dijkstra(1, must[0]);
        int d2 = dijkstra(must[0], must[1]);
        int d3 = dijkstra(must[1], N);
        long route1 = Integer.MAX_VALUE;

        if (d1 != Integer.MAX_VALUE && d2 != Integer.MAX_VALUE && d3 != Integer.MAX_VALUE) {
            route1 = (long) d1 + d2 + d3;
        }

        d1 = dijkstra(1, must[1]);
        d2 = dijkstra(must[1], must[0]);
        d3 = dijkstra(must[0], N);
        long route2 = Integer.MAX_VALUE;

        if (d1 != Integer.MAX_VALUE && d2 != Integer.MAX_VALUE && d3 != Integer.MAX_VALUE) {
            route2 = (long) d1 + d2 + d3;
        }

        long answer = Math.min(route1, route2);
        if (answer >= Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    private static int dijkstra(int start, int end) {
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.position == end) break;

            if (cur.totalWeight > dist[cur.position]) {
                continue;
            }

            for (Edge edge : edges[cur.position]) {
                if (dist[edge.to] > cur.totalWeight + edge.weight) {
                    dist[edge.to] = cur.totalWeight + edge.weight;
                    pq.add(new Node(edge.to, dist[edge.to]));
                }
            }
        }

        return dist[end];
    }
}