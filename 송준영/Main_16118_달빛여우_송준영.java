import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.*;

public class Main_16118_달빛여우_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static List<List<Node>> graph;
    static long[] distFox;
    static double[][] distWolf;

    public static void main(String[] args) throws Exception  {
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = parseInt(st.nextToken()), b = parseInt(st.nextToken()), d = parseInt(st.nextToken());

            graph.get(a).add(new Node(b, d*2));
            graph.get(b).add(new Node(a, d*2));
        }

        djikstraFox(1);
        djikstraWolf(1);


        System.out.println(Arrays.toString(distFox)); 
        for (int i = 0; i <= N; i++) {
            System.out.print(Arrays.toString(distWolf[i]));
        }
        System.out.println();

        int result = 0;
        for (int i = 2; i <= N; i++) {
            if ((double) distFox[i] < Math.min(distWolf[i][0], distWolf[i][1])) result++;
        }

        System.out.println(result);
    }

    public static void djikstraFox(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        distFox = new int[N+1];
        Arrays.fill(distFox, Integer.MAX_VALUE);

        pq.offer(new Node(start, 0));
        distFox[start] = 0;

        while(!pq.isEmpty()) {
            Node temp = pq.poll();

            if (distFox[temp.idx] < temp.dist ) continue;

            for (Node next : graph.get(temp.idx)) {
                if (distFox[next.idx] > next.dist + temp.dist) {
                    distFox[next.idx] = next.dist + temp.dist;
                    pq.offer(new Node(next.idx, distFox[next.idx]));
                }
            }
        }
    }

    public static void djikstraWolf(int start) {
        PriorityQueue<double[]> pq = new PriorityQueue<>((o1, o2) -> {
            return Double.compare(o1[1], o2[1]);
        });
        distWolf = new double[N+1][2];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(distWolf[i], Double.MAX_VALUE);
        }

        pq.offer(new double[] { start, 0.0, 0.0});
        distWolf[start][1] = 0.0;
        distWolf[start][0] = 0.0;

        while(!pq.isEmpty()) {
            double[] temp = pq.poll();

            if (distWolf[(int) temp[0]][((int) temp[2] == 1) ? 0 : 1] < temp[1] ) continue;

            for (Node next : graph.get((int) temp[0])) {
                if (temp[2] == 0.0) {
                    if (distWolf[next.idx][0] > ((double) next.dist / 2) + temp[1]) {
                        distWolf[next.idx][0] = ((double) next.dist / 2) + temp[1];
                        pq.offer(new double[] { next.idx, distWolf[next.idx][0], 1.0 });
                    }
                } else {
                    if (distWolf[next.idx][1] > ((double) next.dist * 2) + temp[1]) {
                        distWolf[next.idx][1] = ((double) next.dist * 2) + temp[1];
                        pq.offer(new double[] { next.idx, distWolf[next.idx][1], 0.0 });
                    }
                }
            }

        }
    }

    public static class Node implements Comparable<Node>{
        int idx;
        long dist;

        public Node(int idx, long dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.dist, o.dist);
        }
    }
}