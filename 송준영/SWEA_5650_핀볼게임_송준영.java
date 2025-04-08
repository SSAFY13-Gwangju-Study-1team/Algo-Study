import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * SWEA_5650_핀볼게임
 * 난이도 7/10
 * 구현, 시뮬레이션
 * 501ms 51mb (다른 사람 평균 800~1500ms 정도) => 매우 좋은 최적화!
 * 
 * 핀볼이 끝나는 조건 -> 시작점으로 오거나, 블랙홀에 빠지거나
 * 근데 여기서 시작점으로 오는 조건이 있는데 공의 이동방향이 ((정반대))로 바뀌면 무조건 시작점으로 온다
 * -> 이걸 인지하고 벽에 부딪히거나 블럭에 부딪혀서 정반대가 되는 상황을 체크해서 시작점까지 가지 않고 (그 시점의 점수 * 2 + 1) 을 해주면 답이 된다
 * => 시간을 절반 줄일 수 있음!
 * 
 * 처음에 DP를 써야할까? 했지만 DP를 쓰기에는 효율도 안나오고 복잡해서 일단 완전탐색으로 해보기로 했다 -> 완탐해도 800만? 정도 나와서 할만하다고 생각
 * 
 * 웜홀 처리는 3차원 배열을 이용해서 [웜홀의 인덱스][서로 다른 웜홀 (0, 1)][웜홀의 좌표 (x, y)] 로 저장해 주었다
 * -> 웜홀은 6 ~ 10 이므로 6을 빼주어 인덱스로 접근하게끔 구성하였다.
 * 
 * 블럭에 부딪히는 로직은 규칙을 찾아서 해보려 했으나 가시성?이 떨어져서 그냥 switch문으로 하드코딩 하였음
 * 
 * 
 */
