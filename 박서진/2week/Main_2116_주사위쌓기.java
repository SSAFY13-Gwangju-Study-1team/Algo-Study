import java.io.*;
import static java.lang.Integer.parseInt;
public class Main_2116_주사위쌓기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = parseInt(br.readLine()); // 주사위 수
        int[][] dice = new int[n][6]; // 입력 주사위 배열

        // 주사위 입력 받기
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < 6; j++) {
                dice[i][j] = parseInt(line[j]);
            }
        }


        int res = 0;

        // 첫 번째 주사위의 각 면을 기준으로 계산
        for (int i = 0; i < 6; i++) {
            int top_idx = i;
            int top = dice[0][top_idx];
            int bottom_idx = pairCheck(top_idx);
            int bottom = dice[0][bottom_idx];
            int sum = 0;

            // 주사위 쌓기
            for (int j = 0; j < n; j++) {
                int max = 0;
                bottom_idx = pairCheck(top_idx);
                bottom = dice[j][bottom_idx];
                for (int k = 0; k < 6; k++) {
                    if (k != top_idx && k != bottom_idx) {
                        max = Math.max(max, dice[j][k]);
                    }
                }
                sum += max;
                if (j < n - 1) {
                    top = bottom;
                    top_idx = findIndex(dice[j + 1], top);
                }
            }

            res = Math.max(res, sum);
        }

        bw.write(String.valueOf(res));
        bw.flush();
    }

    // 주사위 인덱스의 반대편 인덱스 반환
    static int pairCheck(int idx) {
        switch (idx) {
            case 0:
                return 5;
            case 1:
                return 3;
            case 2:
                return 4;
            case 3:
                return 1;
            case 4:
                return 2;
            default:
                return 0;
        }
    }

    // 주사위 배열에서 값의 인덱스 찾기
    static int findIndex(int[] dice, int value) {
        for (int i = 0; i < 6; i++) {
            if (dice[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
