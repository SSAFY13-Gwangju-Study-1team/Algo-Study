/* 메모리 35432kb, 시간 348ms
 * 문제 풀이 아이디어: 내림차순 우선순위큐를 사용한다.
 * 체감 난이도: 2/10
 */

import java.io.*;
import java.util.*;

public class Main_14235_크리스마스_선물_정찬영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        solve();
        output();
    }

    /* ----- 입력 및 구현 ----- */
    static int n;
    static PriorityQueue<Integer> gift = new PriorityQueue<>(Collections.reverseOrder());
    static void solve() throws IOException {
        n = Integer.parseInt(br.readLine());    // 아이들/거점지 방문 횟수 n
        
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int cur = Integer.parseInt(st.nextToken());

            // 아이들을 만난 경우
            if(cur == 0) {
                if(gift.isEmpty()) {    // 선물이 없는 경우 -1
                    sb.append(-1).append("\n");
                } else {
                    sb.append(gift.poll()).append("\n");
                }
            }
            // 선물 충전하는 경우
            else {
                for(int j=0; j<cur; j++) {
                    gift.add(Integer.parseInt(st.nextToken()));
                }
            }
        }
    }

    /* ----- 출력 ----- */
    static void output() throws IOException {
        System.out.println(sb);
        br.close();
    }
}