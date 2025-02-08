
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_13251_조약돌_꺼내기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());

        // 조약돌의 색상이 한 가지라면 무조건 모두 같은 색일테니 바로 1.0
        if (M == 1) {
            System.out.println(1.0);
            return;
        }

        int[] colors = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int K = Integer.parseInt(br.readLine());
        int totalCount = Arrays.stream(colors)
                .sum();

        double result = 0;
        for (int i = 0; i < M; i++) {
            int minus = 0;
            double percentage = 1.0;

            for (int j = 0; j < K; j++) {
                percentage *= 1.0 * (colors[i] - minus) / (totalCount - minus);
                minus++;
            }

            result += percentage;
        }

        System.out.println(result);
    }
}
