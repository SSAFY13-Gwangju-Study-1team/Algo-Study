import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * Main_10473_인간대포_송준영
 * 난이도 8~9/10
 * 다익스트라
 * 112ms 14mb
 * 
 * 시작점 부터 도착점 까지 가는데 걸리는 "최소" 시간을 구하는 문제
 * 대포를 타고 가는 것과 걸어가는 것의 시간을 비교하여 대포를 타고 가는 것이 더 빠르면 대포를 타고 가는 것으로 처리
 * 처음에는 순열을 이용한 완전탐색인줄 알 앗지만 n 크기보고 아닌 것을 직감 (100)
 * 
 * 고민 끝네 최소 시간 인것을 확인후 다익스트라를 이용하여 풀었다
 * 다만 대포 이용시 탈수도 있고 안 탈수도 있어서 둘 을 비교해서 더 시간이 적게 걸리는 방법으로 가중치를 지정
 * 또한 대포 이용시 시작점으로 갈 이유가 없고 도착점에서 다른데로 갈 이유도 없으므로 그것도 처리 해줌
 * 가중치 계산 및 할당이 복잡한 문제여서 이것만 잘 해내면 나머지는 기본적인 다익스트라 구하는 방법을 사용하면 풀린다
 * 변수 헷갈리지 말기!
 */
public class Main_10473_인간대포_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;                   // 대포 수
    static double[] start, end;     // 시작점, 도착점 좌표
    static double[][] cannon;       // 대포 좌표
    static double[][] timeGraph;    // 각 지점에서 대포나, 도착점 까지 가는데 걸리는 시간

    public static void main(String[] args) throws Exception {
        start = new double[2];       // 시작점 좌표
        end = new double[2];         // 도착점 좌표
        
        // 시작점 입력 처리
        st = new StringTokenizer(br.readLine());
        start[0] = parseDouble(st.nextToken());
        start[1] = parseDouble(st.nextToken());
        // 도착점 입력 처리
        st = new StringTokenizer(br.readLine());
        end[0] = parseDouble(st.nextToken());
        end[1] = parseDouble(st.nextToken());

        N = parseInt(br.readLine());    // 대포 수 입력 처리
        cannon = new double[N+2][2];    // 대포 좌표 저장 배열 (시작점, 도착점 포함)

        // 대포 좌표 입력 처리
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            cannon[i][0] = parseDouble(st.nextToken());
            cannon[i][1] = parseDouble(st.nextToken());
        }

        // 시작점, 도착점 좌표 저장, (대포 좌표 저장한 곳에 포함)
        cannon[0][0] = start[0];
        cannon[0][1] = start[1];
        cannon[N+1][0] = end[0];
        cannon[N+1][1] = end[1];

        System.out.println(dijkstra());
    }

    /**
     * 다익스트라 알고리즘을 이용하여 시작점에서 도착점 까지 가는데 걸리는 최소 시간을 구하는 메서드
     * @return  시작점에서 도착점 까지 가는데 걸리는 최소 시간
     */
    public static double dijkstra() {
        timeGraph = new double[N+2][N+2];   // 각 지점에서 대포나, 도착점 까지 가는데 걸리는 시간 저장 배열
        // 시작점~ 시간 구하기
        for (int i = 1; i < N+2; i++) {
            timeGraph[0][i] = euclideanDist(cannon[0][0], cannon[0][1], cannon[i][0], cannon[i][1]) / 5.0;
        }
        // 대포에서~ 시간 구하기 (걸어가는거, 대포 타고 가는거 비교)
        for (int i = 1; i < N; i++) {
            for (int j = i + 1; j < N+1; j++) {
                double run = euclideanDist(cannon[i][0], cannon[i][1], cannon[j][0], cannon[j][1]) / 5.0;
                double jump = (Math.abs(euclideanDist(cannon[i][0], cannon[i][1], cannon[j][0], cannon[j][1]) - 50.0) / 5.0) + 2.0;
                timeGraph[i][j] = Math.min(run, jump);
                timeGraph[j][i] = timeGraph[i][j];
            }
        }

        // 도착점 시간 구하기
        for (int i = 1; i < N+1; i++) {
            double run = euclideanDist(cannon[i][0], cannon[i][1], cannon[N+1][0], cannon[N+1][1]) / 5.0;
            double jump = (Math.abs(euclideanDist(cannon[i][0], cannon[i][1], cannon[N+1][0], cannon[N+1][1]) - 50.0) / 5.0) + 2.0;
            timeGraph[i][N+1] = Math.min(run, jump);
        }

        // for (int i = 0; i < N+2; i++) {
        //     for (int j = 0; j < N+2; j++) {
        //         System.out.print(timeGraph[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        PriorityQueue<Node> pq = new PriorityQueue<>(); // 우선순위 큐 선언
        double[] dist = new double[N+2];                // 시작점에서 각 지점까지 가는데 걸리는 시간 저장 배열
        Arrays.fill(dist, Double.MAX_VALUE);            // 시간 초기화

        // 시작점 초기화
        pq.offer(new Node(0, 0.0));
        dist[0] = 0.0;

        // 다익스트라 알고리즘 실행
        while(!pq.isEmpty()) {
            Node temp = pq.poll();

            if (dist[temp.idx] < temp.val) continue;

            for (int i = 0; i < N+2; i++) {
                if (timeGraph[temp.idx][i] == 0.0) continue;

                if (dist[i] > temp.val + timeGraph[temp.idx][i]) {
                    dist[i] = temp.val + timeGraph[temp.idx][i];
                    if (i != N+1) { // 도착점이 아니면 넣기, 도착점은 어차피 가는 곳 없으니까 미리 안 넣어서 시간 좀 줄이기
                        pq.offer(new Node(i, dist[i]));
                    }
                }
            }
        }

        // for (int i = 0; i < N+2; i++) {
        //     System.out.print(dist[i] + " ");
        // }

        return dist[N+1]; // 도착점 까지 가는데 걸리는 최소 시간 리턴
    }

    /**
     * 유클리드 거리 구하는 메서드
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return  거리
     */
    public static double euclideanDist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    /**
     * Node 클래스
     * PQ에 사용
     */
    public static class Node implements Comparable<Node> {
        int idx;    // 인덱스
        double val; // 시간

        public Node(int idx, double val) {
            this.idx = idx;
            this.val = val;
        }
        
        @Override
        public int compareTo(Node o) {
            return Double.compare(this.val, o.val);
        }
    }
}
