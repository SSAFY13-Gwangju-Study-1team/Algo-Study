import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Main_14235_크리스마스선물
 * 난이도 2/10
 * 우선순위 큐
 * 352ms 35mb
 * 
 * 우선순위 큐를 이용하여 크리스마스 선물을 처리하는 문제
 * 0이 입력되면 큐에서 하나 빼서 출력하고, 아니면 큐에 넣어준다
 * 큐가 비어있을 때 0이 입력되면 -1을 출력해야 한다
 */
public class Main_14235_크리스마스선물_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n;   // 테스트케이스 수

    public static void main(String[] args) throws Exception {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());    // 우선순위 큐 선언
        n = parseInt(br.readLine());    // 테스트케이스 수 입력

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int temp = parseInt(st.nextToken());
            if (temp == 0) {    // 0이 입력되면 큐에서 하나 빼서 출력
                if (!pq.isEmpty()) {    // 큐가 비어있지 않다면
                    sb.append(pq.poll()).append('\n');      // 큐에서 하나 빼서 출력
                } else {
                    sb.append(-1).append('\n');             // 큐가 비어있다면 -1 출력
                }
            } else {            // 0이 아니라면 큐에 넣어준다
                for (int j = 0; j < temp; j++) {
                    int input = parseInt(st.nextToken());   
                    pq.offer(input);    // 큐에 넣어준다
                }   
            }
        }

        // 출력
        System.out.println(sb);
    }
}
