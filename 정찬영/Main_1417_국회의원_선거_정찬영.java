/* 메모리 14304kb, 시간 104ms
 * 문제 풀이 아이디어: 우선순위큐를 내림차순으로 하여 1명씩 그리디로 풀이한다.
 * 체감 난이도: 2/10
 */

import java.io.*;
import java.util.*;

public class Main_1417_국회의원_선거_정찬영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    /* ----- 입력 ----- */
    static int N;
    static int dasom;
    static PriorityQueue<Integer> vote = new PriorityQueue<>(Collections.reverseOrder());

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());    // 후보의 수
        dasom = Integer.parseInt(br.readLine());    // 다솜이의 추종자 수

        for(int i=0; i<N-1; i++) {
            vote.add(Integer.parseInt(br.readLine()));  // 다른 후보들 찍는 수(내림차순 우선순위 큐)
        }
    }

    /* ----- 구현 ----- */
    static void solve() {
        int giveMoney = 0;
        while(!vote.isEmpty() && dasom <= vote.peek()) {    // 다솜 표 수가 우선순위 큐 맨 위보다 높을 때까지 반복
            int maesu = vote.poll();    // 가장 강력한 후보 표에서 1명 매수
            vote.add(maesu-1);
            dasom++;
            giveMoney++;
        }

        sb.append(giveMoney);
    }

    /* ----- 출력 ----- */
    static void output() throws IOException {
        System.out.println(sb);
        br.close();
    }
}