import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/**
 * Main_2075_N번째큰수
 * 난이도 2/10
 * 우선순위 큐
 * 804ms 2744KB
 * 
 * 우선순위 큐를 이용하여 N번째 큰 수를 구하는 문제
 * 큐에 모든 수를 넣고 N-1번째까지 poll을 해주면 N번째 큰 수가 나온다
 * 더 최;적화 할 수 있는 방법이 있을것 같은데...
 */
public class Main_2075_N번째큰수_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;   // 크기
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 우선순위 큐

    public static void main(String[] args) throws Exception {
        // 입력 처리 및 우선순위 큐에 넣기
        N = parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int temp = parseInt(st.nextToken());
                pq.offer(temp);
            }
        }

        // N-1번째까지 poll
        for (int i = 0; i < N - 1; i++) {
            pq.poll();
        }

        // 출력
        System.out.println(pq.poll());
    }
}
