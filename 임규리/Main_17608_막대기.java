import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_17608_막대기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] poles = new int[N];

        for (int i = 0; i < N; i++) {
            poles[i] = Integer.parseInt(br.readLine());
        }

        int max = poles[N - 1];
        int count = 1;  // 맨 오른쪽 막대기는 무조건 카운팅되니 1로 선언

        for (int i = N - 2; i >= 0; i--) {
            if (poles[i] > max) {
                max = poles[i]; // 처음에 이 부분을 빼먹어서 실패했습니다...
                count++;
            }
        }

        System.out.print(count);
        br.close();
    }
}
