/* 메모리 96232kb, 시간 592ms
 * 문제 풀이 아이디어: 가장 풍선을 짧게 부는 직원을 기준으로 최대값 설정 후 이분 탐색을 시작한다.
 * 체감 난이도: 5/10
 */

import java.io.*;
import java.util.*;

public class Main_15810_풍선_공장_정찬영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    /* ----- 입력 ----- */
    static int N, M;
    static long[] balloons;
    static long shortest = Integer.MAX_VALUE;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 스태프의 수
        M = Integer.parseInt(st.nextToken());   // 불어야 하는 풍선의 개수

        balloons = new long[N];  // 스태프 별 풍선 부는 시간

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            balloons[i] = Long.parseLong(st.nextToken());
            shortest = Math.min(shortest, balloons[i]); // 가장 풍선을 짧게 부는 시간
        }
    }

    /* ----- 구현 ----- */
    static void solve() {
        long ans = Long.MAX_VALUE;
        long start = 0;
        long end = shortest * M;    // 가장 짧게 부는 시간 * M이 최대로 오래걸리는 시간

        // 탐색 시작
        while(start <= end) {
            long sum = 0;
            long mid = (start+end)/2;   // 현재 테스트 해보는 시간

            // 설정된 시간동안 불 수 있는 풍선 총량 계산
            for(int i=0; i<N; i++) {
                sum += mid/balloons[i];
            }

            if(sum >= M) {  // 총량이 목표치보다 많으면
                ans = Math.min(ans, mid);   // 갱신
                end = mid-1;    // 시간을 줄이기 위해 end-1
            } else {
                start = mid+1;  // 총량이 목표치보다 적으면 시간을 늘이기 위해 start+1
            }
        }
        sb.append(ans);
    }

    /* ----- 출력 ----- */
    static void output() throws IOException {
        System.out.println(sb);
        br.close();
    }
}