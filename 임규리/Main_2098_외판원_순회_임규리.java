import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * 메모리 : 16,464 kb
 * 시간 : 124 ms
 */
public class Main_2098_외판원_순회_임규리 {

    static int N;
    static int[][] W;
    static int[][] dp;
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = parseInt(br.readLine());
        W = new int[N][N];
        dp = new int[N][1 << N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        // 0번 도시부터 시작, visited = 000...1 (0번만 방문)
        System.out.println(tsp(0, 1));
    }

    /**
     * @param current   현재 위치한 도시
     * @param visited   지금까지 방문한 도시 비트마스크
     * @return  최소 비용
     */
    private static int tsp(int current, int visited) {
        // 모든 도시 방문 완료
        if (visited == (1 << N) - 1) {
            // 출발지(0번)로 돌아가는 길이 있으면 비용 반환, 없으면 INF
            return (W[current][0] != 0) ? W[current][0] : INF;
        }

        // 이미 계산된 상태면 바로 반환
        if (dp[current][visited] != -1) return dp[current][visited];

        int min = INF;

        // 다음 도시 선택
        for (int next = 0; next < N; next++) {
            // 방문한 도시면 스킵
            if ((visited & (1 << next)) != 0) continue;

            // 연결된 경로가 없으면 스킵
            if (W[current][next] == 0) continue;

            // 다음 도시 방문 후 최소 비용 계산
            int newVisited = visited | (1 << next);
            int cost = W[current][next] + tsp(next, newVisited);

            min = Math.min(min, cost);
        }

        return dp[current][visited] = min;
    }
}
