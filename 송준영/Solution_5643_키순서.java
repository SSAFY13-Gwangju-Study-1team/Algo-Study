import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Solution_5643_키순서 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static List<List<Integer>> graph;
    static List<List<Integer>> checkList;
    public static void main(String[] args) throws Exception {
        int T = parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            sb.append(String.format("#%d %d\n", t, solve()));
        }
        System.out.println(sb);
    }
    
    public static int solve() throws Exception {
        int cnt = 0;
        N = parseInt(br.readLine());
        M = parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        checkList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            checkList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int a, b;
            st = new StringTokenizer(br.readLine());
            a = parseInt(st.nextToken());
            b = parseInt(st.nextToken());
            graph.get(a).add(b);
        }

        for (int i = 1; i <= N; i++) {
            BFS(i);
        }

        for (int i = 1; i <= N; i++) {
            if (checkList.get(i).size() == N - 1) {
                cnt++;
            }
        }

        return cnt;
    }
    
    public static void BFS(int start) {
        Deque<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N+1]; 

        q.offerLast(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int node = q.pollFirst();

            for (int next : graph.get(node)) {
                if (!visited[next]) {
                    q.offerLast(next);
                    visited[next] = true;
                    checkList.get(start).add(next);
                    checkList.get(next).add(start);
                }
            }
        }
    }
}
