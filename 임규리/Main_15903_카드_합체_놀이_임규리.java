import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class Main_15903_카드_합체_놀이_임규리 {

    static int n;   // 카드 개수
    static int m;   // 합체 횟수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());

        PriorityQueue<Long> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            pq.add(parseLong(st.nextToken()));
        }

        for (int i = 0; i < m; i++) {
            long num1 = pq.poll();
            long num2 = pq.poll();

            pq.add(num1 + num2);
            pq.add(num1 + num2);
        }

        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += pq.poll();
        }

        System.out.println(sum);
    }
}
