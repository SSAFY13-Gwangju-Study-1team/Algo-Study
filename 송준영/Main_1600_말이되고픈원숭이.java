import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * 객체 Node 버전 -> 메모리초과
 */
public class Main_1600_말이되고픈원숭이 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int K, W, H;

    // 방향 벡터 -> 4 / 12
    static int[] dx = { -1, 0, 1, 0, -2, -1, 1, 2, 2, 1, -1, -2 };
    static int[] dy = { 0, 1, 0, -1, 1, 2, 2, 1, -1, -2, -2, -1 };

    static int[][] map;
    static int visited[][];

    public static void main(String[] args) throws Exception {
        K = parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = parseInt(st.nextToken());
        H = parseInt(st.nextToken());
        map = new int[H][W];
        visited = new int[H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = parseInt(st.nextToken());
                visited[i][j] = Integer.MAX_VALUE;
            }
        }

        if (H == 1 && W == 1) {
            System.out.println(0);
        } else {
            System.out.println(BFS());
        }
    }

    public static int BFS() {
        Queue<Node> q = new ArrayDeque<>();

        q.add(new Node(0, 0, 0, 0));

        while (!q.isEmpty()) {
            Node node = q.poll();
            // System.out.println(node.x + " " + node.y + " " + node.jump + " " + node.count);

            // 갈수 있는 방향 한정
            int move;
            if (node.jump < K) {
                move = 12;
            } else {
                move = 4;
            }

            for (int i = 0; i < move; i++) {
                int nx, ny;
                nx = node.x + dx[i];
                ny = node.y + dy[i];

                if (nx == H - 1 && ny == W - 1) {
                    // System.out.println(nx + " : " + ny);
                    return node.count + 1;
                }   

                // 범위 밖이거나 방문한 곳이거나 벽이면 넘어가기
                if (!isIn(nx, ny) || visited[nx][ny] <= node.count || map[nx][ny] == 1)
                    continue;

                visited[nx][ny] = node.count+1;

                if (i < 4) {
                    q.offer(new Node(nx, ny, node.jump, node.count + 1));
                } else {
                    q.offer(new Node(nx, ny, node.jump + 1, node.count + 1));
                }
            }
        }
        
        return -1;
    }
    
    public static class Node {
        int x, y, jump, count;

        public Node(int x, int y, int jump, int count) {
            this.x = x;
            this.y = y;
            this.jump = jump;
            this.count = count;
        }
    }

    public static boolean isIn(int x, int y) {
        return x >= 0 && x < H && y >= 0 && y < W;
    }
}
