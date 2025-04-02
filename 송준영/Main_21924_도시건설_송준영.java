import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.*;

public class Main_21924_도시건설_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M, total = 0;
    static List<List<int[]>> graph;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        visited = new boolean[N + 1];
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = parseInt(st.nextToken());
            int v = parseInt(st.nextToken());
            int w = parseInt(st.nextToken());

            graph.get(u).add(new int[]{v, w});
            graph.get(v).add(new int[]{u, w});
            total += w;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{1, 0});
        int totalCost = 0;
        int cnt = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];
            int cost = cur[1];

            if (visited[node]) {
                continue;
            }

            visited[node] = true;

            totalCost += cost;
            cnt++;

            for (int[] next : graph.get(node)) {
                if (!visited[next[0]]) {
                    pq.add(next);
                }
            }
        }

        if (cnt != N) {
            System.out.println(-1);
        } else {
            System.out.println(total - totalCost);
        }

    }
}
