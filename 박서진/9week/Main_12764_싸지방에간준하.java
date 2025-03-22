import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * gpt와 함께 풀이.. 너무 어렵다...
 * 1. 우선순위 큐 2개를 사용한다
 *  - [퇴장 시간, 좌석 번호]를 이용해서 퇴장 시간을 기준으로 정렬 시킨다
 *  - 사용 가능한 좌석의 번호를 관리한다
 *  2. 새로운 사람이 들어왔을 때 새로운 좌석이 필요하면 추가, 기존 좌석이 있으면 재사용한다
 *  3. map을 이용해 사용 횟수를 저장한다
 */
public class Main_12764_싸지방에간준하 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = parseInt(br.readLine());
        int[][] times = new int[n][2];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            times[i][0] = parseInt(st.nextToken());
            times[i][1] = parseInt(st.nextToken());
        }
        // 1. 도착 시간 기준 정렬
        Arrays.sort(times, Comparator.comparingInt(a->a[0])); // 도착 시간을 기준으로 오름차순 정렬
        // 2. 우선순위 큐 2개 선언
        PriorityQueue<int[]> usingSeats = new PriorityQueue<>(Comparator.comparingInt(a -> a[0])); // [퇴장 시간, 좌석 번호]종료시간 오름 차순
        PriorityQueue<Integer> availableSeats = new PriorityQueue<>(); // 사용가능한 좌석(번호 작은 순)
        // 3. 좌석 사용 횟수 기록 배열
        Map<Integer, Integer> seatUsage = new HashMap<>();
        int maxSeatIndex = -1; // 최대 좌석 번호

        for(int[] person : times){
            int arrT = person[0];
            int leaveT = person[1];

            //현재 시간에 종료할 사용자들을 정리(퇴장 시간<=현재 도착 시간)
            while(!usingSeats.isEmpty() && usingSeats.peek()[0]<=arrT){ // 현재 사용자의 도착하는 시간보다 종료시간이 빠르다면
                availableSeats.offer(usingSeats.poll()[1]); // 좌석 번호를 넘겨야겠지
            }

            // 좌석 정리 이후
            // 현재 사용자 자리 배치
            int seatNum;
            if(!availableSeats.isEmpty()){
                seatNum = availableSeats.poll();
            }else{
                seatNum = ++maxSeatIndex; // 만약 이요 가능한 좌석이 없으면 하나 할당해서 추가해줌
            }

            seatUsage.put(seatNum, seatUsage.getOrDefault(seatNum,0)+1);

            usingSeats.offer(new int[]{leaveT, seatNum});

        }

        System.out.println(maxSeatIndex+1);
        for(int i=0;i<=maxSeatIndex;i++){
            System.out.print(seatUsage.get(i)+" ");
        }



    }
}
