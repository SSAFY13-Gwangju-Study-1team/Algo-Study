/* 메모리 68984kb, 시간 528ms
 * 문제 풀이 아이디어: 특정 두 정점을 반드시 이동해야 함
 * -> 시작 정점에서 다익스트라 -> 첫 번째(또는 두 번째) 정점까지 최단 경로 이동
 * -> 첫 번째 정점(또는 두 번째)에서 다익스트라 -> 두 번째(첫 번째) 정점까지 최단 경로 이동
 * -> 두 번째 정점(또는 첫 번째)에서 다익스트라 -> 도착지까지 최단 경로 이동
 * 두 경로 중 짧은 것을 선택
 * 만약에 경로가 없었으면 오버플로우 나서 음수일 것임 -> 테스트케이스에서 오버플로우 유도하는 것이 있어서 수정
 * 체감 난이도: 6/10
 */

import java.io.*;
import java.util.*;

public class Main_1504_특정한_최단_경로_정찬영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static class Edge implements Comparable<Edge>{
        int num;
        int dis;
        Edge(int num, int dis) {
            this.num = num;
            this.dis = dis;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dis - o.dis;
        }
    }
    static int N, E;
    static ArrayList<Edge>[] graph;
    static int[] d;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int v1, v2;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    /* ----- 입력 ----- */
    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 정점의 개수 N
        E = Integer.parseInt(st.nextToken());   // 간선의 개수 E

        graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());   // a <-> b 양방향 그래프 이어짐
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());   // 거리는 c
            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());  // 지나야하는 정점 번호 1
        v2 = Integer.parseInt(st.nextToken());  // 2
    }

    /* ----- 구현 ----- */
    static void solve() {
        d = new int[N+1];
        int route1 = 0; // 시작점 -> 정점1 -> 정점2 -> 도착점
        int route2 = 0; // 시작점 -> 정점2 -> 정점1 -> 도착점
        boolean check1 = true;
        boolean check2 = true;

        djikstra(1);
        if(d[v1] == Integer.MAX_VALUE) check1 = false;
        if(d[v2] == Integer.MAX_VALUE) check2 = false;
        route1 += d[v1];    // 시작점 -> 정점1
        route2 += d[v2];    // 시작점 -> 정점2

        djikstra(v1);
        if(d[v2] == Integer.MAX_VALUE) check1 = false;
        if(d[N] == Integer.MAX_VALUE) check2 = false;
        route1 += d[v2];    // 정점1 -> 정점2
        route2 += d[N];     // 정점1 -> 도착지

        djikstra(v2);
        if(d[N] == Integer.MAX_VALUE) check1 = false;
        if(d[v1] == Integer.MAX_VALUE) check2 = false;
        route1 += d[N];     // 정점2 -> 도착지
        route2 += d[v1];    // 정점2 -> 정점1

        if(!check1) {
            if(check2) sb.append(route2);
            else sb.append(-1);
        } else {
            if(!check2) sb.append(route1);
            else sb.append(Math.min(route1, route2));
        }
    }

    static void djikstra(int start) {
        // 초기 세팅
        Arrays.fill(d, Integer.MAX_VALUE);   // 거리 배열 초기화
        d[start] = 0;   // 시작점 초기화
        pq.clear(); // pq 초기화
        pq.add(new Edge(start, 0));

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(cur.dis > d[cur.num]) continue;

            for(Edge e : graph[cur.num]) {
                if(d[cur.num] + e.dis < d[e.num]) {
                    d[e.num] = d[cur.num] + e.dis;
                    pq.add(new Edge(e.num, d[e.num]));
                }
            }
        }
    }

    /* ----- 출력 ----- */
    static void output() throws IOException {
        System.out.println(sb);
        br.close();
    }
}