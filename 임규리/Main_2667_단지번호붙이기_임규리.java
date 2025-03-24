package 스터디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main_2667_단지번호붙이기_임규리 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int N;
    static int[][] map;
    static int[][] danji;
    static List<Integer> list;
    static int num = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        danji = new int[N][N];
        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            Arrays.fill(danji[i], -1);  // -1로 초기화
        }

        for (int i = 0; i < N; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = temp[j] - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && danji[i][j] == -1) {  // 집이고 아직 방문 전이면
                    bfs(i, j);
                }
            }
        }

        System.out.println(num);
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    private static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        danji[x][y] = num;
        list.add(1);

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < dx.length; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;   // 범위 밖이면 무시
                if (map[nx][ny] != 1) continue;     // 집이 아니면 무시
                if (danji[nx][ny] != -1) continue;  // 이미 방문한 곳이면 무시

                danji[nx][ny] = num;
                list.set(num, list.get(num) + 1);
                q.add(new int[]{nx, ny});
            }
        }

        num++;
    }
}
