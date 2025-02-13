import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

public class Main_2234_성곽 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[][] map;
    static int[][] visited;
    static int[] dx = { 0, -1, 0, 1 };
    static int[] dy = { -1, 0, 1, 0 };

    static List<Integer> roomSize = new ArrayList<>();
    static List<Set<Integer>> roomLink = new ArrayList<>();
    static int totalRooms;                          // 방 총 개수
    static int maxRoom = Integer.MIN_VALUE;         // 가장 큰 방
    static int maxWallRoom = Integer.MIN_VALUE;     // 한 개 연결 했을 때 가장 큰 방

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        map = new int[M][N];
        visited = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = parseInt(st.nextToken());
            }
        }

        // 인덱스 1부터 찾을거라 0 채워주기
        roomSize.add(0);
        roomLink.add(new HashSet<>());

        // BFS 순회 및 방 개수 찾기
        int order = 1;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == 0) {
                    roomLink.add(new HashSet<>());
                    BFS(i, j, order++);
                    totalRooms++;
                }
            }
        }

        for (int size : roomSize) {
            maxRoom = Math.max(maxRoom, size);
        }

        for (int i = 1; i < roomLink.size(); i++) {
            for (int link : roomLink.get(i)) {
                maxWallRoom = Math.max(maxWallRoom, roomSize.get(i) + roomSize.get(link));
            }
        }

        sb.append(totalRooms).append("\n");
        sb.append(maxRoom).append("\n");
        sb.append(maxWallRoom).append("\n");
        System.out.println(sb);
    }
    
    public static void BFS(int x, int y, int castleIdx) {
        Queue<int[]> q = new ArrayDeque<>();
        int cnt = 1;

        q.offer(new int[] { x, y });
        visited[x][y] = castleIdx;

        while (!q.isEmpty()) {
            int[] node = q.poll();

            int[] dir = new int[4];
            for (int i = 0; i < 4; i++) {
                dir[i] = map[node[0]][node[1]] % 2;
                map[node[0]][node[1]] = map[node[0]][node[1]] / 2;
                // dir[i] = (map[node[0]][node[1]] >> i) & 1;           // 비트연산으로도 가능 -> 시간차이는 없었음(?)
            }

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx, ny;
                nx = node[0] + dx[i];
                ny = node[1] + dy[i];

                // 범위 밖이면 나락
                if (!isIn(nx, ny))
                    continue;

                // 벽이면 인접 성 잇기
                if (dir[i] == 1) {
                    if (visited[nx][ny] == 0 || visited[nx][ny] == castleIdx)
                        continue;

                    roomLink.get(castleIdx).add(visited[nx][ny]);
                    continue;
                }

                // 벽이 아니면
                if (visited[nx][ny] == 0) {
                    visited[nx][ny] = castleIdx;
                    q.offer(new int[] { nx, ny });
                    cnt++;
                }
            }
        }
        
        roomSize.add(cnt);
    }

    public static boolean isIn(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }
}
