import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Main_2851_슈퍼마리오_송준영
 * 난이도 2/10
 * 누적합, 브루트포스
 * 104ms
 */
public class Main_2851_슈퍼마리오_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[] mushroom = new int[10];    // 버섯 점수 배열

    public static void main(String[] args) throws Exception {
        // 버섯 점수 입력
        for (int i = 0; i < 10; i++) {
            mushroom[i] = Integer.parseInt(br.readLine());
        }
        // 누적합 처리
        for (int i = 1; i < 10; i++) {
            mushroom[i] += mushroom[i - 1];
        }

        int min = Integer.MAX_VALUE;    // 100과의 차이의 최소값
        int ans = 0;                    // 100과의 차이가 최소인 점수
        for (int i = 0; i < 10; i++) {
            if (Math.abs(100 - mushroom[i]) < min) {    // 100과의 차이가 최소인 경우
                min = Math.abs(100 - mushroom[i]);
                ans = mushroom[i];
            } else if (Math.abs(100 - mushroom[i]) == min) {    // 100과의 차이가 같은 경우
                ans = Math.max(ans, mushroom[i]);
            }
        }
        System.out.println(ans);    // 출력
    }
}
