import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 찬영님이 가르쳐주셨어용 ^_^
 */
public class Main_2116_주사위_쌓기 {
    static int N;
    static int[][] dice;
    static int maxSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 주사위 개수 파싱
        N = Integer.parseInt(br.readLine());

        dice = new int[N][6];
        // 주사위 배열 파싱
        for (int i = 0; i < N; i++) {
            dice[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        // i가 첫번째 주사위의 바닥면의 인덱스일 때, 반대면의 인덱스 opposite 구하기
        int opposite = -1;
        for (int i = 0; i < 6; i++) {
            if (i == 0) opposite = 5;
            if (i == 1) opposite = 3;
            if (i == 2) opposite = 4;
            if (i == 3) opposite = 1;
            if (i == 4) opposite = 2;
            if (i == 5) opposite = 0;

            // 바닥면과 반대면이 정해졌을 때, 나머지 숫자들 중 가장 큰 수 구하기
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                if (j != i && j != opposite) {
                    list.add(dice[0][j]);
                }
            }
            int maxSide = Collections.max(list);

            // 첫번째 주사위를 놨기에 count = 1
            // 바닥면은 dice[0][i], 옆면의 숫자들 중 가장 큰 수 maxSide를 파라미터로 넘김
            tower(1, dice[0][i], maxSide);
        }

        System.out.println(maxSum);
    }

    private static void tower(int count, int bottomNum, int sum) {
        // 마지막 주사위까지 쌓았다면 최댓값 찾기
        if (count == N) {
            maxSum = Math.max(maxSum, sum);
            return;
        }

        // 다음 주사위의 바닥면 찾기
        int nextBottom = -1;
        for (int i = 0; i < 6; i++) {
            // dice[count][i]가 현재 bottomNum과 일치한다면
            if (dice[count][i] == bottomNum) {
                if (i == 0) nextBottom = dice[count][5];
                if (i == 1) nextBottom = dice[count][3];
                if (i == 2) nextBottom = dice[count][4];
                if (i == 3) nextBottom = dice[count][1];
                if (i == 4) nextBottom = dice[count][2];
                if (i == 5) nextBottom = dice[count][0];
            }
        }

        // 현재의 바닥면과 다음 바닥면이 아닌 숫자 중 가장 큰 수 구하기
        int maxSide = -1;
        for (int i = 0; i < 6; i++) {
            if (dice[count][i] != bottomNum && dice[count][i] != nextBottom) {
                maxSide = Math.max(maxSide, dice[count][i]);
            }
        }

        // 주사위를 한 개 더 쌓았기에 count + 1
        // 다음 바닥면 nextBottom
        // 옆면의 최댓값을 sum에 더하여 넘김
        tower(count + 1, nextBottom, sum + maxSide);
    }
}
