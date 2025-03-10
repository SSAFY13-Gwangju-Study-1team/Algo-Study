package 스터디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2234_성곽_임규리 {

    // 서 북 동 남
    static final int[] dx = {0, -1, 0, 1};
    static final int[] dy = {-1, 0, 1, 0};

    static int N, M;        // 행, 열 크기
    static int[][] map;     // 벽에 대한 정보 배열
    static int roomNum = 1; // 방 번호 (1부터 시작) => 마지막 값이 방 개수
    static int[][] room;    // 방 번호 저장 배열
    static int[] count;     // 각 방의 크기 저장 배열
    static int max;       // 벽 제거 시 합쳐진 방 크기의 최대값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        room = new int[N][M];

        // 각 칸마다 아직 번호가 부여되지 않은 곳에 대해 bfs로 방 번호 부여
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (room[i][j] == 0) {
                    bfs(i, j);
                }
            }
        }

        count = new int[roomNum];
        // 각 방의 넓이(칸의 수) 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                count[room[i][j]]++;
            }
        }

        // 각 칸에서 4방향에 대해 인접한 방과의 합산 넓이 확인
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                breakWall(i, j);
            }
        }

        System.out.println(roomNum - 1);
        System.out.println(Arrays.stream(count).max().getAsInt());
        System.out.println(max);
    }

    // 각 방 넘버링
    private static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        room[x][y] = roomNum;   // 방 번호 배정

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            // 서쪽 이동 가능
            if ((map[cur[0]][cur[1]] & 1) == 0) {
                int nx = cur[0] + dx[0];
                int ny = cur[1] + dy[0];

                if (isIn(nx, ny) && room[nx][ny] == 0) {
                    q.add(new int[]{nx, ny});
                    room[nx][ny] = roomNum;
                }
            }

            // 북쪽 이동 가능
            if ((map[cur[0]][cur[1]] & 2) == 0) {
                int nx = cur[0] + dx[1];
                int ny = cur[1] + dy[1];

                if (isIn(nx, ny) && room[nx][ny] == 0) {
                    q.add(new int[]{nx, ny});
                    room[nx][ny] = roomNum;
                }
            }

            // 동쪽 이동 가능
            if ((map[cur[0]][cur[1]] & 4) == 0) {
                int nx = cur[0] + dx[2];
                int ny = cur[1] + dy[2];

                if (isIn(nx, ny) && room[nx][ny] == 0) {
                    q.add(new int[]{nx, ny});
                    room[nx][ny] = roomNum;
                }
            }

            // 남쪽 이동 가능
            if ((map[cur[0]][cur[1]] & 8) == 0) {
                int nx = cur[0] + dx[3];
                int ny = cur[1] + dy[3];

                if (isIn(nx, ny) && room[nx][ny] == 0) {
                    q.add(new int[]{nx, ny});
                    room[nx][ny] = roomNum;
                }
            }
        }

        roomNum++;
    }

    // 벽 제거시 인접한 두 방의 넓이를 합산하여 최대값 갱신
    private static void breakWall(int x, int y) {
        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 서로 방 번호가 다르다면 합쳐보기
            if (isIn(nx, ny) && room[nx][ny] != room[x][y]) {
                max = Math.max(max, count[room[nx][ny]] + count[room[x][y]]);
            }
        }
    }

    private static boolean isIn(int x, int y) {
        return (x >= 0 && x < N) && (y >= 0 && y < M);
    }
}