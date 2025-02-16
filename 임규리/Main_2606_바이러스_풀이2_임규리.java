package 스터디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 이전 풀이 : Map<Integer, List<Integer>> 자료구조 사용
 * 새 풀이 : 2차원 배열 사용
 * => 메모리 사용량과 시간 아주 약간 감소 (거의 비슷)
 * 참고 : https://infodon.tistory.com/97
 */
public class Main_2606_바이러스_풀이2_임규리 {

    static boolean[] check;
    static int[][] arr;
    static int N, M;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        arr = new int[N + 1][N + 1];
        check = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            arr[from][to] = 1;
            arr[to][from] = 1;
        }

        dfs(1);
        System.out.println(count - 1);  // 자기 자신(1) 빼주기
    }

    // 큐 사용 X
    private static void dfs(int start) {
        check[start] = true;    // 현재 방문한 위치 체크
        count++;                // 방문 카운트

        // 현재 컴퓨터와 연결된 컴퓨터들 찾기
        for (int i = 1; i <= N; i++) {
            if (arr[start][i] == 1 && !check[i]) {  // 서로 연결되어 있고, 아직 방문하지 않았다면 재귀 호출
                dfs(i);
            }
        }
    }
}
