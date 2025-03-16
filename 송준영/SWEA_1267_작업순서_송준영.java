import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * SWEA_1267_작업순서
 * 난이도 3/10
 * 위상정렬
 * 112ms 29mb
 * 
 * 위상정렬을 아냐 모르냐를 물어보는 문제
 * 위상정렬을 이용해 선행작업이 없는 작업을 큐에 넣고 그 작업을 수행하면서 선행작업을 제거해주는 방식
 * 오랜만에 위상정렬을 풀어서 자신에게 들어오는 간선 체크를 set로 했는데 풀다가 기억나서 int 배열로 카운트만 처리해주면 더 좋고 빠르게 풀 수 있다
 * 
 */
public class SWEA_1267_작업순서_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int V, E;                    // 정점, 간선
    static int[] indegree;              // 진입차수
    static List<List<Integer>> graph;   // 그래프 (인접 리스트)
    static Queue<Integer> q;            // 큐

    public static void main(String[] args) throws Exception {
        // 테케 수만큼 반복
        for (int t = 1; t <= 10; t++) {
            sb.append(String.format("#%d ", t));
            solve();
            sb.append("\n");
        }
        // 결과 출력
        System.out.println(sb);
    }
    
    /**
     * 메인 메서드
     * @throws Exception
     */
    public static void solve() throws Exception {
        // 입력 처리 및 초기화
        st = new StringTokenizer(br.readLine());
        V = parseInt(st.nextToken());
        E = parseInt(st.nextToken());

        indegree = new int[V + 1];
        graph = new ArrayList<>();
        q = new ArrayDeque<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < E * 2; i += 2) {
            int first = parseInt(st.nextToken()), second = parseInt(st.nextToken());
            graph.get(first).add(second); // 그래프에 간선 추가
            indegree[second]++; // 자기한테 들어오는 간선이 생겼으니까 진입차수 증가
        }

        // 일단 다 돌면서 진입차수가 0인 얘들 q에 넣어주기
        for (int i = 1; i <= V; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        // q가 빌때까지 반복 - 위상정렬
        while (!q.isEmpty()) {
            int cur = q.poll();

            // 현재 정점과 연결된 정점들의 진입차수를 감소시키고 0이 되면 큐에 넣어주기
            for (int v : graph.get(cur)) {
                if (--indegree[v] == 0) {
                    q.offer(v);
                }
            }

            sb.append(cur + " ");
        }
    }
}
