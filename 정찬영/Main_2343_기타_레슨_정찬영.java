/* 메모리 24312kb, 시간 292ms
 * 문제 풀이 아이디어: 매개 변수 탐색을 이용한다.
 * 강의 수 10만개, 강의 최대 길이 1만분으로 최대 10억이기 때문에 int 사용가능
 * 맞는 로직을 찾지 못해서 인터넷에 검색 후 로직을 참고하였음... -> 매개변수, 이분 탐색 복습 필요
 * 체감 난이도: 10/10
 */

import java.io.*;
import java.util.*;

public class Main_2343_기타_레슨_정찬영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    /* ----- 입력 ----- */
    static int N, M, start=0, end=0;
    static int[] video;
    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 강의의 수 N
        M = Integer.parseInt(st.nextToken());   // 만들어야 하는 블루레이 수 M
        video = new int[N];                     // 강의 길이 배열

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            video[i] = Integer.parseInt(st.nextToken());    // 강의 길이 저장
            start = Math.max(start, video[i]);  // 가장 작은 블루레이의 크기 (max인 이유는 가장 큰 강의 길이가 블루레이의 가장 작은 값)
            end += video[i];                    // 가장 큰 블루레이의 크기
        }
    }

    /* ----- 구현 ----- */
    static void solve() {
        int ans = 0;
        int mid = 0;
        while(start <= end) {
            mid = (start + end)/2;  // 중간값 = 현재 만들 블루레이의 최대 시간
            int bNum = 1;   // 생성된 블루레이 수 (최소 1개)
            int temp = 0;   // 블루레이 만들기 위한 임시 강의 총합 변수
            for(int i=N-1; i>=0; i--) {
                temp += video[i];

                if(temp > mid) {        // 임시 값이 블루레이 최대 시간을 넘겨버리면
                    bNum++;             // 블루레이 개수+1
                    temp = video[i];    // 넘겼으므로 현재 값으로 temp 초기화
                }
            }

            if(bNum > M) { // 블루레이 개수가 목표치 이상
                start = mid+1;
            }
            else {  // 블루레이 개수가 목표치 이하
                ans = mid;
                end = mid-1;
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