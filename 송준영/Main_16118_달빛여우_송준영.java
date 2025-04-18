import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.*;

public class Main_16118_달빛여우_송준영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int N, M;
    static List<List<Edge>> graph;
    static long[] distFox;
    static long[][] distWolf;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        // 그래프 초기화
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 입력 (가중치를 2배로 저장)
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = parseInt(st.nextToken());
            int b = parseInt(st.nextToken());
            int d = parseInt(st.nextToken());
            int w = d * 2; // ← 부동소수점 없이 반속/배속을 구현하기 위해 2배

            graph.get(a).add(new Edge(b, w));
            graph.get(b).add(new Edge(a, w));
        }

        // 여우와 늑대 최단 거리 계산
        dijkstraFox(1);
        dijkstraWolf(1);

        // System.out.println(Arrays.toString(distFox));
        // for (int i = 0; i <= N; i++) {
        //     System.out.print(Arrays.toString(distWolf[i]));
        // }
        // System.out.println();

        // 비교하여 여우가 더 빨리 도착하는 정점 개수 세기
        int result = 0;
        for (int i = 2; i <= N; i++) {
            long wolfBest = Math.min(distWolf[i][0], distWolf[i][1]);
            if (distFox[i] < wolfBest) {
                result++;
            }
        }

        System.out.println(result);
    }

    // 여우는 항상 같은 속도 → 일반 다익스트라
    static void dijkstraFox(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        distFox = new long[N+1];
        Arrays.fill(distFox, Long.MAX_VALUE);
        
        distFox[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node temp = pq.poll();
            if (temp.dist > distFox[temp.idx]) continue;

            for (Edge next : graph.get(temp.idx)) {
                long nd = temp.dist + next.weight;
                if (nd < distFox[next.to]) {
                    distFox[next.to] = nd;
                    pq.offer(new Node(next.to, nd));
                }
            }
        }
    }

    // 늑대는 반속 ↔ 배속 번갈아
    static void dijkstraWolf(int start) {
        PriorityQueue<WolfNode> pq = new PriorityQueue<>();
        distWolf = new long[N+1][2];
        for (int i = 1; i <= N; i++) {
            distWolf[i][0] = distWolf[i][1] = Long.MAX_VALUE;
        }
        
        // phase=0: 다음 이동이 반속, phase=1: 다음 이동이 배속
        distWolf[start][0] = 0;
        distWolf[start][1] = 0;
        pq.offer(new WolfNode(start, 0, 0));

        while (!pq.isEmpty()) {
            WolfNode temp = pq.poll();
            if (temp.dist > distWolf[temp.idx][temp.phase == 1 ? 0 : 1]) continue;

            for (Edge next : graph.get(temp.idx)) {
                if (temp.phase == 0) {
                    // 반속: weight/2 → (d*2)/2 = d
                    long nd = temp.dist + next.weight / 2;
                    if (nd < distWolf[next.to][0]) {
                        distWolf[next.to][0] = nd;
                        pq.offer(new WolfNode(next.to, nd, 1));
                    }
                } else {
                    // 배속: weight*2 → (d*2)*2 = d*4
                    long nd = temp.dist + next.weight * 2;
                    if (nd < distWolf[next.to][1]) {
                        distWolf[next.to][1] = nd;
                        pq.offer(new WolfNode(next.to, nd, 0));
                    }
                }
            }
        }
    }

    // 간선을 표현하는 클래스
    static class Edge {
        int to, weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // 여우용 우선순위큐 노드
    static class Node implements Comparable<Node> {
        int idx;
        long dist;
        Node(int idx, long dist) {
            this.idx = idx;
            this.dist = dist;
        }
        @Override
        public int compareTo(Node o) {
            return Long.compare(this.dist, o.dist);
        }
    }

    // 늑대용 우선순위큐 노드 (phase: 0=다음이 반속, 1=다음이 배속)
    static class WolfNode implements Comparable<WolfNode> {
        int idx, phase;
        long dist;
        WolfNode(int idx, long dist, int phase) {
            this.idx = idx;
            this.dist = dist;
            this.phase = phase;
        }
        @Override
        public int compareTo(WolfNode o) {
            return Long.compare(this.dist, o.dist);
        }
    }
}
