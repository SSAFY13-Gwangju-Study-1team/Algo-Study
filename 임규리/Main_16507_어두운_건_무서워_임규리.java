import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main_16507_어두운_건_무서워_임규리 {

    static int R;   // 행 크기
    static int C;   // 열 크기
    static int Q;   // 밝기 평균을 알아볼 개수
    static int[][] arr; // 2차원 배열
    static int[][] prefix;  // 누적합 2차원 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = parseInt(st.nextToken());
        C = parseInt(st.nextToken());
        Q = parseInt(st.nextToken());

        arr = new int[R][C];
        prefix = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                arr[i][j] = parseInt(st.nextToken());
            }
        }

//        prefix[0][0] = arr[0][0];
//
//        for (int i = 1; i < C; i++) {
//            prefix[0][i] = prefix[0][i - 1] + arr[0][i];
//        }
//
//        for (int i = 1; i < R; i++) {
//            prefix[i][0] = prefix[i - 1][0] + arr[i][0];
//        }
//
//        for (int i = 1; i < R; i++) {
//            for (int j = 1; j < C; j++) {
//                prefix[i][j] = prefix[i][j - 1] + prefix[i - 1][j] - prefix[i - 1][j - 1] + arr[i][j];
//            }
//        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                prefix[i][j] = arr[i][j];
                if (i > 0) prefix[i][j] += prefix[i - 1][j];
                if (j > 0) prefix[i][j] += prefix[i][j - 1];
                if (i > 0 && j > 0) prefix[i][j] -= prefix[i - 1][j - 1];
            }
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int fromX = parseInt(st.nextToken()) - 1;
            int fromY = parseInt(st.nextToken()) - 1;
            int toX = parseInt(st.nextToken()) - 1;
            int toY = parseInt(st.nextToken()) - 1;

            int size = (toX - fromX + 1) * (toY - fromY + 1);

            int result = prefix[toX][toY];
            if (fromY > 0) result -= prefix[toX][fromY - 1];
            if (fromX > 0) result -= prefix[fromX - 1][toY];
            if (fromX > 0 && fromY > 0) result += prefix[fromX - 1][fromY - 1];

            sb.append(result / size).append(System.lineSeparator());
        }

        System.out.println(sb);
    }
}
