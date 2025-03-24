package Week9;

import java.io.*;
import java.util.*;

/**
 * 조건
 *  - 가장 작은 수들을 계속 더해 나가면 될듯?
 * 설계
 *  - 우선순위 큐를 사용해서 작은수를 계속 뽑아낸후에 둘을 더해준다.
 *
 * 메모리: 15292kb
 * 실행시간: 152ms
 *
 * 정수의 범위가 커서 long타입을 써야한다....
 *
 */

public class Main_15903_카드합체놀이_김태민 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Long> pq = new PriorityQueue<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            pq.add(Long.parseLong(st.nextToken()));
        }

        for (int i = 0; i<m; i++){
            Long x = pq.poll();
            Long y = pq.poll();
            pq.add(x+y);
            pq.add(x+y);
        }
        Long sum = 0L;
        for(Long i: pq){
            sum+=i;
        }
        System.out.println(sum);

    }
}
