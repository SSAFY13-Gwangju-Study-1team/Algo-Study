/* 메모리 61048kb, 시간 876ms
 * 문제 풀이 아이디어: 우선순위 큐를 여러 개 사용한다.
 * 체감 난이도: 8/10
 */

import java.io.*;
import java.util.*;

public class Main_12764_싸지방에_간_준하_정찬영 {
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
    static List<int[]> times = new ArrayList<>(); // (start, end) 저장 리스트

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine()); // 사람 수 입력

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            times.add(new int[]{start, end});
        }

        // 시작 시간 기준 정렬
        times.sort(Comparator.comparingInt(o -> o[0]));
    }

    /* ----- 구현 ----- */
    static void solve() {
        // 사용 가능한 자리 번호 (작은 번호부터 배정)
        PriorityQueue<Integer> canUse = new PriorityQueue<>();

        // 사용 중인 자리 우선순위 큐에 저장 (종료 시간, 자리 번호)
        PriorityQueue<int[]> using = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));

        List<Integer> useCount = new ArrayList<>(); // 자리별 사용 횟수 저장
        int X = 0; // 사용된 최대 자리 개수

        // 대기 중인 사람들 조회
        for (int[] time : times) {
            int start = time[0];
            int end = time[1];

            // 종료 시간이 지난 자리 반환
            while (!using.isEmpty() && using.peek()[0] <= start) {
                canUse.add(using.poll()[1]); // 자리 번호 반환
            }

            int seat;   // 현재 사람이 앉을 자리 번호
            if (!canUse.isEmpty()) {    // 사용 가능한 자리가 있으면
                seat = canUse.poll();   // 가장 작은 번호 자리 사용
            }
            else {                      // 사용 가능한 자리가 없으면
                seat = X++;             // 새로운 자리 배정
                useCount.add(0);        // 새 자리 사용 횟수 배열 크기 증가
            }

            // 자리 사용 횟수 증가
            useCount.set(seat, useCount.get(seat) + 1);
            using.add(new int[]{end, seat}); // 종료 시간, 자리 번호 추가
        }

        // 출력
        sb.append(X).append("\n");
        for (int i = 0; i < X; i++) {
            sb.append(useCount.get(i)).append(" ");
        }
    }

    /* ----- 출력 ----- */
    static void output() throws IOException {
        System.out.println(sb);
        br.close();
    }
}
