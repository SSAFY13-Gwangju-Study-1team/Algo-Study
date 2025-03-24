import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Main_12764_싸지방에간준하
 * 난이도 5/10
 * 구현, 우선순위큐
 * 876ms 68mb
 * 
 * 우선순위큐를 이용한 구현 문제
 * 우선순위 큐를 3개나 이용해서 풀었다
 * 1. 입력을 받는 우선순위큐(시작 시간, 끝나는 시간)             => 시작시간 기준으로 정렬
 * 2. 싸지방에 앉는 우선순위큐(앉는 자리, 시작 시간, 끝나는 시간) => 끝나는 시간 기준으로 정렬
 * 3. 나가는 사람을 처리하는 우선순위큐(남은 자리)               => 남은 자리 기준으로 정렬
 * 
 * 입력을 받는 우선순위큐에서 하나씩 빼면서 싸지방에 앉는 우선순위큐에 넣어줌
 * 싸지방 자리에 넣을때 peek과 비교해서 끝나는 시간이 현재 들어가려는 사람보다 빠르면 꺼내주고 remain에 넣어줌(남은 자리)
 * remain이 비어있으면 새로운 자리를 만들어주고(cnt) remain이 있으면 그 자리에 넣어줌
 * 각 자리별 앉은 사람 수를 세는 것은 int 배열을 이용해서 index 처리
 * 
 */
public class Main_12764_싸지방에간준하_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, P, Q; // 사람 수, 시작 시간, 끝나는 시간
    static int cnt = 0; // 싸지방 자리 수
    static int[] seat = new int[100001];    // 각 자리별 앉은 사람 수
    static PriorityQueue<Integer> remain = new PriorityQueue<>();               // 남은 자리
    static PriorityQueue<int[]> input_pq = new PriorityQueue<>((o1, o2) -> {    // 입력 받는 우선순위큐
        if (o1[0] == o2[0]) {
            return o1[1] - o2[1];
        }
        return o1[0] - o2[0];
    });
    static PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {          // 싸지방에 앉는 우선순위큐
        if (o1[1] == o2[1]) {
            return o1[0] - o2[0];
        }
        return o1[2] - o2[2];
    });

    public static void main(String[] args) throws Exception {
        // 입력 처리
        N = parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            P = parseInt(st.nextToken());
            Q = parseInt(st.nextToken());
            input_pq.offer(new int[] { P, Q });
        }

        // 싸지방 자리 앉기 처리
        while (!input_pq.isEmpty()) {
            int[] temp = input_pq.poll();
            if (pq.isEmpty()) { // 처음에는 무조건 처음 자리에 앉기
                cnt++;
                pq.offer(new int[] { cnt, temp[0], temp[1]});
            } else {
                while (pq.peek()[2] <= temp[0]) {   // 끝나는 시간이 시작 시간보다 작으면 나가기
                    int[] getout = pq.poll();
                    remain.offer(getout[0]);        // 나간 자리를 remain에 넣어주기
                    seat[getout[0]]++;              // 나간 자리에 앉은 사람 수 증가
                    if (pq.isEmpty()) {             // pq가 비어있으면 break
                        break;
                    }
                }
                if (!remain.isEmpty()) {            // remain이 비어있지 않으면 remain에 있는 자리에 앉기(poll)
                    int remainSeat = remain.poll();
                    pq.offer(new int[] { remainSeat, temp[0], temp[1] });
                } else {                            // remain이 비어있으면 새로운 자리 만들어서 앉기
                    cnt++;
                    pq.offer(new int[] { cnt, temp[0], temp[1] });
                }
            }
        }

        // 인풋을 다 넣어도 pq에 남아있는 사람들이 있으므로 처리해주기
        while (!pq.isEmpty()) {
            int[] getout = pq.poll();
            remain.offer(getout[0]);
            seat[getout[0]]++;
        }

        // 결과 출력
        sb.append(cnt).append("\n");
        for (int i = 1; i <= cnt; i++) {
            sb.append(seat[i]).append(" ");
        }
        System.out.println(sb);
    }
}
