import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

public class Main_10953_AB6_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static int T, A, B;

    public static void main(String[] args) throws Exception {
        T = parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String temp = br.readLine();
            st = new StringTokenizer(temp, ",");
            A = parseInt(st.nextToken());
            B = parseInt(st.nextToken());
            sb.append(A + B).append("\n");
        }

        System.out.println(sb);
    }
}
