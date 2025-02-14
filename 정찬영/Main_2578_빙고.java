// 체감 난이도: 5/10
// 지피티가 주석을 잘 다네요...

import java.util.*;
import java.io.*;

public class Main_2578_빙고 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean[][] bingo; // 빙고판의 숫자 체크 여부를 저장하는 배열

    public static void main(String[] args) throws IOException {
        bingo = new boolean[5][5]; // 5x5 빙고판 초기화
        int[][] nums = new int[25][2]; // 입력된 숫자의 위치를 저장하는 배열

        int cur;
        // 빙고판 숫자 입력 및 위치 저장
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                cur = Integer.parseInt(st.nextToken()) - 1;
                nums[cur][0] = i; // 숫자의 행 위치 저장
                nums[cur][1] = j; // 숫자의 열 위치 저장
            }
        }

        int time = 0; // 사회자가 부른 숫자의 개수

        // 사회자가 숫자를 부르며 체크
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                cur = Integer.parseInt(st.nextToken()) - 1;
                bingo[nums[cur][0]][nums[cur][1]] = true; // 해당 숫자를 체크
                time++; // 부른 숫자 개수 증가

                // 12번째 숫자 이후부터 빙고 여부 검사
                if (time >= 12) {
                    if (check() >= 3) { // 3줄 이상 완성 시 종료
                        System.out.println(time);
                        return;
                    }
                }
            }
        }
    }

    /**
     * 현재 빙고판에서 완성된 빙고 라인의 개수를 계산하는 메서드
     * @return 완성된 빙고 라인의 개수
     */
    public static int check() {
        boolean check;
        int line = 0;

        // 가로(행) 체크
        for (int i = 0; i < 5; i++) {
            check = true;
            for (int j = 0; j < 5; j++) {
                if (!bingo[i][j]) { // 하나라도 체크되지 않았으면 빙고 실패
                    check = false;
                    break;
                }
            }
            if (check) line++; // 빙고 완성 시 라인 증가
        }

        // 세로(열) 체크
        for (int i = 0; i < 5; i++) {
            check = true;
            for (int j = 0; j < 5; j++) {
                if (!bingo[j][i]) {
                    check = false;
                    break;
                }
            }
            if (check) line++;
        }

        // 왼쪽 상단 → 오른쪽 하단 대각선 체크
        check = true;
        for (int i = 0; i < 5; i++) {
            if (!bingo[i][i]) {
                check = false;
                break;
            }
        }
        if (check) line++;

        // 오른쪽 상단 → 왼쪽 하단 대각선 체크
        check = true;
        for (int i = 0; i < 5; i++) {
            if (!bingo[i][4 - i]) {
                check = false;
                break;
            }
        }
        if (check) line++;

        return line;
    }
}
