import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * SWEA_1238_Contact
 * 난이도 3/10
 * BFS, hash?
 * 91ms 25mb
 * 
 * 전형적인 BFS문제
 * 근데 노드가 연속적이지 않아서 인덱스로 그래프를 만들기 좀 그렇다 -> HashMap 사용(인접 그래프)
 * BFS를 돌면서 가장 마지막에 방문한 노드를 찾아주면 된다
 * 방문시 visit처리를 int 배열로 하여(크기가 100이라 별 부담 안 됨) 이 곳에 거리를 입력 해줌
 * -> 현재 방문 노드의 거리는 이전 방문 거리의 + 1
 * 가장 긴 거리를 저장해 두었다가 visited배열을 완전 탐색하면서 가장 큰 거리를 가진 노드를 찾아주면 됨
 * 인덱스가 작은 노드부터 탐색하므로 자동적으로 가장 큰 인덱스의 번호가 반환
 */
public class SWEA_1238_Contact_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static Map<Integer, List<Integer>> graph;   // 인접 그래프
    static int start, N;                        // 시작 노드, 노드 수

    public static void main(String[] args) throws Exception {
        // 테케 수 만큼 반복
        for (int t = 1; t <= 10; t++) {
            sb.append(String.format("#%d %d\n", t, solve()));
        }
        // 출력
        System.out.println(sb);
    }

    /**
     * 메인 메서드
     * @return
     * @throws Exception
     */
    public static int solve() throws Exception {
        // 입력 처리 및 초기화
        graph = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        start = parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i += 2) {
            int from = parseInt(st.nextToken());
            int to = parseInt(st.nextToken());
            // System.out.println(from +" "+ to);
            if (graph.containsKey(from)) {  // 키가 안에 있으면 리스트 안에 추가
                graph.get(from).add(to);
            } else {                        // 없으면 새로운 리스트 생성 하고 거기에 추가
                graph.put(from, new ArrayList<>());
                graph.get(from).add(to);
            }
        }

        return bfs(start);  // BFS 결과 반환
    }

    /**
     * BFS 메서드
     * @param start 시작 노드
     * @return      가장 마지막에 방문한 노드
     */
    public static int bfs(int start) {
        // System.out.println("진입");
        Queue<Integer> q = new ArrayDeque<>();  // 큐
        int[] visited = new int[101];           // 방문 배열
        int dist = 1;                           // 최대 거리

        // 시작 노드 처리
        q.offer(start);
        visited[start] = 1;

        // BFS
        while(!q.isEmpty()) {
            int temp = q.poll();

            for (int next : graph.getOrDefault(temp, new ArrayList<>())) {  // 인접 노드 탐색, 그ㅡ냥 get 하면 에러뜸 -> 없으면 빈 리스트 반환
                if (visited[next] != 0) continue;   // 방문한 노드면 무시

                visited[next] = visited[temp] + 1;      // 방문 처리 및 거리 계산
                dist = Math.max(dist, visited[next]);   // 최대 거리 갱신
                q.offer(next);
            }
        }

        // 결과 처리 부분
        int result = 0; // 결과값
        for (int i = 1; i < 101; i++) { // 방문 배열을 완전 탐색하면서 가장 큰 거리를 가진 노드 찾기
            if (visited[i] == dist) {
                result = i;
            }
        }

        // 결과 반환
        return result;
    }
}
