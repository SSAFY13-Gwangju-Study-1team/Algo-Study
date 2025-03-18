/* 메모리 242380kb, 시간 756ms
 * 문제 풀이 아이디어: 내림차순 우선순위큐로 전부 때려박고 n번째 출력
 * 체감 난이도: 1/10
 */

import java.io.*;
import java.util.*;

public class Main_2075_N번째_큰_수_정찬영 {
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
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                pq.add(Integer.parseInt(st.nextToken()));   // 내림차순 우선순위큐에 수 다넣음
            }
        }
    }

    /* ----- 구현 ----- */
    static void solve() {
        for(int i=1; i<N; i++) {
            pq.poll();  // N-1개까지 버림
        }
        sb.append(pq.poll());   // N번째
    }

    /* ----- 출력 ----- */
    static void output() throws IOException {
        System.out.println(sb);
        br.close();
    }
}