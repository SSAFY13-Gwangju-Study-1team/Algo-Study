import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1100_하얀칸_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static char[][] board = new char[8][8];
    static int cnt = 0;

    public static void main(String[] args) throws Exception {1
        for (int i = 0; i < 8; i++) {
            board[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 8; j += 2) {
                    if (board[i][j] == 'F') {
                        cnt++;
                    }
                }
            } else {
                for (int j = 1; j < 8; j += 2) {
                    if (board[i][j] == 'F') {
                        cnt++;
                    }
                }
            }
        }

        System.out.println(cnt);
    }
}
