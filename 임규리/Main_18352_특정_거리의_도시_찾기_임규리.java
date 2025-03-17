package 스터디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_18352_특정_거리의_도시_찾기_임규리 {

    static List<List<Integer>> list = new ArrayList<>();    // 노드 연결
    static int N, M, K, X;  // 도시 개수, 도로 개수, 거리 정보, 출발 도시 번호
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;   // 0부터 시작할거라 빼줌

        dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            list.get(from).add(to);
        }

        calculate();


        boolean found = false;
        for (int i = 0; i < N; i++) {
            if (dist[i] == K) {
                System.out.println(i + 1);
                found = true;
            }
        }

        if (!found) {
            System.out.println(-1);
        }
    }

    private static void calculate() {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(X);   // 시작점 추가
        dist[X] = 0;

        while(!q.isEmpty()) {
            int cur = q.poll();

            for (int idx : list.get(cur)) {
                if (dist[idx] > dist[cur] + 1) {
                    dist[idx] = dist[cur] + 1;
                    q.add(idx);
                }
            }
        }
    }
}
