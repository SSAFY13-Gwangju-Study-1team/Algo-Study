/* 메모리 12865kb, 시간 128ms
 * 문제 풀이 아이디어: dp를 이용한다.
 * 이 때 dp는 무게별 가질 수 있는 가장 높은 가치를 저장한다.
 * 문제 자체는 dp로 접근하니 풀만 하다고 느껴졌지만 정방향이 아닌 역순으로 풀이해야 하는 점에서 시행착오가 있었음
 * 체감 난이도: 7/10
 */

import java.io.*;
import java.util.*;

public class Main_12865_평범한_배낭_정찬영 {
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
    static int[][] item;
    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 물품의 수 N
        K = Integer.parseInt(st.nextToken());   // 최대 무게 K

        item = new int[N][2];  // 물건 종류, 0 = 무게, 1 = 가치
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            item[i][0] = Integer.parseInt(st.nextToken());
            item[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    /* ----- 구현 ----- */
    static int[] dp;
    static void solve() {
        dp = new int[K+1];  // 무게(인덱스)별 가장 높은 가치

        Arrays.sort(item, (a,b) -> {
            if(a[0] == b[0])
                return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });

        for(int i=0; i<N; i++) {
            int weight = item[i][0], value = item[i][1];
            for(int j=K; j>=weight; j--) {
                dp[j] = Math.max(dp[j], dp[j-weight] + value);
            }
        }

//        처음에 틀린부분!!
//        dp를 정방향으로 갱신하면 같은 물건을 여러 번 사용할 수 있음 -> "무한 배낭 문제(Unbounded Knapsack)" 방식
//        0-1 배낭 문제에서는 각 아이템을 한 번씩만 사용해야 하므로 역순(i = K → weight)으로 갱신해야 함

//        for(int i=0; i<K+1; i++) {
//            for(int j=0; j<N; j++) {
//                int weight = item[j][0], value = item[j][1];
//                if(i+weight <= K) {
//                    dp[i+weight] = Math.max(dp[i+weight], dp[i]+value);
//                }
//            }
//        }

        sb.append(dp[K]);
    }

    /* ----- 출력 ----- */
    static void output() throws IOException {
        System.out.println(sb);
        br.close();
    }
}