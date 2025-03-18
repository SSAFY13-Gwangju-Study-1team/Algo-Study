/* 메모리 15196kb, 시간 144ms
 * 문제 풀이 아이디어: 우선순위 큐를 활용하고 오버플로우 주의한다.
 * 체감 난이도: 2/10
 */

import java.io.*;
import java.util.*;

public class Main_15903_카드_합체_놀이_정찬영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    /* ----- 입력 ----- */
    static int n, m;
    static PriorityQueue<Long> pq = new PriorityQueue<>();
    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 카드 개수 n
        m = Integer.parseInt(st.nextToken());   // 카드 합체 횟수 m

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            pq.add(Long.parseLong(st.nextToken()));
        }
    }

    /* ----- 구현 ----- */
    static void solve() {
        for(int i=0; i<m; i++) {
            long x = pq.poll();  // 가장 작은 수 2개 꺼냄
            long y = pq.poll();
            pq.add(x+y);    // 더해서 2번 넣음
            pq.add(x+y);
        }

        long sum = 0;
        while(!pq.isEmpty()) {  // pq에 있는 모든 값 더함
            sum += pq.poll();
        }

        sb.append(sum);
    }

    /* ----- 출력 ----- */
    static void output() throws IOException {
        System.out.println(sb);
        br.close();
    }
}