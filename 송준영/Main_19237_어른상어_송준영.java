import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * Main_19237_어른상어
 * 난이도 7/10
 * 구현, 시뮬레이션
 * 236ms 22mb
 * 
 * 상어가 이동하는 방향을 우선순위에 따라 정해주고, 그 방향으로 이동하게 해준다.
 * 만약 이동할 수 있는 칸이 없다면, 자신의 냄새가 있는 칸으로 이동하게 해준다.
 * 
 * 이동 하는 순서를 파악하는게 중요하다
 * 냄새가 먼저 없어 지는지? 아니면 상어가 먼저 이동하려고 하는지 순서를 파악해야 함
 * 
 * 현재 시점 상어 위치를 기준으로 상어가 이동해야하니까 상어의 움직이는 위치를 따로 저장해두고 한 꺼번에 맵에 반영해야한다
 * - 이렇게 안해서 예제 2번에서 틀렸음
 * - 고치기 전 코드는 그 위치에 같은 시간에 온 상어가 있으면 서로 경쟁해서 없애도록 했음 -> 이런 경우는 애초에 못 가는 위치도 갈 수 있게 판단 해버림
 * 
 * 이 코드 같은 경우는 상어의 정보와 맵 정보를 따로 저장
 */
public class Main_19237_어른상어_송준영 {
    
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M, k, time;               // 맵 크기, 상어 수, 냄새 지속 시간, 현재 시간
    static int[][] inputMap;                // 상어가 있는 맵 (초기 상태 저장) -> 안 써도 되긴 함
    static int[][][] map;                   // [인덱스, 시간]
    static Map<Integer, int[]> sharks;      // 인덱스 : [x, y, 방향]
    static Map<Integer, int[][]> sharkDir;  // 인덱스 : [상어 현재 방향][상어 방향 우선 순위]
    static Queue<int[]> q = new ArrayDeque<>(); // 상어가 이동할 위치 저장 큐 [상어 인덱스, x, y, 방향]

    // 상어의 이동 방향을 나타내는 벡터 ((인덱스)1 : 상, 2 :  하,  3 : 좌, 4 : 우)
    static int[] dx = { 0, -1, 1, 0, 0 };
    static int[] dy = { 0, 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        // 입력 처리 및 초기화
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        k = parseInt(st.nextToken());

        inputMap = new int[N][N];
        map = new int[N][N][2]; 
        sharks = new HashMap<>();
        sharkDir = new HashMap<>();
        time = 0;   // 시간은 0초 부터 시작

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                inputMap[i][j] = parseInt(st.nextToken());
                if (inputMap[i][j] != 0) {
                    sharks.put(inputMap[i][j], new int[] { i, j, 0});   // 상어 인덱스 저장: [x, y, 방향]
                    // 맵에 갱신
                    map[i][j][0] = inputMap[i][j];
                    map[i][j][1] = time;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            sharks.get(i)[2] = parseInt(st.nextToken());    // 상어 방향 sharks에 저장
        }

        for (int i = 1; i <= M; i++) {  // 상어 선택
            sharkDir.put(i, new int[5][4]);
            for (int j = 1; j <= 4; j++) {  // 상어 방향 선택 1 ~ 4
                st = new StringTokenizer(br.readLine());
                int[] temp = new int[4];
                for (int d = 0; d < 4; d++) {
                    temp[d] = parseInt(st.nextToken());
                }
                sharkDir.get(i)[j] = temp;  // i 상어의 j 방향에 우선순위 삽입
            }
        }

        printMap();   // 디버깅용
        printShark(); // 디버깅용
        System.out.println("time : " + time); // 디버깅용
        System.out.println("====================="); // 디버깅용

        // 1000초 동안 반복
        // 상어가 1마리 남을 때까지 반복
        // 만약 1000초가 지나도 상어가 1마리 남지 않으면 -1 출력
        for (int i = 0; i <= 1000; i++) {
            int count = sharks.size();
            if (count == 1) {   // 상어가 1마리 남으면 종료
                System.out.println(time);
                return;
            }
            
            // 복사
            // 그대로 참조하면 원래 키들이 변경되서 에러남;;
            List<Integer> keys = new ArrayList<>();
            for (int shark : sharks.keySet()) {
                keys.add(shark);
            }

            // 상어 다 이동
            for (int shark : keys) {
                move(shark);
            }

            // 이동한 상어들 한번에 처리
            while (!q.isEmpty()) {
                int temp[] = q.poll();
                sharkUpdate(temp[0], temp[1], temp[2], temp[3]);    // 상어 업데이트
            }

            time++; // 시간 증가

            printMap();   // 디버깅용
            printShark(); // 디버깅용
            System.out.println("time : " + time); // 디버깅용
            System.out.println("====================="); // 디버깅용
        }

        // 1000초가 지나도 상어가 1마리 남지 않으면 -1 출력
        System.out.println(-1);

    }

