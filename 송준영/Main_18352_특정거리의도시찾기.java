import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_18352_특정거리의도시찾기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M, K, X;
    static List<List<Integer>> graph = new ArrayList<>();

    static int[] dist;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        K = parseInt(st.nextToken());
        X = parseInt(st.nextToken());

        dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int s, e;
            st = new StringTokenizer(br.readLine());
            s = parseInt(st.nextToken());
            e = parseInt(st.nextToken());
            graph.get(s - 1).add(e - 1);
        }

        djik(X - 1);
        
        boolean flag = false;
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] == K) {
                sb.append(i + 1).append("\n");
            }
        }

        System.out.println(sb);
    }

    public static void djik(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> {
            return n1.cost - n2.cost;
        });

        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            

            if (dist[node.index] != node.cost) {
                continue;
            }
            
            for (int i : graph.get(node.index)) {
                
                if (dist[i] > dist[node.index] + 1) {
                    
                    dist[i] = dist[node.index] + 1;
                    pq.offer(new Node(i, dist[node.index] + 1));
                }
            }
        }
    }
}

class Node {
    int index, cost;

    public Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }
}
