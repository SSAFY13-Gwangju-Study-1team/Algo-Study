import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

public class Main_16926_배열돌리기1_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M, R;
    static int[][] arr;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        R = parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < R; i++) {
            arr = turn();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
    
    public static int[][] turn() {
        int dir = 1;
        int bias = 0;
        int[][] newArr = new int[N][M];
        int minSide = Math.min(N, M);
        
        while (bias < (minSide - 1) - bias) {
            int start = bias, end = bias;

            for (int k = 0; k < 2; k++) {
                for (int i = bias; i < (N - 1) - bias; i++) {
                    start += dir;
                    newArr[start][end] = arr[start - dir][end];
                }

                for (int i = bias; i < (M - 1) - bias; i++) {
                    end += dir;
                    newArr[start][end] = arr[start][end - dir];
                }

                dir *= -1;
            }

            bias++;
        }

        return newArr;
    }
}
