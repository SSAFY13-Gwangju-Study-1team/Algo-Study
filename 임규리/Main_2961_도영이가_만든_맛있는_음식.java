import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_2961_도영이가_만든_맛있는_음식 {
    static int N;   // 재료 개수
    static int min; // 계산 결과의 최소값
    static List<List<Integer>> list;    // 조합 리스트 e.g. ((0), (1), (0, 1))

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] sour = new int[N];
        int[] bitter = new int[N];

        min = Integer.MAX_VALUE;
        list = new ArrayList<>();

        // 신맛과 쓴맛을 각각의 배열에 저장
        for (int i = 0; i < N; i++) {
            int[] temp = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            sour[i] = temp[0];
            bitter[i] = temp[1];
        }

        // 개수(end)를 하나씩 늘려가며 조합 호출
        for (int i = 1; i <= N; i++) {
            combination(new ArrayList<>(), 0, i);
        }

        for (int i = 0; i < list.size(); i++) {
            List<Integer> cur = list.get(i);
            int sourCal = 1;
            int bitterCal = 0;

            for (int j = 0; j < cur.size(); j++) {
                sourCal *= sour[cur.get(j)];
                bitterCal += bitter[cur.get(j)];
            }

            min = Math.min(min, Math.abs(sourCal - bitterCal));

            if (min == 0) {
                break;
            }
        }

        System.out.println(min);
    }

    // 인덱스의 조합을 찾는 함수
    // e.g. 재료의 개수가 N개 -> 인덱스는 0부터 N - 1
    // N = 4일 때 3개의 재료 인덱스를 뽑는다면 ((0, 1, 2), (0, 1, 3), (0, 2, 3), (1, 2, 3))
    private static void combination(List<Integer> temp, int start, int end) {
        if (temp.size() == end) {
            list.add(new ArrayList<>(temp));
            return;
        }

        for (int i = start; i < N; i++) {
            temp.add(i);
            combination(temp, i + 1, end);
            temp.remove(temp.size() - 1);
        }
    }
}
