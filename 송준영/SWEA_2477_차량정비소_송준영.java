import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * 차량 정비소
 * 난이도 8/10
 * 구현, 시뮬레이션
 * 206ms 39mb
 * 
 * 
 * 고객은 도착 순서대로 접수번호 (고객번호가 낮은 순)
 * 접수 창구는 번호가 적은 순
 * 고객번호 : 접수, 정비
 * 
 *  ① 여러 고객이 기다리고 있는 경우 고객번호가 낮은 순서대로 우선 접수한다. -> queue?(고객 번호, 시간)
    ② 빈 창구가 여러 곳인 경우 접수 창구번호가 작은 곳으로 간다. 들어가는 값(고객 번호, 나올 시간)

    ① 먼저 기다리는 고객이 우선한다. -> PQ(고객번호, 들어온 시간(오름), 접수 창구 번호(오름))
    ② 두 명 이상의 고객들이 접수 창구에서 동시에 접수를 완료하고 정비 창구로 이동한 경우, 이용했던 접수 창구번호가 작은 고객이 우선한다.
    ③ 빈 창구가 여러 곳인 경우 정비 창구번호가 작은 곳으로 간다. 들어가는 값(고객 번호, 들어간 시간)
 */
public class SWEA_2477_차량정비소_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T;               // 테스트 케이스 수
    static int N, M, K, A, B;   // 접수 창구 수, 정비 창구 수, 고객 수, A 고객의 접수 창구 번호, B 고객의 정비 창구 번호
    static int[] a, b, c;       // 접수 창구 소요 시간, 정비 창구 소요 시간, 고객 도착 시간
    static int[][] customer;    // 고객별 이용한 창구 저장

    public static void main(String[] args) throws Exception {
        // 테스트 케이스 수 입력
        T = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append(String.format("#%d %d\n", t, solve()));
        }
        // 결과 출력
        System.out.println(sb);
    }

    /**
     * 메인 메서드
     * @return
     * @throws Exception
     */
    public static int solve() throws Exception {
        // 입력 처리 및 초기화
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        K = parseInt(st.nextToken());
        A = parseInt(st.nextToken());
        B = parseInt(st.nextToken());

        a = new int[N+1];
        b = new int[M+1];
        c = new int[K+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            a[i] = parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            b[i] = parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= K; i++) {
            c[i] = parseInt(st.nextToken());
        }

        customer = new int[K+1][2];                 // 고객별 이용한 창구 저장

        simulation();       // 시뮬레이션 진행

        int cnt = 0;        // 고객 번호 저장
        for (int i = 1; i < K+1; i++) {
            if (customer[i][0] == A && customer[i][1] == B ) {  // A, B와 같은 고객 번호 찾기
                cnt += i;             // 찾으면 그 고객의 고객 번호를 cnt에 더함
            }
        }

        return cnt == 0 ? -1 : cnt; // cnt가 0이면 -1 리턴, 아니면 cnt 리턴

    }

    /**
     * 시뮬레이션 메서드
     * @throws Exception
     */
    public static void simulation() {
        
        Queue<int[]> waitingQueue_1 = new ArrayDeque<>();   // 접수 대기 큐1 (고객번호, 도착시간)
        PriorityQueue<Integer> emptyRecipt = new PriorityQueue<>();  // 빈 접수 창구 큐 (접수 창구 번호)
        PriorityQueue<int[]> runningRecipt = new PriorityQueue<>((o1, o2) -> {  // 일하는 접수창구 큐 (고객번호, 접수 창구 번호, 나가는 시간[접수창구 걸리는 시간 + 들어온 시간])
            return Integer.compare(o1[2], o2[2]);
        });
        PriorityQueue<int[]> waitingQueue_2 = new PriorityQueue<>((o1, o2) -> { // 접수 대기 큐2 (고객번호, 접수 창구 번호, 들어온 시간)
            if (o1[2] == o2[2]) {
                return Integer.compare(o1[1], o2[1]);
            }
            return Integer.compare(o1[2], o2[2]);
        });  
        PriorityQueue<Integer> emptyRepair = new PriorityQueue<>();  // 빈 정비 창구 큐 (정비 창구 번호)
        PriorityQueue<int[]> runningRepair = new PriorityQueue<>((o1, o2) -> {  // 일하는 정비창구 큐 (고객번호, 정비 창구 번호, 나가는 시간[정비창구 걸리는 시간 + 들어온 시간])
            return Integer.compare(o1[2], o2[2]);
        });

        int curTime = 0;        // 현재 시간

        // 접수 대기열에 넣기 (고객번호, 도착 시간)
        for (int i = 1; i <= K; i++) {
            waitingQueue_1.offer(new int[] { i , c[i] });
        }

        // 빈 접수 창구 큐 (접수 창구 번호)
        for (int i = 1; i <= N; i++) {
            emptyRecipt.offer(i);
        }

        // 빈 정비 창구 큐 (정비 창구 번호)
        for (int i = 1; i <= M; i++) {
            emptyRepair.offer(i);
        }

        // 시뮬레이션 시작
        while (true) { 

            // 일하는 접수창구 큐 (고객번호, 접수 창구 번호, 나가는 시간[접수창구 걸리는 시간 + 들어온 시간])
            // 접수 대기 큐2 (고객번호, 접수 창구 번호, 들어온 시간)
            // 빈 접수 창구 큐 (접수 창구 번호)
            /**
             * 1. 접수 창구 처리 로직
             */
            while (!runningRecipt.isEmpty() && curTime >= runningRecipt.peek()[2]) {    // 나가는 시간보다 현재 시간이 크거나 같으면 (대부분 같을때 다 나감)
                int[] tempCus = runningRecipt.poll();   // 접수 창구에서 나가는 고객
                waitingQueue_2.offer(tempCus);          // 접수 대기 큐2에 넣음 (고객번호, 접수 창구 번호, 들어온 시간)
                emptyRecipt.offer(tempCus[1]);          // 빈 접수 창구 큐에 넣음 (고객이 나간 접수 창구 번호)
            }
            
            // 접수 대기 큐1 (고객번호, 도착시간)
            // 빈 접수 창구 큐 (접수 창구 번호)
            // 일하는 접수창구 큐 (고객번호, 접수 창구 번호, 나가는 시간[접수창구 걸리는 시간 + 들어온 시간])
            /**
             * 2. 접수 창구 대기열 처리 로직
             */
            while (!waitingQueue_1.isEmpty() && curTime >= waitingQueue_1.peek()[1]) {
                if (emptyRecipt.isEmpty()) break;       // 빈 곳이 없으면 못 들어감

                int[] tempCus = waitingQueue_1.poll();     // 대기열에서 고객 꺼내기 (고객번호, 도착시간)
                int reciptNo = emptyRecipt.poll();         // 빈 접수 창구 꺼내기 (접수 창구 번호)

                runningRecipt.offer(new int[] {tempCus[0], reciptNo, curTime + a[reciptNo]});   // 일하는 접수창구 큐에 넣기 (고객번호, 접수 창구 번호, 나가는 시간[접수창구 걸리는 시간 + 들어온 시간])
                customer[tempCus[0]][0] = reciptNo;       // 고객별 이용한 창구 저장 (고객번호, 접수 창구 번호)
            }

            // 일하는 정비창구 큐 (고객번호, 정비 창구 번호, 나가는 시간[정비창구 걸리는 시간 + 들어온 시간])
            // 빈 정비 창구 큐 (접수 창구 번호)
            /**
             * 3. 정비 창구 처리 로직
             */
            while (!runningRepair.isEmpty() && curTime >= runningRepair.peek()[2]) {
                int[] tempCus = runningRepair.poll();     // 정비 창구에서 나가는 고객
                emptyRepair.offer(tempCus[1]);            // 빈 정비 창구 큐에 넣음 (고객이 나간 정비 창구 번호)
            }

            // 접수 대기 큐2 (고객번호, 접수 창구 번호, 들어온 시간)
            // 빈 정비 창구 큐 (정비 창구 번호)
            // 일하는 정비창구 큐 (고객번호, 정비 창구 번호, 나가는 시간[정비창구 걸리는 시간 + 들어온 시간])
            /**
             * 4. 정비 창구 대기열 처리 로직
             */
            while (!waitingQueue_2.isEmpty() && curTime >= waitingQueue_2.peek()[2]) {
                if (emptyRepair.isEmpty()) break;       // 빈 곳이 없으면 못 들어감

                int[] tempCus = waitingQueue_2.poll();   // 대기열에서 고객 꺼내기 (고객번호, 접수 창구 번호, 들어온 시간)
                int repairNo = emptyRepair.poll();       // 빈 정비 창구 꺼내기 (정비 창구 번호)

                runningRepair.offer(new int[] {tempCus[0], repairNo, curTime + b[repairNo]});   // 일하는 정비창구 큐에 넣기 (고객번호, 정비 창구 번호, 나가는 시간[정비창구 걸리는 시간 + 들어온 시간])
                customer[tempCus[0]][1] = repairNo;     // 고객별 이용한 창구 저장 (고객번호, 정비 창구 번호)
            }

            // System.out.println("curTime : " + curTime);
            // System.out.print("waitingQueue_1 : " );
            // for (int[] e : waitingQueue_1) {
            //     System.out.print(Arrays.toString(e) + " ");
            // }
            // System.out.println();
            // System.out.print("emptyRecipt : " );
            // for (int e : emptyRecipt) {
            //     System.out.print(e + " ");
            // }
            // System.out.println();
            // System.out.print("runningRecipt : " );
            // for (int[] e : runningRecipt) {
            //     System.out.print(Arrays.toString(e) + " ");
            // }
            // System.out.println();
            // System.out.print("waitingQueue_2 : " );
            // for (int[] e : waitingQueue_2) {
            //     System.out.print(Arrays.toString(e) + " ");
            // }
            // System.out.println();
            // System.out.print("emptyRepair : " );
            // for (int e : emptyRepair) {
            //     System.out.print(e + " ");
            // }
            // System.out.println();
            // System.out.print("runningRepair : " );
            // for (int[] e : runningRepair) {
            //     System.out.print(Arrays.toString(e) + " ");
            // }
            // System.out.println();
            // System.out.println("========================================");

            curTime++;  // 시간 증가

            // 정비창구 제외 모든 큐가 비어있으면 종료
            if (waitingQueue_1.isEmpty() && runningRecipt.isEmpty() && waitingQueue_2.isEmpty()) {
                break;
            }
        }
    }
}
