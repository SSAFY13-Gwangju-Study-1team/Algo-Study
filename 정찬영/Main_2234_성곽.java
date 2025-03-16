// GPT 사용 -> Integer.toBinaryString 사용 시 4자리 고정
// Ex) 1의 경우 0001이 아니라 1이 나와 4자리로 고정하는 방법 검색 -> String.format
// -> 문자열 처리 방식으로 벽을 구분하니 너무 많은 메모리와 시간 사용으로 비트로 구분하도록 변경함(성능 약 3배 향상)

import java.io.*;
import java.util.*;

public class Main_2234_성곽 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    // 1000하 0100우 0010상 0001좌
    static int[] dx = {0, 1, 0, -1}; // 하우상좌
    static int[] dy = {1, 0, -1, 0};

    static int N;
    static int M;
    static int[][][] map;
    static int roomSize;
    static int breakSize;
    static boolean[][] visited;
    static ArrayList<Integer> roomID;
    static ArrayList<ArrayList<int[]>> breakable;

    public static void main(String[] args) throws IOException {
        /*-------- input --------*/
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 가로길이
        M = Integer.parseInt(st.nextToken());   // 세로길이

        // 좌1 상2 우4 하8
        // => 0001, 0010, 0100, 1000
        map = new int[M][N][2];    // [n][m][0] 각 좌표별 벽 정보, [n][m][1] 방 번호 1~
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            for (int n = 0; n < N; n++) {
                map[m][n][0] = Integer.parseInt(st.nextToken());
            }
        }

        /*-------- 구현 --------*/
        visited = new boolean[M][N];
        breakSize = 0;  // 3. 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기

        roomID = new ArrayList<>(); // 각 방별 크기 저장용
        roomID.add(0);              // 인덱스 채우기용
        breakable = new ArrayList<>();  // 각 방별 부술 수 있는 벽 좌표 저장용
        breakable.add(new ArrayList()); // 인덱스 채우기용

        roomSize = 0;   // 2. 가장 넓은 방의 넓이

        // BFS를 통해 각 방의 크기를 조사
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    roomID.add(bfs(i, j));
                }
            }
        }

        breakSize = 0;
        for(int i = 1; i < breakable.size(); i++) {
            for (int[] j : breakable.get(i)) {
                int roomA = roomID.get(i);                  // 현재 방의 크기
                int roomB = roomID.get(map[j[0]][j[1]][1]); // 벽 건너편 방의 크기

                // 두 방이 다를 경우만 계산
                if (map[j[0]][j[1]][1] != i) {
                    breakSize = Math.max(breakSize, roomA + roomB);
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        ans.append(roomID.size() - 1).append("\n"); // 1. 이 성에 있는 방의 개수
        ans.append(roomSize).append("\n");          // 2. 가장 넓은 방의 넓이
        ans.append(breakSize).append("\n");         // 3. 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기
        bw.write(ans.toString());
        bw.flush();

        bw.close();
        br.close();
    }   // End of Main

    public static int bfs(int y, int x) {
        int[] cur = {y, x};      // 현재 위치
        int nx, ny;             // 다음 좌표
        int curRoomSize = 0;    // 현재 방 사이즈
        int curRoomID = roomID.size();  // 현재 방 ID
        ArrayDeque<int[]> que = new ArrayDeque<>(); // bfs를 위한 큐
        ArrayList<int[]> wall = new ArrayList<>();  // 부술 수 있는 벽 좌표 저장용

        visited[y][x] = true;
        que.offer(cur);
        while (!que.isEmpty()) {
            cur = que.poll();
            curRoomSize++;
            map[cur[0]][cur[1]][1] = curRoomID;

            for (int i = 0; i < 4; i++) {
                ny = cur[0] + dy[i];
                nx = cur[1] + dx[i];

                if ((map[cur[0]][cur[1]][0] & (1 << (3 - i))) == 0) {   // 벽이 아닌 곳만 탐색
                    if (ny >= 0 && ny < M && nx >= 0 && nx < N && !visited[ny][nx]) {
                        visited[ny][nx] = true;
                        que.offer(new int[]{ny, nx});
                    }
                } else {
                    if (ny >= 0 && ny < M && nx >= 0 && nx < N) {
                        wall.add(new int[]{ny, nx});
                    }
                }
            }
        }

        roomSize = Math.max(roomSize, curRoomSize);
        breakable.add(new ArrayList<>(wall));
        return curRoomSize;
    }
}