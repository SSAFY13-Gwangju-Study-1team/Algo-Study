import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * 전체 흐름 (with GPT)
 * 1. 입력 받기
 * 2. 간선 리스트 만들기 (음수 -> 비용 0, 절약 금액 기록)
 * 3. 간선 오름차순 정렬
 * 4. MST 구성 : Kruskal
 *  - 음수 간선 포함 시 : 비용 없음, 절약 유지
 *  - 양수 간선 포함 시 : 비용 증가, 설치 목록 기록
 * 5. 출력 : 총 비용, 설치 수, 설치된 간선 목록
 */
public class Main_1833_고속철도_설계하기_임규리 {

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
            return this.weight - o.weight;
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

    static int N;   // 도시 수
    static List<Edge> edges;    // 간선 리스트
    static List<String> installed;  // 새로 설치한 간선 저장
    static int savedCost;   // 음수 간선으로 절약한 비용
    static int totalCost;   // 전체 비용
    static int installCount;// 설치 개수
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = parseInt(br.readLine());
        edges = new ArrayList<>();
        installed = new ArrayList<>();
        savedCost = 0;
        totalCost = 0;
        installCount = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int cost = parseInt(st.nextToken());
                if (i < j) {    // 중복 방지
                    if (cost < 0) { // 음수 간선일 경우
                        edges.add(new Edge(i, j, 0));   // 이미 설치되었으니 가중치 0
                        savedCost += -1 * cost;
                    } else {    // 양수 간선일 경우
                        edges.add(new Edge(i, j, cost));    // 설치 가능한 간선
                    }
                }
            }
        }

        Collections.sort(edges);
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for (Edge e : edges) {
            if (union(e.from, e.to)) {
                if (e.weight > 0) {
                    totalCost += e.weight;
                    installCount++;
                    installed.add((e.from + 1) + " " + (e.to + 1));
                }
            }
        }

        System.out.println((totalCost + savedCost) + " " + installCount);
        for (String s : installed) {
            System.out.println(s);
        }
    }
}
