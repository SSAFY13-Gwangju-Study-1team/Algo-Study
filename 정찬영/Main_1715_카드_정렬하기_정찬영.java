/* 메모리 25568kb, 시간 296ms
 * 문제 풀이 아이디어: 우선순위 큐를 사용한다.
 * 체감 난이도: 1/10
 */

import java.io.*;
import java.util.*;

public class Main_1715_카드_정렬하기_정찬영 {
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
    static PriorityQueue<Integer> deck = new PriorityQueue<>();
    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());    // 카드 묶음의 수 N

        // 초기 카드 묶음 입력
        for(int i=0; i<N; i++) {
            deck.add(Integer.parseInt(br.readLine()));
        }
    }

    /* ----- 구현 ----- */
    static void solve() {
        int sum = 0;
        int deckA, deckB, deckC;
        while(deck.size() >= 2) {   // 카드 뭉치를 합치려면 2개 이상의 카드뭉치가 있어야 함
            deckA = deck.poll();
            deckB = deck.poll();

            deckC = deckA + deckB;
            sum += deckC;           // 합친 카드뭉치 수 추가 
            deck.add(deckC);        // 새로운 카드뭉치를 덱에 넣음
        }

        sb.append(sum);
    }

    /* ----- 출력 ----- */
    static void output() throws IOException {
        System.out.println(sb);
        br.close();
    }
}