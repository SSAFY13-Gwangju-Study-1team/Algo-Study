import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;

/**
 * SWEA_2806_NQueen
 * 난이도 3/10
 * 백트래킹
 * 87ms 25mb
 * 
 * NQueen 문제를 백트래킹으로 풀어보았다.
 * 체크를 위한 배열 3개를 따로 두어 체크시 접근을 인덱스로 가능하게 만들어 시간복잡도를 O(1)으로 개선하였다
 * colCheck는 열 체크, cross1Check는 대각선 체크, cross2Check는 반대 대각선 체크를 위한 배열
 */
public class SWEA_2806_NQueen_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int T;               // 테케 수
    static int N, cnt;          // 맵 크기, 카운트
    static boolean[] colCheck;      // 열 체크
    static boolean[] cross1Check;   // 대각선 체크
    static boolean[] cross2Check;   // 반대 대각선 체크

    public static void main(String[] args) throws Exception {
        // 테케 수 입력 및 반복
        T = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            solve();
            sb.append(String.format("#%d %d\n", t, cnt));
        }
        // 출력
        System.out.println(sb);
    }

    /**
     * 메인 메소드
     * @throws Exception
     */
    public static void solve() throws Exception {
        // 입력 처리 및 초기화
        N = parseInt(br.readLine());
        colCheck = new boolean[N];
        cross1Check = new boolean[N*2 - 1];
        cross2Check = new boolean[N*2 - 1];
        cnt = 0;

        // 백트래킹 시작
        // 첫 번째 행을 기준으로 열을 하나씩 고르며 백트래킹 시작
        for (int i = 0; i < N; i++) {
            backtrack(0, i);
        }
    }

    /**
     * 백트래킹 메소드
     * @param x 행
     * @param y 열
     */
    public static void backtrack(int x, int y) {
        // System.out.println(x + " " + y);
        if (!check(x, y)) { // 체크가 안되면 리턴
            return;
        } else if (x == N - 1) {    // 마지막 행에 도달하면 카운트 증가
            cnt++;
            return;
        }

        // 체크가 되면 해당 위치를 체크하고 다음 행으로 이동
        colCheck[y] = true;
        cross1Check[x-y+(N-1)] = true;
        cross2Check[x+y] = true;

        for (int i = 0; i < N; i++) {
            backtrack(x + 1, i);
        }

        // 체크가 끝나면 해당 위치를 언체크
        colCheck[y] = false;
        cross1Check[x-y+(N-1)] = false;
        cross2Check[x+y] = false;
    }

    /**
     * 체크 메소드
     * @param x 행
     * @param y 열
     * @return  true: 체크 가능, false: 체크 불가능
     */
    public static boolean check(int x, int y) {
        return colCheck[y] == false && cross1Check[x-y+(N-1)] == false && cross2Check[x+y] == false;    // 각 체크 배열이 true이면 이미 queen이 있는 것임
    }
}
