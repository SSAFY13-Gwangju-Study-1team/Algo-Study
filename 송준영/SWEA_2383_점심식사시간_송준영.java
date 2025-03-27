import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * SWEA_2383_점심식사시간_송준영
 * 난이도 7/10
 * 구현, 시뮬레이션, queue?
 * 179ms 35mb
 * 
 * 
 */
public class SWEA_2383_점심식사시간_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T, N, humansSize, result;    // 테케 수, 맵 크기, 사람 수, 결과
    static int[] stair1, stair2;            // 계단 1, 2
    static List<int[]> humans;              // 사람들의 좌표
    static List<Integer> subsets;           // 가능한 부분집합

    public static void main(String[] args) throws Exception {
        // 테케 수 입력
        T = parseInt(br.readLine());

        // 테케 수 만큼 반복
        for (int t = 1; t <= T; t++) {
            solve();
            sb.append(String.format("#%d %d\n", t, result));
        }

        // 출력
        System.out.println(sb);
    }

    /**
     * 메인 메서드
     * @throws Exception
     */
    public static void solve() throws Exception {
        // 입력 처리 및 초기화
        N = parseInt(br.readLine());
        humans = new ArrayList<>();
        subsets = new ArrayList<>();
        result = Integer.MAX_VALUE;
        stair1 = null;
        stair2 = null;

        // 입력 처리
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int temp = parseInt(st.nextToken());
                if (temp != 0 && temp != 1) {
                    if (stair1 == null) {
                        stair1 = new int[] { i, j, temp };  // 계단 1 (x, y, 계단 길이)
                    } else {
                        stair2 = new int[] { i, j, temp };  // 계단 2 (x, y, 계단 길이)
                    }
                } else if (temp == 1) {
                    humans.add(new int[] { i, j });
                }
            }
        }
        humansSize = humans.size(); // 사람 수 저장

        subset(0, 0); // 부분집합 생성
        // System.out.println("subsets : " + subsets);

        // 부분집합을 돌면서 계단에 도착하는 시간을 계산
        // 계단에 도착하는 시간을 계산하고, 계단에 도착해서 계단에 들어가는 시간이 가장 큰 값이 결과
        for (int order : subsets) {
            List<int[]> stairReach = new ArrayList<>(); // 계단에 도착해서 나오는 시간을 저장
            int tempResult = 0; // 가장 마지막에 나오는 사람의 시간

            // 사람들의 계단 도착 시간 계산, 계단 1, 2 중 어느 계단을 선택할지 결정
            for (int i = 0; i < humansSize; i++) {
                // 순서가 0이면 계단 1, 1이면 계단 2
                if ((order & (1 << i)) == 0) {  // 몇 번 사람, 계단 종류, 계단 까지 거리 + 1
                    stairReach.add(new int[] { i, 0, calDist(humans.get(i)[0], humans.get(i)[1], stair1[0], stair1[1]) + 1});   
                } else {                        // 몇 번 사람, 계단 종류, 계단 까지 거리 + 1
                    stairReach.add(new int[] { i, 1, calDist(humans.get(i)[0], humans.get(i)[1], stair2[0], stair2[1]) + 1});
                }
            }

            // 계단 도착 시간을 오름차순으로 정렬
            Collections.sort(stairReach, (o1, o2) -> {
                return o1[2] - o2[2];
            });

            Queue<Integer> q1 = new ArrayDeque<>(); // 계단 1에 도착한 사람들의 시간을 저장
            Queue<Integer> q2 = new ArrayDeque<>(); // 계단 2에 도착한 사람들의 시간을 저장

            // 계단에 도착한 사람들의 시간을 계산
            for (int i = 0; i < stairReach.size(); i++) {
                int[] temp = stairReach.get(i); 
                if (temp[1] == 0) { // 계단 1
                    if (q1.size() < 3) {    // 계단에 3명 이하일 때
                        q1.offer(temp[2] + stair1[2]);
                    } else {                // 계단에 3명 이상일 때

                        // 계단에 있는 사람들의 시간이 현재 사람의 도착 시간보다 작거나 같으면 계단을 내려가게 함
                        while (!q1.isEmpty() && q1.peek() <= (temp[2])) {
                            q1.poll();
                            // System.out.println("poll " + (polled + 1));
                        }
                        
                        // 계단에 3명 이하일 때
                        if (q1.size() < 3) {
                            q1.offer(temp[2] + stair1[2]);  // 계단에 도착 시간 + 내려가는 시간
                        } else {    // 계단에 3명 이상일 때
                            int polled = q1.poll();
                            q1.offer(polled + stair1[2]);   // 나온 사람의 시간 + 계단 내려가는 시간
                            // System.out.println("poll " + (polled + 1));
                        }
                    }
                } else {        // 계단 2
                    if (q2.size() < 3) {    // 계단에 3명 이하일 때
                        q2.offer(temp[2] + stair2[2]);
                    } else {                // 계단에 3명 이상일 때

                        // 계단에 있는 사람들의 시간이 현재 사람의 도착 시간보다 작거나 같으면 계단을 내려가게 함
                        while (!q2.isEmpty() && q2.peek() <= (temp[2])) {q2.poll();
                            // System.out.println("poll " + (polled + 1));
                        }

                        // 계단에 3명 이하일 때
                        if (q2.size() < 3) {
                            q2.offer(temp[2] + stair2[2]);  // 계단에 도착 시간 + 내려가는 시간
                        } else {    // 계단에 3명 이상일 때
                            int polled = q2.poll();
                            q2.offer(polled + stair2[2]);   // 나온 사람의 시간 + 계단 내려가는 시간
                            // System.out.println("poll " + (polled + 1));
                        }
                    }
                }
            }

            // 각 계단의 사람의 시간을 빼먄서 가장 큰 값을 결과로 저장
            while (!q1.isEmpty()) {
                int polled = q1.poll();
                tempResult = polled;
                // System.out.println("poll " + (polled + 1));
            }
            while (!q2.isEmpty()) {
                int polled = q2.poll();
                tempResult = Math.max(tempResult, polled);
                // System.out.println("poll " + (polled + 1));
            }

            // System.out.println("temp result " + tempResult);
            result = Math.min(result, tempResult);  // 결과 저장
        }
    }

    /**
     * 부분집합 생성
     * @param depth     현재 깊이
     * @param select    선택된 부분집합
     */
    public static void subset(int depth, int select) {
        if (depth == humansSize) {  // 사람 수만큼 깊이가 도달하면 부분집합 추가
            subsets.add(select);
            return;
        }

        subset(depth + 1, select);
        subset(depth + 1, select | (1 << depth));
    }

    /**
     * 거리 계산
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return  거리
     */
    public static int calDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }
}
