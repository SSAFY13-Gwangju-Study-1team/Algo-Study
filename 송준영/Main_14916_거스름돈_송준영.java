import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;

/**
 * Main_14916_거스름돈
 * 난이도 3/10
 * 수학 그리디
 * 104ms 14mb
 * 
 * 5원, 2원으로 거슬러 줄 수 있는 최소 동전의 개수를 구하는 문제
 * 5원으로 나누어 떨어지면 5원으로 나눈 몫이 최소 동전의 개수가 된다
 * 5원으로 나누어 떨어지지 않으면 2원으로 나누어 떨어지는지 확인하고 2원으로 나누어 떨어지면 2원으로 나눈 몫이 최소 동전의 개수가 된다
 * 2원으로도 나누어 떨어지지 않으면 5원을 하나씩 빼가면서 2원으로 나누어 떨어지는지 확인하고 2원으로 나누어 떨어지면 2원으로 나눈 몫을 더해주면 된다
 * 다 안되면 -1을 출력
 */
public class Main_14916_거스름돈_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;   // 거스름돈

    public static void main(String[] args) throws Exception {
        n = parseInt(br.readLine());

        int cnt = 0;    // 최소 동전의 개수
        
        // 5원으로 나누어 떨어지면 5원으로 나눈 몫이 최소 동전의 개수가 된다
        cnt = n / 5;
        n %= 5; // 5원으로 나눈 나머지

        while (cnt >= 0) {  // 5원으로 나누어 떨어지지 않을 때
            if (n % 2 == 0) {   // 2원으로 나누어 떨어지면 2원으로 나눈 몫이 최소 동전의 개수가 된다
                cnt += n / 2;
                break;
            } else {            // 2원으로 나누어 떨어지지 않으면 5원을 하나씩 빼가면서 2원으로 나누어 떨어지는지 확인
                n += 5;
                cnt--;
            }
        }

        // 다 안되면 -1을 출력
        if (cnt == 0) {
            System.out.println(-1);
        } else {    // 되면 최소 동전의 개수 출력
            System.out.println(cnt);
        }
    }
}
