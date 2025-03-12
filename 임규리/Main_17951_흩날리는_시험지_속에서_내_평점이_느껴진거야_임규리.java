import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * 메모리 : 20,800 KB
 * 시간 : 200 ms
 * ------------------------------------------------------------
 * 풀이 1 => 실패
 * - 이유 : mid를 한 그룹에 담을 수 있는 최대 합으로 가정 -> 각 그룹의 합이 mid를 넘지 않도록 함
 *   -> 각 그룹의 실제 합들 중 최솟값을 구한 뒤 그 값으로 결과를 갱신
 *   -> 그룹의 합이 mid로 제한되도록 나누기 때문에, mid가 낮아지면 그룹의 수가 늘어나고, 그 결과 최솟값도 낮아짐
 * ------------------------------------------------------------
 * 풀이 2 => G쌤의 조언
 * - 각 그룹들의 최저 점수를 기준으로 탐색
 * - 각 그룹의 합 중 최소값을 최대화
 * - 각 그룹의 합이 적어도 mid 이상이어야 함 -> 최소 그룹의 합이 mid!
 */
public class Main_17951_흩날리는_시험지_속에서_내_평점이_느껴진거야_임규리 {

    static int N;   // 시험지 개수
    static int K;   // 그룹 수
    static int[] arr;   // 각 시험지 점수
    static int total;     // 시험지 점수 합
    static int result;  // 최대 점수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        K = parseInt(st.nextToken());
        arr = new int[N];
        result = Integer.MIN_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(st.nextToken());
            total += arr[i];
        }

        search();
        System.out.println(result);
    }

    private static void search() {
        int start = 0;
        int end = total;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (canDivide(mid)) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
    }

    private static boolean canDivide(int mid) {
        int count = 0;
        int sum = 0;

        for (int i = 0; i < N; i++) {
            sum += arr[i];

            if (sum >= mid) {
                count++;
                sum = 0;
            }
        }

        return count >= K;
    }
}
