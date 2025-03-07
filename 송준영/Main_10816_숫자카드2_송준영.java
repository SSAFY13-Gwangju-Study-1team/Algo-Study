import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Main_10816_숫자카드2_송준영
 * 난이도 2/10
 * 해시
 * 988ms 181mb
 * 
 * 숫자 카드를 해시맵에 넣어주고, M개의 숫자를 입력받으면서 해시맵에 있는지 확인하고 있으면 출력해주는 문제
 * 해시맵을 사용하면 쉽게 풀 수 있는 문제
 * 배열 이용해서 풀어도 될 것 같긴 함 -> 더 빠를 수도 있음
 */
public class Main_10816_숫자카드2_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;    // 숫자 카드 수, 찾을 숫자 수
    static Map<Integer, Integer> nums = new HashMap<>();    // 숫자 카드를 담을 해시맵

    public static void main(String[] args) throws Exception {
        // 입력 처리
        N = parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int temp = parseInt(st.nextToken());
            nums.put(temp, nums.getOrDefault(temp, 0) + 1);   // 해시맵에 넣어주기, 값이 없으면 0을 넣어주고 1을 더해줌, 있다면 원래 값에 1을 더해줌
        }
        M = parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int temp = parseInt(st.nextToken());
            sb.append(nums.getOrDefault(temp, 0) + " "); // 해시맵에 있는지 확인하고 있으면 출력
        }

        // 출력
        System.out.println(sb);
    }
}
