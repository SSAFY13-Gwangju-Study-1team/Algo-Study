import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;

public class Main_2145_숫자놀이_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        while (true) { 
            int num = parseInt(br.readLine());
            if (num == 0) break;
            while (num >= 10) {
                int sum = 0;
                while (num > 0) {
                    sum += num % 10;
                    num /= 10;
                }
                num = sum;
            }
            sb.append(num).append("\n");
        }
        System.out.println(sb);
    }
}
