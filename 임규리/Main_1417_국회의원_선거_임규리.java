import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

import static java.lang.Integer.parseInt;

public class Main_1417_국회의원_선거_임규리 {

    static int N;   // 후보의 수
    static int dasom;   // 다솜이 원래 투표 수
    static int count;   // 매수해야 하는 사람 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = parseInt(br.readLine());

        if (N == 1) {
            System.out.println(0);
            return;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < N; i++) {
            int cur = parseInt(br.readLine());
            if (i == 0) {
                dasom = cur;
            } else {
                pq.add(cur);
            }
        }

        while (pq.peek() >= dasom) {
            int temp = pq.poll();
            temp--;
            dasom++;
            count++;
            pq.add(temp);
        }

        System.out.println(count);
    }
}
