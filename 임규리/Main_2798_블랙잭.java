import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_2798_블랙잭 {
    private static final int COUNT = 3;

    static int N, M;
    static int[] cards;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] num = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = num[0];
        M = num[1];

        cards = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        combination(0, new ArrayList<>());

        System.out.println(result);
    }

    private static void combination(int start, List<Integer> list) {
        // 기저조건 : list에 담긴 숫자의 개수가 3개일 때
        if (list.size() == COUNT) {
            // list에 담긴 숫자들의 합 구하기
            int sum = list.stream()
                    .mapToInt(Integer::intValue)
                    .sum();

            // sum이 M 이하라면 result와 비교하여 더 큰 수를 result에 담기
            if (sum <= M) {
                result = Math.max(result, sum);
            }

            return;
        }

        for (int i = start; i < cards.length; i++) {
            list.add(cards[i]);                  // list에 숫자 담기
            combination(i + 1, list);       // 재귀 호출 (다음 숫자 고르기)
            list.remove(list.size() - 1);   // list에서 숫자 빼기 (백트래킹)
        }
    }
}
