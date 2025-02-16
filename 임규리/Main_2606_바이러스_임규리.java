package 스터디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 풀이 1 => 실패
 * - 이유 : map을 초기화할 때 1부터 N까지인데 1부터 N-1까지만 함
 * 풀이 2 => 실패
 * - 이유 : 양방향 연결인데 단방향으로 처리함
 * 풀이 3 => 실패
 * - 이유 : 1번부터 시작이니 방문처리를 해야 하는데 하지 않았음
 */
public class Main_2606_바이러스_임규리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            map.put(i, new ArrayList<>());
        }

        boolean[] visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            map.get(from).add(to);
            map.get(to).add(from);
        }

        int result = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visited[1] = true;

        while(!q.isEmpty()) {
            int cur = q.poll();
            List<Integer> curList = map.get(cur);

            for (int i = 0; i < curList.size(); i++) {
                if (visited[curList.get(i)]) continue;;
                q.add(curList.get(i));
                visited[curList.get(i)] = true;
                result++;
            }
        }

        System.out.println(result);
    }
}
