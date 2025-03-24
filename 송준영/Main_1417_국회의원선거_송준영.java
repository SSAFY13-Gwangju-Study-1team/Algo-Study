import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Main_1417_국회의원선거
 * 난이도 2/10
 * 우선순위 큐
 * 104ms 14mb
 * 
 * 우선순위 큐를 이용하여 국회의원 선거를 처리하는 문제
 * 우선순위 큐에 다솜이를 제외하고 넣은 다음 다솜이가 가장 많은 표를 받을 때까지 반복한다
 * 현재 가장 많은 표를 받은 얘를 꺼내고 1을 빼서 다시 넣어준다 + 다솜이에게 표를 주고 카운트를 증가시킨다
 */
public class Main_1417_국회의원선거_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 변수 선언
    static int N, dasom, cnt = 0;
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) throws Exception {
        // 입력 및 초기화
        N = parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            if (i == 0) {
                dasom = parseInt(br.readLine());
            } else {
                int temp = parseInt(br.readLine());
                pq.offer(temp);
            }
        }

        if (N == 1) {   // 다솜이만 있을 때
            System.out.println(0);
            return;
        }

        while (dasom <= pq.peek()) {    // 다솜이가 가장 많은 표를 받을 때까지 반복
            int temp = pq.poll();
            pq.offer(temp - 1); // 다솜이에게 표를 주고
            dasom++;            // 다솜이 표 증가
            cnt++;              // 카운트 증가
        }

        // 출력
        System.out.println(cnt);        
    }
}
