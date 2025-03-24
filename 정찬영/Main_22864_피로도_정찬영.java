/* 메모리 14324kb, 시간 100ms
 * 문제 풀이 아이디어: 그리디를 사용한다.
 * 일단 피로도 한계치까지 일을 계속 시키다가, 일을 더 시키면 번아웃이 올 때마다 번아웃이 오기 전에 휴식을 준다.
 * 체감 난이도: 2/10
 */

import java.io.*;
import java.util.*;

public class Main_22864_피로도_정찬영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    /* ----- 입력 ----- */
    static int A, B, C, M;
    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());   // 일하고 쌓이는 피로도
        B = Integer.parseInt(st.nextToken());   // 일하고 처리하는 량
        C = Integer.parseInt(st.nextToken());   // 쉬고 내려가는 피로도
        M = Integer.parseInt(st.nextToken());   // 피로도 한계치
        
    }

    /* ----- 구현 ----- */
    static void solve() {
        int tired = 0;  // 현재 피로도
        int work = 0;   // 현재 일 진행량
        int time = 0;   // 현재 시간

        while(time < 24) {  // 하루는 24시간
            if(tired + A <= M) {    // 일하고 나서 번아웃이 오는지 확인해서 안오면
                tired += A; // 피로도 증가하고
                work += B;  // 일량 증가
            } else {        // 일하고 나서 번아웃이 오는 상황이면
                tired -= C; // 휴식을 시킴
                if(tired < 0)   // 피로도는 음수로 가지 않는다.
                    tired = 0;
            }
            time++;
        }

        sb.append(work);
    }

    /* ----- 출력 ----- */
    static void output() throws IOException {
        System.out.println(sb);
        br.close();
    }
}