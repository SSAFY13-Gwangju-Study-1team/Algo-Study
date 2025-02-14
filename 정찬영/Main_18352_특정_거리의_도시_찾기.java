// 1. 인접 행렬로 풀었다가 메모리 초과 발생
// 2. 인접 리스트로 변경했지만 정렬 수행하지 않아 오름차순 출력되지 않아 틀림
// 3. 정신머리 챙기기


import java.io.*;
import java.util.*;

public class Main_18352_특정_거리의_도시_찾기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        /* input */
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 도시 개수 N
        int M = Integer.parseInt(st.nextToken());   // 도로의 개수 M
        int K = Integer.parseInt(st.nextToken());   // 최단 거리 K
        int X = Integer.parseInt(st.nextToken());   // 출발 도시 번호

        ArrayList<Integer>[] city = new ArrayList[N+1];
        for(int i = 1; i <= N; i++){
            city[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            city[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
        }

        // BFS
        ArrayList<Integer> ans = new ArrayList<>();
        boolean[] visited = new boolean[N+1];
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[] {X, 0});    // 현재 도시, 이동 횟수
        visited[X] = true;

        int[] cur;
        while(!queue.isEmpty()) {
            cur = queue.removeFirst();
            if(cur[1] == K) {
                ans.add(cur[0]);
            }
            if(cur[1] > K) break;   // 이동 횟수가 넘어가면 더 진행할 필요 없음

            for(int i : city[cur[0]]) {
                if(!visited[i]) {
                    visited[i] = true;
                    queue.addLast(new int[] {i, cur[1]+1});
                }
            }
        }

        if(ans.isEmpty()) {
            System.out.println(-1);
        }
        else {
            Collections.sort(ans);
            for (Integer i : ans) {
                System.out.println(i);
            }
        }
    }
}
