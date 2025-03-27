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
 * 크루스칼 알고리즘을 이용하여 최소 스패닝 트리를 구하는 문제
 * 소수 계산과 모든 정점 사이의 거리 계산이 들어있어 생각보다 복잡하다
 * 하지만 그것만 유의하면 똑같이 MST 풀이로 풀 수 있다
 */
public class SWEA_1251_하나로_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T, N;            // 테케 수, 정점 수
    static int[] X, Y, parent;  // x좌표, y좌표, 부모 배열
    static double tax;          // 세율
    static List<double[]> list; // 간선 리스트

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
        for (int i = 0; i < N; i++) {
            X[i] = parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            Y[i] = parseInt(st.nextToken());
        }
        tax = Double.parseDouble(br.readLine());
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        list = new ArrayList<>();

        // 모든 정점 사이의 거리 계산
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                list.add(new double[] { calDist(X[i], Y[i], X[j], Y[j]) , i, j });  // 거리, 정점1, 정점2
            }
        }

        // 거리를 기준으로 오름차순 정렬
        Collections.sort(list, (o1, o2) -> {
            return Double.compare(o1[0], o2[0]); // 오름차순 (Double.compare 사용 -> 빼기로 하면 안 됨)
        });

        int cnt = 0;        // 현재 선택한 간선 수
        double result = 0;  // 결과값

        for (double[] e : list) {
            if (union((int) e[1], (int) e[2])) {    // 사이클이 발생하지 않으면 넣기
                result += (tax * Math.pow(e[0], 2));    // 세율 * 거리^2
                if(++cnt == N - 1) break;           // 간선 수가 정점 수 - 1이면 종료
            }
        }

        // 반올림하여 반환
        return Math.round(result);
    }

    /**
     * 부모 찾기
     * @param x 부모 찾을 정점
     * @return  부모
     */
    public static int find(int x) {
        if (parent[x] == x) return x;

        return parent[x] = find(parent[x]);   // 경로 압축
    }

    /**
     * 합치기
     * @param x 
     * @param y
     * @return  합쳐졌는지 여부
     */
    public static boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px == py) return false; // 부모가 같으면 사이클 발생, false 반환

        if (px < py) parent[py] = px;   // 작은 쪽으로 합치기
        else parent[px] = py;
        return true;    // 합쳐졌다면 true 반환
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
