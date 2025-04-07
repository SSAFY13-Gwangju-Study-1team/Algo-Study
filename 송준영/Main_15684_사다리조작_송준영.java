import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * Main_15684_사다리조작
 * 난이도 7/10
 * 백트래킹, 그래프
 * 2324ms -> 472ms(선 고르기 최적화) 19mb
 * 
 * 백트래킹(조합?)을 이용해서 선 3개를 골라야함 + 선 고르는 조건도 고려
 * 선 선택시 이전에 선택했던 선들을 고려할 필요 없음 -> 중복 제거
 * 
 * 사다리 그래프 만들 때 각 점에 대해 0이면 선 없음 1이면 오른쪽 2면 왼쪽 이런식도 좋을 것 같다
*/
public class Main_15684_사다리조작_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M, H, result = -1;    // N : 가로선 수, M : 세로선 수, H : 사다리 높이, result : 결과값
    static boolean[][] lines;           // lines[i][j] : i행 j~j+1열에 사다리 있음

    public static void main(String[] args) throws Exception {
        // 입력 처리 및 초기화
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        lines = new boolean[H+1][N];    // lines[i][j] : i행 j~j+1열에 사다리 있음

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = parseInt(st.nextToken()), b = parseInt(st.nextToken());
            lines[a][b] = true; // a행 b~b+1열에 사다리 있음
        }

        for (int i = 0; i < 4; i++) {
            backtrack(0, i, 1, 1);  // 깊이, 최대 깊이, 시작 행, 시작 열
            if (result != -1) { // 결과값이 -1이 아니면 종료 -> 결과 찾음
                break;
            }
        }

        System.out.println(result); // 결과값 출력
    }

    /**
     * 백트래킹 메서드 (이을 선들 모두 고르기)
     * @param depth     현재 깊이
     * @param maxDepth  최대 깊이 (이을 선 개수)
     * @param sx        시작 행 (전에 골랐던 선에 이어서)
     * @param sy        시작 열 (전에 골랐던 선에 이어서)
     */
    public static void backtrack(int depth, int maxDepth, int sx, int sy) {
        if (result != -1) return;   // 결과값이 -1이 아니면 종료
        if (depth == maxDepth) {    // 깊이가 최대 깊이와 같으면 사다리 조작 완료
            boolean flag = true;
            for (int i = 1; i <= N; i++) {
                if (!laddering(i)) {
                    flag = false;   // i열에 대해 사다리 조작이 실패하면 flag를 false로 변경
                    break;
                }
            }
            if (flag) result = maxDepth;    // 사다리 조작이 성공하면 결과값을 maxDepth로 변경
            return;
        }

        for (int i = sx; i <= H; i++) {   // sx부터 행을 돌면서
            for (int j = (i == sx ? sy : 1); j < N; j++) {  // sy부터 열을 돌면서 (처음 아니면 j는 다시 1로)
                if (!lines[i][j] && check(i, j)) {
                    lines[i][j] = true;
                    backtrack(depth + 1, maxDepth, i, j);   // 다음 깊이로 이동
                    if (result != -1) return;               // 결과값이 -1이 아니면 종료
                    lines[i][j] = false;
                }
            }
        }
    }

    /**
     * 사다리 타기 메서드
     * @param x 어디서 탈까용?
     * @return
     */
    public static boolean laddering(int x) {
        int loc = x;    // 타는 위치 저장
        for (int i = 1; i <= H; i++) {
            if (loc == 1) {
                if (lines[i][loc]) loc++;
            } else if (loc == N) {
                if (lines[i][loc-1]) loc--;
            } else {
                if (lines[i][loc]) loc++;
                else if (lines[i][loc-1]) loc--;
            }
        }

        return x == loc;    // 위치가 같으면 true, 다르면 false
    }

    /**
     * 사다리를 놓을 수 있는지 체크하는 check 메서드
     * @param x 행
     * @param y 열
     * @return  사다리를 놓을 수 있으면 true, 아니면 false
     */
    public static boolean check(int x, int y) {
        if (y == 1 && lines[x][y]) {
            return false;
        } else if (y == N-1 && lines[x][y-1]) {
            return false;
        } else if (lines[x][y-1] || lines[x][y]) {
            return false;
        }
        return true;
    }
}
