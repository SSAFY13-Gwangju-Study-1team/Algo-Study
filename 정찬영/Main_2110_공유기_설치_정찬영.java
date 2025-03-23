/* 메모리 28596kb, 시간 256ms
 * 문제 풀이 아이디어: 로직을 못찾아서 인터넷 참고함 -> 최소거리를 설정하고 최소거리당 공유기를 몇 개 설치하게 되는지 확인
 * 체감 난이도: 10/10
 */

import java.io.*;
import java.util.*;

public class Main_2110_공유기_설치_정찬영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    /* ----- 입력 ----- */
    static int N, C;
    static int[] Nlist;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 집의 개수
        C = Integer.parseInt(st.nextToken());   // 공유기의 개수

        Nlist = new int[N]; // 집 위치 저장 배열
        for(int i=0; i<N; i++) {
            Nlist[i] = Integer.parseInt(br.readLine());
        }
    }

    /* ----- 구현 ----- */
    static void solve() {
        Arrays.sort(Nlist); // 이분탐색을 위해 정렬

        long start = 1;
        long end = Nlist[N-1] - Nlist[0];

        while(start <= end) {
            long sum = 1;    // 공유기를 설치한 집 수
            long mid = (start+end)/2;   // 공유기 설치 최소 거리

            long last = Nlist[0];   // 첫 번째 집에는 무조건 공유기를 설치한다고 가정

            // 직전에 공유기를 설치한 집으로부터 최소거리를 넘는 집에 공유기를 설치
            for(int i=1; i<N; i++) {
                if(Nlist[i] - last >= mid) {
                    sum++;
                    last = Nlist[i];
                }
            }

            // 공유기를 설치한 집 수가 공유기 개수보다 많으면 최소거리를 늘려야 함
            if(sum >= C) {
                start = mid+1;
            }

            // 공유기를 설치한 집 수가 공유기 개수보다 많으면 최소거리를 줄여야 함
            else if(sum < C) {
                end = mid-1;
            }
        }
        sb.append(start-1);
    }

    /* ----- 출력 ----- */
    static void output() throws IOException {
        System.out.println(sb);
        br.close();
    }
}