import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_11728_배열합치기_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static List<Integer> A, B;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new ArrayList<>();
        B = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(st.nextToken());
            A.add(temp);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int temp = Integer.parseInt(st.nextToken());
            B.add(temp);
        }

        A.addAll(B);
        Collections.sort(A);

        for (int i = 0; i < A.size(); i++) {
            sb.append(A.get(i)).append(" ");
        }

        System.out.println(sb);

    }
}
