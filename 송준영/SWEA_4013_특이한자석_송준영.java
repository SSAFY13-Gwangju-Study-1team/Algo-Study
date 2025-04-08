import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * SWEA_4013_특이한자석
 * 난이도 6/10
 * 시뮬레이션
 * 92ms 26mb
 * 
 */
public class SWEA_4013_특이한자석_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T;                           // 테케 수

    static char[][] gear;                   // 톱니바퀴
    static int K;                           // 회전 횟수
    static int[][] rotate;                  // 회전 정보

    public static void main(String[] args) throws Exception {
        // 테케 수 입력 및 반복
        T = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append(String.format("#%d %d\n", t, solve()));
        }

        // 출력
        System.out.println(sb);
    }

    public static int solve() throws Exception {
        // 입력 처리 초기화
        K = parseInt(br.readLine());
        gear = new char[4][8];
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            // gear[i] = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                gear[i][j] = st.nextToken().charAt(0);
            }
        }
        rotate = new int[K][2];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            rotate[i][0] = parseInt(st.nextToken()) - 1;
            rotate[i][1] = parseInt(st.nextToken());
        }
        int[] index = { 0, 0, 0, 0 };    // 톱니바퀴 인덱스 (12시 방향)

        // 회전 수만큼 회전시키기
        for (int[] temp : rotate) {
            int[] isRotate = new int[4];    // 회전 여부 저장 배열 (1: 시계, -1: 반시계)

            int gearNum = temp[0];          // 회전시킬 톱니바퀴 번호
            int dir = temp[1];              // 회전시킬 톱니바퀴의 회전 방향
            isRotate[gearNum] = dir;        // 회전시킬 톱니바퀴의 회전 여부 저장

            // 자기 기준 오른쪽 톱니바퀴 회전 여부 체크
            for (int i = gearNum; i < 4; i++) {
                if (i != gearNum) { // 자기 자신은 회전시키지 않음
                    if (gear[i-1][(index[i-1] + 2) % 8] != gear[i][(index[i] + 6) % 8]) {   // 오른쪽 톱니바퀴와 비교
                        dir *= -1;          // 반대 방향으로 회전
                        isRotate[i] = dir;  // 회전 여부 저장
                    } else {
                        break;              // 같으면 그 이후는 다 회전 하지 않으므로 break
                    }
                }
            }

            // 회전 방향 초기화
            dir = temp[1];  
            // 자기 기준 왼쪽 톱니바퀴 회전 여부 체크
            for (int i = gearNum; i >= 0; i--) {
                if (i != gearNum) { // 자기 자신은 회전시키지 않음
                    if (gear[i][(index[i] + 2) % 8] != gear[i+1][(index[i+1] + 6) % 8]) {   // 왼쪽 톱니바퀴와 비교
                        dir *= -1;          // 반대 방향으로 회전
                        isRotate[i] = dir;  // 회전 여부 저장
                    } else {
                        break;              // 같으면 그 이후는 다 회전 하지 않으므로 break
                    }
                }
            }

            // 회전여부를 확인해서 index 업데이트
            for (int i = 0; i < 4; i++) {
                index[i] = (index[i] - isRotate[i] + 8) % 8;
            }
        }

        // 결과 계산
        int result = 0;
        for (int i = 0; i < 4; i++) {
            if (gear[i][index[i]] == '1') { // S극이면
                result += Math.pow(2, i);
            }
        }

        return result;  // 결과 리턴
    }

}
