import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * Main_14891_톱니바퀴
 * 난이도 5/10 
 * 구현? 시뮬레이션
 * 108ms 14mb
 * 
 * 톱니바퀴를 회전시키는 문제
 * 처음에는 리스트나 queue로 직점 돌릴까 했지만 오버헤드가 클 것 같아 12시 방향을 기준 인덱스로 잡고 인덱스만 회전 시키기로 했다
 * 그러기 위해서 % 연산을 사용해서 인덱스를 계산해 주었다
 * 
 * 회전시킬 톱니바퀴의 왼쪽과 오른쪽 톱니바퀴를 비교해서 회전 여부를 결정해 주었다
 * 회전시킬때 다 같이 돌아야 하므로 회전여부를 일단 저장해두었다가 한 번에 처리해 주었다
 */
public class Main_14891_톱니바퀴_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static char[][] gear;                   // 톱니바퀴
    static int K;                           // 회전 횟수
    static int[][] rotate;                  // 회전 정보
    static int[] index = { 0, 0, 0, 0 };    // 톱니바퀴 인덱스 (12시 방향)

    public static void main(String[] args) throws Exception {
        // 입력 처리 초기화
        gear = new char[4][8];
        for (int i = 0; i < 4; i++) {
            gear[i] = br.readLine().toCharArray();
        }
        K = parseInt(br.readLine());
        rotate = new int[K][2];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            rotate[i][0] = parseInt(st.nextToken()) - 1;
            rotate[i][1] = parseInt(st.nextToken());
        }

        // 회전 수만큼 회전시키기
        for (int[] temp : rotate) {
            int[] isRotate = new int[4];    // 회전 여부 저장 배열 (1: 시계, -1: 반시계)

            int gearNum = temp[0];          // 회전시킬 톱니바퀴 번호
            int dir = temp[1];              // 회전시킬 톱니바퀴의 회전 방향
            isRotate[gearNum] = dir;        // 회전시킬 톱니바퀴의 회전 여부 저장

            // 자기 기준 오른쪽 톱니바퀴 회전 여부 체크
            for (int i = gearNum; i < 4; i++) {
                if (i == gearNum) { // 자기 자신은 회전시키지 않음
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

        // 결과 출력
        System.out.println(result);
    }
}
