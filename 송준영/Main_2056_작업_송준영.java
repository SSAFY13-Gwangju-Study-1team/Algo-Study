import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.*;

public class Main_2056_작업_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static List<List<Integer>> graph;
    static int[] indegree;
    static int[] values;
    static int[] startTime;
    static int result;

    public static void main(String[] args) throws Exception {
        N = parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        indegree = new int[N+1];
        values = new int[N+1];
        startTime = new int[N+1];
        result = 0;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            values[i] = parseInt(st.nextToken());
            indegree[i] = parseInt(st.nextToken());

            for (int j = 0; j < indegree[i]; j++) {
                int next = parseInt(st.nextToken());
                graph.get(next).add(i);
            }
        }
        phaseSort();

        System.out.println(result);
    }

    public static void phaseSort() {
        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) q.offer(i);
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : graph.get(cur)) {
                startTime[next] = Math.max(startTime[next], startTime[cur] + values[cur]);
                if (--indegree[next] == 0) q.offer(next);
            }
        }

        for (int i = 1; i <= N; i++) {
            result = Math.max(result, startTime[i] + values[i]);
        }
    }
}
