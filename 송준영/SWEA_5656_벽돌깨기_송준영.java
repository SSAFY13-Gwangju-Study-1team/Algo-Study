import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5656_벽돌깨기_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T;               // 테케 수
    static int N, W, H;         // 공 수, 맵 크기
    static int[][] map, tempMap;// 맵, 임시 맵
    static int result;          // 결과값

    // 방향 벡터
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        // 테케 수 입력 및 반복
        T = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            solve();
            sb.append(String.format("#%d %d\n", t, result));
        }

        // 결과 출력
        System.out.println(sb);
    }

    /**
     * 메인 메서드
     * @throws Exception
     */
    public static void solve() throws Exception {
        // 입력 처리 및 초기화
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        W = parseInt(st.nextToken());
        H = parseInt(st.nextToken());

        map = new int[H][W];
        tempMap = new int[H][W];
        result = Integer.MAX_VALUE; // 최대값으로 초기화
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = parseInt(st.nextToken());
            }
        }

        // 공 떨어트리는 모든 경우를 찾아서 시뮬레이션
        ballComb(0, new int[N]);    // 0 깊이부터시작, 공 떨어트리는 위치를 저장할 배열(크기 N)
    }

    /**
     * 공 떨어트리는 모든 경우 찾아서 시뮬레이션 하는 메서드
     * @param depth 현재 깊이
     * @param list  공 떨어트리는 위치를 저장할 배열
     */
    public static void ballComb(int depth, int[] list) {
        if (result == 0) {  // 더 이상 줄일 수 없으므로 종료
            return;
        } else if (depth == N) {    // 떨어 트릴 공의 경우를 찾은 경우

            // 임시 맵에 원본 맵을 복사
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    tempMap[i][j] = map[i][j];
                }
            }

            /* 더 빨리 복사할 수 있는 방법이라고 함 (깊은 복사) */
            // for (int i = 0; i < H; i++) {
            //     System.arraycopy(map[i], 0, tempMap[i], 0, W);
            // }
            
            // 공 떨어트리는 위치에 맞춰서 블럭 부수기
            // sx = 세로, sy = 가로
            for (int sy : list) {
                for (int sx = 0; sx < H; sx++) {
                    if (tempMap[sx][sy] != 0) {     // 블럭이 있는 경우
                        breaking(tempMap, sx, sy);  // 블럭 부수기
                        gravity(tempMap);           // 중력 작용 적용
                        break;                      // 블럭 부수고 나면 더 이상 탐색할 필요 없음
                    }
                }
            }

            int cnt = count(tempMap);         // 남은 블럭 수 세기
            result = Math.min(result, cnt);   // 남은 블럭 수와 최소값 비교
            return;
        }

        // 공 떨어트리는 위치를 조합하는 부분
        for (int i = 0; i < W; i++) {
            list[depth] = i;            // 현재 깊이(공 떨어트리는 위치)에 i를 넣음
            ballComb(depth+1, list);    // 다음 깊이로 이동
        }
    }

    /**
     * 중력 작용 적용 메서드
     * @param map   
     */
    public static void gravity(int[][] map) {
        for (int w = 0; w < W; w++) {   // 가로 방향
            int idx = H - 1;

            // 아래에서 위로 탐색하면서 블럭을 아래로 내림
            for (int h = H - 1; h >= 0; h--) {
                if (map[h][w] != 0) {
                    map[idx--][w] = map[h][w];
                }
            }

            // 남은 공간을 0으로 채움
            for (int h = idx; h >= 0; h--) {
                map[h][w] = 0;
            }
        }
    }
    
    /**
     * 블럭 부수기 메서드 (BFS 이용)
     * @param map
     * @param x 처음 부수는 행
     * @param y 처음 부수는 열
     */
    public static void breaking(int[][] map, int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();    // BFS를 위한 큐

        q.offer(new int[] { x, y, map[x][y] });
        map[x][y] = 0;  // 부수는 블럭은 0으로 바꿈

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; i++) {
                for (int j = 1; j < cur[2]; j++) {  // 블럭의 가중치만큼 탐색
                    int nx = cur[0] + dx[i] * j, ny = cur[1] + dy[i] * j;

                    if (!isIn(nx, ny) || map[nx][ny] == 0) continue;    // 범위 밖이거나 부수는 블럭이 없으면 무시

                    if (map[nx][ny] > 1) {  // 블럭의 가중치가 1보다 큰 경우 queue에 넣음
                        q.offer(new int[] { nx, ny, map[nx][ny] });
                    }
                    map[nx][ny] = 0;    // 부수는 블럭은 0으로 바꿈
                }
            }
        }
    }

    /**
     * 블럭 수 세기 메서드
     * @param map
     * @return  남은 블럭 수
     */
    public static int count(int[][] map) {
        int cnt = 0;

        // 아래 -> 위 탐색
        for (int i = 0; i < W; i++) {
            for (int j = H-1; j >= 0; j--) {
                if (map[j][i] == 0) break;  // 블럭이 없으면 더 이상 탐색할 필요 없음
                cnt++;
            }
        }

        return cnt; // 남은 블럭 수 리턴
    }

    /**
     * 범위 체크 메서드
     * @param x 행  
     * @param y 열
     * @return  범위 안이면 true, 아니면 false
     */
    public static boolean isIn(int x, int y) {
        return x >= 0 && x < H && y >= 0 && y < W;
    }

    /**
     * 디버깅을 위한 print 메서드
     * @param map
     */
    // public static void printMap(int[][] map) {
    //     System.out.println("====맵출력====");
    //     for (int i = 0; i < H; i++) {
    //         for (int j = 0; j < W; j++) {
    //             System.out.print(map[i][j] + " ");
    //         }
    //         System.out.println();
    //     }
    // }
}
