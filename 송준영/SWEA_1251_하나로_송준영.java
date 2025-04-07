import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * SWEA_1251_하나로
 * 난이도 5/10
 * MST
 * 744ms 122mb
 * 
 * 프림 알고리즘을 이용하여 최소 스패닝 트리를 구하는 문제
 * 소수 계산과 모든 정점 사이의 거리 계산이 들어있어 생각보다 복잡하다
 * 하지만 그것만 유의하면 똑같이 MST 풀이로 풀 수 있다
 */
public class SWEA_1251_하나로_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T, N;            // 테케 수, 정점 수
    static int[] X, Y;  // x좌표, y좌표, 부모 배열
    static double tax;          // 세율
    static double[][] list; // 인접배열
    static boolean[] visited; // 방문 여부

    public static void main(String[] args) throws Exception {
        // 테케 수 입력 및 반복
        T = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append(String.format("#%d %d\n", t, solve()));
        }
        // 결과 출력
        System.out.println(sb);
    }

    /**
     * 메인 메서드
     * @return 결과값
     * @throws Exception
     */
    public static long solve() throws Exception {
        // 입력 처리 및 초기화
        N = parseInt(br.readLine());
        X = new int[N];
        Y = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) X[i] = parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) Y[i] = parseInt(st.nextToken());
        tax = Double.parseDouble(br.readLine());
    
        list = new double[N][N];    // 인접 배열 초기화
        visited = new boolean[N];   // 방문 배열 초기화
    
        // 거리 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                double dist = calDist(X[i], Y[i], X[j], Y[j]);  // 두 점 사이의 거리 계산
                list[i][j] = dist;
                list[j][i] = dist;
            }
        }
    
        int cnt = 0;        // 정점 개수
        double result = 0;  // 결과값
        PriorityQueue<double[]> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1[1], o2[1])); // 가중치 기준으로 정렬
        pq.offer(new double[] {0, 0}); // 정점 번호, 가중치
        
        // 프림 알고리즘 수행
        while (!pq.isEmpty()) {
            double[] current = pq.poll();
            int idx = (int) current[0];
            double weight = current[1];
    
            if (visited[idx]) continue; // 이미 방문한 정점이면 skip
            visited[idx] = true;        // 방문 처리
    
            result += weight;           // 가중치 합
            cnt++;                      // 정점 개수 증가
            
            if (cnt == N) break;        // 모든 정점을 방문했으면 종료
    
            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    pq.offer(new double[] {i, Math.pow(list[idx][i], 2) * tax});    // 인접 정점과의 거리 계산 후 우선순위 큐에 추가
                }
            }
        }
    
        return Math.round(result);  // 결과값을 반올림하여 반환
    }
    

    /**
     * 두 점 사이의 거리 계산
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return  두 점 사이의 거리 (유클리드 거리)
     */
    public static double calDist(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
