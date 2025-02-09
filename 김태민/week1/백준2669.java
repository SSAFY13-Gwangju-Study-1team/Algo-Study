package argo;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int MAX_COORD = 101; // 좌표 범위 (1~100)
        Scanner scanner = new Scanner(System.in);

        // 방문 표시 배열 (직사각형이 채워진 곳을 기록)
        boolean[][] visited = new boolean[MAX_COORD][MAX_COORD];

        // 입력받아 처리
        for (int t = 0; t < 4; t++) {
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();

            // 직사각형의 범위만큼 방문 표시
            for (int x = x1; x < x2; x++) {
                for (int y = y1; y < y2; y++) {
                    visited[x][y] = true; // 직사각형이 차지한 영역 표시
                }
            }
        }

        // 방문 표시 배열을 순회하며 면적 계산
        int cnt = 0;
        for (int x = 1; x < MAX_COORD; x++) {
            for (int y = 1; y < MAX_COORD; y++) {
                if (visited[x][y]) { // 방문된 곳이면 면적 증가
                    cnt++;
                }
            }
        }

        // 결과 출력
        System.out.println(cnt);
    }
}
