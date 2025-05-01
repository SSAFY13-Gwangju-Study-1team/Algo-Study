import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main_13905_세부_임규리 {

    static class Edge implements Comparable<Edge> {

        private int from;
        private int to;
        private int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return o.weight - this.weight;
        }
    }

    static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return false;

        parent[rootY] = rootX;
        return true;
    }

    static int N;   // 집 개수
    static int M;   // 다리 개수
    static int s;   // 출발 위치
    static int e;   // 도착 위치
    static List<Edge> edges;    // 간선 리스트
    static int[] parent;    // Union-Find용 부모 배열
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        s = parseInt(st.nextToken());
        e = parseInt(st.nextToken());

        edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = parseInt(st.nextToken());
            int to = parseInt(st.nextToken());
            int weight = parseInt(st.nextToken());
            edges.add(new Edge(from, to, weight));
        }

        result = 0;

        Collections.sort(edges);
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (Edge edge : edges) {
            if (union(edge.from, edge.to)) {
                if (find(s) == find(e)) {
                    result = edge.weight;
                    break;
                }
            }
        }

        System.out.println(result);
    }
}
