package Week10;

import java.io.*;
import java.util.*;

/**
 * 조건
 *  - NxM 격자판, N+1행은 성이 있음 (궁수는 이 성에 위치)
 *  - 궁수는 거리 D 이하의 적을 공격할 수 있음
 *  - 같은 적을 여러 궁수가 동시에 공격 가능
 *  - 적은 매 턴마다 아래로 한 칸 이동함
 *  - 공격 맞거나 성에 도달하면 제거됨
 *  - 모든 적이 제거될 때까지 시뮬레이션 진행
 *
 * 설계
 *  - 궁수 3명의 위치를 M칸 중에서 조합으로 뽑기 (백트래킹 사용)
 *  - 각 조합마다 시뮬레이션 돌림
 *  - 궁수는 동시에 공격하므로, 타겟을 먼저 모아서 한 번에 제거
 *  - 적은 매 턴마다 아래로 한 칸 이동 → 시뮬레이션 종료 조건: 적이 다 사라졌을 때
 *  - 타겟 우선순위: 거리 우선 → 같은 거리면 왼쪽 우선
 */

public class Main_17135_캐슬디펜스_김태민 {

    static class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m, d;  // 격자 크기와 사거리
    static int[][] map;  // 원본 맵 저장
    static ArrayList<Integer> archer = new ArrayList<>();  // 궁수 위치 조합 저장
    static int max = 0; // 최대 제거한 적 수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) { // 맵 입력 받기
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        back(0, 0); // 궁수 위치 조합 만들기
        System.out.println(max); // 최대 적 처치 수 출력
    }

    // 궁수 위치를 조합으로 뽑는 백트래킹 함수
    static void back(int cnt, int start) {
        if (cnt == 3) { // 궁수 3명 배치 완료되면 시뮬 돌림
            max = Math.max(max, simul());
            return;
        }

        for (int i = start; i < m; i++) {
            archer.add(i); // i열에 궁수 배치
            back(cnt + 1, i + 1);
            archer.remove(archer.size() - 1); // 백트래킹
        }
    }

    // 실제 게임 시뮬레이션 진행 함수
    static int simul() {
        // 맵 복사 (원본 유지)
        int[][] simulMap = new int[n][m];
        for (int i = 0; i < n; i++) simulMap[i] = map[i].clone();
        int cnt = 0; // 적 제거 수

        // 턴 시작 (최대 n턴, 매 턴마다 적이 한 줄씩 내려감)
        for (int t = 0; t < n; t++) {
            boolean[][] marked = new boolean[n][m]; // 공격 대상 체크용

            // 3명의 궁수가 각자 타겟을 찾음
            for (int col : archer) {
                Node target = findTarget(simulMap, col);
                if (target != null) {
                    marked[target.x][target.y] = true;
                }
            }

            // 타겟 제거 (동시에 죽이기)
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (marked[i][j] && simulMap[i][j] == 1) {
                        simulMap[i][j] = 0;
                        cnt++;
                    }
                }
            }

            // 적 이동 (한 줄씩 아래로 밀고, 맨 위는 초기화)
            for (int i = n - 1; i > 0; i--) {
                simulMap[i] = simulMap[i - 1];
            }
            simulMap[0] = new int[m];
        }

        return cnt;
    }

    static Node findTarget(int[][] simulMap, int archerCol) {
        Node target = null;
        int minDist = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) { // 모든 적을 탐색
            for (int j = 0; j < m; j++) {
                if (simulMap[i][j] == 1) {
                    int dist = dist(n, archerCol, i, j); // 궁수는 항상 n행에 위치
                    if (dist <= d) {
                        // 더 가까운 적이거나, 거리가 같고 더 왼쪽이면 갱신
                        if (dist < minDist || (dist == minDist && j < (target != null ? target.y : m))) {
                            minDist = dist;
                            target = new Node(i, j);
                        }
                    }
                }
            }
        }

        return target;
    }

    static int dist(int x, int y, int x2, int y2) {
        return Math.abs(x - x2) + Math.abs(y - y2);
    }
}
