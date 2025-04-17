import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * Solution_2382_미생물격리
 * 난이도 4/10
 * 구현, 시뮬레이션
 * 
 * 미생물 군집을 queue에 넣고, 이동하면서 겹치는 군집을 합쳐주는 방식으로 구현
 * 겹치는 로직을 구현하기 위해 우선 임시로 map에 저장하고, 그 후에 한 번에 queue에 넣어주는 방식으로 구현
 */
public class Solution_2382_미생물격리_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T;                   // 테케 수
    static int N, M, K;             // 맵 크기, 미생물 수
    static Queue<int[]> qCell;       // 미생물 군집을 담는 큐
    static Map<String, int[]> mapCell; // [x, y, cnt, dir, prime]

    // (상: 1, 하: 2, 좌: 3, 우: 4)
    static int[] dx = { 0, -1, 1, 0, 0 };
    static int[] dy = { 0, 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        // 테케 수 입력
        T = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append(String.format("#%d %d\n", t, solve()));
        }
        // 출력
        System.out.println(sb);
    }

    /**
     * 메인 로직 메서드
     * @return
     * @throws Exception
     */
    public static int solve() throws Exception {
        // 입력 처리 및 초기화
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        K = parseInt(st.nextToken());

        qCell = new ArrayDeque<>();
        mapCell = new HashMap<>();

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = parseInt(st.nextToken()), y = parseInt(st.nextToken()), cnt = parseInt(st.nextToken()), dir = parseInt(st.nextToken());
            qCell.offer(new int[] { x, y, cnt, dir });
        }

        // System.out.println("(((" + 0 + ")))");
        // printQueue();

        // 미생물 이동 처리 (M번)
        for (int i = 0; i < M; i++) {

            // 세포 그룹 이동 처리 후 임시 map에 저장
            while (!qCell.isEmpty()) {
                int[] temp = qCell.poll();
                int x = temp[0], y = temp[1], cnt = temp[2], dir = temp[3];
                int nx = x + dx[dir], ny = y + dy[dir];

                if (isTouch(nx, ny)) {  // 맵의 경계에 닿으면 처리
                    cnt /= 2;
                    if (dir % 2 == 0) dir--;
                    else dir++;
                }

                if (mapCell.containsKey(stringify(nx, ny))) {   // 이미 존재하는 좌표라면
                    int[] origin = mapCell.get(stringify(nx, ny));

                    // 대표값 비교해서 더 큰 값으로 갱신
                    if (origin[4] < cnt) {
                        mapCell.put(stringify(nx, ny), new int[] { nx, ny, cnt + origin[2], dir, cnt });
                    } else {
                        mapCell.put(stringify(nx, ny), new int[] { nx, ny, cnt + origin[2], origin[3], origin[4] });
                    }
                } else {                                        // 처음 방문하는 좌표라면
                    mapCell.putIfAbsent(stringify(nx, ny), new int[] { nx, ny, cnt, dir, cnt });
                }
            }

            // 큐에 있는 미생물 군집을 모두 mapCell에 저장했으니, 다시 큐에 넣어준다
            for (int[] c : mapCell.values()) {
                qCell.offer(new int[] { c[0], c[1], c[2], c[3] });
            }

            mapCell.clear();    // mapCell 초기화

            // System.out.println("(((" + (i+1) + ")))");
            // printQueue();
        }

        // 순회가 끝난 후 남아있는 미생물 군집을 모두 더해준다
        int result = 0;
        while (!qCell.isEmpty()) {
            result += qCell.poll()[2];
        }

        return result;  // 결과 리턴
    } 

    // 미생물이 맵의 경계에 닿았는지 확인하는 메서드
    public static boolean isTouch(int x, int y) {
        return (x == 0 || x == N -1 || y == 0 || y == N-1);
    }

    // 좌표를 문자열로 변환하는 메서드
    public static String stringify(int x, int y) {
        return String.format("%d %d", x, y);
    }

    // 큐에 있는 미생물 군집을 출력하는 메서드 (디버깅용)
    public static void printQueue() {
        for (int[] temp : qCell) {
            System.out.println(Arrays.toString(temp));
        }
    }
}
