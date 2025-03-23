import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * 시간: 184ms
 * 난이도 8/10
 * 우선순위 큐를 활용한 bfs
 * 처음에 0,0부터 완탐을 하고 넣을 수 있는 것 부터 pq에 넣었는데 그게 아니라 bfs를 돌면서 넣어야 한다는 것을 꺠달음
 * 또한 distance를 설계하고 pq의 정렬 기준을 잘 세워줘야 함
 */
public class Main_16236_아기상어 {
    static int n, time;
    static int[][] map;
    static class Shark{
        int r;
        int c;
        int fishCnt;
        int size;

        public Shark(int r, int c, int fishCnt, int size) {
            this.r = r;
            this.c = c;
            this.fishCnt = fishCnt;
            this.size = size;
        }
    }
    static class Point implements Comparable<Point>{
        int r;
        int c;
        int distance;

        public Point(int r, int c, int distance) {
            this.r = r;
            this.c = c;
            this.distance =distance;
        }

        // 상어를 기준으로 상어랑 가까운 순서부터 큐에 우선순위를 줘야 함
        @Override
        public int compareTo(Point o) {
            if(this.distance != o.distance){
                return this.distance - o.distance;
            }
            if(this.r!=o.r){
                return this.r-o.r;
            }
            return this.c-o.c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = parseInt(br.readLine());
        time = 0;
        map = new int[n][n];
        int sr=0, sc=0;
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = parseInt(st.nextToken());
                if(map[i][j] == 9){
                    sr = i;
                    sc = j;
                    map[i][j]=0;
                }
            }
        }

        // 아기 상어 객체 선언
        Shark shark = new Shark(sr, sc, 0, 2);
        // 큐가 빈다면 더이상 잡아먹을 물고기가 없다는 것
        // 큐 정렬 기준이 상어 기준으로 가까운순
        while(true){
            // bfs에서 우선순위 큐를 받는다
            PriorityQueue<Point> q = bfs(shark);
            if(q.isEmpty()) break;

            Point target = q.poll();
            // 거리를 구하고 시간에 더해주기
            map[target.r][target.c] = 0;
            time+= target.distance;
            // 상어 위치 다시 조정해주기
            shark.r = target.r;
            shark.c = target.c;
            shark.fishCnt++;
            // 만약 먹은 물고기 숫자가 상어 사이즈에 도달했다면
            if(shark.fishCnt==shark.size){
                shark.size++;
                shark.fishCnt=0;
            }
        }
        System.out.println(time);

    }
    // 상어를 기준으로 4방을 돌면서 지나갈 수 있는 길을 bfs에 추가하여 갈 수있는 최소거리를 구한다
    // 가까운 곳을 기준으로 탐색을 해야 함
    // 거기서 상어가 먹을 수 있는 물고기가 있는 곳이 있다면 우선순위 큐에 넣어줌
    private static PriorityQueue<Point> bfs(Shark shark) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        ArrayDeque<int[]> q= new ArrayDeque<>();
        PriorityQueue<Point> pq = new PriorityQueue<>();
        boolean[][] isVisited = new boolean[n][n];
        isVisited[shark.r][shark.c] =true;
        q.add(new int[]{shark.r, shark.c, 0});
        while(!q.isEmpty()){
            int[] temp = q.poll();
            // 현재 위치에서 먹을 수 있는 물고기가 있으면 우선순위 큐에 추가
            if(map[temp[0]][temp[1]] > 0  && map[temp[0]][temp[1]] <shark.size){
                pq.add(new Point(temp[0], temp[1], temp[2]));
            }
            for(int i=0;i<4;i++){
                int nr = temp[0] + dr[i];
                int nc = temp[1] + dc[i];
                int dis = temp[2];
                // 범위 안에 들어와 있고 사이즈가 상어크기 이하이고 아직 방문하지 않았다면
                if(nr >=0 && nc>=0 && nr<n && nc<n && map[nr][nc] <= shark.size &&!isVisited[nr][nc]){
                    isVisited[nr][nc] =true;
                    q.add(new int[]{nr, nc, dis+1});
                }
            }
        }
        return pq;
    }


}
