import java.io.*;
import java.util.*;

public class Main_13251_조약돌_꺼내기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String args[]) throws IOException {
        int M = Integer.parseInt(br.readLine()); // 조약돌의 색깔 수

        int N = 0;  // 조약돌의 총 개수
        int[] color = new int[M];   // 각 색상의 조약돌 수
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            color[i] = Integer.parseInt(st.nextToken());
            N += color[i];
        }

        int K = Integer.parseInt(br.readLine()); // 조약돌을 뽑는 수

        if(K > N) {         // 반례 1) 뽑는 수가 조약돌 수보다 많으면 확률은 0
            System.out.println(0);
            return;
        }

        double nCk = combination(N, K);         // 색깔 상관 없이(전체 조약돌) K개를 뽑는 경우의 수
        double colorCk = 0;   // 색깔별로 K개를 뽑을 수 있는 경우의 수의 합

        for(int m=0; m<M; m++) {
            if(color[m] >= K) { // 현재 색깔의 조약돌 갯수가 뽑는 수보다 많아야 함
                colorCk += combination(color[m], K);    // 현재 색깔의 조약돌 중 K개를 뽑는 경우의 수
            }
        }
        if(colorCk == 0)
            System.out.println(0);  // 반례 2) 색상 중 하나라도 K개의 조약돌을 갖지 않는 경우 0%
        else
            System.out.println(colorCk / nCk);
    }

    public static double combination(int n, int r) {   // 서로 다른 n개 중 r개를 뽑는 경우의 수
        if(r > n) return 0.0;
        double result = 1.0;
        // 팩토리얼 함수를 만들었었으나 오버플로우가 일어나서 계속 틀림
        // 팩토리얼을 나누는 방식으로 오버플로우를 피함
        for (int i = 0; i < r; i++) {
            result *= (n - i);
            result /= (i + 1);
        }
        return result;
    }

    /*
     * 조합 nCr = n! / (n-r)!r!
     * Ex) 50C25 = (50 * 49 * 48 * ... * 1) / (25 * 24 * 23 * ... * 1)(25 * 24 * ... * 1)
     * = (50 * 49 * 48 * ... * 26) / (25 * 24 * 23 * ... * 1)   // 계산 대폭 줄음!
     * 즉 반복문이 돌 때마다 아래 수를 하나씩 곱하고 나눔
     * result *= 50, 49, ... , 26
     * result /= 1, 2, ... , 25
     * = 한꺼번에 큰 수를 다루지 않으므로 오버플로우 방지
     */
}