public class SWEA_5650_핀볼게임_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T;               // 테케 수

    static int N;               // 맵 크기
    static int[][] map;         // 맵
    static int[][][] wormHole;  // 웜홀 좌표 -> [웜홀의 인덱스][서로 다른 웜홀 (0, 1)][웜홀의 좌표 (x, y)]

    // 방향 벡터
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        // 테케 수 입력 및 반복
        T = parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            sb.append(String.format("#%d %d\n", t, solve()));
        }
        // 출력
        System.out.println(sb);
    }

    /**
     * 메인 메서드
     * @return
     * @throws Exception
     */
    public static int solve() throws Exception {
        // trim을 써야된다고 함
        // 입력 처리 및 초기화
        N = parseInt(br.readLine().trim());

        map = new int[N][N];
        wormHole = new int[5][2][2]; // 웜홀 좌표 -> [웜홀의 인덱스][서로 다른 웜홀 (0, 1)][웜홀의 좌표 (x, y)]
        int result = 0;              // 결과값

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                map[i][j] = parseInt(st.nextToken());
                if (map[i][j] > 5) {    // 웜홀 처리
                    if (wormHole[map[i][j] - 6][0][0] == 0 && wormHole[map[i][j] - 6][0][1] == 0) { // 웜홀이 첫번째 일 때. 0, 0이 들어와도 어차피 뒤에 0, 0 되서 상관 없음
                        wormHole[map[i][j] - 6][0][0] = i;
                        wormHole[map[i][j] - 6][0][1] = j;
                    } else {
                        wormHole[map[i][j] - 6][1][0] = i;
                        wormHole[map[i][j] - 6][1][1] = j;
                    }
                }
            }
        }


        // 맵을 돌면서 핀볼 게임 시작
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {               // 빈 공간일 때만 가능
                    for (int k = 0; k < 4; k++) {   // 4방향으로 핀볼 게임 시작
                        result = Math.max(result, pinBall(i, j, k));    // 더 큰 값을 result에 저장
                    }
                }
            }
        }

        return result;  // 결과값 리턴
    }

    /**
     * 핀볼 게임 메서드
     * @param sx    시작 x 좌표
     * @param sy    시작 y 좌표
     * @param dir   이동 방향 (0: 위, 1: 오른쪽, 2: 아래, 3: 왼쪽)
     * @return      점수
     */
    public static int pinBall(int sx, int sy, int dir) {
        int x = sx, y = sy; // 현재 좌표
        int score = 0;      // 점수

        // 종료 조건까지 계속 반복
        while (true) { 
            int nx = x + dx[dir], ny = y + dy[dir];     // 다음 좌표 지정
            if (!isIn(nx, ny)) return score * 2 + 1;    // 범위를 벗어나면 종료 (벽에 부딪힘) -> 종료

            int type = map[nx][ny];                     // 다음 좌표의 타입 지정

            if (type == -1) return score;               // 블랙홀에 빠지면 종료 (블랙홀에 부딪힘) -> 종료
            if (sx == nx && sy == ny) return score;     // 시작점으로 돌아오면 종료 (시작점에 부딪힘) -> 종료

            if (type == 0) {                    // 빈 공간일 때 
                x = nx;
                y = ny;
            } else if (type > 5) {              // 웜홀일 때
                type = type - 6;                // 웜홀의 인덱스는 6부터 시작하므로 6을 빼준다
                if (wormHole[type][0][0] == nx && wormHole[type][0][1] == ny) { // 일치하는 좌표에 다른 웜홀로 좌표 지정
                    x = wormHole[type][1][0];
                    y = wormHole[type][1][1];
                } else {
                    x = wormHole[type][0][0];
                    y = wormHole[type][0][1];
                }
            } else if (type > 0) {              // 블럭일 때
                int tempDir = touchBlock(dir, type);                    // 블럭에 부딪히면 방향 바뀜
                if (Math.abs(tempDir - dir) == 2) return score * 2 + 1; // 정반대 방향으로 바뀌면 시작점으로 돌아옴 (블럭에 부딪힘) -> 종료
                dir = tempDir;                  // 방향 변경
                score++;                        // 점수 증가
                x = nx;
                y = ny;
            }
        }
    }

    /**
     * 블럭에 부딪혔을때 바뀌는 방향을 반환하는 메서드
     * @param dir   현재 방향 (0: 위, 1: 오른쪽, 2: 아래, 3: 왼쪽)
     * @param block 블럭의 모양 (1 ~ 5)
     * @return      바뀐 방향 (0: 위, 1: 오른쪽, 2: 아래, 3: 왼쪽)
     */
    public static int touchBlock(int dir, int block) {
        if (block == 5) {           // 블럭이 5일 때는 방향을 180도 바꿔준다
            dir = (dir + 2) % 4;
        } else {
            switch (block) {        // 블럭에 따라 방향을 바꿔준다 (하드코딩)
                case 1:
                    if (dir == 0) dir = 2;      // switch문으로 변경가능하지만 가시성이 떨어질까봐 if ~ else 로 처리
                    else if (dir == 1) dir = 3;
                    else if (dir == 2) dir = 1;
                    else if (dir == 3) dir = 0;
                    break;
                case 2:
                    if (dir == 0) dir = 1;
                    else if (dir == 1) dir = 3;
                    else if (dir == 2) dir = 0;
                    else if (dir == 3) dir = 2;
                    break;
                case 3:
                    if (dir == 0) dir = 3;
                    else if (dir == 1) dir = 2;
                    else if (dir == 2) dir = 0;
                    else if (dir == 3) dir = 1;
                    break;
                case 4:
                    if (dir == 0) dir = 2;
                    else if (dir == 1) dir = 0;
                    else if (dir == 2) dir = 3;
                    else if (dir == 3) dir = 1;
                    break;
            }
        }

        return dir; // 바뀐 방향 리턴
    }
    
    /**
     * 범위 체크 메서드 (벽에 부딪히는지 체크)
     * @param x 
     * @param y
     * @return  범위 안이면 true, 아니면 false
     */
    public static boolean isIn(int x, int y) {
        return x >= 0&& x < N && y >= 0 && y < N;
    }
}
