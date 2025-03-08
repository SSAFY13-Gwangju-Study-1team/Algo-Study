import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

/**
 * 메모리 : 11,480 KB
 * 시간 : 64 ms
 */
public class Main_5585_거스름돈_임규리 {

    static int price;   // 가격
    static int count;   // 잔돈 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        price = parseInt(br.readLine());

        price = 1000 - price;
        while (price > 0) {
            if (price >= 500) {
                int temp = price / 500;
                count += temp;
                price -= 500 * temp;
            } else if (price >= 100) {
                int temp = price / 100;
                count += temp;
                price -= 100 * temp;
            } else if (price >= 50) {
                int temp = price / 50;
                count += temp;
                price -= 50 * temp;
            } else if (price >= 10) {
                int temp = price / 10;
                count += temp;
                price -= 10 * temp;
            } else if (price >= 5) {
                int temp = price / 5;
                count += temp;
                price -= 5 * temp;
            } else {
                count += price;
                price = 0;
            }
        }

        System.out.println(count);
    }
}
