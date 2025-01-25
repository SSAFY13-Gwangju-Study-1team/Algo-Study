import java.util.*;
import java.io.*;

public class Main_2116_주사위_쌓기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] dice;
    static int N;
    static int maxSum;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());    // 주사위 개수
        dice = new int[N][6];                   // 주사위 저장 배열

        // 주사위 배열에 저장하기
        for(int n=0; n<N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 6; i++) {
                dice[n][i] = Integer.parseInt(st.nextToken());
            }
        }

        maxSum = 0; // 옆면을 모두 더한 최대 값(답)
        int opposite = 0;   // 처음 쌓은 주사위 바닥의 반대편 위치
        for(int i=0; i<6; i++) {
            // 주사위 면은 인덱스 0<>5, 1<>3, 2<>4 끼리 마주보고 있음
            if(i==0) opposite = 5;
            else if(i==1) opposite = 3;
            else if(i==2) opposite = 4;
            else if(i==3) opposite = 1;
            else if(i==4) opposite = 2;
            else opposite = 0;

            for(int j=0; j<6; j++) {                        // 첫 번째 주사위를 놓음
                if(j != i && j != opposite) {               // 바닥면과 그 반대편은 옆면이 될 수 없으므로 제외
                    tower(1, dice[0][i], dice[0][j]);   // 두 번째 주사위부터 시작
                }
            }
        }
        System.out.println(maxSum);
    }

    /**
     * 현재까지 쌓은 주사위 옆면 합
     * @param n // 현재 주사위
     * @param bottom    // 바닥면 값
     * @param sum       // 현재까지 더해진 값
     */
    public static void tower(int n, int bottom, int sum) {
        if(n==N) {  // 모든 주사위를 놓았을 때
            maxSum = Math.max(maxSum, sum); // 현재 주사위 탑의 옆면 값 합과 비교하여 최댓값을 저장
            return;
        }

        int nextBottom = 0;     // 다음 바닥면(현재 주사위의 윗면)
        for(int i=0; i<6; i++) {
            if(dice[n][i] == bottom) {
                // 0<>5, 1<>3, 2<>4
                if(i==0) {
                    nextBottom = dice[n][5];
                    break;
                }
                else if(i==1) {
                    nextBottom = dice[n][3];
                    break;
                }
                else if(i==2) {
                    nextBottom = dice[n][4];
                    break;
                }
                else if(i==3) {
                    nextBottom = dice[n][1];
                    break;
                }
                else if(i==4) {
                    nextBottom = dice[n][2];
                    break;
                }
                else if(i==5) {
                    nextBottom = dice[n][0];
                    break;
                }
            }
        }

        int maxNum = 0;     // 위와 아래를 제외하고 남은 옆면 4칸 중 가장 큰 수
        for(int i=0; i<6; i++) {
            if(!(dice[n][i] == bottom || dice[n][i] == nextBottom)) {   // 바닥과 그 반대편을 제외하고
                maxNum = Math.max(maxNum, dice[n][i]);                  // 남은 4가지 옆면 중 가장 큰 값을 구함
            }
        }
        tower(n+1, nextBottom, sum+maxNum); // 다음 주사위 쌓기(재귀)
    }
}