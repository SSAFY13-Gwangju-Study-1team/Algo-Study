import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Main_17135_캐슬디펜스
 * 난이도 8/10
 * 시뮬레이션, 조합
 * 200ms 17mb
 * 
 * 궁수 3명을 배치하여 적을 제거하는 문제
 * 궁수 3명을 조합으로 배치가능한 모든 조합을 찾고 그 한가지 경우마다 몇 명의 적을 제거하는지 확인해야함
 * 좀 많이 복잡한 문제
 * 조합에, 시뮬레이션에 처음에는 BFS로 하려 했으나 머리아프고 입력 값이 그리 크지 않아 전체 탐색으로 전환
 * 다행히 통과했다.
 */
public class Main_17135_캐슬디펜스_송준영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M, D, result; // N: 행, M: 열, D: 궁수 사정거리, result: 결과값
    static int[][] originalMap; // 초기 맵
    static int[] archers = new int[3];  // 궁수 위치
    
    public static void main(String[] args) throws Exception {
        // 입력 처리
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        D = parseInt(st.nextToken());
        
        originalMap = new int[N][M];
        
        // 맵 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                originalMap[i][j] = parseInt(st.nextToken());
            }
        }
        
        // 궁수 위치 조합 선택
        comb(0, 0);
        
        // 결과 출력
        System.out.println(result);
    }
    
    /**
     * 궁수 위치 조합 선택 메서드
     * @param start     시작 인덱스
     * @param count     궁수 수
     */
    private static void comb(int start, int count) {
        if (count == 3) {
            // 게임 시뮬레이션 수행
            result = Math.max(result, game());
            return;
        }
        
        // 조합 선택
        for (int i = start; i < M; i++) {
            archers[count] = i;
            comb(i + 1, count + 1);
        }
    }
    
    /**
     * 게임 시뮬레이션 메서드
     * @return  제거된 적의 수
     */
    private static int game() {
        int[][] map = copyMap(originalMap); // 맵 복사 -> 여러번 다시 사용할꺼기 때문에
        int removed = 0;                    // 제거된 적의 수
        
        // 모든 적이 사라질 때까지 게임 진행
        while (hasEnemy(map)) {
            // 각 궁수가 활을 쏨
            List<int[]> targets = new ArrayList<>();
            
            for (int archerCol : archers) {
                int[] target = findTarget(map, archerCol);  // 적 찾기
                if (target != null) {                       // 적이 있으면 추가
                    targets.add(target);
                }
            }
            
            // 표적 제거
            for (int[] target : targets) {
                if (map[target[0]][target[1]] == 1) {
                    map[target[0]][target[1]] = 0;
                    removed++;
                }
            }
            
            // 적 이동
            moveEnemies(map);
        }
        
        return removed; // 제거된 적의 수 반환
    }
    
    /**
     * 맵 복사 메서드
     * @param original  복사할 맵
     * @return          복사된 맵
     */
    private static int[][] copyMap(int[][] original) {
        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            copy[i] = Arrays.copyOf(original[i], M);
        }
        return copy;
    }
    
    /**
     * 적이 남아있는지 확인
     * @param map   맵
     * @return      적이 남아있으면 true, 없으면 false
     */
    private static boolean hasEnemy(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 적 찾는 메서드
     * @param map       맵
     * @param archerCol 궁수 열
     * @return          적의 위치
     */
    private static int[] findTarget(int[][] map, int archerCol) {
        // 거리, 열 순으로 가장 가까운 적 찾기
        int minDistance = Integer.MAX_VALUE;
        int minRow = -1;
        int minCol = -1;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {   // 적이면
                    int distance = getDistance(N, archerCol, i, j); // 거리 계산
                    
                    // 사정거리 내에 있으면
                    if (distance <= D) {
                        // 거리가 같으면 가장 왼쪽에 있는 적을 공격
                        if (distance < minDistance || (distance == minDistance && j < minCol)) {
                            minDistance = distance;
                            minRow = i;
                            minCol = j;
                        }
                    }
                }
            }
        }
        
        return minRow == -1 ? null : new int[] {minRow, minCol};    // 적이 없으면 null 반환
    }
    
    /**
     * 거리 계산 메서드
     * @param x1    궁수 x좌표
     * @param y1    궁수 y좌표
     * @param x2    적 x좌표
     * @param y2    적 y좌표
     * @return
     */
    private static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    
    /**
     * 적 이동 메서드
     * @param map   맵
     */
    private static void moveEnemies(int[][] map) {
        // 아래에서부터 위로 이동해야 함
        // 일일이 복사해야 깊게 복사됨
        for (int i = N - 1; i > 0; i--) {
            for (int j = 0; j < M; j++) {
                map[i][j] = map[i - 1][j];
            }
        }
        
        // 첫 번째 행은 항상 비어있음
        Arrays.fill(map[0], 0);
    }
}