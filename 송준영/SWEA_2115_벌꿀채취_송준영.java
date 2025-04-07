import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * SWEA_2115_벌꿀채취
 * 난이도 7/10
 * 조합, 부분집합, 비트마스킹
 * 173ms 29mb
 * 
 * 두 양봉업자가 벌꿀을 채취하는데 서로 겹치면 안되고 가로 방향으로 길게 꿀을 채취한다
 * 두 양봉업자가 채취하는 공간을 조합으로 구한다.
 * 그러면 채취한 장소가 나오는데 이 장소에서 C를 안 넘기고 가장 꿀을 많이 채취할 수 있는 방법을 찾아야 한다 -> 부분집합 이용
 * 인풋값이 작아서 가능한 문제, 더 컸으면 안 됐을 듯
 */
public class SWEA_2115_벌꿀채취_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T;   // 테스트케이스 수
    
    static int N, M, C, result, temp;   // N: 맵 크기, M: 벌꿀 채취 길이, C: 최대 양봉업자가 채취할 수 있는 꿀의 양, result: 최대 꿀의 양, temp: 부분집합으로 구한 꿀의 양
    static int[][] honeys;      // 꿀 맵
    static Integer[][] worker;  // 두 양봉업자가 채취하는 꿀의 양을 저장할 배열

    public static void main(String[] args) throws Exception {
        // 테스트케이스 수 입력 및 반복
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
        M = parseInt(st.nextToken());
        C = parseInt(st.nextToken());

        honeys = new int[N][N]; 
        worker = new Integer[2][M];
        result = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                honeys[i][j] = parseInt(st.nextToken());
            }
        }

        backtrack(0, 0, 0); // 조합으로 두 양봉업자가 채취하는 꿀의 양을 구한다
    }

    /**
     * 조합을 구하는 메서드
     * @param depth     현재 깊이, 2명이니까 2까지 감
     * @param sx        시작 x좌표
     * @param sy        
     */
    public static void backtrack(int depth, int sx, int sy) {
        if (depth == 2) {   // 두 양봉업자가 채취하는 꿀의 양을 구했으면
            int totalSum = 0;
            // System.out.println("w1 : ");
            // for (int i = 0; i < M; i++) {
            //     System.out.print(worker[0][i] + " ");
            // }
            // System.out.println();
            // System.out.println("w2 : ");
            // for (int i = 0; i < M; i++) {
            //     System.out.print(worker[1][i] + " ");
            // }
            // System.out.println();

            temp = 0;
            calHoney(0, 0, 0);  // worker[0]의 꿀의 양을 구한다
            totalSum += temp;
            // System.out.println("1빠따 : " + temp);
            temp = 0;
            calHoney(0, 0, 1);  // worker[1]의 꿀의 양을 구한다
            totalSum += temp;
            // System.out.println("2빠따 : " + temp);

            
            result = Math.max(result, totalSum);    // 두 양봉업자가 채취한 꿀의 양을 더해서 최대 꿀의 양을 구한다
            return;
        }
        int i = sx, j = sy; // 시작 좌표

        // 시작  좌표로 부터 꿀을 채취하는데, 가로로 M만큼 꿀을 채취할 수 있다
        // i는 행, j는 열
        for (; i < N; i++) {
            for (; j < N - M + 1; j++) {
                for (int k = 0; k < M; k++) {
                    worker[depth][k] = honeys[i][j + k];    // 꿀을 채취하는 양을 worker[depth]에 저장한다
                }
                backtrack(depth + 1, i, j + M);             // 다음 양봉업자가 채취할 수 있는 꿀의 양을 구하기 위해서 j + M으로 이동한다
            }
            j = 0;  // j는 0으로 초기화한다
        }
    }

    /**
     * 부분집합을 구하는 메서드 ( 꿀의 양을 구하는 메서드 )
     * @param depth     현재 깊이
     * @param select    선택한 꿀 칸을 비트마스킹으로 저장한다
     * @param num       양봉업자 번호 ( 0: worker[0], 1: worker[1] )
     */
    public static void calHoney(int depth, int select, int num) {
        if (depth == M) {   // M만큼 꿀을 채취했으면
            int sum = 0;    // 꿀의 양을 저장할 변수 ^ 2 -> 결과값으로 저장 예정
            int cnt = 0;    // 꿀의 양을 저장할 변수
            for (int i = 0; i < M; i++) {
                if ((select & (1 << i)) != 0) { // 꿀을 채취한 칸이면
                    sum += Math.pow(worker[num][i], 2);   // 꿀의 양을 제곱해서 더한다
                    cnt += worker[num][i];                  // 꿀의 양을 더한다
                }
            }

            if (cnt > C) return;    // 꿀의 양이 C를 넘으면 return, 갱신 안하고 넘어가기 

            temp = Math.max(temp, sum); // 꿀의 양을 제곱해서 더한 값을 temp에 저장한다
            return;
        }

        calHoney(depth + 1, select | (1 << depth), num);    // 꿀을 채취한 칸이면
        calHoney(depth + 1, select, num);                   // 꿀을 채취하지 않은 칸이면
    }
}
