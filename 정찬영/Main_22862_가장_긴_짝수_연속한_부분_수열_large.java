/* 메모리 91212kb, 시간 432ms
 * 문제 풀이 아이디어: 처음에는 홀수로만 이루어진 조합을 모두 구한 뒤
 * 수열에서 이를 제외하고 짝수로 이루어진 수열의 길이를 구하려 하였음
 * -> 시간초과, 다른 방법으로 특정 부분수열에서 K개의 홀수를 가진 부분 수열들만 찾아서
 * 해당 부분 수열 길이 - 홀수 갯수로 수열의 길이를 찾으면 되는 것을 알았음
 * 체감 난이도: 8/10
 */

import java.io.*;
import java.util.*;

public class Main_22862_가장_긴_짝수_연속한_부분_수열_large {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    /* ----- 입력 ----- */
    static int N, K;
    static int[] S;
    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 수열 S의 길이 N
        K = Integer.parseInt(st.nextToken());   // 삭제 최대 횟수 K

        st = new StringTokenizer(br.readLine());
        S = new int[N]; // 수열 S
        for(int i=0; i<N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }
    }

    /* ----- 구현 ----- */
    static void solve() {
        int p1=0;   // 왼쪽 포인터
        int p2;     // 오른쪽 포인터
        int odd = 0;    // 포인터 구간 사이 홀수 갯수
        int maxLen = 0; // 구간의 최대 길이

        for(p2=0; p2<N; p2++) {     // 우측 포인터 1씩 이동
            if(S[p2]%2 != 0) {      // 만약 우측 포인터의 위치가 홀수면 +1
                odd++;
            }

            if(odd == K) {  // 홀수 갯수가 K개면
                maxLen = Math.max(maxLen, p2-p1+1-odd); // 구간의 길이 비교/갱신
            } else if(odd > K) {    // 홀수 갯수가 K개보다 많으면
                if(S[p1]%2 != 0) {  // 좌측 포인터 1씩 증가하며 홀수일 때 -1
                    odd--;
                }
                p1++;
            }
        }
        p2--;   // 마지막에 p2가 N이 되었으므로 -1하고
        maxLen = Math.max(maxLen, p2-p1+1-odd); // 최장길이 한번 더 검사
        sb.append(maxLen);
    }

    /* ----- 출력 ----- */
    static void output() throws IOException {
        System.out.println(sb);
        br.close();
    }
}
