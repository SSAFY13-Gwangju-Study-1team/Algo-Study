/* 메모리 14340kb, 시간 108ms
 * 문제 풀이 아이디어: 단순 구현, 덱을 이용 -> 중간 인덱스 접근 불가로 링크드리스트로 변경
 * 체감 난이도: 7/10
 */

import java.io.*;
import java.util.*;

public class Main_14891_톱니바퀴_정찬영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    /* ----- 입력 ----- */
    static LinkedList<Character>[] wheel = new LinkedList[4];
    static class rotate {
        int no;     // 회전시키는 톱니바퀴 번호
        int dir;    // 방향 1 시계, -1 반시계
        rotate (int no, int dir) {
            this.no = no;
            this.dir = dir;
        }
    }
    static int K;
    static rotate[] rotates;
    static void input() throws IOException {
        for(int i=0; i<4; i++) {
            wheel[i] = new LinkedList<>();
            for(char c : br.readLine().toCharArray()) { // 0: N, 1: S
                wheel[i].add(c);
            }
        }

        K = Integer.parseInt(br.readLine());    // 회전 횟수
        rotates = new rotate[K];   // 회전 순서 및 방향 저장
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int no = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            rotates[i] = new rotate(no-1, dir);
        }
    }

    /* ----- 구현 ----- */
    static int[] dirs = new int[4];
    static void solve() {
        for(rotate r : rotates) {
            Arrays.fill(dirs, 0);
            dirs[r.no] = r.dir;

            // 왼쪽 탐색
            for(int i = r.no-1; i >= 0; i--) {
                if(wheel[i].get(2) != wheel[i+1].get(6)) {
                    dirs[i] = -dirs[i+1];
                }
            }

            // 오른쪽 탐색
            for(int i = r.no+1; i < 4; i++) {
                if(wheel[i].get(6) != wheel[i-1].get(2)) {
                    dirs[i] = -dirs[i-1];
                }
            }

            // 모든 톱니바퀴 동시에 회전
            for(int i=0; i<4; i++) {
                if(dirs[i] != 0) {
                    rotate_result(i, dirs[i]);
                }
            }
        }

        int sum = 0;
        for(int i=0; i<4; i++) {
            if(wheel[i].getFirst() == '1')
                sum += (int)Math.pow(2, i);
        }
        sb.append(sum);
    }

    static void rotate_result(int no, int dir) {
        switch(dir) {
            case 1:     // 시계 방향 -> 우측으로 숫자 밀기
                wheel[no].addFirst(wheel[no].removeLast());
                break;
            case -1:     // 반시계 방향 -> 왼쪽으로 숫자 밀기
                wheel[no].addLast(wheel[no].removeFirst());
                break;
        }
    }

    /* ----- 출력 ----- */
    static void output() throws IOException {
        System.out.println(sb);
        br.close();
    }
}