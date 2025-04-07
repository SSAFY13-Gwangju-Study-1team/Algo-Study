import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main_16928_뱀과_사다리_게임_임규리 {

    static int N;   // 사다리 수
    static int M;   // 뱀 수
    static int[] board; // 게임판
    static boolean[] visited;   // 방문 처리

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        board = new int[101];   // 1번부터 시작
        visited = new boolean[101]; // 1번부터 시작

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = parseInt(st.nextToken());
            int to = parseInt(st.nextToken());
            board[from] = to;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = parseInt(st.nextToken());
            int to = parseInt(st.nextToken());
            board[from] = to;
        }

        bfs();
    }

    private static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{1, 0});
        visited[1] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (cur[0] == 100) {
                System.out.println(cur[1]);
                break;
            }

            for (int i = 1; i <= 6; i++) {
                int idx = cur[0] + i;

                if (idx <= 100 && !visited[idx]) {
                    visited[cur[0] + i] = true;
                    // 뱀인지 사다리인지 판단
                    if (board[idx] > 0) {   // 사다리
                        idx = board[idx];
                        visited[idx] = true;
                        q.add(new int[]{idx, cur[1] + 1});
                    } else {
                        q.add(new int[]{idx, cur[1] + 1});
                    }
                }
            }
        }
    }
}
