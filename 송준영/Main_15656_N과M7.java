import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

public class Main_15656_N과M7 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        backtrack(0, 0, new StringBuilder());

        System.out.println(sb);

    }
    
    public static void backtrack(int depth, int check, StringBuilder temp) {
        if (check == M) {
            sb.append(temp).append("\n");
            return;
        } else if (depth == N) {
            return;
        }

        int len = temp.length();

        for (int i = 0; i < N; i++) {
            temp.append(arr[i]).append(" ");
            backtrack(depth + 1, check + 1, temp);
            temp.setLength(len);
        }
    }
}