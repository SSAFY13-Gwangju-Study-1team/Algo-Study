/* 메모리 73712kb, 시간 388ms
 * 문제해결 아이디어: 슬라이딩 윈도우와 투포인터를 사용한다.
 * 포인터2가 증가하며 라이언의 갯수를 센다.
 * 그 후 포인터1이 증가하며 라이언의 갯수가 K개 이상이면 집합의 크기를 갱신한다.
 * 포인터1은 증가할 때마다 이전 위치가 라이언이었다면 라이언의 갯수를 -1한다.
 * 체감 난이도: 6/10
 */

import java.io.*;
import java.util.StringTokenizer;

public class Main_15565_귀여운_라이언 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        /* ----- 입력 ----- */
        st = new StringTokenizer(br.readLine());
        // 1 = 라이언, 2 = 어피치
        int N = Integer.parseInt(st.nextToken());    // 인형의 총 개수
        int K = Integer.parseInt(st.nextToken());    // 라이언 인형의 연속된 갯수
        int lion = 0;   // -1 출력 확인용 라이언 개수

        int[] dolls = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            dolls[i] = Integer.parseInt(st.nextToken());
            if(dolls[i] == 1) lion++;
        }

        // 집합이 없으면 -1 출력 후 종료
        if(lion < K) {
            sb.append(-1);
            bw.append(sb);
            bw.flush();
            bw.close();
            br.close();
            return;
        }

        /* ----- 구현 ----- */
        int pointer1 = 0;
        int pointer2 = 0;
        int ans = Integer.MAX_VALUE;    // 라이언 인형이 K개 이상 있는 가장 연속된 인형들의 집합의 크기
        lion = 0;   // 현재 집합 내 라이언 개수

        while (pointer2 < N && pointer1 < N) {
            if (dolls[pointer2] == 1) lion++;  // 라이언 개수 증가

            // 라이언 개수가 K 이상이면 최소 길이 갱신
            while (lion >= K) {
                ans = Math.min(ans, pointer2 - pointer1 + 1);
                if (dolls[pointer1] == 1) lion--;  // pointer1이 줄어들면 라이언 개수도 반영
                pointer1++;
            }

            pointer2++;  // pointer2 증가
        }

        sb.append(ans);
        /* -----출력/후처리----- */
        bw.append(sb);
        bw.flush();
        bw.close();
        br.close();
    }	// End of Main
}
