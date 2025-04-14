import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * SWEA_1263_사람네트워크2
 * 난이도 3/10
 * 플로이드-워셜
 * 1121ms 102mb
 * 
 * 사람 네트워크 문제는 플로이드-워셜 알고리즘을 사용하여 최단 경로를 구하면 된다
 * 전형적인 플로이드를 쓰면 맞는 문제
 */
public class SWEA_1263_사람네트워크2_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T;           // 테케 수
    static int[][] dist;    // 거리 배열

    public static void main(String[] args) throws Exception {
        // 테케 수 입력 및 반복
        T = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append(String.format("#%d %d\n", t, solve()));
        }
        // 출력
        System.out.println(sb);
    }

    /**
     * 메인 메서드
     * @return  답
     * @throws Exception
     */
    public static int solve() throws Exception {
        // 입력 처리 및 초기화
        st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());

        dist = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int val = parseInt(st.nextToken());
                dist[i][j] = val == 0 ? 10000000 : val;
                if (i == j) dist[i][j] =  0;
            }
        }
        
        // 플로이드-워셜 알고리즘 실행
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dist[i][j] = Math.min(dist[i][k] + dist[k][j], dist[i][j]);
                }
            }
        }

        // 결과 처리
        // 각 사람의 거리 합을 구하고 그 중 최솟값을 구한다
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int temp = 0;
            for (int j = 0; j < N; j++) {
                temp += dist[i][j];
            }
            result = Math.min(result, temp);
        }

        return result;  // 최종 결과 리턴
    }
}
