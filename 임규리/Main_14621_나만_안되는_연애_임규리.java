import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * 풀이 흐름 (with GPT)
 * 1. 각 대학의 성별 정보 저장 (M or W)
 * 2. 모든 간선 정보를 입력 받으면서, 서로 다른 성별인 간선만 리스트에 저장
 * 3. 간선을 가중치 기준 오름차순으로 정렬
 * 4. Kruskal(Union-Find) 알고리즘으로 MST 구성 => 사이클 방지 (find/union)
 * 5. 간선 개수가 N - 1개가 안 되면 -1 출력
 */
public class Main_14621_나만_안되는_연애_임규리 {

    /**
     * 간선 표현 클래스
     * from 정점, to 정점, weight 가중치
     */
    static class Edge implements Comparable<Edge> {

        private int from;
        private int to;
        private int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        // 가중치 기준 오름차순
        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static int N;   // 학교 수
    static int M;   // 도로 수
    static String[] gender; // 성별 배열
    static List<Edge> list; // 조건을 만족하는 간선 리스트
    static int[] parent;    // Union-Find 용 부모 배열
    static int result;  // 결과값
    static int count;   // 연결된 정점 개수 세기

    // Find 연산 : 경로 압축
    static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    // Union 연산 : 두 집합 합치기
    static boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return false;   // 같은 집합이면 사이클 발생 -> 연결 불가

        parent[rootY] = rootX;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        gender = new String[N + 1]; // 1부터 시작

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            gender[i] = st.nextToken(); // M or W
        }

        list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = parseInt(st.nextToken());
            int v = parseInt(st.nextToken());
            int w = parseInt(st.nextToken());

            if (!gender[u].equals(gender[v])) { // 성별이 다를 경우
                list.add(new Edge(u, v, w));
            }
        }

        Collections.sort(list); // 간선 정렬

        // Union-Find 초기화
        parent = new int[N + 1];    // 1부터 시작
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        // 가중치 작은 간선부터 하나씩 선택
        for (Edge e : list) {
            if (union(e.from, e.to)) {  // 두 정점이 다른 집합일 때만 연결
                result += e.weight;     // 비용 누적
                count++;

                if (count == N - 1) break;  // MST 완성 : 정점 수 - 1 개 간선이면 완료
            }
        }

        System.out.println(count == N - 1 ? result : -1);
    }
}
