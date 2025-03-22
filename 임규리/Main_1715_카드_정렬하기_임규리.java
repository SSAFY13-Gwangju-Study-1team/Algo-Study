import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

import static java.lang.Integer.parseInt;

public class Main_1715_카드_정렬하기_임규리 {

    static int N;   // 카드 묶음 개수
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            pq.add(parseInt(br.readLine()));
        }

        while (pq.size() > 1) {
            int num1 = pq.poll();
            int num2 = pq.poll();

            result += num1 + num2;
            pq.add(num1 + num2);
        }

        System.out.println(result);
    }
}
