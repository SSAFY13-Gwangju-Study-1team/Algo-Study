// 체감 난이도 9/10
// 구현 자체는 쉬웠으나, 알고리즘을 알아내는데 매우 오랜 시간이 걸렸음...

import java.util.*;
import java.io.*;

public class Main_10158_개미 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());   // 격자 가로 길이
        int h = Integer.parseInt(st.nextToken());   // 격자 세로 길이

        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());   // 초기 위치 x
        int q = Integer.parseInt(st.nextToken());   // 초기 위치 y

        int t = Integer.parseInt(br.readLine());    // 움직이는 시간

        /*  구현 과정
         * 개미의 이동 경로를 x축과 y축을 각각 보면 좌우/상하로만 왕복하는 것을 알 수 있음
         * 왕복 길이 = 2w / 2h
         * 초기위치와 최종위치의 상대적 거리 dx, dy = 시간 % 2w, 2h -> 왕복하고 남은 시간만큼 이동하기 때문
         * 초기 위치부터 격자 끝까지의 거리 a, b = w-p, h-q
         * dx, dy가 a,b보다 짧을 경우 초기위치에서 +     => 벽에 부딪히기 전으로 처음 방향대로 가고 있기 때문
         * 길 경우 w - (dx-a), h - (dy-b)            => 벽에 부딪히고 거꾸로 돌아오고 있기 때문
         */

        int dx = t % (2*w);     // 상대거리 x
        int dy = t % (2*h);     // 상대거리 y

        int a = w-p;    // 초기 위치 x부터 격자 x축 끝까지의 거리
        int b = h-q;    // 초기 위치 y부터 격자 y축 끝까지의 거리

        int ansX, ansY;
        if(dx < a)
            ansX = p+dx;
        else
            ansX = Math.abs(w-(dx-a));

        if(dy < b)
            ansY = q+dy;
        else
            ansY = Math.abs(h-(dy-b));

        System.out.print(ansX + " " + ansY);

    }
}