    /**
     * 상어 이동 메서드
     * @param shark 상어 인덱스
     */
    public static void move(int shark) {
        int[] sharkInfo = sharks.get(shark);    // 상어 정보 [x, y, 방향]
        int[] dirOrder = sharkDir.get(shark)[sharkInfo[2]]; // sharkDir.get(shark)[상어 방향]

        // 빈칸 검사
        for (int d : dirOrder) {    // 상어 방향 우선순위 순서대로 탐색
            int nx = sharkInfo[0] + dx[d], ny = sharkInfo[1] + dy[d];   // 이동할 위치

            // 냄새 있던 칸 처리
            if(isIn(nx, ny) && map[nx][ny][1] + k <= time ) {   // 냄새가 없어진 칸
                q.offer(new int[] {shark, nx, ny, d});
                return;
            }

            // 아얘 빈칸 처리
            if(isIn(nx, ny) && map[nx][ny][0] == 0 && map[nx][ny][1] == 0) {    // 원래 빈칸
                q.offer(new int[] {shark, nx, ny, d});
                return;
            }

            // 위의 두 로직의 경우 합칠 수 있을 것 같은데 일단 보류...
        }

        // 만약 빈칸이 없으면 자기 냄새로
        for (int d : dirOrder) {
            int nx = sharkInfo[0] + dx[d], ny = sharkInfo[1] + dy[d];

            if(isIn(nx, ny) && map[nx][ny][0] == shark ) {  // 자기 냄새가 있는 칸
                q.offer(new int[] {shark, nx, ny, d});
                return;
            }
        }
    }

    /**
     * 상어 업데이트 메서드
     * - 상어가 이동한 후에 냄새를 업데이트 해준다.
     * @param shark 상어 인덱스
     * @param x     상어 x좌표
     * @param y     상어 y좌표
     * @param d     상어 방향
     */
    public static void sharkUpdate( int shark, int x, int y, int d) {
        // 만약 상어가 이동한 위치에 다른 상어가 있다면???
        if (map[x][y][1] != 0 && map[x][y][1] == (time+1)) {
            if (map[x][y][0] < shark) {   // 만약 이동한 상어가 더 크면
                sharks.remove(shark);           // 이동한 상어 제거

            } else {                      // 만약 이동한 상어가 더 작으면
                sharks.remove(map[x][y][0]);    // 원래 있던 상어 제거
            }
            return; 
        }

        // 상어의 이동 정보를 sharks와 map에 갱신
        map[x][y][0] = shark;
        map[x][y][1] = time + 1;
        sharks.get(shark)[0] = x;
        sharks.get(shark)[1] = y;
        sharks.get(shark)[2] = d;
    }

    /**
     * 범위 체크 메서드
     * @param x 행
     * @param y 열
     * @return  범위 안이면 true, 아니면 false
     */
    public static boolean isIn(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }



    /**
     * 맵 출력 메서드
     * - 디버깅용
     */
    public static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(Arrays.toString(map[i][j]));
            }
            System.out.println();
        }
    }

    /**
     * 상어 출력 메서드
     * - 디버깅용
     */
    public static void printShark() {
        Set<Integer> keys = sharks.keySet();

        for (int key : keys) {
            int[] temp = sharks.get(key);
            System.out.println(String.format("상어((%d))[x : %d, y : %d, dir : %d]", key, temp[0], temp[1], temp[2]));
        }
    }
}
