import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main_14235_크리스마스_선물_임규리 {

    static int n;
    static int a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            String str = br.readLine();

            if (str.equals("0")) {
                if (pq.isEmpty()) {
                    sb.append("-1");
                    sb.append(System.lineSeparator());
                } else {
                    sb.append(pq.poll());
                    sb.append(System.lineSeparator());
                }
            } else {
                StringTokenizer st = new StringTokenizer(str);
                a = parseInt(st.nextToken());
                for (int j = 0; j < a; j++) {
                    pq.add(parseInt(st.nextToken()));
                }
            }
        }

        System.out.println(sb);
    }
}
