import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * Main_1613_역사_송준영
 * 난이도 5/10
 * 플루이드 워셜
 * 620ms 47mb
 * 
 * 역사 문제는 플로이드-워셜 알고리즘을 사용하여 선수관계를 차악해서 정리
 * 각 dist의 인덱스에 연결여부를 알수 있음 (방향그래프에서)
 * 다만 오늘 프로이드를 안 배웠으면 플로이드 알고리즘이란 것을 알아차리기 힘들었을 것임
 */
public class Main_1613_역사_송준영 {
    static final int INF = 1000000; //  무한대 값 가정
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n, k;        // n: 역사 수, k: 관계 수
    static int[][] dist;    // 거리 배열

    public static void main(String[] args) throws Exception {
        // 입력 처리 및 초기화
        st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        k = parseInt(st.nextToken());

        dist = new int[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {  
                dist[i][j] = INF;           // 무한대 값으로 초기화
                if (i == j) dist[i][j] = 0; // 자기 자신과의 거리는 0
            }
        }

        // 관계 입력
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = parseInt(st.nextToken()), y = parseInt(st.nextToken());
            dist[x][y] = 1;
        }

        // 플로이드-워셜 알고리즘 실행
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][k] + dist[k][j], dist[i][j]);
                }
            }
        }

        int s = parseInt(br.readLine());    // 비교할 쌍의 수
        for (int i = 0; i < s; i++) {
            st = new StringTokenizer(br.readLine());
            int x = parseInt(st.nextToken()), y = parseInt(st.nextToken());

            if (dist[x][y] != INF && dist[y][x] == INF) {           // x가 y보다 먼저
                sb.append(-1).append('\n');
            } else if (dist[x][y] == INF && dist[y][x] != INF) {    // y가 x보다 먼저
                sb.append(1).append('\n');
            } else {                                                // x와 y의 관계를 알 수 없음
                sb.append(0).append('\n');
            }
        }

        // 결과 출력
        System.out.println(sb);
    }
}
