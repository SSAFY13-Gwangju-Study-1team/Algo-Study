import java.io.*;
import java.util.*;

public class Main_2961_도영이가_만든_맛있는_음식 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int[][] arrN;
    static boolean[] visited;
    static int best;    // 신맛과 쓴맛의 차이가 가장 작은 요리

    public static void main(String args[]) throws IOException {
        N = Integer.parseInt(br.readLine());
        arrN = new int[N][2];   // 재료를 저장하는 배열

        for(int n=0; n<N; n++) {
            st = new StringTokenizer(br.readLine());
            arrN[n][0] = Integer.parseInt(st.nextToken());  // 신맛
            arrN[n][1] = Integer.parseInt(st.nextToken());  // 쓴맛
        }

        visited = new boolean[N];
        best = Integer.MAX_VALUE;   // 가장 큰 값으로 시작(Math.min으로 계속 줄일 것이기 때문)
        // 신맛은 곱, 쓴맛은 합
        cook(1, 0);
        System.out.println(best);
    }

    public static void cook(int sour, int bitter) {
        int curSour = sour;         // 백트래킹을 위하여 현재 신맛 저장
        int curBitter = bitter;     // 현재 쓴맛 저장

        for(int n=0; n<N; n++) {
            if(!visited[n]) {           // 현재 재료를 사용하지 않았다면
                visited[n] = true;      // 현재 재료를 사용
                curSour *= arrN[n][0];      // 신맛은 재료들의 신맛의 곱
                curBitter += arrN[n][1];    // 쓴맛은 합
                best = Math.min(best, Math.abs(curSour-curBitter)); // 신맛과 쓴맛의 차 저장
                cook(curSour, curBitter);

                // 백트래킹
                curSour = sour;
                curBitter = bitter;
                visited[n] = false;
            }
        }
    }
}
