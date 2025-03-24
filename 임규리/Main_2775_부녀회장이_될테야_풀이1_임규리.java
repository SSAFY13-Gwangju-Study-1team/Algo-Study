package 스터디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Bottom Up
 * 0층부터 시작해서 K층까지 채워나감
 */
public class Main_2775_부녀회장이_될테야_풀이1_임규리 {

    static int T;   // 테스트 케이스 개수
    static int K;   // 층
    static int n;   // 호
    static int[][] arr; // 입주민 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            K = Integer.parseInt(br.readLine());
            n = Integer.parseInt(br.readLine());

            arr = new int[K + 1][n];
            bottomUp(0);

            System.out.println(arr[K][n - 1]);
        }
    }

    private static void bottomUp(int depth) {
        if (depth == K + 1) {
            return;
        }

        if (depth == 0) {
            for (int i = 0; i < n; i++) {
                arr[depth][i] = i + 1;
            }
        } else {
            for (int i = 0; i < n; i++) {
                int sum = 0;
                for (int j = 0; j < i + 1; j++) {
                    sum += arr[depth - 1][j];
                }

                arr[depth][i] = sum;
            }
        }

        bottomUp(depth + 1);
    }
}
