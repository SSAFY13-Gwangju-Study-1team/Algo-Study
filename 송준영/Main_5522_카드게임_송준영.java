import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

public class Main_5522_카드게임_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        // 입력 처리
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += parseInt(br.readLine());
        }
        // 출력
        System.out.println(sum);
    }
}
