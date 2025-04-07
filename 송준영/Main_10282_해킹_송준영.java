import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.*;

public class Main_10282_해킹_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T;   // 테스트케이스 수
    static int N, D, C;   // N: 컴퓨터 수, D: 통로 수, C: 해킹된 컴퓨터 번호
    static List<List<Node>> graph; // 그래프

    public static void main(String[] args) throws Exception {
        T = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            solve();
        }
        System.out.println(sb);
    }

    public static void solve() throws Exception {
        // 입력 처리
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        D = parseInt(st.nextToken());
        C = parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < D; i++) {
            st = new StringTokenizer(br.readLine());
            int a = parseInt(st.nextToken());
            int b = parseInt(st.nextToken());
            int s = parseInt(st.nextToken());
            graph.get(b).add(new Node(a, s));
        }

        dijkstra(C);
    }

    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (dist[cur.idx] < cur.cost) continue;

            for (Node next : graph.get(cur.idx)) {
                if (dist[next.idx] > cur.cost + next.cost) {
                    dist[next.idx] = cur.cost + next.cost;
                    pq.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }

        // System.out.println(Arrays.toString(dist));
        
        int count = 0, time = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] != Integer.MAX_VALUE) {
                count++;
                time = Math.max(time, dist[i]);
            }
        }

        sb.append(String.format("%d %d\n", count, time));
    }

    public static class Node implements Comparable<Node> {
        int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

}
