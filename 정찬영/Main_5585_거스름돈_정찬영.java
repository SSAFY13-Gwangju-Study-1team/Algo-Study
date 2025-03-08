/* 메모리 14216kb, 시간 100ms
 * 문제 풀이 아이디어: 높은 동전의 수부터 나누고 몫을 더한다.
 * 다음 동전은 나머지로 한다.
 * 체감 난이도: 1/10
 */

import java.io.*;

public class Main_5585_거스름돈_정찬영 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    /* ----- 입력 ----- */
    static int money;
    static void input() throws IOException {
        money = Integer.parseInt(br.readLine());    // 물건의 가격
    }

    /* ----- 구현 ----- */
    static void solve() {
        // 잔돈
        money = 1000 - money;
        int sum = 0;    // 돌려줘야할 동전 개수
        
        // 500 개수+
        sum += money/500;
        money = money%500;
        
        // 100 개수+
        sum += money/100;
        money = money%100;
        
        // 50 개수+
        sum += money/50;
        money = money%50;
        
        // 10 개수+
        sum += money/10;
        money = money%10;
        
        // 5 개수+
        sum += money/5;
        money = money%5;
        
        // 남은건 1원들이기 때문에 그냥 더함
        sum += money;

        sb.append(sum);
    }

    /* ----- 출력 ----- */
    static void output() throws IOException {
        System.out.println(sb);
        br.close();
    }
}