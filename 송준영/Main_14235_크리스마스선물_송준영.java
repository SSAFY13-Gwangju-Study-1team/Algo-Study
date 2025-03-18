import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_14235_크리스마스선물_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n;

    public static void main(String[] args) throws Exception {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        n = parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int temp = parseInt(st.nextToken());
            if (temp == 0) {
                if (!pq.isEmpty()) {
                    sb.append(pq.poll()).append('\n');
                } else {
                    sb.append(-1).append('\n');
                }
            } else {
                for (int j = 0; j < temp; j++) {
                    int input = parseInt(st.nextToken());
                    pq.offer(input);
                }
            }
        }

        System.out.println(sb);
    }
}
