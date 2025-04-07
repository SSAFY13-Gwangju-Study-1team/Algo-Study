/* 메모리 57512kb, 시간 472ms
 * 문제 풀이 아이디어: 매년 빙하를 녹인다. bfs를 이용한다.
 * 체감 난이도: 4/10
 */

import java.io.*;
import java.util.*;

public class Main_2573_빙산_정찬영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    /* ----- 입력 ----- */
    static int N, M;
    static int[][] map;
    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 행
        M = Integer.parseInt(st.nextToken());   // 열

        map = new int[N][M];    // 지도 채우기
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    /* ----- 구현 ----- */
    static int[] dy = {-1,1,0,0};   // 상하좌우
    static int[] dx = {0,0,-1,1};
    static boolean[][] visited;
    static Queue<int[]> melt = new ArrayDeque<>();
    static void solve() {
        int year = 0;
        visited = new boolean[N][M];

        while(true) {
            // visited 배열 초기화(메모리 절약)
            for(int i=0; i<N; i++) {
                Arrays.fill(visited[i], false);
            }
            int ice = 0;    // 나눠진 빙산 개수
            melt.clear();   // 이번 년도에 녹는 빙산 인덱스 초기화

            // 모든 지역 탐색
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    if(map[i][j]!=0 && !visited[i][j]) {
                        bfs(i, j);
                        ice++;
                    }
                }
            }

            // 빙하가 녹음
            while(!melt.isEmpty()) {
                int[] cur = melt.poll();
                if(map[cur[0]][cur[1]] > 0)
                    map[cur[0]][cur[1]]--;
            }

            if(ice >= 2) {
                sb.append(year);
                return;
            }
            else if(ice == 0) {  // 빙산이 하나도 없는 경우
                sb.append(0);
                return;
            }
            year++;
        }
    }

    static Queue<int[]> que = new ArrayDeque<>();
    static void bfs(int y, int x) {
        que.clear();    // 큐 초기화
        que.add(new int[] {y, x});
        visited[y][x] = true;

        while(!que.isEmpty()) {
            int[] cur = que.poll();
            int ny, nx;

            for(int i=0; i<4; i++) {
                ny = cur[0]+dy[i];
                nx = cur[1]+dx[i];

                // 확인하는 다음 구역이 지도 범위 안이고, 바다가 아니고, 방문하지 않았다면
                if(isIn(ny, nx)) {
                    if(map[ny][nx]!=0 && !visited[ny][nx]) {
                        visited[ny][nx] = true; // 방문처리
                        que.add(new int[] {ny, nx});    // 큐에 추가
                    } else if(map[ny][nx] == 0) {
                        melt.add(new int[] {cur[0], cur[1]});
                    }
                }
            }
        }
    }

    static boolean isIn(int ny, int nx) {
        return(ny>=0 && ny<N && nx>=0 && nx<M);
    }

    /* ----- 출력 ----- */
    static void output() throws IOException {
        System.out.println(sb);
        br.close();
    }
}