import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * Main_1516_게임개발
 * 난이도 4/10
 * 위상정렬
 * 236ms 21mb
 * 
 * 위상정렬을 이용한 문제이다
 * 이 문제는 선행 건물을 짓는데 걸리는 시간이 다음 건물을 짓는데 걸리는 시간에 영향을 준다
 * 그래서 각 건물을 짓는데 걸리는 시간을 저장하는 배열을 하나 더 만들어서 갱신해 주었다 -> totalTime
 * 선행 건물중 가장 시간이 걸리는 건물의 시간에서 현재 건물을 짓는데 걸리는 시간을 더해주면 그게 현재 건물의 총 시간이 됨
 */
public class Main_1516_게임개발_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;                       // 건물의 개수
    static int[] time;                  // 각 건물을 짓는데 걸리는 시간
    static int[] indegree;              // 각 노드의 진입 차수
    static int[] totalTime;             // 각 건물을 짓는데 걸리는 총 시간
    static List<List<Integer>> graph;   // 각 건물의 다음 건물을 저장하는 그래프

    public static void main(String[] args) throws Exception {
        // 입력 처리 및 초기화
        N = parseInt(br.readLine());
        time = new int[N + 1];
        indegree = new int[N + 1];
        totalTime = new int[N + 1];
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = parseInt(st.nextToken());
            while (st.hasMoreTokens()) {    // -1이 나올 때까지
                int x = parseInt(st.nextToken());
                if (x == -1) break;         // -1이 나오면 종료
                graph.get(x).add(i);        // x번 건물을 짓고 나서 i번 건물을 짓는다
                indegree[i]++;              // i번 건물의 진입 차수 증가
            }
        }

        // for (int i = 1; i <= N; i++) {
        //     System.out.print(i + " " + indegree[i] + ": ");
        //     for(int g : graph.get(i)) {
        //         System.out.print(g + " ");
        //     }
        //     System.out.println();
        // }

        // 위상정렬 실행
        Queue<Integer> q = new ArrayDeque<>();  // 진입 차수가 0인 노드를 담을 큐
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {             // 진입 차수가 0인 노드를 큐에 넣기
                q.offer(i);
                totalTime[i] = time[i];         // 건물을 짓는데 걸리는 시간을 총 시간에 저장, 얘들은 선행 건물이 없으니까
            }
        }

        // 큐가 빌때 까지 반복
        while(!q.isEmpty()) {
            int cur = q.poll();
            for (int next : graph.get(cur)) {
                // 다음 건물을 짓는데 걸리는 시간을 갱신
                // 다음 건물을 짓는데 걸리는 시간은 선행 건물을 짓는데 걸리는 시간의 최댓값(totalTime) + 다음 건물을 짓는데 걸리는 시간
                totalTime[next] = Math.max(totalTime[next], totalTime[cur] + time[next]);
                indegree[next]--;           // 진입 차수 감소
                if (indegree[next] == 0) {  // 진입 차수가 0이 되면 큐에 넣기
                    q.offer(next);
                }
            }
        }

        // 1 ~ N번 건물을 짓는데 걸리는 시간 sb에 저장
        for (int i = 1; i <= N; i++) {
            sb.append(totalTime[i]).append("\n");
        }

        // 결과 출력
        System.out.println(sb);
    }
}
