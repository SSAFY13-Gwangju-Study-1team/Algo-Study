import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 찬영님, 호빈님 도움을 받았습니다 ^_^...
 */
public class Main_10158_개미 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int w = Integer.parseInt(str[0]);
        int h = Integer.parseInt(str[1]);

        str = br.readLine().split(" ");
        int p = Integer.parseInt(str[0]);
        int q = Integer.parseInt(str[1]);

        int t = Integer.parseInt(br.readLine());

        int pSum = p + t;
        int qSum = q + t;

        // (p + t) / w의 몫이 짝수라면 => 나머지값
        // (p + t) / w의 몫이 홀수라면 => (w - 나머지값)
        if ((pSum / w) % 2 == 0) {
            pSum %= w;
        } else {
            pSum %= w;
            pSum = w - pSum;
        }

        // (q + t) / h의 몫이 짝수라면 => 나머지값
        // (q + t) / h의 몫이 홀수라면 => (h - 나머지값)
        if ((qSum / h) % 2 == 0) {
            qSum %= h;
        } else {
            qSum %= h;
            qSum = h - qSum;
        }

        System.out.println(pSum + " " + qSum);
    }
}