/* 메모리 19260kb, 시간 200ms
 * 문제 풀이 아이디어: 근손실 정도를 정렬하고 양끝에서 더하면 가장 작은 것과 큰 것이 더해지면서 평균적으로 작아진다.
 * 체감 난이도: 3/10
 */

import java.io.*;
import java.util.*;

public class Main_20300_서강근육맨_정찬영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    /* ----- 입력 ----- */
    static int N;
    static long[] t;
    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());    // 운동기구 개수

        t = new long[N]; // 각 운동기구의 근손실 정도
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            t[i] = Long.parseLong(st.nextToken());
        }
    }

    /* ----- 구현 ----- */
    static void solve() {
        Arrays.sort(t); // 근손실 정도를 정렬

        long M = Integer.MIN_VALUE;   // 답
        long sum;

        if(N%2 == 0) {  // 짝수 개일 때
            for(int i=0; i<N/2; i++) {  // 양끝에서 확인하므로 절반까지만 반복
                sum = t[i] + t[N-1-i];  // 양끝 대칭되는 index 하나씩 가져와서 더함
                M = Math.max(M, sum);   // 갱신
            }
        } else {    // 홀수 개일 때
            for(int i=0; i<N/2; i++) {
                sum = t[i] + t[N-2-i];  // 홀수개이므로 가장 큰 것 하나 빼고 양끝 대칭되는 index 하나씩 가져와서 더함
                M = Math.max(M, sum);   // 갱신
            }

            M = Math.max(M, t[N-1]);    // 마지막 것과 비교해서 최종 갱신
        }

        sb.append(M);
    }

    /* ----- 출력 ----- */
    static void output() throws IOException {
        System.out.println(sb);
        br.close();
    }
}