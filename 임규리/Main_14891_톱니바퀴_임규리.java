import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main_14891_톱니바퀴_임규리 {

    // 톱니바퀴 1 ~ 4
    // N극 = 0, S극 = 1
    static int[][] arr = new int[4][8];
    static int K;   // 회전 횟수
    static List<int[]> shouldTurn; // 돌아야하는 톱니바퀴 번호, 방향
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            arr[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        K = parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int num = parseInt(st.nextToken());
            int dir = parseInt(st.nextToken()); // 시계방향 = 1, 반시계방향 = -1
            chooseTurn(num, dir);
        }

        calResult();
        System.out.println(result);
    }

    private static void chooseTurn(int num, int dir) {
        shouldTurn = new ArrayList<>();
        shouldTurn.add(new int[]{num - 1, dir});    // 자기 자신 추가
        int leftOfNum = arr[num - 1][6];
        int rightOfNum = arr[num - 1][2];
        int curDir = dir;

        for (int i = num - 2; i >= 0; i--) {
            if (leftOfNum != arr[i][2]) {
                shouldTurn.add(new int[]{i, -1 * curDir});
                curDir *= -1;
            } else {
                break;
            }
            leftOfNum = arr[i][6];
        }

        curDir = dir;
        for (int i = num; i < 4; i++) {
            if (rightOfNum != arr[i][6]) {
                shouldTurn.add(new int[]{i, -1 * curDir});
                curDir *= -1;
            } else {
                break;
            }
            rightOfNum = arr[i][2];
        }

        turn();
    }

    private static void turn() {
        for (int i = 0; i < shouldTurn.size(); i++) {
            int num = shouldTurn.get(i)[0];
            int dir = shouldTurn.get(i)[1];

            if (dir == 1) {
                arr[num] = Arrays.copyOf(turnRight(arr[num]), 8);
            } else {
                arr[num] = Arrays.copyOf(turnLeft(arr[num]), 8);
            }
        }
    }

    private static int[] turnRight(int[] arr) {
        int temp = arr[arr.length - 1];
        for (int i = 7; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = temp;

        return arr;
    }

    private static int[] turnLeft(int[] arr) {
        int temp = arr[0];
        for (int i = 0; i < 7; i++) {
            arr[i] = arr[i + 1];
        }
        arr[7] = temp;

        return arr;
    }

    private static void calResult() {
        if (arr[0][0] == 1) {
            result += 1;
        }

        if (arr[1][0] == 1) {
            result += 2;
        }

        if (arr[2][0] == 1) {
            result += 4;
        }

        if (arr[3][0] == 1) {
            result += 8;
        }
    }
}
