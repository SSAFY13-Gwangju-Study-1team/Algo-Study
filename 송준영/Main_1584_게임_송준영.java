import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1584_게임_송준영 {
    // 지도 크기 상수 (0 ~ 500)
    static final int SIZE = 501;

    static int[][] map = new int[SIZE][SIZE];

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        // 위험 구역 표시
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            
            int startX = Math.min(x1, x2);
            int endX   = Math.max(x1, x2);
            int startY = Math.min(y1, y2);
            int endY   = Math.max(y1, y2);
            
            // 위험 구역(1)으로 표시
            for (int x = startX; x <= endX; x++) {
                for (int y = startY; y <= endY; y++) {
                    map[x][y] = 1;
                }
            }
        }
        
        // 죽음의 구역 표시
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            
            int startX = Math.min(x1, x2);
            int endX   = Math.max(x1, x2);
            int startY = Math.min(y1, y2);
            int endY   = Math.max(y1, y2);
            
            // 죽음 구역(-1)으로 강제 지정
            for (int x = startX; x <= endX; x++) {
                for (int y = startY; y <= endY; y++) {
                    map[x][y] = -1;
                }
            }
        }
        
        // 다익스트라 알고리즘을 통해 (500,500)까지의 최소 피해량 계산 후 출력
        int result = dijkstra();
        System.out.println(result);
    }
    
    public static int dijkstra() {
        int[][] dist = new int[SIZE][SIZE];
        // 모든 좌표의 거리를 무한대로 초기화
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[0][0] = 0;
        pq.offer(new Node(0, 0, 0));
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            // 현재 노드의 누적 비용보다 큰 경우는 스킵
            if (cur.cost > dist[cur.x][cur.y])
                continue;
            
            // 상하좌우 4방향 탐색
            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                
                // 지도 범위를 벗어난 경우 무시
                if (!isIn(nx, ny))
                    continue;
                // 죽음 구역이면 이동 불가
                if (map[nx][ny] == -1)
                    continue;
                
                // 비용: 위험 구역이면 1, 안전 구역이면 0
                int cost = (map[nx][ny] == 1) ? 1 : 0;
                if (dist[nx][ny] > cur.cost + cost) {
                    dist[nx][ny] = cur.cost + cost;
                    pq.offer(new Node(nx, ny, dist[nx][ny]));
                }
            }
        }
        
        // (500,500)에 도달할 수 없으면 -1, 아니면 최소 피해량 반환
        return dist[500][500] == Integer.MAX_VALUE ? -1 : dist[500][500];
    }
    
    public static boolean isIn(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }
    
    static class Node implements Comparable<Node> {
        int x, y, cost;
        
        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node other) {
            return this.cost - other.cost;
        }
    }
}
