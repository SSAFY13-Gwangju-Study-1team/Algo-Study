import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Main_4386_별자리_만들기_임규리 {

    static class Edge implements Comparable<Edge> {

        private int idx;
        private double weight;

        public Edge (int idx, double weight) {
            this.idx = idx;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.weight, o.weight);
        }
    }

    static int n;   // 별 개수
    static List<List<Edge>> list;   // 인접 리스트
    static double[][] vertex;   // 정점 배열
    static boolean[] visited;   // 방문 처리 배열
    static double result;       // 결과값

    public static void main(String[] args) throws IOException {
        /* 입력 처리 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = parseInt(br.readLine());

        list = new ArrayList<>();   // 0번 인덱스부터 사용
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }

        vertex = new double[n][2];
        visited = new boolean[n];   // 0번 인덱스부터 사용
        result = 0.0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            vertex[i][0] = parseDouble(st.nextToken());
            vertex[i][1] = parseDouble(st.nextToken());
        }

        /* 정점 간 거리 계산 */
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 거리 계산
                double distance = Math.sqrt(Math.pow((vertex[i][0] - vertex[j][0]), 2) + Math.pow((vertex[i][1] - vertex[j][1]), 2));

                // 양방향으로 업데이트
                list.get(i).add(new Edge(j, distance));
                list.get(j).add(new Edge(i, distance));
            }
        }

        mst();
        System.out.println(result);
    }

    private static void mst() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0, 0)); // 0번 정점부터 시작 (거리 0으로 시작)

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (visited[cur.idx]) continue; // 방문한 곳이면 무시

            visited[cur.idx] = true;    // 방문 처리
            result += cur.weight;       // 거리 누적

            for (Edge next : list.get(cur.idx)) {   // 연결된 정점 탐색
                if (!visited[next.idx]) {   // 아직 방문하지 않은 정점만 탐색
                    pq.add(next);
                }
            }
        }
    }
}
