import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * 아이디어
 * - 조합으로 궁수 배치
 * - 시뮬레이션
 * - 최대값 계산
 */
public class Main_17135_캐슬_디펜스_임규리 {

    static int N;   // 행 크기
    static int M;   // 열 크기
    static int D;   // 공격 거리 제한
    static int[][] map; // 지도
    static List<int[]> archer;  // 궁수 위치
    static List<int[]> enemy;   // 적 위치
    static int result;  // 결과값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        D = parseInt(st.nextToken());
        map = new int[N][M];
        archer = new ArrayList<>();
        enemy = new ArrayList<>();
        result = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = parseInt(st.nextToken());

                // 적 위치 저장
                if (map[i][j] == 1) {
                    enemy.add(new int[]{i, j});
                }
            }
        }

        combination(0, 0, new int[3]);

        System.out.println(result);
    }

    private static void combination(int start, int depth, int[] comb) {
        if (depth == 3) {   // 궁수 세 명 조합 시
            archer.clear(); // 이전 궁수 목록이 남아있을 수 있으니까 초기화

            for (int i = 0; i < depth; i++) {
                archer.add(new int[]{N, comb[i]});
            }

            shoot(archer);  // 지역 변수로 넘겨주면 더 빠르게 처리됨
            return;
        }

        for (int i = start; i < M; i++) {
            comb[depth] = i;
            combination(i + 1, depth + 1, comb);
        }
    }

    private static void shoot(List<int[]> curArcher) {
        // 적 리스트 복사 : 기존 리스트를 변경하지 않도록!
        // 깊은 복사... 해야 함...
        List<int[]> copyEnemy = new ArrayList<>();
        for (int[] e : enemy) {
            copyEnemy.add(new int[]{e[0], e[1]});
        }

        int count = 0;

        while (true) {
            // 목표가 겹칠수도 있으니까 Set으로 관리
            // int[]로 처리하면 같은 좌표라도 서로 다른 객체로 취급
            Set<String> targets = new HashSet<>();

            // 적 리스트가 비면 끝내기
            if (copyEnemy.isEmpty()) {
                break;
            }

            // 각 궁수와 가장 가까운 적 찾기
            for (int[] a : curArcher) {
                int[] target = new int[2];
                int minDist = Integer.MAX_VALUE;

                for (int[] e : copyEnemy) {
                    int dist = getDistance(a[0], a[1], e[0], e[1]);
                    if (dist <= D) {    // 거리가 D 이하일 경우
                        if (dist < minDist) {   // dist가 더 작으면 적 선택
                            minDist = dist;
                            target[0] = e[0];
                            target[1] = e[1];
                        } else if (dist == minDist && e[1] < target[1]) {   // dist가 같으면 더 왼쪽에 있는 적 선택
                            target[0] = e[0];
                            target[1] = e[1];
                        }
                    }
                }

                // 찾은 적 추가
                if (minDist != Integer.MAX_VALUE) {
                    targets.add(target[0] + "," + target[1]);
                }
            }

            // 목표 적들 제거
            for (String t : targets) {
                String[] str = t.split(",");
                int tx = parseInt(str[0]);
                int ty = parseInt(str[1]);

                for (int i = 0; i < copyEnemy.size(); i++) {
                    int [] e = copyEnemy.get(i);

                    if (e[0] == tx && e[1] == ty) {
                        copyEnemy.remove(i);
                        count++;
                        break;
                    }
                }
            }

            // 적들 한 칸씩 이동
            Iterator<int[]> iterator = copyEnemy.iterator();
            while (iterator.hasNext()) {
                int[] e = iterator.next();
                e[0]++;

                if (e[0] == N) {
                    iterator.remove();
                }
            }
        }

        result = Math.max(result, count);
    }

    private static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
